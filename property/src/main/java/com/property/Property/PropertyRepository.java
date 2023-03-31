package com.property.Property;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Integer> {

    @Query("SELECT p FROM Property p WHERE p.country=:country AND p.guests=:guests AND p.price <= :price")
    List<Property> find_property_by_country_and_guests(@Param("country") String country,
                                                       @Param("guests") Integer guests,
                                                       @Param("price") Integer price);

}
