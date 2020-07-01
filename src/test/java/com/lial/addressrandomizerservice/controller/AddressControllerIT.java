package com.lial.addressrandomizerservice.controller;

import com.lial.addressrandomizerservice.AddressRandomizerServiceApplication;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.RegularExpressionValueMatcher;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AddressRandomizerServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddressControllerIT {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void testStatusCode() throws JSONException {
        ResponseEntity<String> response = getResponse();
        Assert.assertTrue(response.getStatusCode().is2xxSuccessful());
    }


    @Test
    public void testAddressService() throws JSONException {

        ResponseEntity<String> response = getResponse();

        String expected = "{\"house\":\"[^\\s]+\",\"street\":\"(.*)+\",\"postalCode\":\"[^\\s]+\",\"city\":\"(.*)+\",\"county\":\"(.*)+\",\"state\":\"(.*)+\",\"stateCode\":\"(.*)+\",\"country\":\"^(United States|Mexico|Netherlands|Canada)$\",\"countryCode\":\"^(USA|MEX|NLD|CAN)$\"}";

        System.out.println(response.getBody());
        JSONAssert.assertEquals(expected, response.getBody(),
                new CustomComparator(
                        JSONCompareMode.LENIENT,
                        new Customization("***", new RegularExpressionValueMatcher<>())
                )
        );
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    private ResponseEntity<String> getResponse() {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/randomizer/address/"),
                HttpMethod.GET, entity, String.class);
        return response;
    }
}
