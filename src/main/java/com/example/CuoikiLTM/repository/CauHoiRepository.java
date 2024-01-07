package com.example.CuoikiLTM.repository;

import com.example.CuoikiLTM.model.CauHoi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CauHoiRepository extends JpaRepository<CauHoi,Integer> {
    @Query(value = "SELECT * FROM cau_hoi ORDER BY RAND() LIMIT 1", nativeQuery = true)
    CauHoi findRandomCauHoi();
}
