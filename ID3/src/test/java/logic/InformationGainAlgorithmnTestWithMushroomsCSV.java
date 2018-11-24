package logic;

import model.TrainingExample;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class InformationGainAlgorithmnTestWithMushroomsCSV {

    TrainingExample trainingExample;
    @Before
    public void setup() throws IOException {
        trainingExample = CSVMapper.mapFromCSVFile("C:\\Users\\ahr\\Google Drive\\Master Studium Bamberg\\AngewandteInformatik\\2nd - WS18\\KogSys-ML-M\\Exercise1\\mushrooms.csv", 0);
    }

    @Test
    public void test() {
        InformationGainAlgorithmn.calculateInformationGain(trainingExample,1);
        InformationGainAlgorithmn.calculateInformationGain(trainingExample,2);
        InformationGainAlgorithmn.calculateInformationGain(trainingExample,3);
        InformationGainAlgorithmn.calculateInformationGain(trainingExample,4);
        InformationGainAlgorithmn.calculateInformationGain(trainingExample,5);
        InformationGainAlgorithmn.calculateInformationGain(trainingExample,6);
        InformationGainAlgorithmn.calculateInformationGain(trainingExample,7);
        InformationGainAlgorithmn.calculateInformationGain(trainingExample,8);
        InformationGainAlgorithmn.calculateInformationGain(trainingExample,9);
        InformationGainAlgorithmn.calculateInformationGain(trainingExample,10);
        InformationGainAlgorithmn.calculateInformationGain(trainingExample,11);
        InformationGainAlgorithmn.calculateInformationGain(trainingExample,12);
    }

    @Test
    public void testHighestInformationGain(){
        Assert.assertEquals(5,InformationGainAlgorithmn.calculateHighestInformationGain(trainingExample));
    }
}
