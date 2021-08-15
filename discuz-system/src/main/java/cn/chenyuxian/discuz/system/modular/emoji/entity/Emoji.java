package cn.chenyuxian.discuz.system.modular.emoji.entity;

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
public class Emoji extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 表情 id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 表情分类
     */
    private String category;

    /**
     * 表情地址
     */
    private String url;

    /**
     * 表情符号
     */
    private String code;

    /**
     * 显示顺序
     */
    private Integer order;


}
