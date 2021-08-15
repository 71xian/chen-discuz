package cn.chenyuxian.discuz.system.modular.thread.entity;

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
public class ThreadTom extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 帖子id
     */
    private Long threadId;

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


}
