package com.capisce.implement.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import com.capisce.entity.ExcelInfoEntity;
import org.apache.commons.collections4.MapUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import static org.apache.poi.ss.usermodel.CellType.*;

public class ExcelExportUtils {
    //读取excel
    public static Workbook readExcel(String filePath){
        Workbook wb = null;
        if(filePath==null){
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            if(".xls".equals(extString)){
                return wb = new HSSFWorkbook(is);
            }else if(".xlsx".equals(extString)){
                return wb = new XSSFWorkbook(is);
            }else{
                return wb = null;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }

    public static Object getCellFormatValue(Cell cell){
        Object cellValue = null;
        if(cell!=null){
            //判断cell类型
            switch(cell.getCellType()){
                case NUMERIC:{
                    cellValue = String.valueOf(cell.getNumericCellValue());
                    break;
                }
                case FORMULA:{
                    //判断cell是否为日期格式
                    if(DateUtil.isCellDateFormatted(cell)){
                        //转换为日期格式YYYY-mm-dd
                        cellValue = cell.getDateCellValue();
                    }else{
                        //数字
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case STRING:{
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                default:
                    cellValue = "";
            }
        }else{
            cellValue = "";
        }
        return cellValue;
    }

    public static List<ExcelInfoEntity> convertExcelToList(Workbook wb){
        List<ExcelInfoEntity> list = null;
        Sheet sheet = null;
        Row row = null;
        String cellData = null;
        Map<String,Integer> documentMap = new HashMap<>();
        if(wb != null){
            //用来存放表中数据
            list = new ArrayList<ExcelInfoEntity>();
            //获取第一个sheet
            sheet = wb.getSheetAt(0);
            //获取最大行数
            int rownum = sheet.getPhysicalNumberOfRows();
            //获取第一行
            row = sheet.getRow(0);
            //获取最大列数
            int colnum = row.getPhysicalNumberOfCells();
            for (int i = 1; i<rownum; i++) {
                ExcelInfoEntity excelInfoEntity = new ExcelInfoEntity();
                row = sheet.getRow(i);
                if(row !=null){
                    for (int j=0;j<colnum;j++){
                        cellData = (String) getCellFormatValue(row.getCell(j));
                        switch (j){
                            case 0:
                                excelInfoEntity.setFileName(cellData);
                                break;
                            case 1:
                                if (documentMap.containsKey(cellData)){
                                    int count = MapUtils.getIntValue(documentMap,cellData) + 1;
                                    documentMap.put(cellData,count);
                                    cellData = cellData + "(" + count + ")";
                                    documentMap.put(cellData,1);
                                } else {
                                    documentMap.put(cellData,1);
                                }
                                excelInfoEntity.setDocumentName(cellData);
                                break;
                            default:
                                excelInfoEntity.setUrl(cellData);
                        }
                    }
                }else{
                    break;
                }
                list.add(excelInfoEntity);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        Workbook wb =null;
        List<ExcelInfoEntity> list = null;
        String filePath = "D:\\报表-中城拉取电子签约文件数据-20200512.xlsx";
        wb = readExcel(filePath);
        //遍历解析出来的list
        list = convertExcelToList(wb);
        for (ExcelInfoEntity excelInfoEntity : list) {
            String fileName = excelInfoEntity.getFileName();
            String documentName = excelInfoEntity.getDocumentName();
            String url = excelInfoEntity.getUrl();
            DownloadFileUtils.downloadFile(url,fileName,documentName);
        }
    }
}
