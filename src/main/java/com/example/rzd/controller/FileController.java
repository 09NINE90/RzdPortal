package com.example.rzd.controller;

import com.example.rzd.service.ExcelService;
import com.example.rzd.service.UserService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

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
        InputStream in = file.getInputStream();
        FileOutputStream f = new FileOutputStream("src/main/resources/excel/" + file.getOriginalFilename());
        int ch = 0;
        while ((ch = in.read()) != -1) {
            f.write(ch);
        }
        f.flush();
        f.close();
        System.out.println("message File: " + file.getOriginalFilename() + " has been uploaded successfully!");
        Thread thread = new Thread(() -> excelService.read("src/main/resources/excel/" + file.getOriginalFilename()));
        thread.start();

        return "redirect:/rzd/portal/home";
    }

    @GetMapping("/upload")
    public void readFile() {
        System.out.println("Грузите файл");
    }
}
