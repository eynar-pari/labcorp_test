package control;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import session.Session;

public class ControlBase {
    protected By locator;
    protected WebElement control;

    public ControlBase (By locator){
        this.locator=locator;
    }

    public void findControl() throws Exception {
       try{
           this.control=Session.getInstance().getDriver().findElement(this.locator);
           WebDriverWait explicitWait = new WebDriverWait(Session.getInstance().getDriver(),30);
           explicitWait.until(ExpectedConditions.presenceOfElementLocated(this.locator));
          // System.out.println(" Find Element > "+locator+ " amount "+Session.getInstance().getDriver().findElements(this.locator).size());
       }catch (NoSuchElementException exception){
           throw new Exception("ERROR> the locator : ["+this.locator+"] was not found, Error message detail: \n"+exception.getMessage());
       }catch (Exception e){
           throw new Exception("ERROR> the locator : ["+this.locator+"] can not be found, Error message detail:\n "+e.getMessage());
       }
    }


    public void click() throws Exception {
        this.findControl();
        this.control.click();
    }

    public boolean controlIsDisplayed(){
        try{
            this.findControl();
            return this.control.isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    public String getText() throws Exception {
        Thread.sleep(1000);
        this.findControl();
        return this.control.getText();
    }

    public String getTextAttribute(String attribute) throws Exception {
        this.findControl();
        return this.control.getAttribute(attribute);
    }

}
