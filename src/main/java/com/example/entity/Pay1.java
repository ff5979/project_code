package com.example.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "PAY1TBL")
@SequenceGenerator(name = "PAY1SEQ", sequenceName = "SEQ_PAY1_NO", initialValue = 1, allocationSize = 1)
public class Pay1 {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PAY1SEQ")
    Long no;

    String userid;

    String method;

    String item_name;

    int total_amount;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm.ss.SSS")
    @CreationTimestamp
    @Column(name = "REGDATE", updatable = false)
    Date regdate = null;

    @OneToOne
    @MapsId
    @JoinColumn(name = "order1no")
    private Order1 order1;
}
