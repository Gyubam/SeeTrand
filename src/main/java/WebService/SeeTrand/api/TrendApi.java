package WebService.SeeTrand.api;

import WebService.SeeTrand.dto.SearchListDto;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrendApi {

    public String init(List<SearchListDto> searchList) {
        String clientId = "fwKEknCciIKpAZnOmECb"; // 애플리케이션 클라이언트 아이디
        String clientSecret = "etxiBOMxpF"; // 애플리케이션 클라이언트 시크릿

        LocalDateTime localDateTimeNow = LocalDateTime.now();
        LocalDateTime localDateTimeAfterYear = LocalDateTime.now().minusYears(1);
        String parsedLocalDateTimeNow = localDateTimeNow.format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd")
        );

        String parsedLocalDateTimeAfterYear = localDateTimeAfterYear.format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd")
        );

        String apiUrl = "https://openapi.naver.com/v1/datalab/search";

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        requestHeaders.put("Content-Type", "application/json");

//        String date = "2017-02-01";
        // "2017-01-01"
        String requestBody = "{\"startDate\":" + "\"" + parsedLocalDateTimeAfterYear + "\"," +
                "\"endDate\":" + "\"" + parsedLocalDateTimeNow + "\"," +
                "\"timeUnit\":\"month\"," +
                "\"keywordGroups\":[{\"groupName\":" + "\""+ searchList.get(0).getSearchWord() + "\"," + "\"keywords\""+ ":[\"" + searchList.get(0).getSearchWord() + "\"]}," +
                "{\"groupName\":" + "\"" + searchList.get(1).getSearchWord() + "\"," + "\"keywords\""+ ":[\"" + searchList.get(1).getSearchWord() + "\"]}," +
                "{\"groupName\":" + "\"" + searchList.get(2).getSearchWord() + "\"," + "\"keywords\""+ ":[\"" + searchList.get(2).getSearchWord() + "\"]}," +
                "{\"groupName\":" + "\"" + searchList.get(3).getSearchWord() + "\"," + "\"keywords\""+ ":[\"" + searchList.get(3).getSearchWord() + "\"]}," +
                "{\"groupName\":" + "\"" + searchList.get(4).getSearchWord() + "\"," + "\"keywords\""+ ":[\"" + searchList.get(4).getSearchWord() + "\"]}]," +

                "\"device\":\"\"," +
                "\"ages\":[]," +
                "\"gender\":\"\"}";

        String responseBody = post(apiUrl, requestHeaders, requestBody);
//        System.out.println(responseBody);

        return responseBody;
    }

    private static String post(String apiUrl, Map<String, String> requestHeaders, String requestBody) {
        HttpURLConnection con = connect(apiUrl);

        try {
            con.setRequestMethod("POST");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            con.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(requestBody.getBytes());
                wr.flush();
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 응답
                return readBody(con.getInputStream());
            } else {  // 에러 응답
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect(); // Connection을 재활용할 필요가 없는 프로세스일 경우
        }
    }

    private static HttpURLConnection connect(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body) {
        InputStreamReader streamReader = new InputStreamReader(body, StandardCharsets.UTF_8);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
}
