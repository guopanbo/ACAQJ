package club.janna.acaqj.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * @author guopanbo
 * @Title: JsoupTest
 * @Description: TODO
 * @date 2018/8/223:59
 */
public class JsoupTest {
    public static void main(String[] args) {
        Document doc = Jsoup.parse("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2017/12/1201.html");
    }
}
