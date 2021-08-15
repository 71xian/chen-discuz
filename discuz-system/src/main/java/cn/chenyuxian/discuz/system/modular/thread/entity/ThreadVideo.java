package cn.chenyuxian.discuz.system.modular.thread.entity;

import cn.chenyuxian.discuz.core.base.entity.BaseEntity;
import java.math.BigDecimal;
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
public class ThreadVideo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 音视频 id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 主题 id
     */
    private Long threadId;

    /**
     * 帖子 id
     */
    private Long postId;

    /**
     * 用户 id
     */
    private Long userId;

    /**
     * 类型：0 视频 1 音频
     */
    private Integer type;

    /**
     * 音视频状态：0 转码中 1 转码完成 2 转码失败
     */
    private Integer status;

    /**
     * 转码失败原因
     */
    private String reason;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 媒体文件唯一标识
     */
    private String fileId;

    /**
     * 视频高
     */
    private Integer height;

    /**
     * 视频宽
     */
    private Integer width;

    /**
     * 视频时长
     */
    private BigDecimal duration;

    /**
     * 媒体播放地址
     */
    private String mediaUrl;

    /**
     * 媒体封面地址
     */
    private String coverUrl;


}
