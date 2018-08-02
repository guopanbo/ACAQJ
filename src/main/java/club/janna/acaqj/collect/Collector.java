package club.janna.acaqj.collect;


import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Iterator;

/**
 * @author guopanbo
 * @Title: Collector
 * @Description: TODO
 * @date 2018/8/222:28
 */
@Slf4j
public class Collector implements Runnable {


    private String url;
    private int level;
    private String pcode;

    public Collector(String url, int level, String pcode) {
        this.url = url;
        this.level = level;
        this.pcode = pcode;
    }

    /**
     * 加载一个网页
     */
    private Document load() {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(doc == null) {
            log.error("load page error, url[{}]", url);
        }
        return doc;
    }

    /**
     * 解析网页
     */
    private void parse() {
        Document doc = load();
        if(doc == null)
            throw new RuntimeException("Document is null");
        Elements elements = null;
        Iterator<Element> iterable = null;
        switch (level) {
            case 1:
                elements = doc.select("body > table:nth-child(3) > tbody > tr:nth-child(1) > td > table > tbody > tr:nth-child(2) > td > table > tbody > tr > td > table > tbody > tr.provincetr > td > a");
                iterable = elements.iterator();
                while (iterable.hasNext()) {
                    Element element = iterable.next();
                    Executor.getInstance().execute(new Collector(element.baseUri() + element.attr("href"), level + 1, element.text()));
                }
                break;
            case 2:
                elements = doc.select("body > table:nth-child(3) > tbody > tr:nth-child(1) > td > table > tbody > tr:nth-child(2) > td > table > tbody > tr > td > table > tbody > tr.citytr");
                iterable = elements.iterator();
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
        }
    }

    @Override
    public void run() {
        parse();
    }
}
