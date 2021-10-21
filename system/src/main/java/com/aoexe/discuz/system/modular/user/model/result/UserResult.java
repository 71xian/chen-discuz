package com.aoexe.discuz.system.modular.user.model.result;

import java.util.List;

import com.aoexe.discuz.system.modular.group.model.entity.Groups;
import com.aoexe.discuz.system.modular.user.model.entity.User;

import lombok.Data;

@Data
public class UserResult extends User{

	private List<Groups> groups;
}
