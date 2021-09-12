package com.aoexe.discuz.system.modular.category.service;

import java.util.List;

import com.aoexe.discuz.system.modular.category.model.entity.Category;
import com.aoexe.discuz.system.modular.category.model.param.CategoryParam;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenyuxian
 * @since 2021-08-30
 */
public interface ICategoryService extends IService<Category> {
	
	void createCategory(CategoryParam param);
	
	void deleteCategory(Long cid);
	
	void updateCategory(Long cid, CategoryParam param);
	
	Category selectCategory(Long cid);
	
	List<Long> getMenuIds();
	
}
