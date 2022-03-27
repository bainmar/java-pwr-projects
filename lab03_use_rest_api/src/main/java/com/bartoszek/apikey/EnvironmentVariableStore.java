package com.bartoszek.apikey;

import java.util.Map;
import java.util.NoSuchElementException;

public class EnvironmentVariableStore {
    private static final Map<String, String> VARIABLES = System.getenv();

    public static String getVariable(String label) {
        if (VARIABLES.containsKey(label)) {
            return VARIABLES.get(label);
        } else {
            String excMsg = "No environment variable for label \""
                    + label + "\"";
            throw new NoSuchElementException(excMsg);
        }
    }
}