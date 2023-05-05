package com.fileScanner.OCR;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.fileScanner.OCR.controller.Controllers.pojo;


public class Tesseract {

    public List<String> tesseract(@NotNull MultipartFile file, @NotNull List<String> regex) throws TesseractException, IOException {
        Paths.get("src/main/resources/uploads/" + file.getOriginalFilename());
        File fl = new File(pojo.getFilePath().toUri());
        if(!fl.exists()){
            Files.copy(file.getInputStream(),Paths.get(pojo.getFilePath().toUri()));
        }

        ITesseract tesseract = new net.sourceforge.tess4j.Tesseract();
        tesseract.setDatapath("C:/Users/asus1/Downloads/OCRscanner/Tess4J-3.4.8-src/Tess4J/tessdata");

        List<String> text = new ArrayList<>();
        if (file.getName().endsWith(".pdf")) {
            PDDocument document = PDDocument.load(new File(Paths.get("src/main/resources/uploads/" + file.getOriginalFilename()).toUri()));
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            for (int page = 0; page < 2; ++page) {
                BufferedImage bim = pdfRenderer.renderImageWithDPI(
                        page, 300, ImageType.RGB);

                String result = tesseract.doOCR(bim);

                text.add(result);

            }
            document.close();

        } else {

            String result = tesseract.doOCR((File) file);
            text.add(result);
        }


        List<String> listOfLists = new ArrayList<>();

        for (String value : regex) {
            List<String> allPage = new ArrayList<>();
            for (int i = 0; i < text.size(); i++) {
                Pattern pattern = Pattern.compile(value);
                Matcher matcher = pattern.matcher(text.get(i));
                List<String> singlePage = new ArrayList<>();

                String pageNumber = "PAGE " + (i + 1) + " : ";
                while (matcher.find()) {
                    singlePage.add(matcher.group());
                }
                if (!singlePage.isEmpty()) {
                    allPage.add(pageNumber + singlePage.toString().substring(1,singlePage.toString().length()-1));
                }
            }
            listOfLists.add(allPage.toString().substring(1,allPage.toString().length()-1));
        }
        return listOfLists;
    }
}

