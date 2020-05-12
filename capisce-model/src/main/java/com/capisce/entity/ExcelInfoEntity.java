package com.capisce.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ExcelInfoEntity implements Serializable {
    public String fileName;
    public String documentName;
    public String url;
}
