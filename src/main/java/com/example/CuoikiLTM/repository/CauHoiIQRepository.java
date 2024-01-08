package com.example.CuoikiLTM.repository;

import com.example.CuoikiLTM.model.CauHoi;
import com.example.CuoikiLTM.model.CauHoiIQ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CauHoiIQRepository extends JpaRepository<CauHoiIQ,Integer> {
    @Query(value = "SELECT * FROM cau_hoiiq ORDER BY RAND() LIMIT 1", nativeQuery = true)
    CauHoiIQ findRandomCauHoi();
}
