package i18examples;

import java.text.ChoiceFormat;
import java.text.Format;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class ChoiceFormatDemo {
    static void displayMessage(Locale currentLocale) {
        System.out.println("Current locale: " + currentLocale);
        System.out.println();
        ResourceBundle bundle = ResourceBundle.getBundle("ChoiceBundle", currentLocale);
        MessageFormat messageFormat = new MessageFormat("");
        messageFormat.setLocale(currentLocale);

        double[] fileLimits = {0, 1, 2};
        String[] fileStrings = {
                bundle.getString("noFiles"),
                bundle.getString("oneFile"),
                bundle.getString("multipleFiles")
        };
        ChoiceFormat choiceFormat = new ChoiceFormat(fileLimits, fileStrings);
        String pattern = bundle.getString("pattern");
        Format[] formats = {choiceFormat, null, NumberFormat.getInstance()};

        messageFormat.applyPattern(pattern);
        messageFormat.setFormats(formats);
        Object[] messageArguments = {null, "xDisk", null};
        for (int numFiles = 0; numFiles < 4; numFiles++) {
            messageArguments[0] = Integer.valueOf(numFiles);
            messageArguments[2] = Integer.valueOf(numFiles);
            String result = messageFormat.format(messageArguments);
            System.out.println(result);
        }
    }

    public static void main(String[] args) {
        displayMessage(new Locale("en", "US"));
        System.out.println();
    }



}