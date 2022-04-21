import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.http.client.methods.RequestBuilder;
import org.testng.annotations.Test;

import java.io.File;

public class APITests {

    @Test
    public void getPetByStatus(){

        Response response = RestAssured.get("https://petstore.swagger.io/v2/pet/findByStatus?status=available");
        System.out.println("STATUS CODE: "+response.statusCode());
        System.out.println("RESPONSE: "+response.body().prettyPrint());

    }

    @Test
    public void getPetByStatusBDD(){

        Response response =
                 RestAssured.given()
                .baseUri("https://petstore.swagger.io/v2")
                .when()
                    .get("/pet/findByStatus?status=available")
                .then()
                    .statusCode(200)
                    .body("status", hasItem("available"))
                 .extract()
                     .response();

        System.out.println("RESPONSE FOR GET: "+response.body().prettyPrint());

    }

    @Test
    public void getPetByStatusBDDFail(){

        Response response =
                RestAssured.given()
                        .baseUri("https://petstore.swagger.io/v2")
                    .when()
                        .get("/pet/findByStatus?status=available")
                    .then()
                        .statusCode(200)
//                        added incorrect string to cause failure
                        .body("status", hasItem("sold"))
                    .extract()
                        .response();

        System.out.println("RESPONSE FOR GET: "+response.body().prettyPrint());

    }

    @Test
    public void createNewPet(){
        File jsondata = new File("src/test/resources/pet.json");

        Response response =
                RestAssured.given()
                        .baseUri("https://petstore.swagger.io/v2")
                        .contentType(ContentType.JSON)
                        .body(jsondata)
                    .when()
                        .post("/pet")
                    .then()
                        .statusCode(200)
                        .contentType(ContentType.JSON)
                        .body("name", equalTo("doggie"))
                    .extract()
                        .response();

        String header = response.getContentType();
        System.out.println("HEADER: "+header);
//        System.out.println("RESPONSE FOR POST: "+response.body().prettyPrint());
    }

    @Test
    public void createNewPetFail(){
        File jsondata = new File("src/test/resources/pet.json");

        Response response =
                RestAssured.given()
                        .baseUri("https://petstore.swagger.io/v2")
                        .contentType(ContentType.JSON)
                        .body(jsondata)
                    .when()
//                        added incorrect endpoint to cause failure
                        .post("/pets")
                    .then()
                        .statusCode(200)
                        .contentType(ContentType.JSON)
                        .body("name", equalTo("doggie"))
                    .extract()
                        .response();

        String header = response.getContentType();
        System.out.println("HEADER: "+header);


    }

    @Test
    public void editPet(){
        File jsondata = new File("src/test/resources/petPUT.json");

        Response response =
                RestAssured.given()
                        .baseUri("https://petstore.swagger.io/v2")
                        .contentType(ContentType.JSON)
                        .body(jsondata)
                    .when()
                        .put("/pet")
                    .then()
                        .statusCode(200)
                        .contentType(ContentType.JSON)
                        .body("category.name", equalTo("stringbow"))
                    .extract()
                        .response();

        System.out.println("RESPONSE FOR PUT: "+response.body().prettyPrint());
    }

    @Test
    public void editPetFail(){
        File jsondata = new File("src/test/resources/petPUT.json");

        Response response =
                RestAssured.given()
                        .baseUri("https://petstore.swagger.io/v2")
                        .contentType(ContentType.JSON)
                        .body(jsondata)
                    .when()
                        .put("/pet")
                    .then()
                        .statusCode(204)
                        .contentType(ContentType.JSON)
                        .body("category.name", equalTo("stringbow"))
                    .extract()
                        .response();

        System.out.println("RESPONSE FOR PUT: "+response.body().prettyPrint());
    }
}
