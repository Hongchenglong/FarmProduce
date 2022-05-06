package cn.ecnu.gateway.filter;

/**
 * @Description: 此配置用于后期维护需要放行的URL
 */
public class URLFilter {
    // 后期需要方向的请求url
    private static String uri = "/api/swagger-ui,/api/swagger-resources,/api/v2/api-docs,/api/user/login,/api/user/register," +
            "/api/css,/api/js,/api/webfonts";

    // 判断用户的请求是否包含上面允许放行的uri
    public static boolean hasAuthorization(String url) {
        String[] uris = uri.split(",");
        for (String uri : uris) {
            if (url.startsWith(uri)) { //尽量不用contains,防止后面路径有伪造的path,包含就放行了
                return true;            // 放行
            }
        }
        return false;                   // 拦截
    }
}
