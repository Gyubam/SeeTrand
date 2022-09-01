package WebService.SeeTrand.controller;

import WebService.SeeTrand.api.Crawling;
import WebService.SeeTrand.api.SeleniumCrawling;
import WebService.SeeTrand.api.TrendApi;
import WebService.SeeTrand.dto.SearchListDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class HomeController {

    @GetMapping("/")
    public String home() {

        TrendApi trendApi = new TrendApi();
        trendApi.init();

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
    public String searchGraph(Model model) {

        return "layout/searchGraph";
    }


    @GetMapping("/re")
    public String ho() {

        SeleniumCrawling seleniumCrawling = new SeleniumCrawling();
        seleniumCrawling.process();

        return "home";
    }

}
