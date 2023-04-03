package com.renter.Renter;

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
@RequestMapping("/renter")
public class RenterController {

    private final RenterService renterService;

    public RenterController(RenterService renterService) {
        this.renterService = renterService;
    }

    // get all renter
    @GetMapping()
    public ResponseEntity<?> get_all_renters() {

        List<Renter> all_renters = renterService.get_all_renters();

        if (all_renters.isEmpty()) {
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("code", NOT_FOUND.value());
            responseBody.put("message", "There are no renter records");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
        }

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("code", OK.value());
        responseBody.put("data", all_renters);
        return ResponseEntity.ok(responseBody);
    }

    // get renter by renterID
    @GetMapping("/{renterID}")
    public ResponseEntity<Map<String, Object>> get_renter_by_renterID(@PathVariable("renterID") Integer renterID) {

        Optional renter = renterService.get_renter_by_renterID(renterID);

        if (renter.isEmpty()) {
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("code", NOT_FOUND.value());
            responseBody.put("message", "RenterID " + renterID + " does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
        }

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("code", OK.value());
        responseBody.put("data", renter.get());
        return ResponseEntity.ok(responseBody);

    }
}