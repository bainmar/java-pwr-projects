package com.bartoszek.jtzrestapi;

import javax.swing.*;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

public class AppController {
    private AppView appView;
    private GeoDbClientApi geoDbClientApi;

    public AppController(AppView appView) {
        this.appView = appView;
        this.geoDbClientApi = new GeoDbClientApi();
    }

    public void initController() {

        appView.getqTwoJButton().addActionListener(e -> configureQuestionTwo());
        appView.getqOneJButton().addActionListener(e -> configureQuestionOne());
    }

    private void configureQuestionOne() {
        int answeredPopulation = Integer.parseInt(appView.getqOneQuestionJTextField()
                .getText());
        String selectedCountry = appView.getqOneJComboBox().getSelectedItem().toString();
        try {
            String countryWikiData = geoDbClientApi.getCountryWikiData(selectedCountry);

            if (countryWikiData == null) {
                JOptionPane.showMessageDialog(appView, appView.getResourceBundle().getString("geoDBError"));
            } else {

                Thread.sleep(2000);

                int citiesCountForCountry = geoDbClientApi
                        .getCitiesCountForCountry(countryWikiData
                                , answeredPopulation);

                int result = Integer.parseInt(appView.getqOneAnswerJTextField().getText());
                if (result == citiesCountForCountry) {
                    JOptionPane.showMessageDialog(appView, appView.getResourceBundle().getString("goodAnswer"));
                } else {
                    JOptionPane.showMessageDialog(appView, appView.getResourceBundle().getString("wrongAnswer"));
                }

                String answerOne = MessageFormat.format(appView.getResourceBundle().getString("answerOne"),
                        selectedCountry, citiesCountForCountry);

                JOptionPane.showMessageDialog(appView, answerOne
                        , appView.getResourceBundle().getString("numberOfCountries")
                        , JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

    }


    private void configureQuestionTwo() {

        String selectedCountry = appView.getqTwoJComboBox().getSelectedItem().toString();
        try {
            String countryWikiData = geoDbClientApi.getCountryWikiData(selectedCountry);
            Thread.sleep(1000);
            if (countryWikiData == null) {
                JOptionPane.showMessageDialog(appView, appView.getResourceBundle().getString("geoDBError"));
            } else {
                List<String> currencyCodesForCountry = geoDbClientApi.getCurrencyCodesForCountry(countryWikiData);
                String selectedAnswer = appView.getqTwoAnswerJComboBox()
                        .getSelectedItem()
                        .toString();
                if (currencyCodesForCountry.contains(selectedAnswer)) {
                    JOptionPane.showMessageDialog(appView
                            , appView.getResourceBundle().getString("goodAnswer")
                                    + " :" + currencyCodesForCountry
                            , appView.getResourceBundle().getString("currencyInCountry")
                            , JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(appView
                            , appView.getResourceBundle().getString("wrongAnswer")
                                    + " :" + currencyCodesForCountry
                            , appView.getResourceBundle().getString("currencyInCountry")
                            , JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (IOException | InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

}