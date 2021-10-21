package com.aoexe.discuz.system.modular.group.model.result;

import java.io.Serializable;
import java.util.List;

import com.aoexe.discuz.system.modular.group.model.entity.GroupPermission;
import com.aoexe.discuz.system.modular.group.model.entity.Groups;

import lombok.Data;

@Data
public class GroupResult extends Groups implements Serializable{

	private List<GroupPermission> permissions;

}
