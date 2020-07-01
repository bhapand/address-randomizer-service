package com.lial.addressrandomizerservice.controller;

import com.lial.addressrandomizerservice.model.Address;
import com.lial.addressrandomizerservice.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController {

    @Autowired
    private AddressService service;

    /**
     * Implementation of the get mapping at endpoint /randomizer/address
     * @return
     */
    @GetMapping("/randomizer/address")
    public Address getAddress() {
        return service.getRandomLocaleAddress();
    }
}
