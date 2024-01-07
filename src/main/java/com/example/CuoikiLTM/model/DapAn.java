package com.example.CuoikiLTM.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DapAn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    private String name;
    private boolean ketqua;
}
