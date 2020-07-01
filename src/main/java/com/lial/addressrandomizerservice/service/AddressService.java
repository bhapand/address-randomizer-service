package com.lial.addressrandomizerservice.service;

import com.github.javafaker.Faker;
import com.lial.addressrandomizerservice.model.Address;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Manages the business service to randomize Address data for the countries US, Canada, Mexico and Netherlands
 */
@Service
public class AddressService {

    /**
     * Gets the locale address randomly selecting one of the countries from the immutable list of US, Canada, Mexico and Netherlands
     * using their locale display names
     * @return Address generated
     */
    public Address getRandomLocaleAddress() {

        List<String> locales = Arrays.asList("en-US", "en-CA", "es-MX", "nl-NL");
        Random random = new Random();
        String locale = locales.get(random.nextInt(locales.size()));
        return getAddress(locale);
    }

    /**
     * Logic to randomize and generate test address data for a given locale
     *
     * @param locale
     * @return Address generated
     */
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

    /**
     * As required by the service maps a locale's coutry name and 3 letter iso code to be used by calling methods
     *
     * @param country
     * @return Map of country and countryCodes as Key for respective locales
     */
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
