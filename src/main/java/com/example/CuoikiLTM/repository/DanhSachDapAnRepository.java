package com.example.CuoikiLTM.repository;

import com.example.CuoikiLTM.model.DapAn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DanhSachDapAnRepository extends JpaRepository<DapAn,Integer> {
}
