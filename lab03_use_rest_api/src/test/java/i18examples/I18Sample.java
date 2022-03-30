package i18examples;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18Sample {
    public static void main(String[] args) {
        String language;
        String country;

        if(args.length!=2){
            language = new String("pl");
            country = new String("PL");
        }else{
            language = args[0];
            country = args[1];
        }
        Locale currentLocale;
        ResourceBundle messages;
        currentLocale = new Locale(language,country);

        messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);
        System.out.println(messages.getString("greetings"));
        System.out.println(messages.getString("inquiry"));
        System.out.println(messages.getString("farewell"));
    }
}