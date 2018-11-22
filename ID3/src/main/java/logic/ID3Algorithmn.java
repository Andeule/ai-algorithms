package logic;

import com.sun.istack.internal.Nullable;
import model.TrainingExample;
import model.TrainingExampleRow;
import model.Tree;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class ID3Algorithmn {

    public static Tree<String> execute(String[][] trainingExamples) {
        Tree<String> generatedTree = new Tree<String>(trainingExamples[0][0]); //ex: Age


        return generatedTree;
    }

    public static double calculateInformationGain(TrainingExample trainingExample, int columnIndexOfAttribute) {
        double originalEntropy = calculateOriginalEntropy(trainingExample);
        double relativeEntropy = calculateRelativeEntropy(trainingExample, columnIndexOfAttribute);
        double informationGain = originalEntropy - relativeEntropy;
        System.out.printf("Information gain for %s: %s%n", trainingExample.getHeadline().getAttributes().get(columnIndexOfAttribute), informationGain);
        return informationGain;
    }

    public static double calculateOriginalEntropy(TrainingExample trainingExample) {
        //Get fraction of each target value
        Map<Object, Double> fractionOfTargetValue = calculateFractionOfTargetValue(trainingExample,null,null);
        //Calculate original entropy
        double countOfAttributes = trainingExample.getAttributes().size();
        double term = 0;
        for (Map.Entry<Object, Double> entry : fractionOfTargetValue.entrySet()) {
            BigDecimal numinator = new BigDecimal(entry.getValue());
            BigDecimal denominator = new BigDecimal(countOfAttributes);
            term += (-(numinator.divide(denominator).doubleValue()) * (Math.log(numinator.divide(denominator).doubleValue()) / Math.log(2)));
        }
        return term;
    }

    //TODO encapsulate parts and make it more readable
    public static double calculateRelativeEntropy(TrainingExample trainingExample, int columnOfAttribute) {
        double relativeEntropy = 0;
        int countNumberOfAttributes = trainingExample.getAttributes().size();

        //calculate whole fraction of that attribute e.g. age
        Map<Object, Double> fractionOfAttribute = new HashMap<>();
        for (TrainingExampleRow row : trainingExample.getAttributes()) {
            Object cellValueAttribute = row.getAttributes().get(columnOfAttribute);
            if (fractionOfAttribute.containsKey(cellValueAttribute)) {
                fractionOfAttribute.put(cellValueAttribute, 1D + fractionOfAttribute.get(cellValueAttribute));
            } else {
                fractionOfAttribute.put(cellValueAttribute, 1D);
            }
        }


        for (Map.Entry<Object, Double> entry : fractionOfAttribute.entrySet()) {

            //FRACTION FOR ONE ATTRIBUTE's target value e.g. old, young
            Map<Object, Double> fractionOfInnerAttribute = calculateFractionOfTargetValue(trainingExample,columnOfAttribute, entry);
            //calculate
            double term = 0;
            for (Map.Entry<Object, Double> entryOfInner : fractionOfInnerAttribute.entrySet()) {
                BigDecimal numinator = new BigDecimal(entryOfInner.getValue());
                BigDecimal denominator = new BigDecimal(entry.getValue());
                term += (-(numinator.divide(denominator, 4, RoundingMode.HALF_DOWN).doubleValue()) * (Math.log(numinator.divide(denominator, 4, RoundingMode.HALF_DOWN).doubleValue()) / Math.log(2)));
            }
            BigDecimal numinator = new BigDecimal(entry.getValue());
            BigDecimal denominator = new BigDecimal(countNumberOfAttributes);
            term *= numinator.divide(denominator, 4, RoundingMode.HALF_DOWN).doubleValue();
            relativeEntropy += term;
        }
        return relativeEntropy;
    }

///////////////////////////////////////////////
//------------PRIVATE------------------------//
///////////////////////////////////////////////

    /**
     *
     * @param trainingExample
     * @param columnOfAttribute With this attribute you can restrict the calculation to a column.
     *                          If it is null
     * @param restrictionToOneAttribute  You can further restrict it to one value of that attribute e.g. column = "age" attributes of that column "average","old","young". Then you could restrict it to "young"
     *                                   If this attribute is set, then columnOfAttribute must be also set.
     * @return
     */
    private static Map<Object, Double> calculateFractionOfTargetValue(TrainingExample trainingExample,@Nullable Integer columnOfAttribute,@Nullable Map.Entry<Object, Double> restrictionToOneAttribute) {
        if(columnOfAttribute != null && restrictionToOneAttribute == null || columnOfAttribute == null && restrictionToOneAttribute != null)
            throw new IllegalArgumentException("If columnfOfAttribute is set then restrictionToOneAttribute must be set also and vice versa.");
        Map<Object, Double> fractionOfTargetValue = new HashMap<>();
        for (TrainingExampleRow row : trainingExample.getAttributes()) {
            Object targetValue = row.getAttributes().get(row.getColumnIndexOfTargetValue());
            if ((columnOfAttribute == null && restrictionToOneAttribute == null) || row.getAttributes().get(columnOfAttribute).equals(restrictionToOneAttribute.getKey()))
                if (fractionOfTargetValue.containsKey(targetValue)) {
                    fractionOfTargetValue.put(targetValue, 1D + fractionOfTargetValue.get(targetValue));
                } else {
                    fractionOfTargetValue.put(targetValue, 1D);
                }
        }
        return fractionOfTargetValue;
    }
}
