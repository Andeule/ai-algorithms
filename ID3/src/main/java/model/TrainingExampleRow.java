package model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class TrainingExampleRow<A> {

    List<A> attributes = new ArrayList<>();
    int columnIndexOfTargetValue;

    public TrainingExampleRow(int columnIndexOfTargetValue, A... attributes) {
        this.columnIndexOfTargetValue = columnIndexOfTargetValue;
        this.attributes = Arrays.asList(attributes);
    }
    public TrainingExampleRow(int columnIndexOfTargetValue, List<A> attributes) {
        this.columnIndexOfTargetValue = columnIndexOfTargetValue;
        this.attributes = attributes;
    }


}
