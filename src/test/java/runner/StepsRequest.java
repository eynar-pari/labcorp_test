package runner;

import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import factoryRequest.FactoryRequest;
import factoryRequest.RequestInformation;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import utils.GetProperties;
import utils.JsonHelper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class StepsRequest {

    RequestInformation requestInformation = new RequestInformation();
    Map<String,String> variablesRunTime = new HashMap<>();
    Response response;

    @Given("I have access to mockApi")
    public void iHaveAccessToMockApi() {

    }

    @When("I send a {} request to {string} with body")
    public void iSendAPOSTRequestToWithBody(String method, String url, String body) {
        requestInformation.setUrl(GetProperties.getInstance().getUrlApi() +url)
                          .setBody(this.replaceValues(body));
        response=FactoryRequest.make(method).send(requestInformation);

    }

    @Then("I expected the response code {int}")
    public void iExpectedTheResponseCode(int responseCode) {
        response.then().statusCode(responseCode);
    }

    @And("I expected the schema validation with {string}")
    public void iExpectedTheSchemaValidationWith(String jsonSchemaPath ) {
        JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.newBuilder()
                .setValidationConfiguration(ValidationConfiguration.newBuilder()
                        .setDefaultVersion(SchemaVersion.DRAFTV4).freeze()).freeze();
        response.then()
                .body(matchesJsonSchemaInClasspath(jsonSchemaPath).using(jsonSchemaFactory));
    }



    @And("I get the jsonObject where {string} is {string} in {}")
    public void iGetTheJsonObjectWhereIsInJSON_OBJECT(String property, String value,String variable) {
        String json= JsonHelper.jsonFromArray(response.getBody().asString(),property,value);
        variablesRunTime.put(variable,json);
    }

    private String replaceValues(String value){
        for (String key:variablesRunTime.keySet()
             ) {
            value=value.replace(key,variablesRunTime.get(key));
        }
        return value;
    }

    @And("I expected the {} should be equal to")
    public void iExpectedTheJSON_OBJECTShouldBeEqualTo(String jsonActual, String jsonExpected) {
        JsonHelper.assertAreEqualJson(this.replaceValues(jsonExpected),this.replaceValues(jsonActual),"the expected json are not equal");

    }

    @And("I create a random value on {}")
    public void iCreateARandomValueOnRANDOM(String variable) {
        variablesRunTime.put(variable,new Date().getTime()+"");
    }
}
