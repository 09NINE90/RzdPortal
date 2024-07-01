package com.example.rzd.service;


import com.example.rzd.entity.Product;
import com.example.rzd.repository.ProductsRepository;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class ExcelService {
    final
    ProductService productService;

    public ExcelService(ProductService productService) {
        this.productService = productService;
    }

    public void read(String path){
        Workbook workbook;
        long startTime = System.currentTimeMillis();
        {
            try {
                workbook = WorkbookFactory.create(new File(path));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } {
            Sheet sheet = workbook.getSheetAt(0); // Получаем первую страницу

            for (int i = 3; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                Product product = new Product();
                Long productId = Long.parseLong(row.getCell(7).getStringCellValue());
                if (!productService.existsByProductId(productId)){
                    product.setProductId(productId);
                    product.setProductName(row.getCell(8).getStringCellValue());
                    product.setProductBrand(row.getCell(9).getStringCellValue());
                    product.setProductSize(row.getCell(10).getStringCellValue());
                    product.setProductUnits(row.getCell(11).getStringCellValue());
                    product.setProductSGRT(row.getCell(12).getStringCellValue());
                    product.setProductVector(row.getCell(13).getStringCellValue());
                    product.setProductNameManufacturer(row.getCell(14).getStringCellValue());
                    product.setProductCodeManufacturer(row.getCell(15).getStringCellValue());
                    if (row.getCell(16).getCellType() == CellType.NUMERIC) {
                        product.setProductFeature(String.valueOf(row.getCell(16).getNumericCellValue()));
                    }else {
                        product.setProductFeature(String.valueOf(row.getCell(16).getStringCellValue()));
                    }
                    product.setProductGOST(row.getCell(17).getStringCellValue());
                    product.setProductNotes(row.getCell(18).getStringCellValue());
                    product.setProductSCLSL(row.getCell(19).getStringCellValue());
                    product.setProductTGMTR(Long.parseLong(row.getCell(20).getStringCellValue()));
                    product.setProductStartDate(row.getCell(21).getStringCellValue());
                    product.setProductUpdateDate(row.getCell(22).getStringCellValue());
                    product.setProductEndDate(row.getCell(23).getStringCellValue());
                    product.setProductSpecialSigns(row.getCell(24).getStringCellValue());
//                product.setProductImage(row.getCell(25).getStringCellValue());
                    product.setProductName1(row.getCell(26).getStringCellValue());
                    product.setProductNameShort(row.getCell(27).getStringCellValue());
                    product.setProductPRSIZ(row.getCell(28).getStringCellValue());
                    if (row.getCell(29).getCellType() == CellType.BOOLEAN) {
                        product.setProductDopFile(String.valueOf(row.getCell(29).getBooleanCellValue()));
                    }else {
                        product.setProductDopFile(row.getCell(29).getStringCellValue());
                    }
                    product.setProductCountry(row.getCell(30).getStringCellValue());
                    product.setProductOKPD2(row.getCell(31).getStringCellValue());
                    product.setProductCategory(row.getCell(32).getStringCellValue());
                    product.setProductApplicationArea(row.getCell(33).getStringCellValue());
                    product.setProductSignsMtr(row.getCell(34).getStringCellValue());
                    product.setProductSign_GOST_SKMTR(row.getCell(35).getStringCellValue());
                    product.setProductPORTAL_QUERY_ID(row.getCell(36).getStringCellValue());
                    product.setProductID_TU(row.getCell(37).getStringCellValue());
                    product.setProductStandart(row.getCell(38).getStringCellValue());
                    product.setProductProtectionClass(row.getCell(39).getStringCellValue());

                    productService.saveProduct(product);
                }else {
                    System.out.println("Такая запись уже есть в таблице!");
                }


            }
        }
        File file = new File(path);

        // Проверяем, существует ли файл
        if (file.exists()) {
            // Удаляем файл
            if (file.delete()) {
                System.out.println("Файл успешно удален.");
            } else {
                System.out.println("Не удалось удалить файл.");
            }
        } else {
            System.out.println("Файл не существует.");
        }
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        double seconds = (double) elapsedTime / 1000;
        System.out.println("Загрузка в БД успешно завершена! Время - " + seconds);
    }


}
