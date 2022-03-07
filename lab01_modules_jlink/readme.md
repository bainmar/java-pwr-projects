# 1. lab01_modules_jlink

- [1. lab01_modules_jlink](#1-lab01_modules_jlink)
  - [1.1. diagram klas w poszczególnych pakietach](#11-diagram-klas-w-poszczególnych-pakietach)
    - [1.1.1. com.bartoszek.md5library (ApplicationLibrary)](#111-combartoszekmd5library-applicationlibrary)
    - [1.1.2. com.bartoszek.md5library (ApplicationLibrary - JUnit Tests)](#112-combartoszekmd5library-applicationlibrary---junit-tests)
    - [1.1.3. com.bartoszek.consoleapp (ConsoleApp)](#113-combartoszekconsoleapp-consoleapp)
    - [1.1.4. com.bartoszek.desktopapp (DesktopApp)](#114-combartoszekdesktopapp-desktopapp)
  - [1.2. Generowanie modularnych plików jar](#12-generowanie-modularnych-plików-jar)
    - [1.2.1. konsola](#121-konsola)
      - [1.2.1.1. Moduł ApplicationLibrary](#1211-moduł-applicationlibrary)
      - [1.2.1.2. Moduł ConsoleApp](#1212-moduł-consoleapp)
      - [1.2.1.3. Moduł DesktopApp](#1213-moduł-desktopapp)
  - [1.3. Uruchomienie](#13-uruchomienie)
    - [1.3.1. ConsoleApp](#131-consoleapp)
    - [1.3.2. DesktopApp](#132-desktopapp)
  - [1.4. Przypadek użycia (dodawanie, modyfikacja usuwanie)](#14-przypadek-użycia-dodawanie-modyfikacja-usuwanie)

## 1.1. diagram klas w poszczególnych pakietach

### 1.1.1. com.bartoszek.md5library (ApplicationLibrary)

![](img/readme/2022-03-07-16-42-32.png)

### 1.1.2. com.bartoszek.md5library (ApplicationLibrary - JUnit Tests)

![](img/readme/2022-03-07-16-43-46.png)

### 1.1.3. com.bartoszek.consoleapp (ConsoleApp)

![](img/readme/2022-03-07-16-45-30.png)

### 1.1.4. com.bartoszek.desktopapp (DesktopApp)

![](img/readme/2022-03-07-16-45-05.png)

## 1.2. Generowanie modularnych plików jar

Do implementacji zadania utworzono projekt mavenowy w celu uniknięcia ręcznego procesu kompilacji klas oraz tworzenia modularnych plików jar. Projekt składa się z trzech modułów (```ApplicationLibrary, ConsoleApp, DesktoApp```), które są dziećmi modułu ```ModulesAndJlink```. ```ConsoleApp``` oraz ```DesktopApp``` zależą od ```ApplicationLibrary```. Maven automatycznie tworzy pliki jar w lokalizacji ```target/``` przy użyciu odpowiedniej komendy mavena dla każdego modułu. W tym podpunkcie przetestuję tworzenie modularnych plików jar na trzy sposoby:

- za pomocą konsoli
- przy użyciu IntelliJ
- za pomocą narzędzia maven

### 1.2.1. konsola

#### 1.2.1.1. Moduł ApplicationLibrary

Struktura programu wygląda następująco.

![](img/readme/2022-03-07-20-27-08.png)

W pierwszej kolejności skompiluj klasy znajdujące się w module ApplicationLibrary od, którego zależą moduły ConsoleApp oraz DesktopApp. Struktura modułu ApplicationLibrary wygląda następująco.

![](img/readme/2022-03-07-20-29-43.png)

Jest to struktura plików powstała przy użyciu narzędzia maven. Maven kompiluje pliku źródłowe do foldter ```/target``` natomiast pliki jar są umieszczane w folderach docelowych w ```~/.m2/repository``` w zależności od konfiguracji pliku ```settings.xml``` znajdującego się w lokalizacji ```~/.m2```.

Struktura plików wygląda w sposób nastepujący

![](img/readme/2022-03-07-20-35-27.png)

W tym podpunkcie utworzę podobną strukturę przy użyciu komend javac oraz jar.

![](img/readme/2022-03-07-20-37-55.png)

Znajdując się w folderze głównym ApplicationLibrary tworzę foldery

```bash
mkdir -p target_console/classes target_console/lib
```

Kompiluję pliki źródłowe do wcześniej utworzonych folderów

```bash
javac -d target_console/classes/ src/main/java/module-info.java 
javac -d target_console/classes/ src/main/java/com/bartoszek/md5library/*
```

Następnie tworzę modularny plik jar

```bash
jar --create --file target_console/lib/bartoszek-application-library-1.0.0.jar -C target_console/classes/ .
```

#### 1.2.1.2. Moduł ConsoleApp

W analogiczy sposób jak w podpunkcie powyżej tworzę strukturę ```target_console/```. Wersja finalna wygląda następująco.

![](img/readme/2022-03-07-20-53-04.png)

Użyte komendy z folderu głównego modułu ConsoleApp.

```bash
mkdir -p target_console/classes target_console/lib

javac -d target_console/classes/ --module-path ../ApplicationLibrary/target_console/lib/  src/main/java/module-info.java
javac -d target_console/classes/ --module-path ../ApplicationLibrary/target_console/lib/  src/main/java/com/bartoszek/consoleapp/*
```

Ponieważ moduł ConsoleApp jest zależny od modułu ApplicationLibrary w procesie kompilacji dołączam utworzony modularny plik jar w poprzednim podpunkcie. Następnie tworzę modularny plik jar.

```bash
jar --create --file target_console/lib/bartoszek-console-app-1.0.0.jar -C target_console/classes/ .
```

W dokładnie ten sam sposób postępuję w przypadku modułu DesktopApp

#### 1.2.1.3. Moduł DesktopApp

Znajdując się w folderze głównym modułu DesktopApp użyto następujących komend.

```bash
javac -d target_console/classes/ --module-path ../ApplicationLibrary/target_console/lib/ src/main/java/module-info.java src/main/java/com/bartoszek/desktopapp/*

jar --create --file target_console/lib/bartoszek-desktop-app-1.0.0.jar --main-class com.bartoszek.desktopapp.SystemTest -C target_console/classes/ .
```

![](img/readme/2022-03-07-22-00-42.png)

## 1.3. Uruchomienie

Uruchomienie programu z i bez użycia środowiska uruchomieniowego

### 1.3.1. ConsoleApp

Aby uruchomić program bez tworzenia własnego środowiska uruchomieniowego wykonano nastepujące kroki.
Z folderu głównego projektu (lab01_modules_jlink) użyto polecenia java. Zakładam, że odpowiednie modularne pliki jar zostały stworzone w poprzednich podpunktach

```bash
java --module-path ApplicationLibrary/target_console/lib:ConsoleApp/target_console/lib --module ConsoleApp/com.bartoszek.consoleapp.SystemTest
```

W konsoli widać wynik działania programu bez gui.

![](img/readme/2022-03-07-23-52-42.png)


### 1.3.2. DesktopApp

Jak w poprzednim podpunkcie zakładam, że modularne pliki jar, które umieszczam na ścieżce modułów zostały utworzone.

```bash
java --module-path ApplicationLibrary/target_console/lib:DesktopApp/target_console/lib --module DesktopApp
```

Po wykonaniu komendy zostaje wyświetlony graficzny interfejs użytkownika. Komenda nie wymagała podania klasy main, gdyż została ona zdefiniowana w manifeście modułu DesktopApp podczas tworzenia modułu.

## 1.4. Przypadek użycia (dodawanie, modyfikacja usuwanie)

Folder lab01, który będę śledził w aplikacji składa się z następujących plików

![](img/readme/2022-03-07-16-51-38.png)

Wybieram w aplikacji, folder do śledzenia.

![](img/readme/2022-03-07-16-54-09.png)

Następnie usuwam **plik_1.txt**, edytuję plik to **todo.txt** oraz dodaje plik **nowy.txt**

![](img/readme/2022-03-07-16-57-35.png)

Po wciśnięciu przycisku wyświetl w aplikacji są wyświetlane zmiany w folderze.

![](img/readme/2022-03-07-16-58-44.png)