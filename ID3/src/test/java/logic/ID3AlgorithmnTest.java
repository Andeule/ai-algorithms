package logic;

import model.TrainingExample;
import model.TrainingExampleHeadline;
import model.TrainingExampleRow;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ID3AlgorithmnTest {
    TrainingExample trainingExample;

    @Before
    public void prepare(){
        List<TrainingExampleRow> exampleRows = new ArrayList<>();
        exampleRows.add(new TrainingExampleRow<String,String>("high","old","clerk","sufficient"));
        exampleRows.add(new TrainingExampleRow<String,String>("high","old","clerk","sparse"));
        exampleRows.add(new TrainingExampleRow<String,String>("average","young","clerk","sparse"));
        exampleRows.add(new TrainingExampleRow<String,String>("high","average","clerk","sufficient"));
        exampleRows.add(new TrainingExampleRow<String,String>("low","young","self-employed","sparse"));
        exampleRows.add(new TrainingExampleRow<String,String>("low","young","self-employed","sufficient"));
        exampleRows.add(new TrainingExampleRow<String,String>("average","average","self-employed","sufficient"));
        exampleRows.add(new TrainingExampleRow<String,String>("low","average","self-employed","sparse"));
        exampleRows.add(new TrainingExampleRow<String,String>("average","old","self-employed","sparse"));
        exampleRows.add(new TrainingExampleRow<String,String>("high","old","self-employed","sufficient"));
        trainingExample = new TrainingExample(new TrainingExampleHeadline("age","occupation","security","worthy"),exampleRows);
    }

    @Test
    public void testOriginalEntropy(){
        Assert.assertEquals(1.5709505944546684,ID3Algorithmn.calculateOriginalEntropy(trainingExample),0.0001);
    }

    @Test
    public void testRelativeEntropy(){
        double relativeEntropy = ID3Algorithmn.calculateRelativeEntropyOfOneAttributeOrTargetValue(trainingExample,0);
        Assert.assertEquals(1.0754744789463977,relativeEntropy,0.00001);
    }

    @Test
    public void testInformationGain(){
        double informationGain = ID3Algorithmn.calculateInformationGain(trainingExample,0);
        Assert.assertEquals(0.4955,informationGain,0.001);
    }
}
