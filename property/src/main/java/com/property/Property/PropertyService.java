package com.property.Property;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    // get all properties
    public List<Property> get_all_property(){
        return propertyRepository.findAll();
    }

    // get property by propertyID
    public Optional get_property_by_propertyID(Integer propertyID) {
        return propertyRepository.findById(propertyID);
    }

    // get property(ies) by country & guests
    public List<Property> find_property_by_country_and_guests(String country, Integer guests){
        return propertyRepository.find_property_by_country_and_guests(country, guests);
    }

    // get all unique countries
    public List<String> find_unique_countries(){
        return propertyRepository.find_unique_countries();
    }
}
