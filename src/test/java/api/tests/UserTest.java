package api.tests;

import api.endpoints.Routes;
import api.payload.user;
import api.payload.userUpdate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import jdbc.UserJdbc;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.UUID;

import static api.endpoints.userEndpoints.*;

public class UserTest {

    Faker faker;
    user userPayload;
    userUpdate userUpdatePayload;

    public Logger logger;
    @BeforeClass
    public void setupData() {
        faker = new Faker();
        userPayload = new user();
        userPayload.setFirstname(faker.name().firstName());
        userPayload.setLastname(faker.name().lastName());
        userPayload.setEmail(userPayload.getFirstname()+"@gmail.com");
        userPayload.setPassword("User@123");
        userPayload.setPaying_tax("true");
        userPayload.setTerms_condition_checked("true");
        userPayload.setProvider("internal");

        //log
        logger = LogManager.getLogger(this.getClass());
    }

    @Test(priority = 0)
    public void testCreateUser() {
        logger.info("******************** Member creation *********************");
        Response response = createUser(userPayload);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 201);
        UUID uuid = UserJdbc.getUserUUID(userPayload.getEmail());
        System.out.println("User uuid : " + uuid);
        logger.info("******************** Member is created *********************");
    }

    @Test(priority = 1)
    public void testUpdateUser() {
        logger.info("******************** Member Updation *********************");
        userUpdatePayload = new userUpdate();
        userUpdatePayload.setGender("F");
        userUpdatePayload.setCountry("IN");
        userUpdatePayload.setState("KL");
        userUpdatePayload.setAddress1("ABC");
        userUpdatePayload.setAddress2("No:16 garden villa");
        userUpdatePayload.setCity("TVM");
        userUpdatePayload.setZip("655555");
        userUpdatePayload.setPhone("+91-5877777311");

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload = null;
        try {
            jsonPayload = objectMapper.writeValueAsString(userUpdatePayload);
            System.out.println("" +jsonPayload);
        } catch (Exception e) {
            throw new RuntimeException("Error converting userPayload to JSON", e);
        }
        String email = userPayload.getEmail();
        System.out.println("check email: "+email);
        UUID uuid = UserJdbc.getUserUUID(email);
        System.out.println("check uuid: "+uuid);
        Response response = updateUser(String.valueOf(uuid),userUpdatePayload);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        logger.info("******************** Member is updated *********************");
    }

    @Test(priority = 2)
    public void testGetUser() {
        logger.info("******************** Member info reading *********************");
        String email = userPayload.getEmail();
        UUID uuid = UserJdbc.getUserUUID(email);
        Response response = getUser(String.valueOf(uuid));
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        logger.info("******************** Member info is displayed *********************");
    }

    @Test(priority = 3)
    public void testDBConnection() {
        String email = userPayload.getEmail();
        UUID uuid = UserJdbc.getUserUUID(email);
    }

}
