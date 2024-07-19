package org.alsea.repos;

import org.alsea.models.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface users  extends JpaRepository<user,Long> {
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM user u WHERE u.mail = :mail")
    boolean existsByMail(@Param("mail") String mail);

}
