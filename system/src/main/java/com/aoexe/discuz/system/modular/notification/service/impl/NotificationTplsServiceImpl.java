package com.aoexe.discuz.system.modular.notification.service.impl;

import com.aoexe.discuz.core.util.BeanUtil;
import com.aoexe.discuz.system.modular.notification.mapper.NotificationTplsMapper;
import com.aoexe.discuz.system.modular.notification.model.entity.NotificationTpls;
import com.aoexe.discuz.system.modular.notification.service.INotificationTplsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenyuxian
 * @since 2021-08-30
 */
@Service
public class NotificationTplsServiceImpl extends ServiceImpl<NotificationTplsMapper, NotificationTpls> implements INotificationTplsService {

	@Override
	public List<NotificationTpls> getTplsByType(Integer type) {
		QueryWrapper<NotificationTpls> wrapper = new QueryWrapper<>();
		wrapper.eq("type", type);
		return this.list(wrapper);
	}

	@Override
	public NotificationTpls updateTemplate(Long id, NotificationTpls notificationTpls) throws Exception {
		NotificationTpls tpl = getById(id);
		BeanUtil.copyProperties(notificationTpls, tpl);
		this.updateById(tpl);
		return tpl;
	}

}
