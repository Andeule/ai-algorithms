package model;

import lombok.Getter;

import java.util.List;

@Getter
public class TrainingExample {

    List<TrainingExampleRow> attributes;
    TrainingExampleHeadline headline;


    public TrainingExample(TrainingExampleHeadline headline, List<TrainingExampleRow> attributes) {
        this.headline = headline;
        this.attributes = attributes;
    }
}
