package com.owner.Owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    // get all owners
    public List<Owner> get_all_owners(){
        return ownerRepository.findAll();
    }

    // get owner by ownerID

    public Optional get_owner_by_ownerID(Integer ownerID) {

        boolean exits = ownerRepository.existsById(ownerID);

        if (!exits) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Owner with ownerID " + ownerID + "does not exist");
        }

        return ownerRepository.findById(ownerID);
    }
}
