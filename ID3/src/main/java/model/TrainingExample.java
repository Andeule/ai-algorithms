package model;

import lombok.Getter;

import java.util.List;

@Getter
public class TrainingExample {

    List<TrainingExampleRow> attributes;
    TrainingExampleRow headline;


    public TrainingExample(TrainingExampleRow headline, List<TrainingExampleRow> attributes) {
        this.headline = headline;
        this.attributes = attributes;
    }
}
