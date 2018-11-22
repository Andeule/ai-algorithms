package model;

/**
 * Represents the headline of trainingexamples
 */
public class TrainingExampleHeadline<T> extends TrainingExampleRow<String> {

    public TrainingExampleHeadline(int indexColumnOfTargetValue, String... attributes) {
        super(indexColumnOfTargetValue, attributes);
    }
}
