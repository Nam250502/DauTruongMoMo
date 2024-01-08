package com.example.CuoikiLTM.service;

import com.example.CuoikiLTM.model.CauHoi;
import com.example.CuoikiLTM.model.CauHoiIQ;
import com.example.CuoikiLTM.repository.CauHoiIQRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CauHoiIQService {
    @Autowired
    private CauHoiIQRepository cauHoiRepository;
    public void saveCauhoi(CauHoiIQ cauHoi){
        cauHoiRepository.save(cauHoi);
    }
    public void removeCauhoi(CauHoiIQ cauHoi){
        cauHoiRepository.delete(cauHoi);
    }
    public void removeCauhoiById(Integer id){
        cauHoiRepository.deleteById(id);
    }
    public List<CauHoiIQ> getAll(){
        return cauHoiRepository.findAll();
    }
    public CauHoiIQ layCauHoiNgauNhien() {
        return cauHoiRepository.findRandomCauHoi();
    }

}
