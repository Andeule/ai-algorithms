package logic;

import model.Node;
import model.TrainingExample;
import model.TrainingExampleRow;
import model.Tree;

import java.util.*;

public class ID3Algorithmn {


    public static Node<String> execute(TrainingExample trainingExample, List<Integer> indexOfTakenOutAttributes, Node parent, String branchLabel) {
        List<Integer> deepCopyOfIndexOfTakenOutAttributes = new ArrayList<>(indexOfTakenOutAttributes);
        int indexOfBestInformationGainAttribute = getAttributeIndexWithHighestInformationGain(trainingExample, deepCopyOfIndexOfTakenOutAttributes);
        Node<String> root = new Node<>(trainingExample.getHeadline().getAttributes().get(indexOfBestInformationGainAttribute), parent,branchLabel);
        if (allExampleTargetValueArePositive(trainingExample)) {
            return new Node<String>(trainingExample.getExampleRowList().get(0).getAttributes().get(trainingExample.getHeadline().getColumnIndexOfTargetValue()), parent, branchLabel);
        }

        List<String> decisionAttribute = getDecisionAttributesOfA(trainingExample, indexOfBestInformationGainAttribute);
        for (int i = 0; i < decisionAttribute.size(); i++) {
            String branch = decisionAttribute.get(i);
            TrainingExample subSetTrainingExampleOfA = extractSubSetByAnAttribute(trainingExample, indexOfBestInformationGainAttribute, decisionAttribute.get(i));
            if (subSetTrainingExampleOfA.getExampleRowList().size() != 0) {
                deepCopyOfIndexOfTakenOutAttributes.add(indexOfBestInformationGainAttribute);
                root.getChildren().add(execute(subSetTrainingExampleOfA, deepCopyOfIndexOfTakenOutAttributes, root, branch));
            }
        }
        return root;
    }

    protected static int getAttributeIndexWithHighestInformationGain(TrainingExample trainingExample, List<Integer> indexOfTakenOutAttributes) {
        double highestInformationGain = 0;
        int indexOfBestInformationGainAttribute = 0;
        for (int i = 0; i < trainingExample.getHeadline().getAttributes().size(); i++) {
            if (i != trainingExample.getHeadline().getColumnIndexOfTargetValue() && !indexOfTakenOutAttributes.contains(i)) {
                double informationGain = InformationGainAlgorithmn.calculateInformationGain(trainingExample, i);
                if (informationGain > highestInformationGain) {
                    highestInformationGain = informationGain;
                    indexOfBestInformationGainAttribute = i;
                }
            }
        }
        return indexOfBestInformationGainAttribute;
    }

    protected static List<String> getDecisionAttributesOfA(TrainingExample trainingExample, int indexOfA) {
        Set<String> decisionAttributeSet = new HashSet<>();
        trainingExample.getExampleRowList().stream().forEach(trainingExampleRow ->
                decisionAttributeSet.add(trainingExampleRow.getAttributes().get(indexOfA)));
        List<String> attributeList = new ArrayList<>();
        attributeList.addAll(decisionAttributeSet);
        return attributeList;
    }

    protected static TrainingExample extractSubSetByAnAttribute(TrainingExample trainingExampleOriginal, int indexOfColumn, String attribute) {
        TrainingExample trainingExampleSubSet = new TrainingExample(trainingExampleOriginal.getHeadline(), new ArrayList<>());
        for (TrainingExampleRow row : trainingExampleOriginal.getExampleRowList()) {
            if (row.getAttributes().get(indexOfColumn).equals(attribute)) {
                trainingExampleSubSet.getExampleRowList().add(row);
            }
        }
        return trainingExampleSubSet;
    }

    protected static boolean allExampleTargetValueArePositive(TrainingExample trainingExample) {
        boolean allSame = true;
        Object previousTarget = null;
        for (TrainingExampleRow row : trainingExample.getExampleRowList()) {
            Object newTarget = row.getAttributes().get(row.getColumnIndexOfTargetValue());
            if (!newTarget.equals(previousTarget) && previousTarget != null) {
                allSame = false;
            } else {
                previousTarget = newTarget;
            }
        }
        return allSame;
    }
}
