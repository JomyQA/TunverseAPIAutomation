package api.endpoints;

import api.payload.user;
import api.payload.userUpdate;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.ResourceBundle;


public class userEndpoints {

    public static ResourceBundle getUrl() {
        ResourceBundle routes = ResourceBundle.getBundle("routes");
        return routes;
    }

    public static Response createUser(user payload) {
        String create_url = getUrl().getString("create_user");
        Response response = RestAssured
                .given()
                .header("partner_id","614608f2-6538-4733-aded-96f902007254")
                .body(payload)
                .when()
                .post(create_url);
        response.then().log().all();
        return response;
    }

    public static Response getUser(String uuid) {
        String get_url = getUrl().getString("get_user");
        Response response = RestAssured
                .given()
                .header("partner_id","614608f2-6538-4733-aded-96f902007254")
                .pathParam("uuid" , uuid)
                .get(get_url);
        response.then().log().all();
        return response;

    }

    public static Response updateUser(String uuid , userUpdate payload) {
        String update_url = getUrl().getString("update_user");
        Response response = RestAssured
                .given()
                .pathParam("uuid",uuid)
                .body(payload)
                .patch(update_url);
        response.then().log().all();
        return response;

    }
}
