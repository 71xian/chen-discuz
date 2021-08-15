package cn.chenyuxian.discuz.core.specialchar;

/**
 * 过滤内容 防止xss注入
 *
 * @author chenyuxian
 * @date 2021-08-10
 */
public interface ISpecialChar {

	String purify(String specialChar);
}
