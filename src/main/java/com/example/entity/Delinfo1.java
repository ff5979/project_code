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
@Table(name = "DELINFO1TBL")
@SequenceGenerator(name = "DELINFO1SEQ", sequenceName = "SEQ_DELINFO1_NO", initialValue = 1, allocationSize = 1)
public class Delinfo1 {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DELINFO1SEQ")
    Long no;

    Long success;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm.ss.SSS")
    @CreationTimestamp
    @Column(name = "REGDATE", updatable = false)
    Date regdate = null;

    @OneToOne
    @MapsId
    @JoinColumn(name = "order1no")
    private Order1 order1;

    @ToString.Exclude
    @JsonBackReference(value = "DELIEVERY1")
    @ManyToOne
    @JoinColumn(name = "code")
    private Delievery1 delievery1;
}
