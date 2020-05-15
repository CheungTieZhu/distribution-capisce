package com.capisce.entity;

import lombok.Data;
import java.io.Serializable;

@Data
public class SelectPageEntity implements Serializable {
    String fundCode;
    int page;
    int row;
    int beginIndex;
    String beginDate;
    String endDate;
}
