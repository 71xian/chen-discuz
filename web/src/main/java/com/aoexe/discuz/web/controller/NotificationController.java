package com.aoexe.discuz.web.controller;


import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aoexe.discuz.system.modular.notification.model.entity.NotificationTpls;
import com.aoexe.discuz.system.modular.notification.service.INotificationService;
import com.aoexe.discuz.system.modular.notification.service.INotificationTplsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chenyuxian
 * @since 2021-08-30
 */
@RestController
@RequestMapping("/notification")
@Api(value = "通知管理", tags = "通知管理")
public class NotificationController {

	@Resource
	private INotificationService notificationService;
	
	@Resource
	private INotificationTplsService notificationTplsService;
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除通知", notes = "删除通知")
	public void deleteNotification(@PathVariable("id") Long nid) {
		notificationService.removeById(nid);
	}
	
	@GetMapping
	@ApiOperation(value = "通知列表", notes = "通知列表")
	public void getNotifications(HttpServletRequest request) {
		
	}
	
	@GetMapping("/tpl")
	@ApiOperation(value = "消息模板列表", notes = "消息模板列表")
	public List<NotificationTpls> tpls(@Nullable @RequestParam("type") Integer type){
		type = Objects.isNull(type) ? 0 : 1;
		return notificationTplsService.getTplsByType(type);
	}
	
	@PatchMapping("/tpl/{id}")
	@ApiOperation(value = "修改系统消息模板", notes = "修改系统消息模板")
	public NotificationTpls updateTpl(@PathVariable("id") Long id, @RequestBody NotificationTpls tpl) throws Exception {
		return notificationTplsService.updateTemplate(id, tpl);
	}
	
	@GetMapping("/tpl/{id}")
	@ApiOperation(value = "获取消息模板", notes = "获取消息模板")
	public NotificationTpls getTpl(@PathVariable("id") Long id) {
		return notificationTplsService.getById(id);
	}
}
