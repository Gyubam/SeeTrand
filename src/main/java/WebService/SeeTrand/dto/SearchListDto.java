package WebService.SeeTrand.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SearchListDto {

    private Integer index;
    private String searchWord;

    public SearchListDto(Integer index, String searchWord) {
        this.index = index;
        this.searchWord = searchWord;
    }
}
