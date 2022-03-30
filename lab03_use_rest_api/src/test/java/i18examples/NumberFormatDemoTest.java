package i18examples;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

@Disabled
public class NumberFormatDemoTest {
//my default locale en,US
    @Test
    void shouldPrintFormattedNumberWithLocale(){
        Double amount = Double.valueOf(345987.246);
        NumberFormat numberFormatter;
        String amountOut;
        numberFormatter = NumberFormat.getNumberInstance();
        amountOut = numberFormatter.format(amount);
        System.out.println(amountOut + " current locale: " + Locale.getDefault());
        numberFormatter = NumberFormat.getNumberInstance(new Locale("pl","PL"));
        System.out.println(numberFormatter.format(amount));
    }
    @Test
    void shouldPrintFormattedCurrenciesWithLocale(){
        Double amount = Double.valueOf(345987.246);
        NumberFormat numberFormat;
        String currencyOut;
        numberFormat = NumberFormat.getCurrencyInstance();
        currencyOut = numberFormat.format(amount);
        System.out.println(currencyOut);
        numberFormat = NumberFormat.getNumberInstance(new Locale("pl","PL"));
        System.out.println(numberFormat.format(amount));
    }
    @Test
    void shouldPrintFormattedPercentage(){
        Double percent = Double.valueOf(0.75);
        NumberFormat percentFormat;
        percentFormat = NumberFormat.getPercentInstance();
        System.out.println(percentFormat.format(percent));
        percentFormat = NumberFormat.getPercentInstance(new Locale("pl","PL"));
        System.out.println(percentFormat.format(percent));
    }
    @Test
    void DateFormatTest(){
        Date today;
        String dateOut;
        DateFormat dateFormatter;
        dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT);
        today = new Date();
        System.out.println(dateFormatter.format(today));
        dateFormatter = DateFormat.getDateInstance(DateFormat.LONG,new Locale("pl","PL"));
        System.out.println(dateFormatter.format(today));
    }


}