package runner;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import pages.*;
import session.Session;
import utils.GetProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StepsLabCorp {

    CareerSitePage careerSitePage = new CareerSitePage();
    JobsPage jobsPage = new JobsPage();
    MainPage  mainPage = new MainPage();
    SignDialog signDialog= new SignDialog();
    AcceptCookiesDialog acceptCookiesDialog= new AcceptCookiesDialog();

    @Then("verify the job position information")
    public void verifyTheJobPositionInformation(Map<String,String> expectedValues) throws Exception {
        String message="";
        boolean areEqual=true;
        for ( String label:expectedValues.keySet()) {
             String actualResult=jobsPage.labelsMap.get(label).getText();
             String expectedResult=expectedValues.get(label);
            if (!actualResult.contains(expectedResult)) {
                  areEqual=false;
                  message= message+ "ERROR! label ["+label+"]  expected result: ["+expectedResult+"] vs actual result ["+actualResult+"]\n";
            }
        }

        Assertions.assertTrue(areEqual,message);
    }

    @And("I apply to the job")
    public void iApplyToTheJob() throws Exception {
        jobsPage.applyButton.click();
        signDialog.closeButton.click();

    }

    @Given("I open {}")
    public void iOpenWwwLabcorpCom(String url) throws Exception {
        Session.getInstance().getDriver().get(GetProperties.getInstance().getUrlWeb());
        acceptCookiesDialog.acceptCookiesButton.click();
    }

    @When("I search the {string}")
    public void iSearchThe(String word) throws Exception {
        mainPage.careersLink.click();
        this.moveToTab("Working at Labcorp | Jobs and Careers at Labcorp");
        acceptCookiesDialog.acceptButton.click();
        jobsPage.keywordSearchInput.setText(word);
        jobsPage.locationInput.clear();
        jobsPage.submitButton.click();

    }

    @Then("verify the job information")
    public void verifyTheJobInformation(Map<String,String>expectedValues) throws Exception {
        String message="";
        boolean areEqual=true;
        for ( String label:expectedValues.keySet()) {
            String actualResult=careerSitePage.labelsMap.get(label).getText();
            String expectedResult=expectedValues.get(label);
            if (!actualResult.contains(expectedResult)) {
                areEqual=false;
                message= message+ "ERROR! label ["+label+"]  expected result: ["+expectedResult+"] vs actual result ["+actualResult+"]\n";
            }
        }

        Assertions.assertTrue(areEqual,message);
    }

    @And("return to the job search")
    public void returnToTheJobSearch() throws Exception {
       careerSitePage.returnToJobSearch.click();
    }

    @And("click on result {string}")
    public void clickOnResult(String value) throws Exception {
        jobsPage.clickOnResultSearch(value);
    }

    @And("verify the responsibilities")
    public void verifyTheResponsibilities(Map<Integer,String> expectedValues) throws Exception {
        String message="";
        boolean areEqual=true;
        for ( int label:expectedValues.keySet()) {
            String actualResult=jobsPage.getResponsibleList(label);
            String expectedResult=expectedValues.get(label);
            if (!actualResult.equals(expectedResult)) {
                areEqual=false;
                message= message+ "ERROR! responsibilities number ["+label+"] has actual result: ["+actualResult+"] vs expected result: ["+expectedResult+"]\n";
            }
        }

        Assertions.assertTrue(areEqual,message);
    }

    @And("verify the job responsibilities")
    public void verifyTheJobResponsibilities(Map<Integer,String> expectedValues) throws Exception {
        String message="";
        boolean areEqual=true;
        for ( int label:expectedValues.keySet()) {
            String actualResult=careerSitePage.getResponsibleList(label);
            String expectedResult=expectedValues.get(label);
            if (!actualResult.equals(expectedResult)) {
                areEqual=false;
                message= message+ "ERROR! responsibilities number ["+label+"] has actual result: ["+actualResult+"] vs expected result: ["+expectedResult+"]\n";
            }
        }

        Assertions.assertTrue(areEqual,message);
    }

    private void moveToTab(String tabTitle) throws Exception {
        List<String> tabList=new ArrayList<String>(Session.getInstance().getDriver().getWindowHandles());
        Map<String, Integer> titlePositionTab= new HashMap<>();
        System.out.println("NUMBER OF TABS "+tabList.size());
        for (int i=0;i<tabList.size();i++) {
            Session.getInstance().getDriver().switchTo().window(tabList.get(i));
            titlePositionTab.put(Session.getInstance().getDriver().getTitle(),i);
            System.out.println("TABS "+Session.getInstance().getDriver().getTitle());
        }
        if (!titlePositionTab.containsKey(tabTitle))
            throw new Exception("The tab name "+tabTitle+" does not exist! review if the tab name");

        Session.getInstance().getDriver().switchTo().window(tabList.get(titlePositionTab.get(tabTitle)));
        System.out.println("Current Tab "+Session.getInstance().getDriver().getTitle());

    }
}
