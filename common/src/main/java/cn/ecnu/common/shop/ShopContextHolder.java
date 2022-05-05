package cn.ecnu.common.shop;

import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.experimental.UtilityClass;

/**
 * @author lqj
 * @version 1.0 2020/9/28
 */
@UtilityClass
public class ShopContextHolder {

    private final ThreadLocal<Integer> THREAD_LOCAL_SHOP = new TransmittableThreadLocal<>();
    private final ThreadLocal<String> THREAD_LOCAL_STORE_LOCALE = new TransmittableThreadLocal<>();

    /**
     * thread
     * TTL 设置店铺ID
     *
     * @param shopId
     */
    public void setShopId(Integer shopId) {
        THREAD_LOCAL_SHOP.set(shopId);
    }

    /**
     * 设置店铺语言
     *
     * @param locale
     */
    public void setShopLocale(String locale) {
        THREAD_LOCAL_STORE_LOCALE.set(locale);
    }

    /**
     * 获取店铺语言
     *
     * @return
     */
    public String getShopLocale() {
        return THREAD_LOCAL_STORE_LOCALE.get();
    }

    /**
     * 获取TTL中的店铺ID
     *
     * @return
     */
    public Integer getShopId() {
        return THREAD_LOCAL_SHOP.get();
    }

    public void clear() {
        THREAD_LOCAL_SHOP.remove();
        THREAD_LOCAL_STORE_LOCALE.remove();
    }
}
