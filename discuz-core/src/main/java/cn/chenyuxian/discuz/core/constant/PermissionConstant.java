package cn.chenyuxian.discuz.core.constant;

import lombok.Getter;

/**
 * 分类权限枚举
 *
 * @author chenyuxian
 * @date 2021-08-12
 */
@Getter
public class PermissionConstant {

	public static final String[] Permissions = {
		"viewThreads",   // 查看帖子列表
		"createThread",  // 发布帖子
		"thread.reply",// 回复帖子
		"thread.edit",// 编辑帖子
		"thread.hide", // 删除帖子
		"thread.essence",// 加精帖子
		"thread.viewPosts",// 查看详情
		"thread.editPosts",// 编辑回复
		"thread.hidePosts",// 删除回复
		"thread.canBeReward",// 是否允许被打赏
		"thread.editOwnThreadOrPost",// 编辑自己主题或回复的权限
		"thread.hideOwnThreadOrPost",// 删除自己主题或回复的权限
		"thread.freeViewPosts.1",// 免费查看付费帖子
		"thread.freeViewPosts.2",// 免费查看付费视频
		"thread.freeViewPosts.3",// 免费查看付费图片
		"thread.freeViewPosts.4",// 免费查看付费语音
		"thread.freeViewPosts.5",// 免费查看付费问答
		
		"createThread", // 发布帖子
		"insertImage",// 插入图片
		"insertVideo",// 插入视频
		"insertAudio",// 插入语音
		"insertDoc",// 插入附件
		"insertGoods", // 插入商品
		"insertPay", // 插入付费
		"insertReward", // 插入悬赏
		"insertRedPacket",// 插入红包
		"insertPosition",// 插入位置
		
	};
	
	public static final String[] DEFAULT_PERMISSION = {
			"thread.favorite",//收藏
			"thread.likePosts",//点赞
			"userFollow.create",//关注
			"user.view",//查看个人信息，目前仅用于前台显示权限
			"order.create",//创建订单
			"trade.pay.order",//支付订单
			"cash.create",//提现
	};
	
	public static final String[] THREAD_PERMISSION = {
			"switch.createThread",           //开启/允许发布帖子
	        "thread.insertImage",          //开启/允许插入图片
	        "thread.insertVideo" ,             //开启/允许发布视频
	        "thread.insertAudio",            //开启/允许发布语音
	        "thread.insertAttachment" ,             //开启/允许发布附件
	        "thread.insertGoods" ,           //开启/允许发布商品
	        "thread.insertPay" ,            //开启/允许发布付费
	        "thread.insertReward" ,          //开启/允许发布悬赏
	        "thread.insertRedPacket",         //开启/允许发布红包
	        "thread.insertPosition",         //开启/允许发布位置
	        "thread.allowAnonymous",         //开启/允许发布匿名贴
	};
}
