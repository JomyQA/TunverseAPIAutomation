package api.tests;

import api.payload.user;
import api.utilities.dataProviders;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import jdbc.UserJdbc;
import lombok.ToString;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.UUID;

import static api.endpoints.userEndpoints.createUser;
import static api.endpoints.userEndpoints.getUser;

public class DDUserTest {

    //@Test(priority = 0, dataProvider = "Data", dataProviderClass = dataProviders.class)
//    @Test(dataProvider = "Data")
//    public void createMember(String firstname, String lastname, String email, String password, boolean terms_condition_checked, boolean paying_tax, String provider) {
//        user userPayload = new user();
//        userPayload.setFirstname(firstname);
//        userPayload.setLastname(lastname);
//        userPayload.setEmail(email);
//        userPayload.setPassword(password);
////        userPayload.setTerms_condition_checked(terms_condition_checked);
////        userPayload.setPaying_tax(paying_tax);
//        userPayload.setProvider(provider);
//        Response response = createUser(userPayload);
//        int statusCode = response.getStatusCode();
//        System.out.println("Check status code: " + statusCode);
//    }

    @Test(enabled = false, dataProvider = "DataOne", dataProviderClass = dataProviders.class)

    public void createNewMember(String firstname, String lastname, String email, String password, String terms_condition_checked, String paying_tax, String provider ) {
        user userPayload = new user();
        userPayload.setFirstname(firstname);
        userPayload.setLastname(lastname);
        userPayload.setEmail(email);
        userPayload.setPassword(password);
        userPayload.setTerms_condition_checked(terms_condition_checked);
        userPayload.setPaying_tax(paying_tax);
        userPayload.setProvider(provider);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload = null;
        try {
            jsonPayload = objectMapper.writeValueAsString(userPayload);
            System.out.println("check payload" + jsonPayload);
        } catch (Exception e) {
            throw new RuntimeException("Error converting userPayload to JSON", e);
        }
        Response response = createUser(userPayload);
        int statusCode = response.getStatusCode();
        System.out.println("Check status code: " + statusCode);
        UUID uuid = UserJdbc.getUserUUID(email);
        System.out.println("User uuid : " + uuid);
    }

//    @Test(dataProvider = "Member", dataProviderClass = dataProviders.class)
//    public void getMember(String email) {
//        user userPayload = new user();
//        userPayload.setEmail(email);
//        UUID uuid = UserJdbc.getUserUUID(email);
//        Response response = getUser(String.valueOf(uuid));
//        int statusCode = response.getStatusCode();
//        Assert.assertEquals(statusCode, 200);
//    }
}
