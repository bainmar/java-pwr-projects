module bartoszek.jtzrestapi {
    requires java.desktop;
    requires java.net.http;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    exports com.bartoszek.jackson.countries;
    exports com.bartoszek.jackson.currencies;
    exports com.bartoszek.jackson.cities;
    exports com.bartoszek.jackson.countrydetails;
}