package cn.chenyuxian.discuz.system.modular.attachment.entity;

import cn.chenyuxian.discuz.core.base.entity.BaseEntity;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 附件分享表
 * </p>
 *
 * @author chenyuxian
 * @since 2021-08-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class AttachmentsShare extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    private String sign;

    /**
     * 附件id
     */
    private Long attachmentsId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 下载次数
     */
    private Integer downloadCount;

    /**
     * 验证码
     */
    private String code;

    /**
     * 过期时间
     */
    private LocalDateTime expiredAt;


}
