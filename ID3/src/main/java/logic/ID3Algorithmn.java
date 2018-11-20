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

    public static double calculateInformationGain(TrainingExample trainingExample, int columnIndexOfAttribute) {
        double originalEntropy = calculateOriginalEntropy(trainingExample);
        double relativeEntropy = calculateRelativeEntropyOfOneAttributeOrTargetValue(trainingExample, columnIndexOfAttribute);
        return originalEntropy - relativeEntropy;
    }

    public static double calculateOriginalEntropy(TrainingExample trainingExample) {
        //Get fraction of each target value
        Map<Object, Double> fractionOfTargetValue = calculateFractionOfAttributes(trainingExample.getAttributes(),true, 0);
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
    public static double calculateRelativeEntropyOfOneAttributeOrTargetValue(TrainingExample trainingExample, int columnOfAttribute) {

        int countNumberOfAttributes = trainingExample.getAttributes().size();

        //calculates the fraction of each attribute in the selected column e.g. column ="age", attributes ="young","old","average"
        Map<Object, Double> fractionOfAttribute = calculateFractionOfAttributes(trainingExample.getAttributes(),false,columnOfAttribute);

        double relativeEntropy = 0;
        for (Map.Entry<Object, Double> entry : fractionOfAttribute.entrySet()) {
            Map<Object, Double> fractionOfAttributesTargetValues = new HashMap<>();
            //Calculate the target value fraction withing an attribute (e.g. "young")
            for (TrainingExampleRow row : trainingExample.getAttributes()) {
                //Fraction of
                if (row.getAttributes().get(columnOfAttribute).equals(entry.getKey())) {
                    if (fractionOfAttributesTargetValues.containsKey(row.getTargetValue())) {
                        fractionOfAttributesTargetValues.put(row.getTargetValue(), 1D + fractionOfAttributesTargetValues.get(row.getTargetValue()));
                    } else {
                        fractionOfAttributesTargetValues.put(row.getTargetValue(), 1D);
                    }
                }
            }
            relativeEntropy += calculateRelativeEntropyOfOneAttributeOrTargetValue(fractionOfAttributesTargetValues, entry, countNumberOfAttributes);
        }
        return relativeEntropy;
    }

    private static Map<Object, Double> calculateFractionOfAttributes(List<TrainingExampleRow> trainingExampleRowList, boolean isTargetValueCalculation, int columnOfAttribute){
        Map<Object, Double> fractionOfTargetValue = new HashMap<>();
        for (TrainingExampleRow row : trainingExampleRowList) {
            Object objectForFractionCalculation;
            if(isTargetValueCalculation) objectForFractionCalculation = row.getTargetValue();
            else objectForFractionCalculation = row.getAttributes().get(columnOfAttribute);
            if (fractionOfTargetValue.containsKey(objectForFractionCalculation)) {
                fractionOfTargetValue.put(objectForFractionCalculation, 1D + fractionOfTargetValue.get(objectForFractionCalculation));
            } else {
                fractionOfTargetValue.put(objectForFractionCalculation, 1D);
            }
        }
        return fractionOfTargetValue;
    }

    /**
     * Calculate the relative entropy of either one attribute or of the target values
     * @param fractionOfAttributesTargetValues
     * @param attribute
     * @param countNumberOfAttributes
     * @return
     */
    private static double calculateRelativeEntropyOfOneAttributeOrTargetValue(Map<Object, Double> fractionOfAttributesTargetValues, Map.Entry<Object, Double> attribute, int countNumberOfAttributes) {
        double term = 0;
        for (Map.Entry<Object, Double> entryOfInner : fractionOfAttributesTargetValues.entrySet()) {
            BigDecimal numinator = new BigDecimal(entryOfInner.getValue());
            BigDecimal denominator = new BigDecimal(attribute.getValue());
            term += (-(numinator.divide(denominator, 4, RoundingMode.HALF_DOWN).doubleValue()) * (Math.log(numinator.divide(denominator, 4, RoundingMode.HALF_DOWN).doubleValue()) / Math.log(2)));
        }
        BigDecimal numinator = new BigDecimal(attribute.getValue());
        BigDecimal denominator = new BigDecimal(countNumberOfAttributes);
        term *= numinator.divide(denominator, 4, RoundingMode.HALF_DOWN).doubleValue();
        return term;
    }


///////////////////////////////////////////////
//------------PRIVATE------------------------//
///////////////////////////////////////////////

}
