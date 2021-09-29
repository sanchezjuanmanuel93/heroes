package com.hiberus.heroes.repository;

import com.hiberus.heroes.model.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface HeroRepository extends JpaRepository<Hero, Long> {

    @Query("SELECT s FROM Hero s WHERE UPPER(s.name) LIKE CONCAT('%', UPPER(:name),'%')")
    Collection<Hero> findByName(@Param("name") String name);
}
