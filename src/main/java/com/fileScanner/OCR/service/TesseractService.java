package com.fileScanner.OCR.service;

import jakarta.servlet.http.HttpServletRequest;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;


public interface TesseractService {

    public List<String> getResult(MultipartFile file, List<String> list) throws TesseractException, IOException;

    public List<String> getRegex(HttpServletRequest request);

    public List<String> engine() throws TesseractException, IOException;

}
