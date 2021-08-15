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
public class PostGoods extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 商品 id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户 id
     */
    private Long userId;

    /**
     * 帖子 id
     */
    private Long postId;

    /**
     * 平台商品 id
     */
    private String platformId;

    /**
     * 商品标题
     */
    private String title;

    /**
     * 价格
     */
    private String price;

    /**
     * 商品封面图
     */
    private String imagePath;

    /**
     * 商品来源:0淘宝 1天猫 2京东 等
     */
    private Integer type;

    /**
     * 商品状态:0正常 1失效/下架
     */
    private Integer status;

    /**
     * 预解析内容
     */
    private String readyContent;

    /**
     * 解析详情页地址
     */
    private String detailContent;

    /**
     * 删除时间
     */
    private LocalDateTime deletedAt;


}
