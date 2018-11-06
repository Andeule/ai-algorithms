package model;

import org.junit.Before;
import org.junit.Test;

public class TrainingExampleCreditWorthinessTest {


    TrainingExample trainingExample;

    @Before
    public void setup(){
        trainingExample = new TrainingExample(new TrainingExampleHeadline<String>("age","occupation","security","worthy"),null);
        trainingExample.getAttributes().add(new TrainingExampleRow<String, String>("high","old","clerk","sufficient"));
        trainingExample.getAttributes().add(new TrainingExampleRow<String, String>("high","old","clerk","sparse"));
        trainingExample.getAttributes().add(new TrainingExampleRow<String, String>("average","young","clerk","sparse"));
        trainingExample.getAttributes().add(new TrainingExampleRow<String, String>("high","average","clerk","sufficient"));
        trainingExample.getAttributes().add(new TrainingExampleRow<String, String>("low","young","self-employed","sparse"));
        trainingExample.getAttributes().add(new TrainingExampleRow<String, String>("low","young","self-employed","sufficient"));
        trainingExample.getAttributes().add(new TrainingExampleRow<String, String>("average","average","self-employed","sufficient"));
        trainingExample.getAttributes().add(new TrainingExampleRow<String, String>("low","average","self-employed","sparse"));
        trainingExample.getAttributes().add(new TrainingExampleRow<String, String>("average","old","self-employed","sparse"));
        trainingExample.getAttributes().add(new TrainingExampleRow<String, String>("high","old","self-employed","sufficient"));

    }

    @Test
    public void voidTest(){

    }
}
