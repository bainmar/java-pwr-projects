package com.bartoszek.jtzrestapi;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import com.bartoszek.apikey.EnvironmentVariableStore;
import com.bartoszek.jackson.countries.CountriesData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.bartoszek.jackson.cities.Cities;
import com.bartoszek.jackson.countries.Countries;
import com.bartoszek.jackson.countrydetails.CountryDetails;
import com.bartoszek.jackson.currencies.CurrenciesData;
import com.bartoszek.jackson.currencies.CurrenciesModel;

public class GeoDbClientApi {
    private final String x_rapidapi_key = EnvironmentVariableStore.getVariable("GEODB_API_KEY");
    private final String x_rapidapi_host = "wft-geo-db.p.rapidapi.com";
    private final String host = "https://wft-geo-db.p.rapidapi.com/v1/";
    private final String countriesEndpoint = "geo/countries";
    private final String countriesDetailsEndpoint = "geo/countries/";
    private final String citiesEndpoint = "geo/cities";
    private final String currenciesEndpoint = "locale/currencies";
    private static final int NUMBER_OF_RESULTS = 10;

    private HttpRequest request;
    private HttpResponse<String> response;
    private HttpClient client;
    private ObjectMapper mapper;

    public GeoDbClientApi() {
        super();
        client = HttpClient.newHttpClient();
        mapper = new ObjectMapper();
    }

    public String getCountryWikiData(String countryName) throws IOException, InterruptedException {
        setResponseFromCountriesEndpointWithCountryName(countryName);
        Countries countries = mapper.readValue(response.body(), Countries.class);
        return countries.getData().get(0).getWikiDataId();
    }

    public int getCitiesCountForCountry(String countryIds, int minPopulation) throws IOException, InterruptedException {
        setResponseFromCitiesEndpointWithWikiDataIdAndMinPopulation(countryIds, minPopulation);
        Cities cities = mapper.readValue(response.body(), Cities.class);
        return cities.getMetadata().getTotalCount();
    }

    public List<String> getCurrencyCodesForCountry(String countryId) throws IOException, InterruptedException {
        setResponseFromCountriesDetailsEndpointWithCountryId(countryId);
        CountryDetails countryData = mapper.readValue(response.body(), CountryDetails.class);
        return countryData.getData().getCurrencyCodes();
    }

    public List<String> getCurrenciesForAllCountries() throws IOException, InterruptedException {
        int offset = 0;
        List<String> listOfCurrencies = new ArrayList<>();
        setResponseFromCurrenciesEndpointWithOffset(offset);
        CurrenciesModel currenciesModel = mapper.readValue(response.body(), CurrenciesModel.class);
        int totalCount = currenciesModel.getMetadata().getTotalCount();

        //api allow access every second
        Thread.sleep(1000);
        while (offset < totalCount) {
            List<CurrenciesData> currentListOfCurrencies = currenciesModel.getData();
            currentListOfCurrencies.stream()
                    .map(CurrenciesData::getCode)
                    .collect(() -> listOfCurrencies, List::add, List::addAll);
            offset += GeoDbClientApi.NUMBER_OF_RESULTS;
            if (offset < totalCount) {
                setResponseFromCurrenciesEndpointWithOffset(offset);
                currenciesModel = mapper.readValue(response.body(), CurrenciesModel.class);
            }
            Thread.sleep(3000);
        }
        return listOfCurrencies;
    }

    public String getCountryCapital(String countryID) throws IOException, InterruptedException {
        setResponseFromCountriesDetailsEndpointWithCountryId(countryID);
        CountryDetails countryData = mapper.readValue(response.body(), CountryDetails.class);
        return countryData.getData().getCapital();
    }

    public List<String> getAllCountries(LanguageCode languageCode) throws IOException, InterruptedException {
        int offset = 0;
        List<String> listOfCountries = new ArrayList<>();
        setResponseFromCountriesEndpointWithOffsetAndLanguageCode(offset, languageCode);
        Countries countries = mapper.readValue(response.body(), Countries.class);
        int totalCount = countries.getMetadata().getTotalCount();

        //api allow access every second
        Thread.sleep(1000);
        while (offset < totalCount) {
            List<CountriesData> currentListOfCountriesData = countries.getData();
            currentListOfCountriesData.stream()
                    .map(CountriesData::getName)
                    .collect(() -> listOfCountries, List::add, List::addAll);
            offset = offset + GeoDbClientApi.NUMBER_OF_RESULTS;
            if (offset < totalCount) {
                setResponseFromCountriesEndpointWithOffsetAndLanguageCode(offset, languageCode);
                countries = mapper.readValue(response.body(), Countries.class);
            }
            Thread.sleep(3000);
        }
        return listOfCountries;
    }


    private void setResponseFromCountriesEndpointWithOffsetAndLanguageCode(int offset, LanguageCode languageCode) throws IOException, InterruptedException {
        URI uri = URI.create(host + countriesEndpoint + "?offset=" + offset
                + "&languageCode=" + languageCode
                + "&limit=" + GeoDbClientApi.NUMBER_OF_RESULTS);
        setRequest(uri);
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private void setResponseFromCurrenciesEndpointWithOffset(int offset) throws IOException, InterruptedException {
        URI uri = URI.create(host + currenciesEndpoint
                + "?offset=" + offset
                + "&limit=" + GeoDbClientApi.NUMBER_OF_RESULTS);
        setRequest(uri);
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private void setResponseFromCountriesDetailsEndpointWithCountryId(String wikiDataId) throws IOException, InterruptedException {
        URI uri = URI.create(host + countriesDetailsEndpoint + wikiDataId);
        setRequest(uri);
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private void setResponseFromCitiesEndpointWithWikiDataIdAndMinPopulation(String wikiDataId, int minPopulation) throws IOException, InterruptedException {
        URI uri = URI.create(host + citiesEndpoint + "?countryIds=" + wikiDataId + "&minPopulation=" + minPopulation);
        setRequest(uri);
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private void setResponseFromCountriesEndpointWithCountryName(String countryName) throws IOException, InterruptedException {
        String trimCountryName = countryName.replace(" ", "%20");
        URI uri = URI.create(host + countriesEndpoint + "?namePrefix=" + trimCountryName);
        setRequest(uri);
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private void setRequest(URI uri) {
        request = HttpRequest.newBuilder()
                .uri(uri)
                .header("x-rapidapi-key", x_rapidapi_key)
                .header("x-rapidapi-host", x_rapidapi_host)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
    }
}