package com.example.rzd.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long productId; // id товара // id в таблице 7
    @Column(columnDefinition = "TEXT")
    private String productName; // название товара // id в таблице 8
    private String productBrand; // маркировка товара // id в таблице 9
    @Column(columnDefinition = "TEXT")
    private String productSize; // параметры товара // id в таблице 10
    private String productUnits; // базовая единика измерения товара // id в таблице 11
    private String productSGRT; //группа ВКГ // id в таблице 12
    private String productVector; // производитель // id в таблице 14
    private String productNameManufacturer; // производитель наименование // id в таблице 15
    private String productCodeManufacturer; // производитель код // id в таблице 16
    private String productFeature; // признак // id в таблице 17
    private String productGOST; // ГОСТ // id в таблице 18
    @Column(columnDefinition = "TEXT")
    private String productNotes; // дополнительно // id в таблице 19
    private String productSCLSL; // Код по классификатору // id в таблице 20
    private long productTGMTR; // Значение по ТГМТР // id в таблице 21
    private String productStartDate; // Дата и время создания // id в таблице 22
    private String productEndDate; // Дата и время удаления // id в таблице 24
    private String productUpdateDate; // Дата и время последнего изменения // id в таблице 23
    private String productSpecialSigns; // Специальные признаки // id в таблице 25
//    private String productImage; // Изображение // id в таблице 26
    private String productName1; // Наименование товара (ОТС) // id в таблице 27
    private String productNameShort; // краткое наименование // id в таблице 28
    private String productPRSIZ; // признак сиз // id в таблице 29
    private String productDopFile; // дополнитедбный файл // id в таблице 30
    private String productCountry; // страна производитель // id в таблице 31
    private String productOKPD2; // ОКПД2 // id в таблице 32
    private String productCategory; // категория товара // id в таблице 33
    private String productApplicationArea; // область применения // id в таблице 34
    private String productSignsMtr; //  Признаки МТР // id в таблице 35
    private String productSign_GOST_SKMTR; // Есть неактуальные стандарты // id в таблице 36
    private String productPORTAL_QUERY_ID; // портальная заявка // id в таблице 37
    private String productID_TU; // ТУ // id в таблице 39
    private String productStandart; //прочие стандарты // id в таблице 40
    private String productProtectionClass; //класс защиты // id в таблице 43

}
