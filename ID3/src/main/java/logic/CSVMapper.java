package logic;

import model.TrainingExample;
import model.TrainingExampleHeadline;
import model.TrainingExampleRow;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CSVMapper {

    public static TrainingExample mapFromCSVFile(String filepath, int indexOfTargetColumn) throws IOException {
        String line = "";
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            TrainingExample trainingExample = new TrainingExample();
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                String[] lineArray = line.split(cvsSplitBy);
                if(isFirstLine) {
                    TrainingExampleHeadline<String> trainingExampleHeadline = new TrainingExampleHeadline<String>(indexOfTargetColumn,lineArray);
                    trainingExample.setHeadline(trainingExampleHeadline);
                    isFirstLine = false;
                }
                else{
                    TrainingExampleRow<String> trainingExampleRow = new TrainingExampleRow<String>(indexOfTargetColumn,lineArray);
                    trainingExample.getAttributes().add(trainingExampleRow);
                }
            }
            return trainingExample;

        } catch (IOException e) {
            throw e;
        }
    }
}
