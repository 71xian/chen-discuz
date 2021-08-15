package cn.chenyuxian.discuz.system.modular.report.entity;

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
public class Reports extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 举报 id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户 id
     */
    private Long userId;

    /**
     * 主题 id
     */
    private Long threadId;

    /**
     * 回复 id
     */
    private Long postId;

    /**
     * 举报类型:0个人主页 1主题 2评论/回复
     */
    private Integer type;

    /**
     * 举报理由
     */
    private String reason;

    /**
     * 举报状态:0未处理 1已处理
     */
    private Integer status;


}
