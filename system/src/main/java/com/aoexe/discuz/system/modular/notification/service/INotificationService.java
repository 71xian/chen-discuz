package com.aoexe.discuz.system.modular.notification.service;

import javax.servlet.http.HttpServletRequest;

import com.aoexe.discuz.system.modular.notification.model.entity.Notification;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenyuxian
 * @since 2021-08-30
 */
public interface INotificationService extends IService<Notification> {

	IPage<Notification> list(HttpServletRequest request) throws Exception;
}
