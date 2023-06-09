package com.property.Property;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@CrossOrigin
@RestController
@RequestMapping("/property")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    // get all property
    @GetMapping()
    public ResponseEntity<?> get_all_property() {

        List<Property> all_property = propertyService.get_all_property();

        if (all_property.isEmpty()) {
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("code", NOT_FOUND.value());
            responseBody.put("message", "There are no property records");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
        }

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("code", OK.value());
        responseBody.put("data", all_property);
        return ResponseEntity.ok(responseBody);
    }

    // get property by propertyID
    @GetMapping("/{propertyID}")
    public ResponseEntity<Map<String, Object>> get_property_by_propertyID(@PathVariable("propertyID") Integer propertyID) {

        Optional property = propertyService.get_property_by_propertyID(propertyID);

        if (property.isEmpty()) {
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("code", NOT_FOUND.value());
            responseBody.put("message", "PropertyID " + propertyID + " does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
        }

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("code", OK.value());
        responseBody.put("data", property.get());
        return ResponseEntity.ok(responseBody);
    }


    // get property(ies) by country & guests
    @GetMapping("/search")
    public ResponseEntity<?> find_property_by_country_and_guests(@RequestParam String country, @RequestParam Integer guests){

        List<Property> property = propertyService.find_property_by_country_and_guests(country, guests);

        if (property.isEmpty()) {
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("code", NOT_FOUND.value());
            responseBody.put("data", property);
            responseBody.put("message", "No available properties");
            return ResponseEntity.status(NOT_FOUND).body(responseBody);
        }

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("code", OK.value());
        responseBody.put("data", property);

        return ResponseEntity.ok(responseBody);

    }

    @GetMapping("/get_unique_countries")
    public ResponseEntity<?> find_unique_countries(){

        List<String> unique_countries = propertyService.find_unique_countries();

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("code", OK.value());
        responseBody.put("data", unique_countries);

        return ResponseEntity.ok(responseBody);

    }


}