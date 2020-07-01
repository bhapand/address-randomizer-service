package com.lial.addressrandomizerservice.controller;

import com.lial.addressrandomizerservice.model.Address;
import com.lial.addressrandomizerservice.service.AddressService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AddressController.class)
public class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressService addressService;


    @Test
    public void verifyAddress() throws Exception {
        Address address = createMockAddress();
        Mockito.when(
                addressService.getRandomLocaleAddress()).thenReturn(address);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/randomizer/address/"));
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.house", Matchers.is("8356")))
                .andExpect(jsonPath("$.street", Matchers.is("Adolph Ways")))
                .andExpect(jsonPath("$.postalCode", Matchers.is("44418")))
                .andExpect(jsonPath("$.city", Matchers.is("New Kenda")))
                .andExpect(jsonPath("$.county", Matchers.is("Rogahnside")))
                .andExpect(jsonPath("$.state", Matchers.is("New Jersey")))
                .andExpect(jsonPath("$.stateCode", Matchers.is("NJ")))
                .andExpect(jsonPath("$.country", Matchers.is("United States")))
                .andExpect(jsonPath("$.countryCode", Matchers.is("USA")));

        String response = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("Response -->" + response);

    }

    private Address createMockAddress() {
        Address address = new Address();
        address.setHouse("8356");
        address.setStreet("Adolph Ways");
        address.setPostalCode("44418");
        address.setCity("New Kenda");
        address.setCounty("Rogahnside");
        address.setState("New Jersey");
        address.setStateCode("NJ");
        address.setCountry("United States");
        address.setCountryCode("USA");
        return address;
    }
}