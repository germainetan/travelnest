package com.owner.Owner;

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
@RequestMapping("/owner")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    // get all owner
    @GetMapping()
    public ResponseEntity<?> get_all_owners() {

        List<Owner> all_owners = ownerService.get_all_owners();

        if (all_owners.isEmpty()) {
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("code", NOT_FOUND.value());
            responseBody.put("data", "There are no owner records");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
        }

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("code", OK.value());
        responseBody.put("data", all_owners);
        return ResponseEntity.ok(responseBody);

    }

    // get owner by ownerID
    @GetMapping("/{ownerID}")
    public ResponseEntity<Map<String, Object>> get_owner_by_ownerID(@PathVariable("ownerID") Integer ownerID) {

        Optional owner = ownerService.get_owner_by_ownerID(ownerID);

        if (owner.isEmpty()) {
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("code", NOT_FOUND.value());
            responseBody.put("data", "OwnerID " + ownerID + " does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
        }

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("code", OK.value());
        responseBody.put("data", owner.get());
        return ResponseEntity.ok(responseBody);

    }
}