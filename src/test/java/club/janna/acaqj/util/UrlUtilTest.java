package club.janna.acaqj.util;

import lombok.extern.slf4j.Slf4j;

/**
 * @author guopanbo
 * @Title: UrlUtilTest
 * @Description: TODO
 * @date 2018/8/310:31
 */
@Slf4j
public class UrlUtilTest {
    public static void main(String[] args) {
        log.info(UrlUtil.getBaseUrlByBaseUri("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2017/12/1201.html"));
    }
}
