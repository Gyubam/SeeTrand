package WebService.SeeTrand.api;

import WebService.SeeTrand.dto.SearchListDto;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class SeleniumCrawling {

    private WebDriver driver;

    private static final String url = "https://www.signal.bz";

    public List<SearchListDto> process() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\X\\chromedriver.exe");
        //크롬 드라이버 셋팅 (드라이버 설치한 경로 입력)

        List<SearchListDto> searchList = new ArrayList<>();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-popup-blocking");       //팝업안띄움
        options.addArguments("headless");                       //브라우저 안띄움
        options.addArguments("--disable-gpu");			//gpu 비활성화
        options.addArguments("--blink-settings=imagesEnabled=false"); //이미지 다운 안받음

        driver = new ChromeDriver(options);
        //브라우저 선택

        try {
            searchList = getDataList();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.close();	//탭 닫기
        driver.quit();	//브라우저 닫기

        return searchList;
    }

    /**
     * data가져오기
     */
    private List<SearchListDto> getDataList() throws InterruptedException {
        List<SearchListDto> list = new ArrayList<>();


        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));	//⭐⭐⭐

        driver.get(url);    //브라우저에서 url로 이동한다.
//        Thread.sleep(1000); //브라우저 로딩될때까지 잠시 기다린다.

        webDriverWait.until(
                ExpectedConditions.presenceOfElementLocated(By.className("rank-text"))
                //cssSelector로 선택한 부분이 존재할때까지 기다려라
        );


        List<WebElement> elements = driver.findElements(By.className("rank-text"));


//        for (WebElement element : elements) {
//            log.info("----------------------------");
//            log.info("실시간 인기 검색어 = {}", element.getText());
//            list.add(new SearchListDto(element.get));
//        }

        for (int i = 0; i < elements.size(); i++) {
            log.info("----------------------------");
            log.info("실시간 인기 검색어 = {}", elements.get(i).getText());
            list.add(new SearchListDto(i + 1, elements.get(i).getText()));
        }

        return list;
    }
}
