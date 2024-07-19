package org.alsea.repos;

import org.alsea.models.country;
import org.springframework.data.jpa.repository.JpaRepository;


public interface countryRepo extends JpaRepository<country,Long> {

}
