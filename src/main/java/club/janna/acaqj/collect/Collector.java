package club.janna.acaqj.collect;


import club.janna.acaqj.mq.Sender;
import club.janna.acaqj.pojo.Area;
import club.janna.acaqj.pojo.ErrorLog;
import club.janna.acaqj.util.UrlUtil;
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
            ErrorLog errorLog = new ErrorLog();
            errorLog.setMsg("load page error");
            errorLog.setNote(url);
            //放入rabbit mq
            Sender.getInstance().sendMessage(errorLog);
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
        Elements elements;
        Iterator<Element> iterable;
        switch (level) {
            case 1:
                elements = doc.select("body > table:nth-child(3) > tbody > tr:nth-child(1) > td > table > tbody > tr:nth-child(2) > td > table > tbody > tr > td > table > tbody > tr.provincetr > td > a");
                iterable = elements.iterator();
                while (iterable.hasNext()) {
//                if (iterable.hasNext()) {
                    Element element = iterable.next();
//                    Element element = elements.get(0);
                    Area area = new Area();
                    area.setCode("0");
                    area.setName(element.text());
                    area.setOrigin(url);
                    area.setLevel(level);
                    log.debug("[{}] - [{}]", area.getCode(), area.getName());
                    //放入 rabbit mq
                    Sender.getInstance().sendMessage(area);
                    Executor.getInstance().execute(new Collector(element.baseUri() + element.attr("href"), level + 1, element.text()));
                }
                break;
            case 2:
                elements = doc.select("body > table:nth-child(3) > tbody > tr:nth-child(1) > td > table > tbody > tr:nth-child(2) > td > table > tbody > tr > td > table > tbody > tr.citytr");
                iterable = elements.iterator();
//                while (iterable.hasNext()) {
                if (iterable.hasNext()) {
//                    Element element = iterable.next();
                    Element element = elements.get(0);
                    Elements children = element.select("td > a");
                    if(children.size() == 2) {
                        Area area = new Area();
                        area.setCode(children.get(0).text());
                        area.setOrigin(url);
                        area.setName(children.get(1).text());
                        area.setLevel(level);
                        area.setPcode(pcode);
                        log.debug("[{}] - [{}]", area.getCode(), area.getName());
                        //放入 rabbit mq
                        Sender.getInstance().sendMessage(area);
                        Executor.getInstance().execute(new Collector(UrlUtil.getBaseUrlByBaseUri(doc.baseUri()) + children.get(0).attr("href"), level + 1, area.getCode()));
                    } else {
                        children = element.select("td");
                        if(children.size() == 2) {
                            Area area = new Area();
                            area.setCode(children.get(0).text());
                            area.setOrigin(url);
                            area.setName(children.get(1).text());
                            area.setLevel(level);
                            area.setPcode(pcode);
                            log.debug("[{}] - [{}]", area.getCode(), area.getName());
                            //放入 rabbit mq
                            Sender.getInstance().sendMessage(area);
                        } else {
                            log.error("parse error, children is not 2 or 3, url[{}]", url);
                            ErrorLog errorLog = new ErrorLog();
                            errorLog.setMsg("parse error, children is not 2 or 3");
                            errorLog.setNote(url);
                            //放入rabbit mq
                            Sender.getInstance().sendMessage(errorLog);
                        }
                    }
                }
                break;
            case 3:
                elements = doc.select("body > table:nth-child(3) > tbody > tr:nth-child(1) > td > table > tbody > tr:nth-child(2) > td > table > tbody > tr > td > table > tbody > tr.countytr");
                iterable = elements.iterator();
//                while (iterable.hasNext()) {
                if (iterable.hasNext()) {
//                    Element element = iterable.next();
                    Element element = elements.get(0);
                    Elements children = element.select("td > a");
                    if(children.size() == 2) {
                        Area area = new Area();
                        area.setCode(children.get(0).text());
                        area.setOrigin(url);
                        area.setName(children.get(1).text());
                        area.setLevel(level);
                        area.setPcode(pcode);
                        log.debug("[{}] - [{}]", area.getCode(), area.getName());
                        //放入 rabbit mq
                        Sender.getInstance().sendMessage(area);
                        Executor.getInstance().execute(new Collector(UrlUtil.getBaseUrlByBaseUri(doc.baseUri()) + children.get(0).attr("href"), level + 1, area.getCode()));
                    } else {
                        children = element.select("td");
                        if(children.size() == 2) {
                            Area area = new Area();
                            area.setCode(children.get(0).text());
                            area.setOrigin(url);
                            area.setName(children.get(1).text());
                            area.setLevel(level);
                            area.setPcode(pcode);
                            log.debug("[{}] - [{}]", area.getCode(), area.getName());
                            //放入 rabbit mq
                            Sender.getInstance().sendMessage(area);
                        } else {
                            log.error("parse error, children is not 2 or 3, url[{}]", url);
                            ErrorLog errorLog = new ErrorLog();
                            errorLog.setMsg("parse error, children is not 2 or 3");
                            errorLog.setNote(url);
                            //放入rabbit mq
                            Sender.getInstance().sendMessage(errorLog);
                        }
                    }
                }
                break;
            case 4:
                elements = doc.select("body > table:nth-child(3) > tbody > tr:nth-child(1) > td > table > tbody > tr:nth-child(2) > td > table > tbody > tr > td > table > tbody > tr.towntr");
                iterable = elements.iterator();
//                while (iterable.hasNext()) {
                if (iterable.hasNext()) {
//                    Element element = iterable.next();
                    Element element = elements.get(0);
                    Elements children = element.select("td > a");
                    if(children.size() == 2) {
                        Area area = new Area();
                        area.setCode(children.get(0).text());
                        area.setOrigin(url);
                        area.setName(children.get(1).text());
                        area.setLevel(level);
                        area.setPcode(pcode);
                        log.debug("[{}] - [{}]", area.getCode(), area.getName());
                        //放入 rabbit mq
                        Sender.getInstance().sendMessage(area);
                        Executor.getInstance().execute(new Collector(UrlUtil.getBaseUrlByBaseUri(doc.baseUri()) + children.get(0).attr("href"), level + 1, area.getCode()));
                    } else {
                        children = element.select("td");
                        if(children.size() == 2) {
                            Area area = new Area();
                            area.setCode(children.get(0).text());
                            area.setOrigin(url);
                            area.setName(children.get(1).text());
                            area.setLevel(level);
                            area.setPcode(pcode);
                            log.debug("[{}] - [{}]", area.getCode(), area.getName());
                            //放入 rabbit mq
                            Sender.getInstance().sendMessage(area);
                        } else {
                            log.error("parse error, children is not 2 or 3, url[{}]", url);
                            ErrorLog errorLog = new ErrorLog();
                            errorLog.setMsg("parse error, children is not 2 or 3");
                            errorLog.setNote(url);
                            //放入rabbit mq
                            Sender.getInstance().sendMessage(errorLog);
                        }
                    }
                }
                break;
            case 5:
                elements = doc.select("body > table:nth-child(3) > tbody > tr:nth-child(1) > td > table > tbody > tr:nth-child(2) > td > table > tbody > tr > td > table > tbody > tr.villagetr");
                iterable = elements.iterator();
//                while (iterable.hasNext()) {
                if (iterable.hasNext()) {
//                    Element element = iterable.next();
                    Element element = elements.get(0);
                    Elements children = element.select("td");
                    if(children.size() == 3) {
                        Area area = new Area();
                        area.setCode(children.get(0).text());
                        area.setOrigin(url);
                        area.setName(children.get(2).text());
                        area.setLevel(level);
                        area.setPcode(pcode);
                        log.debug("[{}] - [{}]", area.getCode(), area.getName());
                        //放入 rabbit mq
                        Sender.getInstance().sendMessage(area);
                    } else {
                        log.error("parse error, children is not 3, url[{}]", url);
                        ErrorLog errorLog = new ErrorLog();
                        errorLog.setMsg("parse error, children is or 3");
                        errorLog.setNote(url);
                        //放入rabbit mq
                        Sender.getInstance().sendMessage(errorLog);
                    }
                }
                break;
        }
    }

    @Override
    public void run() {
        parse();
    }
}
