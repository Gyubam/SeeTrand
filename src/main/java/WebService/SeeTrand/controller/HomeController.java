package WebService.SeeTrand.controller;

import WebService.SeeTrand.api.Crawling;
import WebService.SeeTrand.api.SeleniumCrawling;
import WebService.SeeTrand.api.TrendApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
public class HomeController {

    @GetMapping("/")
    public String home() {

        TrendApi trendApi = new TrendApi();
        trendApi.init();

        return "home";

    }

    @GetMapping("/re")
    public String ho() {

        SeleniumCrawling seleniumCrawling = new SeleniumCrawling();
        seleniumCrawling.process();

        return "home";
    }

}
