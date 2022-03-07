# Informacje o repozytorium <!-- omit in toc -->

- [Autor](#autor)
- [Kurs](#kurs)
- [Zadania do wykonania](#zadania-do-wykonania)
  - [Lab01 (modules, jlink)](#lab01-modules-jlink)

## Autor

@bainmar

## Kurs

PWr/Java_techniki_zaawansowane

## Zadania do wykonania

### Lab01 (modules, jlink)

Aplikacja umożliwiająca sprawdzanie wskazanych katalogów pod kątem wystąpienia zmian zawartych w nich plikach

założenia:

- aplikacja powinna wyliczać skrót md5 dla każdego badanego pliku
  - przygotowując snapshot bieżącej wersji
  - weryfikacja na podstawie zapamiętanego snapshotu czy w plikach wprowadzono jakieś zmiany
- wykorzystać moduły (wprowadzone w jdk9)
  - moduł biblioteki
  - moduł aplikacji korzystający z biblioteki
- moduły wyeksportować do plików jar
- użyć jlink przygotować minimalne środowisko uruchomieniowe
  - możliwość uruchomienia aplikacji z linii komend korzystając w wygenerowanego środowiska uruchomieniowego
- stworzyć interfejs użytkownika
- do operacji na plikach wykorzystać pakiet java.nio
- program w stylu funkcjonalnym
- dodać menadzer bezpieczeństwa i skorzystać z pliku polityki

[informacje o wykonany zadaniu (readme.md)](lab01_modules_jlink/readme.md)
