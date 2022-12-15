package com.example.dto.address;

import java.util.List;

import com.example.entity.PayResult;

import lombok.Data;

@Data
public class AddressAndPaylist {
    
    List<PayResult> pList;

    List<AddressinfoDTO> aList;
}
