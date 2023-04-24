package tests;

import api.phone.PhoneApi;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CommonPhoneTest extends PhoneApi {
    @Test
    public void createEditDeleteNewPhone() {
        Response actualResponse = createPhone(201);
        Response response = getAllPhones(200);
        int phoneId = response.jsonPath().getInt("[0].id");
        String phoneNumber = response.jsonPath().getString("[0].phoneNumber");
        System.out.println(phoneNumber );
        Assert.assertEquals(phoneNumber, randomDataBodyForCreatePhone().getPhoneNumber(), "Created phone is not equals");

        editExistingPhone(200, phoneId);
        response = getAllPhones(200);
        phoneId = response.jsonPath().getInt("[0].id");
        phoneNumber = response.jsonPath().getString("[0].phoneNumber");
        System.out.println(phoneNumber );
        Assert.assertEquals(phoneNumber, randomDataBodyForEditPhone(phoneId, 4819).getPhoneNumber(), "Edited phone is not equals");

        deleteExistingPhone(200, phoneId);
        Response errorMessage = getPhone(500, phoneId);
        Assert.assertEquals(errorMessage.jsonPath().getString("message"), "Error! This phone number doesn't exist in our DB");
    }

}
