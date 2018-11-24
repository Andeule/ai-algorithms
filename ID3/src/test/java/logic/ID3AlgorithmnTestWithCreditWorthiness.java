package logic;

import model.Node;
import model.TrainingExample;
import model.TrainingExampleHeadline;
import model.TrainingExampleRow;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ID3AlgorithmnTestWithCreditWorthiness {

    TrainingExample trainingExample;

    @Before
    public void prepare() {
        List<TrainingExampleRow> exampleRows = new ArrayList<>();
        exampleRows.add(new TrainingExampleRow(0, "high", "old", "clerk", "sufficient"));
        exampleRows.add(new TrainingExampleRow(0, "high", "old", "clerk", "sparse"));
        exampleRows.add(new TrainingExampleRow(0, "average", "young", "clerk", "sparse"));
        exampleRows.add(new TrainingExampleRow(0, "high", "average", "clerk", "sufficient"));
        exampleRows.add(new TrainingExampleRow(0, "low", "young", "self-employed", "sparse"));
        exampleRows.add(new TrainingExampleRow(0, "low", "young", "self-employed", "sufficient"));
        exampleRows.add(new TrainingExampleRow(0, "average", "average", "self-employed", "sufficient"));
        exampleRows.add(new TrainingExampleRow(0, "low", "average", "self-employed", "sparse"));
        exampleRows.add(new TrainingExampleRow(0, "average", "old", "self-employed", "sparse"));
        exampleRows.add(new TrainingExampleRow(0, "high", "old", "self-employed", "sufficient"));
        trainingExample = new TrainingExample(new TrainingExampleHeadline(0, "worthy", "age", "occupation", "security"), exampleRows);
    }

    @Test
    public void testExtractSubset(){
        TrainingExample subset= ID3Algorithmn.extractSubSetByAnAttribute(trainingExample,1,"old");
        Assert.assertEquals(4,subset.getExampleRowList().size());
    }

    @Test
    public void testGetDecisionAttributesOfA(){
        int index = ID3Algorithmn.getAttributeIndexWithHighestInformationGain(trainingExample, new ArrayList<>());
        List<String> decisionAttributesOfA = ID3Algorithmn.getDecisionAttributesOfA(trainingExample, index);
        Assert.assertEquals(3, decisionAttributesOfA.size());
        List<String> expectedList = new ArrayList<>();
        expectedList.add("average");
        expectedList.add("young");
        expectedList.add("old");
        Assert.assertEquals(expectedList,decisionAttributesOfA);
    }

    @Test
    public void testAllTargetValuesAreSame(){
        boolean same = ID3Algorithmn.allExampleTargetValueArePositive(trainingExample);
        Assert.assertFalse(same);


        List<TrainingExampleRow> exampleRows = new ArrayList<>();
        exampleRows.add(new TrainingExampleRow(0, "high", "old", "clerk", "sufficient"));
        exampleRows.add(new TrainingExampleRow(0, "high", "old", "clerk", "sparse"));
        exampleRows.add(new TrainingExampleRow(0, "high", "average", "clerk", "sufficient"));
        TrainingExample trainingExampleWithSameTargetValue = new TrainingExample(new TrainingExampleHeadline(0, "worthy", "age", "occupation", "security"), exampleRows);

        same = ID3Algorithmn.allExampleTargetValueArePositive(trainingExampleWithSameTargetValue);
        Assert.assertTrue(same);
    }

    @Test
    public void testID3(){
        Node tree = ID3Algorithmn.execute(trainingExample,new ArrayList<>(), null, "");
        tree.print();

    }
}
