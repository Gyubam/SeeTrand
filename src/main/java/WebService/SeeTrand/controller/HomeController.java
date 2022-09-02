package WebService.SeeTrand.controller;

import WebService.SeeTrand.api.SeleniumCrawling;
import WebService.SeeTrand.api.TrendApi;
import WebService.SeeTrand.converter.GraphDataDtoSeperater;
import WebService.SeeTrand.dto.GraphDataDto;
import WebService.SeeTrand.dto.SearchListDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class HomeController {

    private ObjectMapper mapper = new ObjectMapper();
    private GraphDataDtoSeperater seperater = new GraphDataDtoSeperater();

    @GetMapping("/")
    public String home() {

//        TrendApi trendApi = new TrendApi();
//        trendApi.init();

        return "index";

    }
    @GetMapping("/searchWord")
    public String searchWord(Model model) {

        SeleniumCrawling seleniumCrawling = new SeleniumCrawling();

        List<SearchListDto> searchList = seleniumCrawling.process();

        model.addAttribute("searchList", searchList);
        for (SearchListDto searchListDto : searchList) {
            System.out.println("searchListDto index = " + searchListDto.getIndex());
            System.out.println("searchListDto word = " + searchListDto.getSearchWord());
        }
        return "layout/searchWord";
    }

    @GetMapping("/searchGraph")
    public String searchGraph(Model model) throws JsonProcessingException {

        SeleniumCrawling seleniumCrawling = new SeleniumCrawling();

        // 웹 크롤링 진행
        List<SearchListDto> searchList = seleniumCrawling.process();
        List<SearchListDto> searchListFirst = new ArrayList<>();
        List<SearchListDto> searchListSecond = new ArrayList<>();

        for (int i = 0; i < searchList.size(); i++) {
            if (i < (searchList.size() + 1) / 2) {
                searchListFirst.add(searchList.get(i));
            } else {
                searchListSecond.add(searchList.get(i));
            }
        }


        List<String> dateList = new ArrayList<>();

        TrendApi trendApi = new TrendApi();
        String trendApiResult = trendApi.init(searchList);
        String trendApiResultFirst = trendApi.init(searchListFirst);
        String trendApiResultSecond = trendApi.init(searchListSecond);

        // 1년 날짜 계산
        LocalDateTime localDateTimeNow = LocalDateTime.now();
        for (int i = 11; i >= 0; i--) {
            dateList.add(localDateTimeNow.minusMonths(i).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }

//        log.info("트렌드 api 결과 = {}", trendApiResult);

        GraphDataDto graphDataDto = null;
        GraphDataDto graphDataDtoFirst = null;
        GraphDataDto graphDataDtoSecond = null;

        graphDataDto = mapper.readValue(trendApiResult, GraphDataDto.class);
        graphDataDtoFirst = mapper.readValue(trendApiResultFirst, GraphDataDto.class);
        graphDataDtoSecond = mapper.readValue(trendApiResultSecond, GraphDataDto.class);


//        log.info("트렌드 api 결과 = {}", graphDataDto);

        String startDate = seperater.returnStartDate(graphDataDto);
        String endDate = seperater.returnEndDate(graphDataDto);
        List<String> title = seperater.returnTitle(graphDataDto);
        List<String> titleSecond = seperater.returnTitle(graphDataDtoSecond);
        for (String titles : titleSecond) {
            title.add(titles);
        }


        List<String> period0 = seperater.returnPeriod0(graphDataDto);
        List<String> period1 = seperater.returnPeriod1(graphDataDto);
        List<String> period2 = seperater.returnPeriod2(graphDataDto);
        List<String> period3 = seperater.returnPeriod3(graphDataDto);
        List<String> period4 = seperater.returnPeriod4(graphDataDto);
        List<String> period5 = seperater.returnPeriod0(graphDataDtoSecond);
        List<String> period6 = seperater.returnPeriod1(graphDataDtoSecond);
        List<String> period7 = seperater.returnPeriod2(graphDataDtoSecond);
        List<String> period8 = seperater.returnPeriod3(graphDataDtoSecond);
        List<String> period9 = seperater.returnPeriod4(graphDataDtoSecond);

        List<Integer> ratio0 = seperater.returnRatio0(graphDataDto);
        List<Integer> ratio1 = seperater.returnRatio1(graphDataDto);
        List<Integer> ratio2 = seperater.returnRatio2(graphDataDto);
        List<Integer> ratio3 = seperater.returnRatio3(graphDataDto);
        List<Integer> ratio4 = seperater.returnRatio4(graphDataDto);
        List<Integer> ratio5 = seperater.returnRatio0(graphDataDtoSecond);
        List<Integer> ratio6 = seperater.returnRatio1(graphDataDtoSecond);
        List<Integer> ratio7 = seperater.returnRatio2(graphDataDtoSecond);
        List<Integer> ratio8 = seperater.returnRatio3(graphDataDtoSecond);
        List<Integer> ratio9 = seperater.returnRatio4(graphDataDtoSecond);



        model.addAttribute("title", title);
        model.addAttribute("period0", period0);
        model.addAttribute("period1", period1);
        model.addAttribute("period2", period2);
        model.addAttribute("period3", period3);
        model.addAttribute("period4", period4);
        model.addAttribute("period5", period5);
        model.addAttribute("period6", period6);
        model.addAttribute("period7", period7);
        model.addAttribute("period8", period8);
        model.addAttribute("period9", period9);
        model.addAttribute("ratio0", ratio0);
        model.addAttribute("ratio1", ratio1);
        model.addAttribute("ratio2", ratio2);
        model.addAttribute("ratio3", ratio3);
        model.addAttribute("ratio4", ratio4);
        model.addAttribute("ratio5", ratio5);
        model.addAttribute("ratio6", ratio6);
        model.addAttribute("ratio7", ratio7);
        model.addAttribute("ratio8", ratio8);
        model.addAttribute("ratio9", ratio9);

        model.addAttribute("dateList", dateList);

        return "layout/searchGraph";
    }


    @GetMapping("/re")
    public String ho() {

        SeleniumCrawling seleniumCrawling = new SeleniumCrawling();
        seleniumCrawling.process();

        return "home";
    }

}
