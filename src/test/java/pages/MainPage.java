package pages;

import control.Link;
import org.openqa.selenium.By;

public class MainPage {
    public Link careersLink = new Link(By.xpath("//ul[@class='menu vertical nested']//a[text()='Careers']"));
}
