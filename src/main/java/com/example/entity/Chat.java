package com.example.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "CHATTBL")
@SequenceGenerator(name = "CHATSEQ", sequenceName = "SEQ_CHAT_NO", initialValue = 1, allocationSize = 1)
public class Chat {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHATSEQ")
    Long no;
    
    // 채팅내용
    @Column(length = 300)
    String content;

    // roomid
    String roomid;

    String userid;

    // 생성일 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm.ss.SSS")
    @CreationTimestamp
    @Column(name = "REGDATE", updatable = false)
    Date regdate = null;
}
