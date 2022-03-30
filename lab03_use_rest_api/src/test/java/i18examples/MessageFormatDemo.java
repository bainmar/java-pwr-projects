package i18examples;

import java.text.MessageFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class MessageFormatDemo {
    static void displayMessage(Locale currentLocale) {
        System.out.println("Current local: " + currentLocale);
        System.out.println();

        ResourceBundle messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);

        Object[] messageArguments = {messages.getString("planet")
                , Integer.valueOf(7), new Date()};

        MessageFormat formatter = new MessageFormat("");
        formatter.setLocale(currentLocale);
        formatter.applyPattern(messages.getString("template"));
        String output = formatter.format(messageArguments);
        System.out.println(output);
    }

    public static void main(String[] args) {
        displayMessage(new Locale("en", "US"));
        System.out.println();
    }
}