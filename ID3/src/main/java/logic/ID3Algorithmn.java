package logic;

import model.Tree;

public class ID3Algorithmn {

    public static Tree<String> execute(String[][] trainingExamples){
        Tree<String> generatedTree = new Tree<String>(trainingExamples[0][0]); //ex: Age


        return generatedTree;
    }

    ///////////////////////////////////////////////
    //------------PRIVATE------------------------//
    ///////////////////////////////////////////////

    private static boolean allTrainingExamplesArePositive(String[][] trainingExamples){

        int lastColumnIndex = trainingExamples[0].length -1;

    }
}
