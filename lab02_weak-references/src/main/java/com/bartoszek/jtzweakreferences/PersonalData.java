package com.bartoszek.jtzweakreferences;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class PersonalData {
    private List<String> recordTxtLines;
    private BufferedImage imagePng;

    public PersonalData(List<String> recordTxtLines,BufferedImage imagePng){
        this.recordTxtLines=recordTxtLines;
        this.imagePng=imagePng;
    }

    public List<String> getRecordTxtLines() {
        return recordTxtLines;
    }

    public BufferedImage getImagePng() {
        return imagePng;
    }

    public static PersonalData createEntry(Path folderPath) throws IOException {
        Path recordPath = folderPath.resolve("record.txt");
        Path imagePath = folderPath.resolve("image.png");

        if(Files.exists(recordPath)&&Files.exists(imagePath)){
            List<String> recordLines = Files.readAllLines(recordPath);
            BufferedImage image = ImageIO.read(Files.newInputStream(imagePath));
            return new PersonalData(recordLines,image);
        }
        return null;
    }

}