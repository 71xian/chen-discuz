package cn.chenyuxian.discuz.system.modular.group.service;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;

import cn.chenyuxian.discuz.system.modular.group.entity.Group;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenyuxian
 * @since 2021-08-23
 */
public interface IGroupService extends IService<Group> {
	
	Map<String, Object> getDefaultGroup();
}
