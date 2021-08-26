package cn.chenyuxian.discuz.system.modular.group.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author chenyuxian
 * @since 2021-08-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "DzqGroup对象", description = "")
public class DzqGroup implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "用户组 id")
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	@ApiModelProperty(value = "用户组名称")
	private String name;

	@ApiModelProperty(value = "类型")
	private String type;

	@ApiModelProperty(value = "颜色")
	private String color;

	@ApiModelProperty(value = "icon")
	private String icon;

	@ApiModelProperty(value = "是否默认")
	private Integer isDefault;

	@ApiModelProperty(value = "是否显示在用户名后")
	private Integer isDisplay;

	@ApiModelProperty(value = "是否收费：0不收费，1收费")
	private Integer isPaid;

	@ApiModelProperty(value = "收费金额")
	private BigDecimal fee;

	@ApiModelProperty(value = "付费获得天数")
	private Long days;

	@ApiModelProperty(value = "分成比例")
	private Double scale;

	@ApiModelProperty(value = "是否可以 推广下线")
	private Integer isSubordinate;

	@ApiModelProperty(value = "是否可以 收入提成")
	private Integer isCommission;

	@TableField(exist = false)
	private List<String> permissions;

}
