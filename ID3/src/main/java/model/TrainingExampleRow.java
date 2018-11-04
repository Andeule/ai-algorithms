package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrainingExampleRow<T> {

    List<T> attributes = new ArrayList<>();
    boolean isPositiveExample;
    boolean isHeadline = false;

    public TrainingExampleRow(boolean isPositiveExample, T... attributes) {
        this.isPositiveExample = isPositiveExample;
        this.attributes = Arrays.asList(attributes);
    }

    /**
     * This constructor should be used to create a headline
     * @param attributes
     */
    public TrainingExampleRow(T... attributes) {
        this(false,attributes);
        this.isHeadline = true;

    }

}
