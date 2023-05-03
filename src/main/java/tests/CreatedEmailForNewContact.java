package tests;

import api.contact.ContactApi;
import api.email.EmailApi;
import api.tests.CommonContactTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class CreatedEmailForNewContact {
    ContactApi contactApi = new ContactApi();
    EmailApi emailApi = new EmailApi();

    @Test
    public void createdEmailForNewContact(){
        Response newContactResponse =contactApi.createContact(201);
        Integer contactId =newContactResponse.jsonPath().getInt("id");
        emailApi.createEmail(201, contactId);
        Response newEmailResponse =emailApi.getAllEmails(200,contactId);
        int emailId =newEmailResponse.jsonPath().getInt('[0.id]');





        emailsApi.deleteExistingEmail(200, emailId);
        Response errorMessage = emailsApi.getEmail(500, emailId);
        Assert.assertEquals(errorMessage.jsonPath().getString("message"), "Error! This email doesn't exist in our DB");

        contactApi.deleteExistingContact(200, contactId);
        Response actualDeletedResponse = contactApi.getContact(500, contactId);
        Assert.assertEquals(actualDeletedResponse.jsonPath().getString("message"), "Error! This contact doesn't exist in our DB");

    }


    }

    }

}
