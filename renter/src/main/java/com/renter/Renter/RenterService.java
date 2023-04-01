package com.renter.Renter;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RenterService {

    private final RenterRepository renterRepository;

    public RenterService(RenterRepository renterRepository) {
        this.renterRepository = renterRepository;
    }

    // get all renters
    public List<Renter> get_all_renters(){
        return renterRepository.findAll();
    }

    // get renter by renterID

    public Optional get_renter_by_renterID(Integer renterID) {
        return renterRepository.findById(renterID);
    }

}
