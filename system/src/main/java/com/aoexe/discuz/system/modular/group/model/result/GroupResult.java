package com.aoexe.discuz.system.modular.group.model.result;

import java.io.Serializable;
import java.util.List;

import com.aoexe.discuz.system.modular.group.model.entity.DzqGroup;
import com.aoexe.discuz.system.modular.group.model.entity.GroupPermission;

import lombok.Data;

@Data
public class GroupResult extends DzqGroup implements Serializable{

	private List<GroupPermission> permissions;

}
