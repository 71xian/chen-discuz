package com.aoexe.discuz.system.modular.group.model.param;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.hibernate.validator.constraints.Length;

import com.aoexe.discuz.system.modular.group.model.entity.Groups;

import lombok.Data;

@Data
public class GroupParam extends Groups{

	@Null
	private Long id;
	
	@NotNull
	@Length(min = 2, max = 10, message = "用户组名不得超过十个字符，不得小于两个字符")
	private String name;

}
