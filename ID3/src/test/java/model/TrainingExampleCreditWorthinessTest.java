package model;

import org.junit.Before;
import org.junit.Test;

public class TrainingExampleCreditWorthinessTest {


    TrainingExample trainingExample;

    @Before
    public void setup(){
        trainingExample = new TrainingExample(new TrainingExampleHeadline<String>(0,"age","occupation","security","worthy"),null);
        trainingExample.getAttributes().add(new TrainingExampleRow<String>(0,"high","old","clerk","sufficient"));
        trainingExample.getAttributes().add(new TrainingExampleRow<String>(0,"high","old","clerk","sparse"));
        trainingExample.getAttributes().add(new TrainingExampleRow<String>(0,"average","young","clerk","sparse"));
        trainingExample.getAttributes().add(new TrainingExampleRow<String>(0,"high","average","clerk","sufficient"));
        trainingExample.getAttributes().add(new TrainingExampleRow<String>(0,"low","young","self-employed","sparse"));
        trainingExample.getAttributes().add(new TrainingExampleRow<String>(0,"low","young","self-employed","sufficient"));
        trainingExample.getAttributes().add(new TrainingExampleRow<String>(0,"average","average","self-employed","sufficient"));
        trainingExample.getAttributes().add(new TrainingExampleRow<String>(0,"low","average","self-employed","sparse"));
        trainingExample.getAttributes().add(new TrainingExampleRow<String>(0,"average","old","self-employed","sparse"));
        trainingExample.getAttributes().add(new TrainingExampleRow<String>(0,"high","old","self-employed","sufficient"));

    }

    @Test
    public void voidTest(){

    }
}
