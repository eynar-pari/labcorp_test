package control;

import org.openqa.selenium.By;

public class Input extends ControlBase {

    public Input(By locator) {
        super(locator);
    }

    public void setText(String value) throws Exception {
        this.findControl();
        this.control.clear();
        this.control.sendKeys(value);
    }

    public void clear() throws Exception {
        this.findControl();
        this.control.clear();
    }
}
