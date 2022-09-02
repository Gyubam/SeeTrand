package WebService.SeeTrand.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class GraphDataDto {

    private List<Results> results;
    private String startDate;
    private String timeUnit;
    private String endDate;

    @NoArgsConstructor
    @Getter
    @ToString
    public static class Results {
        private List<Data> data;
        private List<String> keywords;
        private String title;
    }

    @Getter
    @NoArgsConstructor
    @ToString
    public static class Data {
        private int ratio;
        private String period;
    }

    public GraphDataDto(List<Results> results, String startDate, String timeUnit, String endDate) {
        this.results = results;
        this.startDate = startDate;
        this.timeUnit = timeUnit;
        this.endDate = endDate;
    }

    public GraphDataDto() {
    }

    //    private String startDate;
//    private String endDate;
//    private String timeUnit;
//    private String title;
//    private List<String> keywords;
//    private String period;
//    private String ratio;


}
