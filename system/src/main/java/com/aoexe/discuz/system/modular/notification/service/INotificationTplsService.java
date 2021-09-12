package com.aoexe.discuz.system.modular.notification.service;

import java.util.List;

import com.aoexe.discuz.system.modular.notification.model.entity.NotificationTpls;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenyuxian
 * @since 2021-08-30
 */
public interface INotificationTplsService extends IService<NotificationTpls> {

	List<NotificationTpls> getTplsByType(Integer type);
	
	NotificationTpls updateTemplate(Long id, NotificationTpls notificationTpls) throws Exception;
}
