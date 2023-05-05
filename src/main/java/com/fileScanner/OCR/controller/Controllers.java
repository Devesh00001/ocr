package com.fileScanner.OCR.controller;


import com.fileScanner.OCR.POJO.pojo;

import com.fileScanner.OCR.service.TesseractService;
import jakarta.servlet.http.HttpServletRequest;
import net.sourceforge.tess4j.TesseractException;
import org.jetbrains.annotations.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class Controllers {

    @Autowired
    private TesseractService tesseractService;

    public static final pojo pojo = new pojo();

    @RequestMapping("/" )
    public String landing() {

        return "home";
    }


    @PostMapping(value = "/home")
    public String fileUpload(@RequestParam("file") MultipartFile file, @NotNull HttpServletRequest request, @NotNull Model model) throws IOException, TesseractException {

        List<String> regexs = tesseractService.getRegex(request);
        List<String> result = tesseractService.getResult(file,regexs);
        try {

            model.addAttribute("FileName", "The scan file: " + pojo.getFileName());
            for (int i = 0; i < regexs.size(); i++) {
                int a = i + 1;
                model.addAttribute("Result" + a + "", result.get(i));
            }
        } catch (Exception e) {
            model.addAttribute("result " + e.getMessage());
        }

        return "result";
    }


}
