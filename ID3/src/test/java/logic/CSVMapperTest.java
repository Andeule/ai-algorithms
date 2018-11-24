package logic;

import model.TrainingExample;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class CSVMapperTest {

    @Test
    public void testHeadlineWithMushroomsCSVFile() throws IOException {
        TrainingExample trainingExample = CSVMapper.mapFromCSVFile("C:\\Users\\ahr\\Google Drive\\Master Studium Bamberg\\AngewandteInformatik\\2nd - WS18\\KogSys-ML-M\\Exercise1\\mushrooms.csv", 0);
        String[] assertionHeadline = "class,cap-shape,cap-surface,cap-color,bruises,odor,gill-attachment,gill-spacing,gill-size,gill-color,veil-color,spore-print-color,population,habitat".split(",");
        Assert.assertEquals(assertionHeadline, trainingExample.getHeadline().getAttributes().toArray());
    }

    @Test
    public void testFirstLineWithMushroomsCSVFile() throws IOException {
        TrainingExample trainingExample = CSVMapper.mapFromCSVFile("C:\\Users\\ahr\\Google Drive\\Master Studium Bamberg\\AngewandteInformatik\\2nd - WS18\\KogSys-ML-M\\Exercise1\\mushrooms.csv", 0);
        String[] assertionFirstAttributeLine = "p,x,s,n,t,p,f,c,n,k,w,k,s,u".split(",");
        Assert.assertEquals(assertionFirstAttributeLine, trainingExample.getAttributes().get(0).getAttributes().toArray());
    }

    @Test
    public void testLastLineWithMushroomsCSVFile() throws IOException {
        TrainingExample trainingExample = CSVMapper.mapFromCSVFile("C:\\Users\\ahr\\Google Drive\\Master Studium Bamberg\\AngewandteInformatik\\2nd - WS18\\KogSys-ML-M\\Exercise1\\mushrooms.csv", 0);
        String[] assertionLastAttributeLine = "e,x,s,n,f,n,a,c,b,y,o,o,c,l".split(",");
        Assert.assertEquals(assertionLastAttributeLine, trainingExample.getAttributes().get(trainingExample.getAttributes().size() - 1).getAttributes().toArray());
    }
}
