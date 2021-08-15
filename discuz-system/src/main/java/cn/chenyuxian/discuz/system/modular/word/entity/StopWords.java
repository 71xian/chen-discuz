package cn.chenyuxian.discuz.system.modular.word.entity;

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
public class StopWords extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 敏感词 id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 创建用户 id
     */
    private Long userId;

    /**
     * 用户内容处理方式
     */
    private String ugc;

    /**
     * 用户名处理方式
     */
    private String username;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户签名处理方式
     */
    private String signature;

    /**
     * 短消息处理方式
     */
    private String dialog;

    /**
     * 敏感词或查找方式
     */
    private String find;

    /**
     * 替换词或替换规则
     */
    private String replacement;


}
