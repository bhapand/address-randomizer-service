package com.lial.addressrandomizerservice;

import com.lial.addressrandomizerservice.controller.AddressController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AddressController.class)
public class AddressControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressController addressController;


    @Test
    public void verifyAddress() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/randomizer/address")).andReturn();

        String response = result.getResponse().getContentAsString();
        System.out.println(result.getResponse());

    }
}
