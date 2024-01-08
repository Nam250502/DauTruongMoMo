package com.example.CuoikiLTM.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private  String name;
    private  String phone;
    private  String image;
    private String address;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    private int sotranthang;
    private int sotranthua;
    private int chuoithang;
    private int chuoithua;
    private int enabled ;
    private int iq ;
    public User(){

        this.sotranthua=0;
        this.sotranthang=0;
        this.chuoithang=0;
        this.chuoithua=0;
        this.enabled=1;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;

}
