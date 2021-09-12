package com.aoexe.discuz.system.modular.category.model.param;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CategoryParam{

	@NotBlank(message = "分类名不能为空")
	private String name;
	
	@NotBlank(message = "分类描述不能为空")
	private String description;
	
	private String icon;
	
	@NotNull(message = "排序号不能为空")
	private Integer sort;
	
	private Integer property;
	
	private Long parentId;
}
