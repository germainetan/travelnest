package com.property.Property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    // get all properties
    public List<Property> get_all_property(){
        return propertyRepository.findAll();
    }

    // get property by propertyID

    public Optional get_property_by_propertyID(Integer propertyID) {

        boolean exits = propertyRepository.existsById(propertyID);

        if (!exits) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Property with propertyID " + propertyID + " does not exist");
        }

        return propertyRepository.findById(propertyID);
    }

    // get propert(ies) by country & guests

    public List<Property> find_property_by_country_and_guests(String country, Integer guests){
        return propertyRepository.find_property_by_country_and_guests(country, guests);
    }
}
