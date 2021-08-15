package cn.chenyuxian.discuz.system.modular.log.entity;

import cn.chenyuxian.discuz.core.base.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author chenyuxian
 * @since 2021-08-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class OperationLogs extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 日志 id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户 id
     */
    private Long userId;

    /**
     * url路径
     */
    private String path;

    /**
     * 请求方式
     */
    private String method;

    /**
     * ip 地址
     */
    private String ip;

    /**
     * body请求数据
     */
    private String input;

    /**
     * 日志类型:0后台操作
     */
    private Integer type;


}
