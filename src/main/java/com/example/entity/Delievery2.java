package com.example.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Entity
@Table(name = "DELIEVERY2TBL")
public class Delievery2 {
    
    @Id
    String code;

    @Column(length = 50)
    String name;

    @Column(length = 20)
    String phone;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm.ss.SSS")
    @CreationTimestamp
    @Column(name = "REGDATE", updatable = false)
    Date regdate = null;

    // 막히면 안 되는 쪽
    @JsonManagedReference(value = "DELIEVERY2")
    @OneToMany(mappedBy = "delievery2", cascade = CascadeType.REMOVE)
    private List<Delinfo2> delinfo2;
}
