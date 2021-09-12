package com.aoexe.discuz.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aoexe.discuz.system.modular.category.model.entity.Category;
import com.aoexe.discuz.system.modular.category.model.param.CategoryParam;
import com.aoexe.discuz.system.modular.category.service.ICategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author chenyuxian
 * @since 2021-08-30
 */
@RestController
@RequestMapping("/category")
@Api(value = "分类管理", tags = "分类管理")
public class CategoryController {

	@Resource
	private ICategoryService categoryService;

	@PostMapping
	@ApiOperation(value = "创建分类", notes = "创建分类")
	public void create(@RequestBody CategoryParam param) throws Exception {
		categoryService.createCategory(param);
	}

	@PostMapping("/batch")
	@ApiOperation(value = "创建多条分类", notes = "创建多条分类")
	public void createBatch(@RequestBody List<CategoryParam> params) {
		params.forEach(c -> categoryService.createCategory(c));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除分类", notes = "删除分类")
	public void delete(@PathVariable("id") Long id) {
		categoryService.deleteCategory(id);
	}

	@DeleteMapping("/batch/{ids}")
	@ApiOperation(value = "批量删除分类", notes = "批量删除分类")
	public void deleteBatch(@PathVariable("ids") Long[] ids) {
		for (Long id : ids) {
			categoryService.deleteCategory(id);
		}
	}

	@PatchMapping("/{id}")
	@ApiOperation(value = "修改分类", notes = "修改分类")
	public void update(@PathVariable("id") Long id, @RequestBody CategoryParam param) {
		categoryService.updateCategory(id, param);
	}

	@PatchMapping("/batch")
	@ApiOperation(value = "批量修改分类", notes = "批量修改分类")
	public void updateBatch(@RequestBody List<Long> ids,  @RequestBody List<CategoryParam> params) {
		for(int i = 0; i < ids.size(); i++) {
			categoryService.updateCategory(ids.get(i), params.get(i));
		}
	}

	@GetMapping
	@ApiOperation(value = "查询分类", notes = "查询分类")
	public List<Category> list() {
		List<Long> menuIds = categoryService.getMenuIds();
		List<Category> categories = new ArrayList<>(menuIds.size());
		menuIds.forEach(id -> categories.add(categoryService.selectCategory(id)));
		return categories;
	}
}
