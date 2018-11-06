package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrainingExampleRow<T> {

    List<T> attributes = new ArrayList<>();
    boolean isPositiveExample;

    public TrainingExampleRow(boolean isPositiveExample, T... attributes) {
        this.isPositiveExample = isPositiveExample;
        this.attributes = Arrays.asList(attributes);
    }


}
