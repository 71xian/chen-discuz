package com.aoexe.discuz.system.modular.post.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author chenyuxian
 * @since 2021-09-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PostRedPacket implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 红包ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 关联的post主键ID
     */
    private Long postId;

    /**
     * 关联的reply主键ID
     */
    private Long replyId;

    /**
     * 发放规则，0定额，1随机
     */
    private Integer rule;

    /**
     * 领取红包条件，0回复，1集赞
     */
    private Integer condition;

    /**
     * 若红包领取条件为集赞，必填集赞数
     */
    private Integer likenum;

    /**
     * 红包总金额
     */
    private Long money;

    /**
     * 红包个数
     */
    private Integer number;

    /**
     * 剩余红包总额
     */
    private Long remainMoney;

    /**
     * 剩余红包个数
     */
    private Integer remainNumber;

    /**
     * 0:红包已过期,1:红包未过期
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;


}
