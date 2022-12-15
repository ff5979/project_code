package com.example.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "DELINFO2TBL")
@SequenceGenerator(name = "DELINFO2SEQ", sequenceName = "SEQ_DELINFO2_NO", initialValue = 1, allocationSize = 1)
public class Delinfo2 {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DELINFO2SEQ")
    Long no;

    Long success;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm.ss.SSS")
    @CreationTimestamp
    @Column(name = "REGDATE", updatable = false)
    Date regdate = null;

    @OneToOne
    @MapsId
    @JoinColumn(name = "order2no")
    private Order2 order2;

    @ToString.Exclude
    @JsonBackReference(value = "DELIEVERY2")
    @ManyToOne
    @JoinColumn(name = "code")
    private Delievery2 delievery2;
}
