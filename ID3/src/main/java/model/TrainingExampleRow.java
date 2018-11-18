package model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class TrainingExampleRow<A,T> {

    List<A> attributes = new ArrayList<>();
    T targetValue;

    public TrainingExampleRow(T targetValue, A... attributes) {
        this.targetValue = targetValue;
        this.attributes = Arrays.asList(attributes);
    }


}
