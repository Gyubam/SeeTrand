package WebService.SeeTrand.converter;

import WebService.SeeTrand.dto.GraphDataDto;

import java.util.ArrayList;
import java.util.List;

public class GraphDataDtoSeperater {

    public String returnStartDate(GraphDataDto graphDataDto) {

        return graphDataDto.getStartDate();
    }

    public String returnEndDate(GraphDataDto graphDataDto) {

        return graphDataDto.getEndDate();
    }

    public String returnTimeUnit(GraphDataDto graphDataDto) {

        return graphDataDto.getTimeUnit();
    }

    public List<String> returnTitle(GraphDataDto graphDataDto) {

        List<String> list = new ArrayList<>();
        for (int i = 0; i < graphDataDto.getResults().size(); i++) {

            list.add(graphDataDto.getResults().get(i).getTitle());
        }

        return list;
    }

    public List<String> returnPeriod0(GraphDataDto graphDataDto) {

        List<String> list = new ArrayList<>();
        for (int i = 0; i < graphDataDto.getResults().get(0).getData().size(); i++) {

            list.add(graphDataDto.getResults().get(0).getData().get(i).getPeriod());
        }

        return list;
    }

    public List<String> returnPeriod1(GraphDataDto graphDataDto) {

        List<String> list = new ArrayList<>();
        for (int i = 0; i < graphDataDto.getResults().get(1).getData().size(); i++) {

            list.add(graphDataDto.getResults().get(1).getData().get(i).getPeriod());
        }

        return list;
    }

    public List<String> returnPeriod2(GraphDataDto graphDataDto) {

        List<String> list = new ArrayList<>();
        for (int i = 0; i < graphDataDto.getResults().get(2).getData().size(); i++) {

            list.add(graphDataDto.getResults().get(2).getData().get(i).getPeriod());
        }

        return list;
    }

    public List<String> returnPeriod3(GraphDataDto graphDataDto) {

        List<String> list = new ArrayList<>();
        for (int i = 0; i < graphDataDto.getResults().get(3).getData().size(); i++) {

            list.add(graphDataDto.getResults().get(3).getData().get(i).getPeriod());
        }

        return list;
    }

    public List<String> returnPeriod4(GraphDataDto graphDataDto) {

        List<String> list = new ArrayList<>();
        for (int i = 0; i < graphDataDto.getResults().get(4).getData().size(); i++) {

            list.add(graphDataDto.getResults().get(4).getData().get(i).getPeriod());
        }

        return list;
    }


    public List<Integer> returnRatio0(GraphDataDto graphDataDto) {

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < graphDataDto.getResults().get(0).getData().size(); i++) {

            list.add(graphDataDto.getResults().get(0).getData().get(i).getRatio());
        }

        return list;
    }

    public List<Integer> returnRatio1(GraphDataDto graphDataDto) {

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < graphDataDto.getResults().get(1).getData().size(); i++) {

            list.add(graphDataDto.getResults().get(1).getData().get(i).getRatio());
        }

        return list;
    }

    public List<Integer> returnRatio2(GraphDataDto graphDataDto) {

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < graphDataDto.getResults().get(2).getData().size(); i++) {

            list.add(graphDataDto.getResults().get(2).getData().get(i).getRatio());
        }

        return list;
    }

    public List<Integer> returnRatio3(GraphDataDto graphDataDto) {

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < graphDataDto.getResults().get(3).getData().size(); i++) {

            list.add(graphDataDto.getResults().get(3).getData().get(i).getRatio());
        }

        return list;
    }

    public List<Integer> returnRatio4(GraphDataDto graphDataDto) {

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < graphDataDto.getResults().get(4).getData().size(); i++) {

            list.add(graphDataDto.getResults().get(4).getData().get(i).getRatio());
        }

        return list;
    }


}
