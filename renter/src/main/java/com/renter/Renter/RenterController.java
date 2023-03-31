package com.renter.Renter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<List<Renter>> get_all_renters() {
        return new ResponseEntity<>(renterService.get_all_renters(), OK);
    }

    // get renter by renterID
    @GetMapping("/{renterID}")
    public Optional get_owner_by_renterID(@PathVariable("renterID") Integer renterID) {
        return renterService.get_renter_by_renterID(renterID);
    }
}