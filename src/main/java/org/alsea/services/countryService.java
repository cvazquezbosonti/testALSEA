package org.alsea.services;


import org.alsea.models.country;
import org.alsea.models.user;
import org.alsea.repos.countryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class countryService {

    @Autowired
    private countryRepo countryRepov;

    public List<country> getAllCountrys(){
        return countryRepov.findAll();
    }


}
