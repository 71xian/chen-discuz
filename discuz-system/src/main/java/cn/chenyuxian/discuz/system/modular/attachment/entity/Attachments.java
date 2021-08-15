package cn.chenyuxian.discuz.system.modular.attachment.entity;

import cn.chenyuxian.discuz.core.base.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 附件表
 * </p>
 *
 * @author chenyuxian
 * @since 2021-08-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Attachments extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 附件 id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * uuid
     */
    private String uuid;

    /**
     * 用户 id
     */
    private Long userId;

    /**
     * 类型数据ID(post_id,dialog_message_id…)
     */
    private Long typeId;

    /**
     * 附件排序
     */
    private Integer order;

    /**
     * 附件类型(0帖子附件，1帖子图片，2帖子视频，3帖子音频，4消息图片)
     */
    private Integer type;

    /**
     * 是否远程附件
     */
    private Integer isRemote;

    /**
     * 是否合法
     */
    private Integer isApproved;

    /**
     * 文件系统生成的名称
     */
    private String attachment;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 文件原名称
     */
    private String fileName;

    /**
     * 文件大小
     */
    private Integer fileSize;

    /**
     * 宽度
     */
    private Long fileWidth;

    /**
     * 高度
     */
    private Long fileHeight;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * ip 地址
     */
    private String ip;


}
