package com.bartoszek.consoleapp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class SystemTest {

    public static void main(String[] args) throws IOException {

        System.out.println("Podaj sciezke (np. /home/marcin/sciezka)");
        String input;
        try (Scanner sc = new Scanner(System.in)) {
            input = sc.next();
            Path path = Paths.get(input);
            if (!Files.exists(path)) {
                throw new InvalidPathException(path.getFileName().toString(), "nieprawidlowe dane");
            }

            UserOfVC userOfVC = new UserOfVC(path);
            int userSelected;

            do {
                System.out.println("path: " + path.toAbsolutePath().toString());
                userSelected = menuData(sc);

                switch (userSelected) {
                    case 1:
                        userOfVC.showFilesInRepo();
                        break;
                    case 2:
                        userOfVC.checkChanges();
                        break;
                    default:
                        break;
                }

            } while (userSelected <= 3 && userSelected > 0);
        }
    }

    public static int menuData(Scanner scanner) {
        int selection;
        System.out.println();
        Scanner sc = scanner;
        StringBuilder builder = new StringBuilder();
        builder.append("Opcje: ");
        builder.append("\n----------------");
        builder.append("\n1 - wyświetl obserwowane pliki");
        builder.append("\n2 - wyswietl informacje, które pliki zostały zmienione");
        builder.append("\n----------------");
        builder.append("\nWybrna opcja: ");
        System.out.println(builder);
        System.out.println();
        selection = sc.nextInt();
        return selection;

    }

}