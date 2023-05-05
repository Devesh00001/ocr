package com.fileScanner.OCR.POJO;

import java.nio.file.Path;
import java.util.List;

public class pojo {
    private Path filePath;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    private String fileName;

    public Path getFilePath() {
        return filePath;
    }

    public void setFilePath(Path filePath) {
        this.filePath = filePath;
    }

    public List<String> pdfText;

    public List<String> getPdfText() {
        return pdfText;
    }

    public void setPdfText(List<String> pdfText) {
        this.pdfText = pdfText;
    }
}

