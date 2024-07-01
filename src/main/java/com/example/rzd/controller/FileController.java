package com.example.rzd.controller;

import com.example.rzd.service.ExcelService;
import com.example.rzd.service.UserService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Controller
@RequestMapping("/rzd/portal")
public class FileController {
    private final ExcelService excelService;

    public FileController(UserService userService, ExcelService excelService) {
        this.excelService = excelService;
    }
    @SneakyThrows
    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        long startTime = System.currentTimeMillis();
        InputStream in = file.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(in);

        FileOutputStream f = new FileOutputStream("src/main/resources/excel/" + file.getOriginalFilename());
        BufferedOutputStream bos = new BufferedOutputStream(f);

        byte[] buffer = new byte[1024]; // Размер буфера, можно настроить под свои нужды
        int bytesRead;
        while ((bytesRead = bis.read(buffer)) != -1) {
            bos.write(buffer, 0, bytesRead);
        }

        bos.flush();
        bos.close();
        bis.close();
//        InputStream in = file.getInputStream();
//        FileOutputStream f = new FileOutputStream("src/main/resources/excel/" + file.getOriginalFilename());
//        int ch = 0;
//        while ((ch = in.read()) != -1) {
//            f.write(ch);
//        }
//        f.flush();
//        f.close();
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        double seconds = (double) elapsedTime / 1000;
        System.out.println("File: " + file.getOriginalFilename() + " успешно загружен! Время - " + seconds);
        System.out.println("Начинается загрузка данных из файла в БД");
        Thread thread = new Thread(() -> excelService.read("src/main/resources/excel/" + file.getOriginalFilename()));
        thread.start();

        return "redirect:/rzd/portal/home";
    }

    @GetMapping("/upload")
    public void readFile() {
        System.out.println("Грузите файл");
    }
}
