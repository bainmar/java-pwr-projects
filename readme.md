# Informacje o repozytorium <!-- omit in toc -->

- [Autor](#autor)
- [Kurs](#kurs)
- [Zadania do wykonania](#zadania-do-wykonania)
  - [Lab01 (modules, jlink)](#lab01-modules-jlink)
  - [Lab02 (weak references)](#lab02-weak-references)
  - [Lab03 (client api)](#lab03-client-api)

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

[dokumentacja wykonanego zadania (readme.md)](lab01_modules_jlink/readme.md)

### Lab02 (weak references)

Aplikacja przeglądanie danych osobowych zapisanych na dysku

Napisz aplikację, która umożliwi przeglądanie danych osobowych zapisanych na dysku. Zakładamy, że dane osobowe zapisywane będą w folderach o nazwach odpowiadających identyfikatorom osób, których dotyczą. Dokładniej, w folderach pojawiać się mają dwa pliki: record.txt (o dowolnej strukturze, w pliku tym zapisane mają być: imię, nazwisko, wiek, ....) oraz ```image.png``` (ze zdjęciem danej osoby, przy czym do celów testowych można zamiast zdjęcia użyć dowolnego obrazka).

Interfejs graficzny tej aplikacji można zrealizować za pomocą dwóch paneli - jednego, przeznaczonego na listę przeglądanych folderów oraz drugiego, służącego do wyświetlania danych osobowych i zdjęcia odpowiedniego do folderu wybranego z listy.

Aplikację należy zaprojektować z wykorzystaniem słabych referencji (ang. weak references). Zakładamy, że podczas przeglądania folderów zawartość plików tekstowych i  plików zawierających obrazki będzie ładowana do odpowiedniej struktury. Słabe referencje powinny pozwolić na ominięcie konieczności wielokrotnego ładowania tej samej zawartości - co może nastąpić podczas poruszanie się wprzód i wstecz po liście folderów.

Aplikacja powinna wskazywać, czy zawartość pliku została załadowana ponownie, czy też została pobrana z pamięci. Wskazanie to może być zrealizowane za pomocą jakiegoś znacznika prezentowanego na interfejsie.

W celu oceny poprawności działania aplikację należy uruchamiać przekazując wirtualnej maszynie parametry ograniczające przydzielaną jej pamięć. Na przykład ```-Xms512m``` (co oznacza minimalnie ```512 MB``` pamięci), ```-Xmx1024m``` (co oznacza maksymalnie ```1GB```).
Należy też przetestować możliwość regulowania zachowania się algorytmu odśmiecania, do czego przydają się opcje ```-XX:+ShrinkHeapInSteps```, ```-XX:-ShrinkHeapInSteps```. Proszę przestudiować, jakie inne atrybuty można przekazać do wirtualnej maszyny, w tym selekcji algorytmu ```-XX:+UseSerialGC```, ```-XX:+UseParNewGC (deprecated)```, ```-XX:+UseParallelGC```, ```-XX:+UseG1GC```.

Architektura aplikacji powinna umożliwiać dołączanie różnych podglądaczy zawartości (czyli klas odpowiedzialnych za renderowanie zawartości plików z danymi), przy czym podglądacze powinny być konfigurowalne (np. poprzez określenie sposobu renderowania czcionek czy obrazków).

[dokumentacja wykonanego zadania (readme.md)](lab02_weak-references//readme.md)

### Lab03 (client api)

Napisz aplikację, która pozwoli skonsumować dane pozyskiwane z serwisu oferującego publiczne restowe API. Ciekawą listę serwisów można znaleźć pod adresem:
```https://rapidapi.com/collection/list-of-free-apis```
Na tej liście jest GeoDB Cities API (```https://rapidapi.com/wirefreethought/api/geodb-cities?endpoint=59908d68e4b075a0d1d6d9ac```). I niech to API posłuży do realizacji ćwiczenia.

Projektowana aplikacja niech służy do testów z wiedzy z geograficznej. Zapytania i odpowiedzi powinny być wyświetlane na graficznym interfejsie, który umożliwi zmianę ustawień językowych (lokalizacji). Wspierane mają być język polski i język angielski. Implementacja opierać się ma na tzw. bundlach (plikach zawierających klucze i wartości) i z wykorzystaniem klas z pakietów ```java.text, java.util```.

Wystarczy zaimplementować kilka przykładowych pytań, np. "Ile miast w kraju .... ma liczbę mieszkańców większą niż ....". 
Niech użytkownik ma możliwość parametryzowania tych zapytania (w miejsce kropek niech wpisywane będą wartości z list wyboru - jeśli da się je pozyskać z serwisu, lub niech będą to wartości wprowadzone wolnym tekstem) oraz zadeklarowanie odpowiedzi.

Niech system sprawdza odpowiedzi wprowadzone przez użytkownika oraz prezentuje poprawne odpowiedzi. Odpowiedź poprawne mogą przyjąć postać, np.:
"W kraju .... istnieją 3 takie miasta", "W kraju .... istnieje 5 takich miast".
Niech w tych odpowiedziach obsłużona będzie odmiana przez liczny. Generalnie chodzi o przetestowanie wariantowego pobierania tekstów z bundli. Do tego przyda się klasa ```ChoiceFormat```. 

Proszę zauważyć, że w ```API``` serwisu jednym z parametrów metod jest ```languageCode```.

[dokumentacja wykonanego zadania (readme.md)](lab03_use_rest_api/readme.md)
