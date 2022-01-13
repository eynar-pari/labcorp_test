package pages;

import control.Button;
import org.openqa.selenium.By;

public class AcceptCookiesDialog {

   public Button acceptCookiesButton= new Button(By.xpath("//button[@title='Accept Cookies Button']"));
   public Button acceptButton= new Button(By.xpath("//button[@data-custom-label='GDPR Accept Button']"));


}
