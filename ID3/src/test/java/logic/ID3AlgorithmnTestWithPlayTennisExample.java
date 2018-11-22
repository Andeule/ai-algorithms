package logic;

import model.TrainingExample;
import model.TrainingExampleHeadline;
import model.TrainingExampleRow;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ID3AlgorithmnTestWithPlayTennisExample {


    private TrainingExample trainingExample;

    @Before
    public void prepare() {
        List<TrainingExampleRow> exampleRows = new ArrayList<>();
        exampleRows.add(new TrainingExampleRow<String>(4, "sunny", "hot", "high", "weak","no"));
        exampleRows.add(new TrainingExampleRow<String>(4, "sunny", "hot", "high", "strong","no"));
        exampleRows.add(new TrainingExampleRow<String>(4, "overcast", "hot", "high", "weak","yes"));
        exampleRows.add(new TrainingExampleRow<String>(4, "rain", "mild", "high", "weak","yes"));
        exampleRows.add(new TrainingExampleRow<String>(4, "rain", "cool", "normal", "weak","yes"));
        exampleRows.add(new TrainingExampleRow<String>(4, "rain", "cool", "normal", "strong","no"));
        exampleRows.add(new TrainingExampleRow<String>(4, "overcast", "cool", "normal", "strong","yes"));
        exampleRows.add(new TrainingExampleRow<String>(4, "sunny", "mild", "high", "weak","no"));
        exampleRows.add(new TrainingExampleRow<String>(4, "sunny", "cool", "normal", "weak","yes"));
        exampleRows.add(new TrainingExampleRow<String>(4, "rain", "mild", "normal", "weak","yes"));
        exampleRows.add(new TrainingExampleRow<String>(4, "sunny", "mild", "normal", "strong","yes"));
        exampleRows.add(new TrainingExampleRow<String>(4, "overcast", "mild", "high", "strong","yes"));
        exampleRows.add(new TrainingExampleRow<String>(4, "overcast", "hot", "normal", "weak","yes"));
        exampleRows.add(new TrainingExampleRow<String>(4, "rain", "mild", "high", "strong","no"));
        trainingExample = new TrainingExample(new TrainingExampleHeadline(4,"outlook","Temperatur","humidity","wind","PlayTennis"), exampleRows);
    }

    @Test
    public void testInformationGain(){
        Assert.assertEquals(0.246,ID3Algorithmn.calculateInformationGain(trainingExample,0),0.001);
        Assert.assertEquals(0.151,ID3Algorithmn.calculateInformationGain(trainingExample,2),0.001);
        Assert.assertEquals(0.048,ID3Algorithmn.calculateInformationGain(trainingExample,3),0.001);
        Assert.assertEquals(0.029,ID3Algorithmn.calculateInformationGain(trainingExample,1),0.001);
    }
}
