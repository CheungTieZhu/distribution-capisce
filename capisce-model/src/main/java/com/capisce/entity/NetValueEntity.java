package com.capisce.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class NetValueEntity implements Serializable {
    String netDate;
    String fundCode;
    String netValue;
    String targetDate;
}
