# Informacje o repozytorium <!-- omit in toc -->

- [Autor](#autor)
- [Kurs](#kurs)
- [Zadania do wykonania](#zadania-do-wykonania)
  - [Lab01 (modules, jlink)](#lab01-modules-jlink)
  - [Lab02 (weak references)](#lab02-weak-references)

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

[szczegółowa dokumentacja wykonanego zadania (readme.md)](lab01_modules_jlink/readme.md)

### Lab02 (weak references)

Aplikacja przeglądanie danych osobowych zapisanych na dysku

Napisz aplikację, która umożliwi przeglądanie danych osobowych zapisanych na dysku. Zakładamy, że dane osobowe zapisywane będą w folderach o nazwach odpowiadających identyfikatorom osób, których dotyczą. Dokładniej, w folderach pojawiać się mają dwa pliki: record.txt (o dowolnej strukturze, w pliku tym zapisane mają być: imię, nazwisko, wiek, ....) oraz image.png (ze zdjęciem danej osoby, przy czym do celów testowych można zamiast zdjęcia użyć dowolnego obrazka).

Interfejs graficzny tej aplikacji można zrealizować za pomocą dwóch paneli - jednego, przeznaczonego na listę przeglądanych folderów oraz drugiego, służącego do wyświetlania danych osobowych i zdjęcia odpowiedniego do folderu wybranego z listy.

Aplikację należy zaprojektować z wykorzystaniem słabych referencji (ang. weak references). Zakładamy, że podczas przeglądania folderów zawartość plików tekstowych i  plików zawierających obrazki będzie ładowana do odpowiedniej struktury. Słabe referencje powinny pozwolić na ominięcie konieczności wielokrotnego ładowania tej samej zawartości - co może nastąpić podczas poruszanie się wprzód i wstecz po liście folderów.

Aplikacja powinna wskazywać, czy zawartość pliku została załadowana ponownie, czy też została pobrana z pamięci. Wskazanie to może być zrealizowane za pomocą jakiegoś znacznika prezentowanego na interfejsie.

W celu oceny poprawności działania aplikację należy uruchamiać przekazując wirtualnej maszynie parametry ograniczające przydzielaną jej pamięć. Na przykład -Xms512m (co oznacza minimalnie 512 MB pamięci), -Xmx1024m (co oznacza maksymalnie 1GB).
Należy też przetestować możliwość regulowania zachowania się algorytmu odśmiecania, do czego przydają się opcje -XX:+ShrinkHeapInSteps, -XX:-ShrinkHeapInSteps. Proszę przestudiować, jakie inne atrybuty można przekazać do wirtualnej maszyny, w tym selekcji algorytmu -XX:+UseSerialGC, -XX:+UseParNewGC (deprecated), -XX:+UseParallelGC, -XX:+UseG1GC.

Architektura aplikacji powinna umożliwiać dołączanie różnych podglądaczy zawartości (czyli klas odpowiedzialnych za renderowanie zawartości plików z danymi), przy czym podglądacze powinny być konfigurowalne (np. poprzez określenie sposobu renderowania czcionek czy obrazków).

Proszę dodać do źródeł plik readme.md z wnioskami co do stosowalności opcji wirtualnej maszyny.

Proszę sięgnąć do materiałów http://tomasz.kubik.staff.iiar.pwr.wroc.pl/dydaktyka/Java/UnderstandingWeakReferences.pdf
