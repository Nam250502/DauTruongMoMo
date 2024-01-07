package com.example.CuoikiLTM.service;

import com.example.CuoikiLTM.model.CauHoi;
import com.example.CuoikiLTM.repository.CauHoiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class CauHoiService {
    @Autowired
    private CauHoiRepository cauHoiRepository;
    public void saveCauhoi(CauHoi cauHoi){
        cauHoiRepository.save(cauHoi);
    }
    public void removeCauhoi(CauHoi cauHoi){
        cauHoiRepository.delete(cauHoi);
    }
    public void removeCauhoiById(Integer id){
        cauHoiRepository.deleteById(id);
    }
    public List<CauHoi> getAll(){
        return cauHoiRepository.findAll();
    }
    public CauHoi layCauHoiNgauNhien() {
        return cauHoiRepository.findRandomCauHoi();
    }

}
