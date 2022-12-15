package com.example.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "ORDERTBL2")
@SequenceGenerator(name = "ORDER2SEQ", sequenceName = "SEQ_ORDER2_NO", initialValue = 101, allocationSize = 1)
public class Order2 {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER2SEQ")
    Long no;
    
    Long quantity;

    Long price;

    String name;

    String msg;

    Long type;

    Long  tprice;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm.ss.SSS")
    @CreationTimestamp
    @Column(name = "REGDATE", updatable = false)
    Date regdate = null;

    @ToString.Exclude
    @JsonBackReference(value = "member5")
    @ManyToOne
    @JoinColumn(name = "userid")
    private Member member;

    @ToString.Exclude
    @JsonBackReference(value = "address2")
    @ManyToOne
    @JoinColumn(name = "addressno")
    private Address address;

    @ToString.Exclude
    @JsonBackReference(value = "product2")
    @ManyToOne
    @JoinColumn(name = "productno")
    private Product product;
}
