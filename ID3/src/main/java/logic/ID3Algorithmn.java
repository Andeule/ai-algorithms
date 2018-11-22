package logic;

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
    public static double calculateInformationGain(TrainingExample trainingExample,int columnIndexOfAttribute){
        double originalEntropy = calculateOriginalEntropy(trainingExample);
        double relativeEntropy = calculateRelativeEntropy(trainingExample,columnIndexOfAttribute);
        return originalEntropy-relativeEntropy;
    }

    public static double calculateOriginalEntropy(TrainingExample trainingExample) {
        //Get fraction of each target value
        Map<Object, Double> fractionOfTargetValue = new HashMap<>();
        for (TrainingExampleRow row : trainingExample.getAttributes()) {
            Object targetValue = row.getAttributes().get(row.getColumnIndexOfTargetValue());
            if (fractionOfTargetValue.containsKey(targetValue)){
                fractionOfTargetValue.put(targetValue, 1D + fractionOfTargetValue.get(targetValue));
            } else {
                fractionOfTargetValue.put(targetValue, 1D);
            }
        }
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
    public static double calculateRelativeEntropy(TrainingExample trainingExample, int culmnOfAttribute) {
        double relativeEntropy = 0;
        int countNumberOfAttributes = trainingExample.getAttributes().size();

        //calculate whole fraction of that attribute e.g. age
        Map<Object, Double> fractionOfAttribute = new HashMap<>();
        for (TrainingExampleRow row : trainingExample.getAttributes()) {
            Object cellValueAttribute = row.getAttributes().get(culmnOfAttribute);
            if (fractionOfAttribute.containsKey(cellValueAttribute)) {
                fractionOfAttribute.put(cellValueAttribute, 1D + fractionOfAttribute.get(cellValueAttribute));
            } else {
                fractionOfAttribute.put(cellValueAttribute, 1D);
            }
        }



        for (Map.Entry<Object, Double> entry : fractionOfAttribute.entrySet()) {
            Map<Object, Double> fractionOfInnerAttribute = new HashMap<>();
            //FRACTION FOR ONE ATTRIBUTE's target value e.g. old, young
            for (TrainingExampleRow row : trainingExample.getAttributes()) {
                //Fraction of
                Object targetValue = row.getAttributes().get(row.getColumnIndexOfTargetValue());
                if (row.getAttributes().get(culmnOfAttribute).equals(entry.getKey())) {
                    if (fractionOfInnerAttribute.containsKey(targetValue)) {
                        fractionOfInnerAttribute.put(targetValue, 1D + fractionOfInnerAttribute.get(targetValue));
                    } else {
                        fractionOfInnerAttribute.put(targetValue, 1D);
                    }
                }
            }
            //calculate
            double term = 0;
            for (Map.Entry<Object, Double> entryOfInner : fractionOfInnerAttribute.entrySet()) {
                BigDecimal numinator = new BigDecimal(entryOfInner.getValue());
                BigDecimal denominator = new BigDecimal(entry.getValue());
                term += (-(numinator.divide(denominator,4,RoundingMode.HALF_DOWN).doubleValue()) * (Math.log(numinator.divide(denominator,4,RoundingMode.HALF_DOWN).doubleValue()) / Math.log(2)));
            }
            BigDecimal numinator = new BigDecimal(entry.getValue());
            BigDecimal denominator = new BigDecimal(countNumberOfAttributes);
            term *= numinator.divide(denominator,4,RoundingMode.HALF_DOWN).doubleValue();
            relativeEntropy += term;
        }
        return relativeEntropy;
    }

///////////////////////////////////////////////
//------------PRIVATE------------------------//
///////////////////////////////////////////////

}
