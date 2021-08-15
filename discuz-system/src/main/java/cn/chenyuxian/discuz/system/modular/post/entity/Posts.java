package cn.chenyuxian.discuz.system.modular.post.entity;

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
public class Posts extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 回复 id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 发表用户 id
     */
    private Long userId;

    /**
     * 关联主题 id
     */
    private Long threadId;

    /**
     * 回复 id
     */
    private Long replyPostId;

    /**
     * 回复用户 id
     */
    private Long replyUserId;

    /**
     * 评论回复 id
     */
    private Long commentPostId;

    /**
     * 评论回复用户 id
     */
    private Long commentUserId;

    /**
     * 内容
     */
    private String content;

    /**
     * ip 地址
     */
    private String ip;

    /**
     * 端口
     */
    private Integer port;

    /**
     * 关联回复数
     */
    private Integer replyCount;

    /**
     * 喜欢数
     */
    private Integer likeCount;

    /**
     * 删除时间
     */
    private LocalDateTime deletedAt;

    /**
     * 删除用户 id
     */
    private Long deletedUserId;

    /**
     * 是否首个回复
     */
    private Integer isFirst;

    /**
     * 是否是回复回帖的内容
     */
    private Integer isComment;

    /**
     * 是否合法
     */
    private Integer isApproved;


}
