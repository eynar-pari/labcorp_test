package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetProperties {
    private static GetProperties getProperties = null;
    private String urlWeb;
    private String browser;
    private String urlApi;
    private GetProperties () {
        Properties properties= new Properties();
        String propFileName=System.getProperty("propertyFile")==null?"environment.properties": System.getProperty("propertyFile");
        InputStream inputStream= getClass().getClassLoader().getResourceAsStream(propFileName);
        if (inputStream!=null){
            try {
                properties.load(inputStream);
                urlWeb=properties.getProperty("urlWeb");
                browser= properties.getProperty("browser");
                urlApi=properties.getProperty("urlAPI");
                System.out.println("urlWeb "+urlWeb);
                System.out.println("browser "+browser);
                System.out.println("urlApi "+urlApi);
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static GetProperties getInstance() {
        if (getProperties == null)
            getProperties= new GetProperties();
        return getProperties;
    }

    public String getUrlWeb() {
        return urlWeb;
    }
    public String getBrowser() {
        return browser;
    }

    public String getUrlApi() {
        return urlApi;
    }
}
