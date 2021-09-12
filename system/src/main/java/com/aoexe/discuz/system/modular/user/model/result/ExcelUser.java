package com.aoexe.discuz.system.modular.user.model.result;

import java.time.LocalDateTime;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentFontStyle;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadFontStyle;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.alibaba.excel.annotation.write.style.HeadStyle;

import lombok.Data;

@Data
@HeadStyle(fillPatternType = FillPatternType.NO_FILL, horizontalAlignment = HorizontalAlignment.GENERAL, wrapped = false, borderBottom = BorderStyle.NONE, borderLeft = BorderStyle.NONE, borderRight = BorderStyle.NONE, borderTop = BorderStyle.NONE)
@HeadFontStyle(fontName = "Calibri", fontHeightInPoints = 11, bold = false)
@HeadRowHeight(15)
@ContentFontStyle(fontName = "Calibri", fontHeightInPoints = 11)
@ContentRowHeight(15)
@ColumnWidth(10)
public class ExcelUser {

	@ExcelProperty(value = "用户ID")
	private Long userId;

	@ExcelProperty(value = "用户名")
	private String username;

	@ExcelProperty(value = "手机号")
	private String mobile;

	@ExcelProperty(value = "账号状态")
	private String status;

	@ExcelProperty(value = "性别")
	private String sex;

	@ExcelProperty(value = "用户组名")
	private String groupName;

	@ExcelProperty(value = "微信openid")
	private Long weixinOpenId;

	@ExcelProperty(value = "微信unionid")
	private Long weixinunionId;

	@ExcelProperty(value = "微信昵称")
	private String weixinNickname;

	@ExcelProperty(value = "注册时间")
	private LocalDateTime registerAt;

	@ExcelProperty(value = "注册IP")
	private String registerIp;

	@ExcelProperty(value = "注册端口")
	private Integer registerPort;

	@ExcelProperty(value = "最后登录时间")
	private LocalDateTime loginAt;

	@ExcelProperty(value = "最后登录IP")
	private String lastLoginIp;

	@ExcelProperty(value = "最后登录端口")
	private Integer lastLoginPort;
}
