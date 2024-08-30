package com.example.weldingShop.repository;

import com.example.weldingShop.entity.Welding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface WeldingRepository extends JpaRepository <Welding, UUID> {

    @Query("SELECT s FROM Welding s WHERE s.name LIKE %:name%")
    List<Welding> findWeldsByName(@Param("name") String name);

}
