package com.example.CuoikiLTM.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class CauHoi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String cauhoi;
    private Integer time;
    private String tag;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<DapAn> dapan;
    public CauHoi() {
        this.dapan = new ArrayList<>();
        // Khởi tạo 4 đáp án trống
        for (int i = 0; i < 4; i++) {
            DapAn dapAn = new DapAn();
            dapAn.setName("");
            dapAn.setKetqua(false);
            this.dapan.add(dapAn);
        }
    }
}
