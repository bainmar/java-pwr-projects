package com.bartoszek.jtzrestapi;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class UtilsSpec {
    @Disabled
    @Test
    void getAllCountriesFormattedEN() throws IOException {
        String fileWithCountries = Files.readString(Paths.get("src/test/resources/countriesFromGeoDB.txt"));
        Pattern pattern = Pattern.compile(",\\s");
        Matcher matcher = pattern.matcher(fileWithCountries);
        String result = matcher.replaceAll(",");

        String[] split = result.split(",");
        StringBuilder collect = Stream.of(split).sorted().collect(StringBuilder::new,
                (builder, element) -> {
                    builder.append("\"");
                    builder.append(element);
                    builder.append("\"");
                    builder.append(",");
                }, StringBuilder::append);
        System.out.println(collect.toString());
    }
    @Disabled
    @Test
    void getAllCountriesFormattedPL() throws IOException {
        List<String> strings = Files.readAllLines(Paths.get("src/test/resources/countriesFromPanstwaCom.txt"));
        StringBuilder collectedData = strings.stream().collect(StringBuilder::new, (b, e) -> {
            b.append("\"");
            b.append(e);
            b.append("\"");
            b.append(",");
        }, StringBuilder::append);
        System.out.println(collectedData);
    }
}