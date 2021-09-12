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
public class PostTom implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 帖子id
     */
    private Long postId;

    /**
     * 资源类型：image/media/question/vote....
     */
    private String tomType;

    /**
     * 元数据索引 $0,$1,$2 资源占位符
     */
    private String key;

    /**
     * 资源结构化数据
     */
    private String value;

    /**
     * -1：删除 0：正常
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
