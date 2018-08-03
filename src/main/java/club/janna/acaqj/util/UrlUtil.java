package club.janna.acaqj.util;

import org.apache.commons.lang.StringUtils;

/**
 * @author guopanbo
 * @Title: UrlUtil
 * @Description: TODO
 * @date 2018/8/310:25
 */
public class UrlUtil {

    /**
     * 通过BaseUri获取BaseUrl
     * @param uri
     * @return
     */
    public static String getBaseUrlByBaseUri(String uri) {
        if (StringUtils.isBlank(uri) || uri.indexOf("/") == -1)
            return null;
        return uri.substring(0, uri.lastIndexOf("/") + 1);
    }

}
