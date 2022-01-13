package factoryBrowser;

public class FactoryBrowser {

    private static final String CHROME="chrome";
    private static final String HEADLESS="headless";

     public static IBrowser make (String typeBrowser){
         IBrowser browser;
         switch (typeBrowser.toLowerCase()){
             case CHROME:
                 browser= new Chrome();
                 break;
             case HEADLESS:
                 browser= new ChromeHeadless();
                 break;
             default:
                 browser= new Chrome();
                 break;
         }
         return browser;
     }
}
