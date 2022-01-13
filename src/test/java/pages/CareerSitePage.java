package pages;

import control.Button;
import control.Label;
import org.openqa.selenium.By;
import java.util.HashMap;

import java.util.Map;

public class CareerSitePage {

    public Map<String, Label> labelsMap = new HashMap<>();
    public Button returnToJobSearch = new Button(By.xpath("//button[contains(.,'Return to Job Search')]"));
    public CareerSitePage(){
        labelsMap.put("Job Title",new Label(By.xpath("//*[@class='jobTitle job-detail-title']")));
        labelsMap.put("Job Location",new Label(By.xpath("//span[@class='resultLocationLink']//*[@class='resultfootervalue']")));
        labelsMap.put("Job Id",new Label(By.xpath("//*[@class='jobnum']")));
    }

        public String getResponsibleList(int number) throws Exception {
            Label labelResponsible= new Label(By.xpath("//div[@class='content']/ul[1]/li["+number+"]"));
            return labelResponsible.getText();
        }
}
