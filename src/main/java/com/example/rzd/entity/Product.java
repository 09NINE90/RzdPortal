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

    private long productId; // id товара
    private String productName; // название товара
    private String productBrand; // маркировка товара
    private String productSize; // параметры товара
    private String productUnits; // базовая единика измерения товара
    private String productSGRT; //группа ВКГ
    private String productVector; // производитель
    private String productNameManufacturer; // производитель наименование
    private String productCodeManufacturer; // производитель код
    private String productFeature; // признак
    private String productGOST; // ГОСТ
    private String productNotes; // дополнительно
    private String productSCLSL; // Код по классификатору
    private long productTGMTR; // Значение по ТГМТР
    private String productStartDate; // Дата и время создания
    private String productEndDate; // Дата и время удаления
    private String productUpdateDate; // Дата и время последнего изменения
    private String productSpecialSigns; // Специальные признаки
    private String productImage; // Изображение
    private String productName1; // Наименование товара (ОТС)
    private String productNameShort; // краткое наименование
    private String productPRSIZ; // признак сиз
    private String productDopFile; // дополнитедбный файл
    private String productCountry; // страна производитель
    private String productOKPD2; // ОКПД2
    private String productCategory; // категория товара
    private String productApplicationArea; // область применения
    private String productSignsMtr; //  Признаки МТР
    private String productSign_GOST_SKMTR; // Есть неактуальные стандарты
    private String productPORTAL_QUERY_ID; // портальная заявка
    private String productID_TU; // ТУ
    private String productStandart; //прочие стандарты
    private String productProtectionClass; //класс защиты

}
