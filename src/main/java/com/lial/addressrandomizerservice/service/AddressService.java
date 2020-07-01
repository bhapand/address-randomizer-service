package com.lial.addressrandomizerservice.service;

import com.github.javafaker.Faker;
import com.lial.addressrandomizerservice.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AddressService {

    public Address getUSAddress() {

        List<String> locales = Arrays.asList("en-US", "en-CA", "es-MX", "nl-NL");
        Random random = new Random();
        String locale = locales.get(random.nextInt(locales.size()));
        return getAddress(locale);
    }

    private Address getAddress(String locale) {
        Faker randomizer = new Faker(new Locale(locale));
        Address address = new Address();
        String zipCode = randomizer.address().zipCode();
        String county = randomizer.address().city();

        HashMap<String, String> countryMap = getCountryMap(new Locale(locale).getDisplayName());

        String country = countryMap.get("country");
        String countryCode = countryMap.get("countryCode");
        address.setHouse(randomizer.address().buildingNumber());
        address.setStreet(randomizer.address().streetName());
        address.setPostalCode(zipCode);
        address.setCity(randomizer.address().cityName());
        address.setCounty(county);
        address.setState(randomizer.address().state());
        address.setStateCode(randomizer.address().stateAbbr());
        address.setCountry(country);
        address.setCountryCode(countryCode);
        return address;
    }

    private HashMap<String, String> getCountryMap(String country) {
        HashMap<String, String> map = new HashMap<>();
        switch (country) {
            case "en-us" :  {
                map.put("country", "United States");
                map.put("countryCode", "USA");
                break;
            }
            case "en-ca": {
                map.put("country", "Canada");
                map.put("countryCode", "CAN");
                break;
            }
            case "es-mx": {
                map.put("country", "Mexico");
                map.put("countryCode", "MEX");
                break;
            }
            case "nl-nl": {
                map.put("country", "Netherlands");
                map.put("countryCode", "NLD");
                break;
            }
            default: {
                map.put("country", "United States");
                map.put("countryCode", "USA");
            }
        }
        return map;
    }
}
