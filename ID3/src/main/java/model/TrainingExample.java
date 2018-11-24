package model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class TrainingExample {

    private List<TrainingExampleRow> exampleRowList = new ArrayList<>();

    private TrainingExampleHeadline headline;




    public TrainingExample(TrainingExampleHeadline headline, List<TrainingExampleRow> exampleRowList) {
        this.headline = headline;
        this.exampleRowList = exampleRowList;
    }

}
