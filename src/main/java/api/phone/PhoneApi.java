package api.phone;

import api.ApiBase;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import schemas.contact.ContactDto;
import schemas.phone.PhoneDto;

public class PhoneApi extends ApiBase {
    Response response;
    PhoneDto dto;
    Faker faker = new Faker();

    public PhoneDto randomDataBodyForCreatePhone() {
        dto = new PhoneDto();
//        dto.setCountryCode(faker.internet().uuid());
//        dto.setPhoneNumber(faker.internet().uuid());
        dto.setCountryCode("+48");
        dto.setPhoneNumber("10000");
        dto.setContactId(4819);
        return dto;
    }

    public PhoneDto randomDataBodyForEditPhone(Integer phoneId, Integer contactId) {
        dto = new PhoneDto();
        dto.setId(phoneId);
        dto.setCountryCode("+48");
        dto.setPhoneNumber("100000");
        dto.setContactId(contactId);
        return dto;
    }

    public Response createPhone(Integer code) {
        String endpoint = "/api/phone";
        response = postRequest(endpoint, code, randomDataBodyForCreatePhone());
        return response;
    }

    public void editExistingPhone(Integer code, Integer phoneId) {
        String endpoint = "/api/phone";
        putRequest(endpoint, code, randomDataBodyForEditPhone(phoneId, 4819));
    }

    public void deleteExistingPhone(Integer code, int phoneId) {
        String endpoint = "/api/phone/{id}";
        deleteRequest(endpoint, code, phoneId);
    }

    public Response getAllPhones(Integer code) {
        String endpoint = "/api/phone/{contactId}/all";
        response = getRequestWithParam(endpoint, code, "contactId", 4819);
        return response;
    }

    public Response getPhone(Integer code, int phoneId) {
        String endpoint = "/api/phone/{id}";
        response = getRequestWithParam(endpoint, code, "id", phoneId);
        return response;
    }

}
