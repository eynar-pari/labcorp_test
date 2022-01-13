package utils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;

import java.util.Iterator;

public class JsonHelper {


    public static String jsonFromArray(String jsonArrayString,String property, String value){
        JSONArray jsonArray = new JSONArray(jsonArrayString);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());

            if (jsonObject.get(property).equals(value)){
               return jsonArray.get(i).toString()  ;
            }

        }
        return null;
    }

    public static void assertAreEqualJson(String expectedResult,String actualResult, String errorMessage){
        boolean areEqual = true;
        String compartionErrors="";
        JSONObject expectedJSON= new JSONObject(expectedResult);
        JSONObject actualJSON= new JSONObject(actualResult);
        Iterator<?> keyList= expectedJSON.keys();
        while (keyList.hasNext()){
            String key= (String) keyList.next();
            String expectedValue= String.valueOf(expectedJSON.get(key));
            String actualValue= String.valueOf(actualJSON.get(key));
            if (expectedJSON.has(key) && actualJSON.has(key)) {
                if (expectedValue.equals("IGNORE") || actualValue.equals("IGNORE")) {
                    System.out.println("WARN> the value was ignored [" + key + "]");
                } else if (!expectedValue.equals(actualValue)) {
                    areEqual = false;
                    compartionErrors=compartionErrors+"\nERROR> the attribute [" + key + "] --> expected: [" + expectedValue + "] VS actual: [" + actualValue+"]";

                }
            }else{
                areEqual = false;
                compartionErrors=compartionErrors+"\nERROR> the attribute [" + key + "] does not exist";
            }
        }
        Assertions.assertTrue(areEqual,errorMessage+compartionErrors);
    }

}
