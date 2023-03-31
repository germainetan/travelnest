package com.renter.Renter;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class RenterService {

    private final RenterRepository renterRepository;

    public RenterService(RenterRepository renterRepository) {
        this.renterRepository = renterRepository;
    }

    // get all owners
    public List<Renter> get_all_renters(){
        return renterRepository.findAll();
    }

    // get owner by ownerID

    public Optional get_renter_by_renterID(Integer renterID) {

        boolean exits = renterRepository.existsById(renterID);

        if (!exits) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Renter with renterID " + renterID + "does not exist");
        }

        return renterRepository.findById(renterID);
    }
}
