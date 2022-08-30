package WebService.SeeTrand.api;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Crawling {

    /**
     * 조회할 URL셋팅 및 Document 객체 로드하기
     */
    private static final String url = "https://signal.bz";

    public void process() {
        log.info("크롤링 프로세스 진행");
        Connection conn = Jsoup.connect(url);
        //Jsoup 커넥션 생성

        Document document = null;
        try {
            document = conn.get();
            //url의 내용을 HTML Document 객체로 가져온다.
            //https://jsoup.org/apidocs/org/jsoup/nodes/Document.html 참고
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> list = getDataList(document);
    }

    /**
     * data가져오기
     */
    private List<String> getDataList(Document document) {
        log.info("크롤링 데이터 수집 실행");
        List<String> list = new ArrayList<>();
//        Elements selects = document.select("span.rank-text");
        //select 메서드 안에 css selector를 작성하여 Elements를 가져올 수 있다.

//        Elements elements = document.getElementsByAttributeValueContaining("class", "realtime-rank rank-text");

        Elements classes = document.getElementsByClass("div.realtime-rank");
        log.info("classes size = {}", classes.size());
        Elements spans = classes.select("span.rank-text");
        log.info("elemets size = {}", spans.size());
        for (Element select : spans) {
            log.info("반복문 출력");
            System.out.println(select.html());		//⭐⭐⭐
            //html(), text(), children(), append().... 등 다양한 메서드 사용 가능
            //https://jsoup.org/apidocs/org/jsoup/nodes/Element.html 참고
        }
        return list;
    }

}
