package com.property.Property;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;

@CrossOrigin
@RestController
@RequestMapping("/property")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    // get all owner
    @GetMapping()
    public ResponseEntity<List<Property>> get_all_property() {
        return new ResponseEntity<>(propertyService.get_all_property(), OK);
    }

    // get owner by ownerID
    @GetMapping("/{propertyID}")
    public Optional get_property_by_propertyID(@PathVariable("propertyID") Integer propertyID) {
        return propertyService.get_property_by_propertyID(propertyID);
    }

    // get propert(ies) by country & guests
    @GetMapping("/search")
    public List<Property> find_property_by_country_and_guests(@RequestParam String country,
                                                              @RequestParam Integer guests,
                                                              @RequestParam Integer price){
        return propertyService.find_property_by_country_and_guests(country, guests, price);
    }
}