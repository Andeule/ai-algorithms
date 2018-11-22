package logic;

import model.TrainingExample;
import org.junit.Test;

import java.io.IOException;

public class ID3AlgorithmTestWithMushroomsCSV {

    @Test
    public void test() throws IOException {
        TrainingExample trainingExample = CSVMapper.mapFromCSVFile("C:\\Users\\ahr\\Google Drive\\Master Studium Bamberg\\AngewandteInformatik\\2nd - WS18\\KogSys-ML-M\\Exercise1\\mushrooms.csv", 0);
        ID3Algorithmn.calculateInformationGain(trainingExample,1);
        ID3Algorithmn.calculateInformationGain(trainingExample,2);
        ID3Algorithmn.calculateInformationGain(trainingExample,3);
        ID3Algorithmn.calculateInformationGain(trainingExample,4);
        ID3Algorithmn.calculateInformationGain(trainingExample,5);
        ID3Algorithmn.calculateInformationGain(trainingExample,6);
        ID3Algorithmn.calculateInformationGain(trainingExample,7);
        ID3Algorithmn.calculateInformationGain(trainingExample,8);
        ID3Algorithmn.calculateInformationGain(trainingExample,9);
        ID3Algorithmn.calculateInformationGain(trainingExample,10);
        ID3Algorithmn.calculateInformationGain(trainingExample,11);
        ID3Algorithmn.calculateInformationGain(trainingExample,12);

    }
}
