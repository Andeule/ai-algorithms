package logic;

import model.TrainingExample;
import model.TrainingExampleRow;
import model.Tree;

import java.math.BigDecimal;
import java.util.*;

public class ID3Algorithmn {

    public static Tree<String> execute(String[][] trainingExamples) {
        Tree<String> generatedTree = new Tree<String>(trainingExamples[0][0]); //ex: Age


        return generatedTree;
    }

    public static double calculateOriginalEntropy(TrainingExample trainingExample) {
        //Get fraction of each target value
        Map<Object, Double> fractionOfTargetValue = new HashMap<>();
        for(TrainingExampleRow row : trainingExample.getAttributes()){
            if(fractionOfTargetValue.containsKey(row.getTargetValue())){
                fractionOfTargetValue.put(row.getTargetValue(),1D+fractionOfTargetValue.get(row.getTargetValue()));
            }
            else{
                fractionOfTargetValue.put(row.getTargetValue(),1D);
            }
        }
        //Calculate original entropy
        double countOfAttributes = trainingExample.getAttributes().size();
        double term = 0;
        for (Map.Entry<Object, Double> entry : fractionOfTargetValue.entrySet())
        {
            BigDecimal numinator = new BigDecimal(entry.getValue());
            BigDecimal denominator = new BigDecimal(countOfAttributes);
            term += (-(numinator.divide(denominator).doubleValue() )*(Math.log(numinator.divide(denominator).doubleValue())/Math.log(2)));
        }
        return term;
    }

    ///////////////////////////////////////////////
    //------------PRIVATE------------------------//
    ///////////////////////////////////////////////

}
