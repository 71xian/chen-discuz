package cn.chenyuxian.discuz.system.modular.topic.entity;

import cn.chenyuxian.discuz.core.base.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
public class Topics extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 话题ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * user_id
     */
    private Long userId;

    /**
     * 话题名称
     */
    private String content;

    /**
     * 主题数
     */
    private Integer threadCount;

    /**
     * 阅读数
     */
    private Integer viewCount;

    /**
     * 是否推荐
     */
    private Integer recommended;

    /**
     * 推荐时间
     */
    private LocalDateTime recommendedAt;


}
