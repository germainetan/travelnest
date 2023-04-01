package com.owner.Owner;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;

    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    // get all owners
    public List<Owner> get_all_owners(){
        return ownerRepository.findAll();
    }

    // get owner by ownerID

    public Optional get_owner_by_ownerID(Integer ownerID) {
        return ownerRepository.findById(ownerID);
    }

}
