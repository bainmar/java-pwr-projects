# 1. lab01_modules_jlink <!-- omit in toc -->

- [1. Diagramy klas w poszczególnych pakietach](#1-diagramy-klas-w-poszczególnych-pakietach)
  - [1.1. com.bartoszek.md5library (moduł ApplicationLibrary)](#11-combartoszekmd5library-moduł-applicationlibrary)
  - [1.2. com.bartoszek.md5library (moduł ApplicationLibrary - JUnit Tests)](#12-combartoszekmd5library-moduł-applicationlibrary---junit-tests)
  - [1.3. com.bartoszek.consoleapp (moduł ConsoleApp)](#13-combartoszekconsoleapp-moduł-consoleapp)
  - [1.4. com.bartoszek.desktopapp (moduł DesktopApp)](#14-combartoszekdesktopapp-moduł-desktopapp)
- [2. Generowanie modularnych plików jar](#2-generowanie-modularnych-plików-jar)
  - [2.1. Konsola](#21-konsola)
    - [2.1.1. Moduł ApplicationLibrary](#211-moduł-applicationlibrary)
    - [2.1.2. Moduł ConsoleApp](#212-moduł-consoleapp)
    - [2.1.3. Moduł DesktopApp](#213-moduł-desktopapp)
- [3. Uruchomienie](#3-uruchomienie)
  - [3.1. konsola](#31-konsola)
    - [3.1.1. ConsoleApp](#311-consoleapp)
    - [3.1.2. ConsoleApp-jlink](#312-consoleapp-jlink)
    - [3.1.3. DesktopApp](#313-desktopapp)
    - [3.1.4. DesktopApp-jlink](#314-desktopapp-jlink)
- [4. Przypadek użycia (dodawanie, modyfikacja usuwanie)](#4-przypadek-użycia-dodawanie-modyfikacja-usuwanie)

## 1. Diagramy klas w poszczególnych pakietach

### 1.1. com.bartoszek.md5library (moduł ApplicationLibrary)

![](img/readme/2022-03-07-16-42-32.png)

### 1.2. com.bartoszek.md5library (moduł ApplicationLibrary - JUnit Tests)

![](img/readme/2022-03-07-16-43-46.png)

### 1.3. com.bartoszek.consoleapp (moduł ConsoleApp)

![](img/readme/2022-03-07-16-45-30.png)

### 1.4. com.bartoszek.desktopapp (moduł DesktopApp)

![](img/readme/2022-03-07-16-45-05.png)

## 2. Generowanie modularnych plików jar

Do implementacji zadania utworzono projekt mavenowy w celu uniknięcia ręcznego procesu kompilacji klas oraz tworzenia modularnych plików jar. Projekt składa się z trzech modułów (```ApplicationLibrary, ConsoleApp, DesktoApp```), które są dziećmi modułu ```ModulesAndJlink```. ```ConsoleApp``` oraz ```DesktopApp``` zależą od ```ApplicationLibrary```. Maven automatycznie tworzy pliki skompilowane pliki klas w lokalizacji ```target/``` przy użyciu odpowiedniej komendy mavena dla każdego modułu. W tym przetestuję różne możliwości kompilowania kodu źródłowego i tworzenia na ich podstawie modularnych plików jar.

- za pomocą konsoli
- przy użyciu IntelliJ
- za pomocą narzędzia maven

### 2.1. Konsola

#### 2.1.1. Moduł ApplicationLibrary

Struktura programu wygląda następująco.

![](img/readme/2022-03-07-20-27-08.png)

W pierwszej kolejności skompiluję klasy znajdujące się w module ```ApplicationLibrary``` od, którego zależą moduły ```ConsoleApp``` oraz ```DesktopApp```. Struktura modułu ```ApplicationLibrary``` wygląda następująco.

![](img/readme/2022-03-07-20-29-43.png)

Jest to struktura plików powstała przy użyciu narzędzia maven. Maven kompiluje pliku źródłowe do folderu ```/target``` w danym module. Pliki jar są umieszczane w folderach docelowych w ```~/.m2/repository``` w zależności od konfiguracji pliku ```settings.xml``` znajdującego się w lokalizacji ```~/.m2``` (ubuntu).

Struktura plików wygląda w sposób nastepujący.

![](img/readme/2022-03-07-20-35-27.png)

W tym podpunkcie utworzę podobną strukturę przy użyciu komend ```javac``` oraz ```jar```.
Docelowa struktura wygląda w sposób następujący.

![](img/readme/2022-03-07-20-37-55.png)

Znajdując się w folderze głównym ```ApplicationLibrary``` tworzę foldery do przechowywania plików klas oraz pliku jar.

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

#### 2.1.2. Moduł ConsoleApp

W analogiczy sposób jak w podpunkcie powyżej tworzę strukturę ```target_console/```. Wersja finalna wygląda następująco.

![](img/readme/2022-03-07-20-53-04.png)

Ponieważ moduł ```ConsoleApp``` jest zależny od modułu ```ApplicationLibrary``` w procesie kompilacji dołączam wcześniej utworzony modularny plik jar.

```bash
mkdir -p target_console/classes target_console/lib

javac -d target_console/classes/ --module-path ../ApplicationLibrary/target_console/lib/  src/main/java/module-info.java
javac -d target_console/classes/ --module-path ../ApplicationLibrary/target_console/lib/  src/main/java/com/bartoszek/consoleapp/*

jar --create --file target_console/lib/bartoszek-console-app-1.0.0.jar -C target_console/classes/ .
```

#### 2.1.3. Moduł DesktopApp

Znajdując się w folderze głównym modułu ```DesktopApp``` użyto następujących komend.

```bash
javac -d target_console/classes/ --module-path ../ApplicationLibrary/target_console/lib/ src/main/java/module-info.java src/main/java/com/bartoszek/desktopapp/*

jar --create --file target_console/lib/bartoszek-desktop-app-1.0.0.jar --main-class com.bartoszek.desktopapp.SystemTest -C target_console/classes/ .
```

![](img/readme/2022-03-07-22-00-42.png)

## 3. Uruchomienie

Przedstawiono sposoby uruchomienia aplikacji za pomocą konsoli, mavena oraz programu IntelliJ.

### 3.1. konsola

Zawarto przykłady użycia programu za pomocą zainstalowanego JDK oraz utworzonego środowiska uruchomieniowego.

#### 3.1.1. ConsoleApp

Aby uruchomić program bez tworzenia własnego środowiska uruchomieniowego wykonano nastepujące kroki.
Z folderu głównego projektu (```lab01_modules_jlink```) użyto polecenia ```java```. Zakładam, że odpowiednie modularne pliki jar zostały utworzone w poprzednim punkcie.

```bash
java --module-path ApplicationLibrary/target_console/lib:ConsoleApp/target_console/lib --module ConsoleApp/com.bartoszek.consoleapp.SystemTest
```

W konsoli widać wynik działania programu bez gui.

![](img/readme/2022-03-07-23-52-42.png)

#### 3.1.2. ConsoleApp-jlink

Aby stworzyć minimalne środowisko uruchomieniowe użyto komendy jlink z głównego folder aplikacji
```lab01_modules_jlink```.

```bash
marcin:~/java-pwr-projects/lab01_modules_jlink(main)$ jlink --module-path /usr/lib/jvm/java-11-openjdk-amd64/jmods/:ApplicationLibrary/target_console/lib/bartoszek-application-library-1.0.0.jar:ConsoleApp/target_console/lib/bartoszek-console-app-1.0.0.jar --add-modules ConsoleApp --output console_app_custom_runtime --launcher conapp=ConsoleApp/com.bartoszek.consoleapp.SystemTest
```

W celu sprawdzenia ile modułów zawiera wygenerowane środowisko użyto

```bash
marcin:~/java-pwr-projects/lab01_modules_jlink(main)$ console_app_custom_runtime/bin/java --list-modules 
ApplicationLibrary
ConsoleApp
java.base@11.0.13
```

Istnieje możliwość uruchomienia programu na dwa sposoby

- za pomocą komendy java z utworzonego środowiska uruchomieniowego
- za pomocą skryptu utworzonego przy użyciu komendy jlink

```bash
marcin:~/java-pwr-projects/lab01_modules_jlink(main)$ console_app_custom_runtime/bin/java --module ConsoleApp/com.bartoszek.consoleapp.SystemTest
Podaj scieżkę (np. /home/marcin/sciezka)
ścieżka: 
```

Środowisko uruchomieniowe zadbało o ustawienie ```--module-path```

Za pomocą skryptu ```conapp``` program został uruchomiony w sposób następujacy.

```bash
marcin:~/java-pwr-projects/lab01_modules_jlink(main)$ console_app_custom_runtime/bin/conapp
Podaj scieżkę (np. /home/marcin/sciezka)
ścieżka: 
```

#### 3.1.3. DesktopApp

Jak w poprzednim podpunkcie zakładam, że modularne pliki jar, które umieszczam na ścieżce modułów zostały utworzone.

```bash
java --module-path ApplicationLibrary/target_console/lib:DesktopApp/target_console/lib --module DesktopApp
```

Po wykonaniu komendy zostaje wyświetlony graficzny interfejs użytkownika. Komenda nie wymagała podania klasy zawierającej metodę ```main```, gdyż została ona zdefiniowana w manifeście modułu ```DesktopApp``` podczas tworzenia modułu.

#### 3.1.4. DesktopApp-jlink

W sposób analogiczny jak dla podpunktu ```ConsoleApp-jlink``` tworze środowisko uruchomieniowe przy użyciu programu ```jlink```.

```bash
marcin:~/java-pwr-projects/lab01_modules_jlink(main)$ jlink --module-path /usr/lib/jvm/java-11-openjdk-amd64/jmods/:ApplicationLibrary/target_console/lib/bartoszek-application-library-1.0.0.jar:DesktopApp/target_console/lib/bartoszek-desktop-app-1.0.0.jar --add-modules DesktopApp --output desktop_app_custom_runtime --launcher desktopapp=DesktopApp
```

Sprawdzam moduły zawarte w utworzonym w środowisku uruchomieniowym.

```bash
marcin:~/java-pwr-projects/lab01_modules_jlink(main)$ desktop_app_custom_runtime/bin/java --list-modules
ApplicationLibrary
DesktopApp
java.base@11.0.13
java.datatransfer@11.0.13
java.desktop@11.0.13
java.prefs@11.0.13
java.xml@11.0.13
```

Uruchomienie na dwa sposoby:

```bash
marcin:~/java-pwr-projects/lab01_modules_jlink(main)$ desktop_app_custom_runtime/bin/java --module DesktopApp
```

lub

```bash
marcin:~/java-pwr-projects/lab01_modules_jlink(main)$ desktop_app_custom_runtime/bin/desktopapp 
```

## 4. Przypadek użycia (dodawanie, modyfikacja usuwanie)

Folder ```lab01```, który będę śledził w aplikacji składa się z następujących plików

![](img/readme/2022-03-07-16-51-38.png)

Wybieram w aplikacji folder do śledzenia.

![](img/readme/2022-03-07-16-54-09.png)

Następnie usuwam **plik_1.txt**, edytuję plik  **todo.txt** oraz dodaje plik **nowy.txt**

![](img/readme/2022-03-07-16-57-35.png)

Po wciśnięciu przycisku wyświetl w aplikacji są pokazane zmiany w folderze.

![](img/readme/2022-03-07-16-58-44.png)