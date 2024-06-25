package com.example.rzd.service;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class ExcelService {
    public void read(){
        Workbook workbook;

        {
            try {
                workbook = WorkbookFactory.create(new File("src/main/resources/SKMTR.xlsx"));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } {
            Sheet sheet = workbook.getSheetAt(0); // Получаем первую страницу

            for (Row row : sheet) {
                for (Cell cell : row) {
                    switch (cell.getCellType()) {
                        case NUMERIC:
                            System.out.print(cell.getNumericCellValue() + "\t");
                            break;
                        case STRING:
                            System.out.print(cell.getStringCellValue() + "\t");
                            break;
                        case BOOLEAN:
                            System.out.print(cell.getBooleanCellValue() + "\t");
                            break;
                        default:
                            System.out.print(" " + "\t");
                    }
                }
                System.out.println(); // Переход на новую строку
            }
        }
    }


}
