package pages;

import control.Button;
import control.Input;
import control.Label;
import control.Link;
import org.openqa.selenium.By;
import java.util.HashMap;
import java.util.Map;

public class JobsPage {

    public Input keywordSearchInput = new Input(By.xpath("//input[contains(@id,'search-keyword')]"));
    public Input locationInput=new Input(By.xpath("//input[@class='search-location']"));
    public Button submitButton= new Button(By.cssSelector(".search-form__submit"));
    public Button applyButton = new Button(By.xpath("//*[@class='button job-apply top']"));
    public Map<String, Label> labelsMap = new HashMap<>();

    public JobsPage(){
        labelsMap.put("Job Title",new Label(By.xpath("//*[@class='job-description__heading']")));
        labelsMap.put("Job Location",new Label(By.xpath("//*[@class='job-location job-info']")));
        labelsMap.put("Job Id",new Label(By.xpath("//*[@class='job-id job-info']")));
    }

    public void clickOnResultSearch(String value) throws Exception {
        Link resultLink= new Link(By.xpath("//section[@id='search-results-list']//h2[text()='"+value+"']"));
        resultLink.click();
    }

    public String getResponsibleList(int number) throws Exception {
        Label labelResponsible= new Label(By.xpath("//*[@class='ats-description']/ul[1]/li["+number+"]"));
        return labelResponsible.getText();
    }
}
