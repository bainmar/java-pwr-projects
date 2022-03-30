package com.bartoszek.jtzrestapi;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Disabled
public class GeoDbClientApiSpec {
    private static GeoDbClientApi geoDbClientApi;

    @BeforeAll
    static void init(){
        geoDbClientApi = new GeoDbClientApi();
    }

    @Test
    void GeoDbClientShouldGetAllCountries() throws IOException, InterruptedException {
        List<String> allCountries = geoDbClientApi.getAllCountries(LanguageCode.EN);
        System.out.println(allCountries);
    }
    @Test
    void GeoDBClientShouldGetAllCurrenciesForGivenCountry() throws IOException, InterruptedException {
        List<String> currenciesForAllCountries = geoDbClientApi.getCurrenciesForAllCountries();
        System.out.println(currenciesForAllCountries);
    }
}