package com.aoexe.discuz.system.modular.group.model.result;

import java.util.List;

import com.aoexe.discuz.system.modular.group.model.entity.GroupPaidUser;
import com.aoexe.discuz.system.modular.group.model.entity.GroupPermission;

import lombok.Data;

@Data
public class GroupPaidResult extends GroupPaidUser {

	private List<GroupPermission> permissions;
}
