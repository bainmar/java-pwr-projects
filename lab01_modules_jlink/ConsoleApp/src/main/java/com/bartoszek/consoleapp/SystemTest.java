package com.bartoszek.consoleapp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class SystemTest {

    public static void main(String[] args) throws IOException {

        System.out.println("Podaj scieżkę (np. /home/marcin/sciezka)");
        System.out.print("ścieżka: ");

        String input;
        Path path;
        try (Scanner sc = new Scanner(System.in)) {
            input = sc.next();
            path = Paths.get(input);
            while (!Files.exists(path)) {
                System.out.printf("Nieprawdiłowa ścieżka: %s%n", path.getFileName().toString());
                System.out.print("ścieżka: ");
                input = sc.next();
                path = Paths.get(input);
            }

            UserOfVC userOfVC = new UserOfVC(path);
            int userSelected;

            do {
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
        StringBuilder builder = new StringBuilder();
        builder.append("\n");
        builder.append("\n1 - Wyświetl obserwowane pliki");
        builder.append("\n2 - Wyświetl informacje, które pliki zostały zmienione");
        builder.append("\n");
        System.out.println(builder);
        System.out.println();
        selection = scanner.nextInt();
        return selection;

    }

}