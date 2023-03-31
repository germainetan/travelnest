package com.owner.Owner;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<List<Owner>> get_all_owners() {
        return new ResponseEntity<>(ownerService.get_all_owners(), OK);
    }

    // get owner by ownerID
    @GetMapping("/{ownerID}")
    public Optional get_owner_by_ownerID(@PathVariable("ownerID") Integer ownerID) {
        return ownerService.get_owner_by_ownerID(ownerID);
    }
}