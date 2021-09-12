package com.aoexe.discuz.system.modular.category.mapper;

import java.util.List;

import com.aoexe.discuz.system.modular.category.model.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chenyuxian
 * @since 2021-08-30
 */
public interface CategoryMapper extends BaseMapper<Category> {

	void removeCategory(Long cid);
	
	List<Long> selectIds(Long cid);
}
