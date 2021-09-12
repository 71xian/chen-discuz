package com.aoexe.discuz.system.modular.category.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.aoexe.discuz.core.base.exception.BaseException;
import com.aoexe.discuz.core.constant.ResponseEnum;
import com.aoexe.discuz.core.util.BeanUtil;
import com.aoexe.discuz.core.util.IpUtil;
import com.aoexe.discuz.core.util.RequestUtil;
import com.aoexe.discuz.system.core.cache.CategoryCache;
import com.aoexe.discuz.system.modular.category.mapper.CategoryMapper;
import com.aoexe.discuz.system.modular.category.model.entity.Category;
import com.aoexe.discuz.system.modular.category.model.param.CategoryParam;
import com.aoexe.discuz.system.modular.category.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author chenyuxian
 * @since 2021-08-30
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

	@Resource
	private CategoryCache cache;

	@Override
	public void createCategory(CategoryParam param) {
		// 分类名重复
		if (lambdaQuery().eq(Category::getName, param.getName()).one() != null) {
			throw new BaseException(ResponseEnum.CATEGORY_HAS_EXIST);
		}
		// 如果设置该分类为子分类，则父分类id必须存在
		if (param.getParentId() != null && lambdaQuery().eq(Category::getId, param.getParentId()).one() == null) {
			throw new BaseException(ResponseEnum.CATEGORY_NOT_FOUND);
		}
		Category category = new Category();
		// 将参数类中的属性复制到实体类中
		BeanUtil.copyProperties(param, category);
		// 分类属性只有0和1之分,如果设置不为1，则默认为0
		if (category.getProperty() != null && category.getProperty() != 1) {
			category.setProperty(0);
		}
		category.setIp(IpUtil.getIp(RequestUtil.getRequest()));
		LocalDateTime now = LocalDateTime.now();
		category.setCreatedAt(now);
		category.setUpdatedAt(now);
		this.save(category);
		// 将分类加入到缓存中
		cache.set(category.getId(), category);
	}

	@Override
	public void deleteCategory(Long cid) {
		Category category = cache.get(cid);
		if (category == null) {
			return;
		}
		// 将子分类也一同删除
		baseMapper.removeCategory(cid);
		// 删除缓存中的分类
		cache.remove(cid);
	}

	@Override
	public void updateCategory(Long cid, CategoryParam param) {
		emptyValueFill(param);
		// 更新之前先确认一下该分类是否存在
		Category category = cache.get(cid);
		if (category == null) {
			throw new BaseException(ResponseEnum.CATEGORY_NOT_FOUND);
		}
		// 检查父分类是否存在
		if (param.getParentId() != 0L && cache.get(param.getParentId()) == null) {
			throw new BaseException(ResponseEnum.CATEGORY_NOT_FOUND);
		}
		// 分类名已存在
		if (lambdaQuery().ne(Category::getId, cid).eq(Category::getName, param.getName()).one() != null) {
			throw new BaseException(ResponseEnum.CATEGORY_HAS_EXIST);
		}
		BeanUtil.copyProperties(param, category);
		// 先更新数据库，再删除缓存，为什么删除缓存，懒加载思想
		this.updateById(category);
		cache.remove(cid);
	}

	@Override
	public Category selectCategory(Long cid) {
		Category category = cache.get(cid);
		if (category == null) {
			category = this.getById(cid);
			// 只有二级分类，父分类需要设置子分类
			if (category.getParentId() == 0L) {
				category.setChildren(lambdaQuery().eq(Category::getParentId, cid).list());
			}
			cache.set(cid, category);
		}
		return category;
	}

	@Override
	public List<Long> getMenuIds() {
		return baseMapper.selectIds(0L);
	}

	// 空值填充
	private void emptyValueFill(CategoryParam param) {
		if (param.getIcon() == null) {
			param.setIcon("");
		}

		if (param.getParentId() == null) {
			param.setParentId(0L);
		}
	}

}
