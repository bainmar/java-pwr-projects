# Informacje o repozytorium <!-- omit in toc -->

- [Autor](#autor)
- [Kurs](#kurs)
- [Zadania do wykonania](#zadania-do-wykonania)
  - [Lab01 (modules, jlink)](#lab01-modules-jlink)
  - [Lab02 (weak references)](#lab02-weak-references)
  - [Lab03 (client api)](#lab03-client-api)
  - [Lab04](#lab04)
  - [Lab05](#lab05)
  - [Lab06](#lab06)
  - [Lab07](#lab07)
  - [Lab08](#lab08)
  - [Lab09](#lab09)
  - [Lab10](#lab10)
  - [Lab11](#lab11)
  - [Lab13](#lab13)
  - [Lab14](#lab14)

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

### Lab04

Napisz aplikację, która umożliwi zlecanie wykonywania zadań instancjom klas ładowanym dynamicznie własnym ładowaczem klas. Zadanie to należy zrealizować za pomocą reflection API.

Aplikacja powinna udostępniać interfejs, na którym będzie można, wielokrotnie, wprowadzić tekst (dane do przetworzenia), wybrać klasę do załadowania (której instancja ten tekst przetworzy),  zlecić zadanie przetwarzania i monitorować jego przebiec, wyświetlić wynik przetwarzania, wyładować załadowane klasy.

Klasy przetwarzające tekst powinny implementować interfejs processing.Processor o kodzie jak niżej. W kodzie tym zdokumentowano znaczenie zawartych w nim metod oraz ich parametrów. Ponadto dostarczono zdokumentowany kod źródłowy innych wymaganych klas.

Należy dostarczyć przynajmniej 3 różne klasy przetwarzające tekst. Klasy te powinny być skompilowane odrębnie od samej aplikacji (podczas budowania aplikacja nie powinna ona ich znać). Można założyć, że kod bajtowy tych klas będzie umieszczany w katalogu, do którego aplikacja będzie miała dostęp. Ścieżka do tego katalogu powinna być parametrem ustawianym w aplikacji w trakcie jej działania. Wartością domyślną dla ścieżki niech będzie katalog, w którym uruchomiono aplikację. Aplikacja powinna odczytać zawartość tego katalogu i załadować własnym ładowaczem odnalezione klasy. Zakładamy, że podczas działania aplikacji będzie można "dorzucić" nowe klasy do tego katalogu (należy więc pomyśleć o pewnego rodzaju "odświeżaniu").

Wybieranie klas powinno odbywać się poprzez listę wyświetlającą nazwy załadowanych klas. Nazwom tym niech towarzyszą opisy pozyskane metodą getInfo() utworzonych instancji tych klas.

Zlecanie zadań instancjom klas powinno odbywać się poprzez metodę submitTask(String task, StatusListner sl). 
W metodzie tej należy podać słuchacza typu StatusListener, który będzie otrzymywał informacje o zmianie statusu przetwarzania.
Do reprezentacji statusu przetwarzania służy klasa processing.Status (klasę tę zadeklarowano tak, aby po utworzeniu statusu jego atrybuty były tylko do odczytu). 

Proszę przetwarzanie zaimplementować w wątku z opóźnieniami, by dało się monitorować bieżący status przetwarzania.
Do monitorowania statusów przetwarzania i wyświetlania wyników można użyć osobnej listy (monitora zadań) wyświetlanej na interfejsie aplikacji.

Proszę napisać własny (!) ładowacz klas. Ładowacz ten może być klasą, do której przekazana zostanie ścieżka położenia kodów bajtowych ładowanych klas z algorytmami przetwarzania. Własny ładowacz nie może rozszerzać klasy URLClassLoader.

Aplikację można stworzyć korzystając z JDK1.8. Można też spróbować zaimplementować ją korzystając z JDK11 (wtedy należy zastanowić się, jak wykorzystać moduły oraz deklaracje services, ponadto należy zastanowić się, jak wtedy należy zaimplementować własny ładowacz klas).

Uwaga: klasa zostanie wyładowana, gdy znikną wszystkie referencje od jej instancji oraz gdy zniknie referencja do ładowacza, który tę klasę załadował (i zadziała odśmiecanie). Proszę monitorować liczbę załadowanych i wyładowanych klas za pomocą jconsole lub jmc.

Proszę zajrzeć do materiałów wymienionych przy wykładzie o refleksji i ładowaczach klas.

Wspomniane kody:

```java
package processing;

public interface Processor {

	/**
	 * Metoda służąca do zlecania zadań
	 * 
	 * @param task - tekst podlegający przetwarzaniu (reprezentuje zadanie)
	 * @param sl   - słuchacz, który będzie informowany o statusie przetwarzania
	 * @return - wartość logiczną mówiącą o tym, czy zadanie przyjęto do
	 *         przetwarzania (nie można zlecić kolejnych zadań dopóki bieżące
	 *         zadanie się nie zakończyło i nie został pobrany wynik przetwarzania
	 */
	boolean submitTask(String task, StatusListener sl);

	/**
	 * Metoda służąca do pobierania informacji o algorytmie przetwarzania (np. "Algorytm zliczający samogłoski")
	 * 
	 * @return - informacja o algorytmie przetwarzania 
	 */
	String getInfo();

	/**
	 * Metoda służąca do pobierania wyniku przetwarzania
	 * 
	 * @return - tekst reprentujący wynik przetwarzania
	 */
	String getResult();
}

package processing;
public interface StatusListener {
	/**
	 * Metoda dostarczająca słuchaczowi status przetwarzania zadania
	 * s - status przetwarzania zadania
	 */
	void statusChanged(Status s);
}

package processing;
public class Status {
	
	/**
	 * nazwa zadania
	 */
	private String taskName;	
	/**
	 * progres w procentach
	 */
	private int progress;

	public int getProgress() {
		return progress;
	}

	public String getTaskName() {
		return taskName;
	}

	public Status(String taskName, int progress) {
		this.taskName = taskName;
		this.progress = progress;
	}
}
```

### Lab05

Zaimplementuj aplikację z graficznym interfejsem pozwalającą przeprowadzić analizę skupień na danych tabelarycznych.
Aplikacja ta powinna umożliwiać:

- wyświetlanie/edytowanie danych tabelarycznych;
- wybranie algorytmu, jakim będą one przetwarzane;
- wyświetlenie wyników przetwarzania.

W trakcie implementacji należy wykorzystać interfejs dostarczyciela serwisu (ang. Service Provider Interface, SPI).
Dokładniej, stosując podejście SPI należy zapewnić aplikacji możliwość załadowania klas implementujących zadany interfejs
już po zbudowaniu samej aplikacji. 
Klasy te (z zaimplementowanymi wybranymi algorytmami analizy skupień) mają być dostarczane w plikach jar umieszczanych w ścieżce. 
Należy stworzyć dwie wersje projektu: standardową oraz modularną.

Aby zapoznać się z problemem proszę sięgnąć do przykładowych projektów w archiwum udostępnionym pod adresem:
    ```http://tomasz.kubik.staff.iiar.pwr.wroc.pl/dydaktyka/Java/ServiceProviderInterfaceWorkspace.zip```

W implementacji należy wykorzystać pakiet ex.api, zawierającym klasy o kodzie źródłowy pokazanym poniżej.

UWAGA: Przychylając się do prośby uczestników kursu technologia JBeans zostanie omówiona tylko na wykładzie 
(nie będzie osobnego, poświeconego jej laboratorium).

Trochę informacji o SPI można znaleźć pod adresem:
    ```https://www.baeldung.com/java-spi```
Porównanie SPI ze SpringBoot DI zamieszczono pod adresem:
    ```https://itnext.io/serviceloader-the-built-in-di-framework-youve-probably-never-heard-of-1fa68a911f9b```

--------------------------------

```java
package ex.api;

/**
 * Interfejs serwisu pozwalającego przeprowadzić analizę skupień.
 * Zakładamy, że serwis działa asynchronicznie.
 * Na początek należy do serwisu załadować dane.
 * Potem można z serwisu pobrać wyniki analizy.
 * W przypadku niepowodzenia wykonania jakiejś metody wyrzucony zostanie wyjątek.
 * 
 * @author tkubik
 *
 */
public interface ClusterAnalysisService {
	public void setOptions(String[] options) throws ClusteringException; // ustawia opcje
	// metoda zwracająca nazwę algorytmu
	public String getName();                                   
	// metoda pozwalająca przekazać dane do analizy
	// wyrzuca wyjątek, jeśli aktualnie trwa przetwarzanie danych
	public void submit(DataSet ds) throws ClusteringException; 
	// metoda pozwalająca pobrać wynik analizy
    // zwraca null - jeśli trwa jeszcze przetwarzanie lub nie przekazano danych do analizy
	// wyrzuca wyjątek - jeśli podczas przetwarzania doszło do jakichś błędów
	// clear = true - jeśli wyniki po pobraniu mają zniknąć z serwisu
    public DataSet retrieve(boolean clear) throws ClusteringException;   
}
--------------------------------

package ex.api;

public class ClusteringException extends Exception {
	private static final long serialVersionUID = 1L;
    ClusteringException(String msg){
    	super(msg);
    }
}

--------------------------------
package ex.api;
/**
 * Klasa reprezentująca zbiór danych w postaci tabelarycznej.
 * Przechowuje nagłówek (jednowymiarowa tablica z nazwami kolumn) 
 * oraz dane (dwuwymiarowa tablica, której wiersze reprezentują wektory danych).
 * Zakładamy, że będą zawsze istnieć przynajmniej dwie kolumny o nazwach:
 * "RecordId" - w kolumnie tej przechowywane są identyfikatory rekordów danych;
 * "CategoryId" - w kolumnie tej przechowywane są identyfikatory kadegorii rekordów danych (wynik analizy skupień).
*
 * @author tkubik
 *
 */
public class DataSet {
	private String[] header = {}; 
	private String[][] data = {{}};

	private <T> T[][] deepCopy(T[][] matrix) {
	    return java.util.Arrays.stream(matrix).map(el -> el.clone()).toArray(i -> matrix.clone());
	}
	
	public String[] getHeader() {
		return header;
	}
	public void setHeader(String[] header) {
		this.header = header.clone();
	}
	public String[][] getData() {
		return data;
	}
	public void setData(String[][] data) {
		this.data = deepCopy(data);
	}
}
```

--------------------------------

### Lab06

Zaimplementuj rozproszony system imitujący działanie sieci tablic reklamowych, na których cyklicznie wyświetlane są zadane teksty (tj. przez określony czas widać jedno hasło reklamowe, po czym następuje zmiana).

W systemie tym wyróżnione mają być trzy typy aplikacji (klas z metodą main):

- Manager (Menadżer) - odpowiedzialna za przyjmowanie od klientów zamówień wyświetlanie haseł reklamowych oraz przesyłanie tych haseł na tablice reklamowe
- Client (Klient) - odpowiedzialna za zgłaszanie menadżerowi zamówień lub ich wycofywanie
- Billboard (Tablica) - odpowiedzialna za wyświetlanie haseł, dowiązująca się do menadżera, który może zatrzymać i uruchomić wyświetlanie haseł

Podczas uruchomienia systemu należy utworzyć: 1 instancję Menadżera, przynajmniej 2 instancje Klienta, przynajmniej 3 instancje Tablicy. 
Muszą to być osobne aplikacje (nie mogą korzystać z tej samej przestrzeni adresowej!!!). Aplikacje powinny być parametryzowane na interfejsie lub w linii komend (by dało się je uruchomić na różnych komputerach).

Poniżej znajdują się kody interfejsów oraz kod klasy, które należy użyć we własnej implementacji. Kody te zawierają opisy, które powinny pomóc w zrozumieniu ich zastosowania.
 
Uwaga - Proszę uważać na niebezpieczeństwo konfliktu portów.
      - Proszę użyć dokładnie tego samego kodu co niżej (bez żadnych modyfikacji!!)

```java
////////////////////////////
package bilboards;

import java.io.Serializable;
import java.time.Duration; // available since JDK 1.8

/**
 * Klasa reprezentująca zamówienie wyświetlania ogłoszenia o zadanej treści
 * przez zadany czas ze wskazaniem na namiastke klienta, przez którą można
 * przesłać informacje o numerze zamówienia w przypadku jego przyjęcia
 * 
 * @author tkubik
 *
 */
public class Order implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String advertText;
	public Duration displayPeriod;
	public IClient client;
}
////////////////////////////
package bilboards;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.Duration;

/**
 * Interfejs, który powinna implementować aplikacja pełniąca rolę tablicy ogłoszeniowej (nazwijmy ją Tablica).
 * Aplikacja ta powinna wyświetlać cyklicznie teksty ogłoszeń dostarczane metodą addAdvertisement.
 * Za wyświetlanie tych ogłoszeń powinien odpowiadać osobny wątek.
 * Wątek powinien mieć dostęp do bufora na ogłoszenia, którego pojemność i liczbę wolnych miejsc
 * zwraca metoda getCapacity.
 * Za dodawanie ogłoszenia do bufora odpowiada metoda addAdvertisment. 
 * Z chwilą pierwszego wyświetlenia ogłoszenia na tablicy powinien zacząć zliczać się czas jego wyświetlania.
 * Usuwanie ogłoszenia może nastąpić z dwóch powodów: i) ogłoszenie może zostać usunięte na skutek
 * wywołania metody removeAdvertisement (przez Menadżera); ii) ogłoszenie może zostać usunięte, gdy skończy się przyznany 
 * mu czas wyświetlania na tablicy (przez wątek odpowiedzialny w Tablicy za cykliczne wyświetlanie testów).
 * Usuwanie ogłoszeń z bufora i ich dodawanie do bufora wiąże się z odpowiednim zarządzaniem
 * podległą strukturą danych
 * W "buforze" mogą się robić "dziury", bo ogłoszenia mogą mieć przyznany różny czas wyświetlania. 
 * Należy więc wybrać odpowiednią strukturę danych, która pozwoli zapomnieć o nieregularnym występowaniu tych "dziur").
 * Metoda start powinna dać sygnał aplikacji, że należy rozpocząć cykliczne wyświetlanie ogłoszeń.
 * Metoda stop zatrzymuje wyświetlanie ogłoszeń.
 * Metody start i stop można odpalać naprzemiennie, przy czym nie powinno to resetować zliczonych czasów wyświetlania 
 * poszczególnych ogłoszeń.
 * Uwaga: Tablica powininna być sparametryzowany numerem portu i hostem rejestru
 * rmi, w którym zarejestrowano namiastkę Menadżera, oraz nazwa, pod którą
 * zarejestrowano tę namiastkę.
 * Jest to potrzebne, by Tablica mogła dowiązać się do Menadżera. 
 * Tablica robi to wywołując metodę bindBillboard (przekazując jej swoją namiastkę typu IBillboard).
 * Otrzymuje przy tym identyfikator, który może użyć, by się mogła wypisać z Menadżera 
 * (co może stać się podczas zamykania tablicy).
 * 
 * @author tkubik
 *
 */

public interface IBillboard extends Remote {
	/**
	 * Metoda dodająca tekst ogłoszenia do tablicy ogłoszeniowej (wywoływana przez
	 * Menadżera po przyjęciu zamówienia od Klienta)
	 * 
	 * @param advertTextn   - tekst ogłoszenia, jakie ma pojawić się na tablicy
	 *                      ogłoszeniowej
	 * @param displayPeriod - czas wyświetlania ogłoszenia liczony od pierwszego
	 *                      jego ukazania się na tablicy ogłoszeniowej
	 * @param orderId       - numer ogłoszenia pod je zarejestrowano w menadżerze
	 *                      tablic ogłoszeniowych
	 * @return - zwraca true, jeśli udało się dodać ogłoszenie lub false w
	 *         przeciwnym wypadku (gdy tablica jest pełna)
	 * @throws RemoteException
	 */
	public boolean addAdvertisement(String advertText, Duration displayPeriod, int orderId) throws RemoteException;

	/**
	 * Metoda usuwająca ogłoszenie z tablicy (wywoływana przez Menadżera po
	 * wycofaniu zamówienia przez Klienta)
	 * 
	 * @param orderId - numer ogłoszenia pod jakim je zarejestrowano w menadżerze
	 *                tablic ogłoszeniowych
	 * @return - zwraca true, jeśli operacja się powiodła lub false w przeciwnym
	 *         wypadku (gdy nie ma ogłoszenia o podanym numerze)
	 * @throws RemoteException
	 */
	public boolean removeAdvertisement(int orderId) throws RemoteException;

	/**
	 * Metoda pobierająca informację o zajętości tablicy (wywoływana przez
	 * Menadżera)
	 * 
	 * @return - zwraca tablicę dwóch liczb całkowitych - pierwsza to pojemność
	 *         bufora tablicy, druga to liczba wolnych w nim miejsc
	 * @throws RemoteException
	 */
	public int[] getCapacity() throws RemoteException;

	/**
	 * Metoda pozwalająca ustawić czas prezentacji danego tekstu ogłoszenia na
	 * tablicy w jednym cyklu (wywoływana przez Menadżera). Proszę nie mylić tego z
	 * czasem, przez jaki ma być wyświetlany sam tekst ogłoszenia. Prezentacja
	 * danego hasła musi być powtórzona cyklicznie tyle razy, aby sumaryczny czas
	 * prezentacji był równy lub większy zamówionemu czasowi wyświetlania tekstu
	 * ogłoszenia.
	 * 
	 * @param displayInterval - definiuje czas, po którym następuje zmiana hasła
	 *                        wyświetlanego na tablicy. Odwrotność tego parametru
	 *                        można interpetować jako częstotliwość zmian pola
	 *                        reklamowego na Tablicy.
	 * @throws RemoteException
	 */
	public void setDisplayInterval(Duration displayInterval) throws RemoteException;

	/**
	 * Metoda startująca cykliczne wyświetlanie ogłoszeń (wywoływana przez
	 * Menadżera)
	 * 
	 * @return - zwraca true, jeśli zostanie poprawnie wykonana lub false w
	 *         przeciwnym wypadku
	 * @throws RemoteException
	 */
	public boolean start() throws RemoteException;

	/**
	 * Metoda stopująca cykliczne wyświetlanie ogłoszeń (wywoływana przez Menadżera)
	 * 
	 * @return - zwraca true, jeśli zostanie poprawnie wykonana lub false w
	 *         przeciwnym wypadku
	 * @throws RemoteException
	 */
	public boolean stop() throws RemoteException;
}
////////////////////////////
package bilboards;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfejs, który powinien zaimplementować klient (nazwijmy tę alikację
 * Klient) komunikujący się z menadżerem tablic. Klient powinien mieć interfejs
 * pozwalający: i) definiować zamówienia wyświetlania ogłoszenia (tekst
 * ogłoszenia, czas wyświetlania) ii) składać zamówienia wyświetlania ogłoszenia
 * Menadżerowi, iii) wycofywać złożone zamówienia.
 * 
 * Przy okazji składania zamówienia wyświetlania ogłoszenia Klient przekazuje
 * Menadżerowi namiastkę IClient. Menagdżer użyje tej namiastki, by zwrotnie
 * przekazać klientowi numer zamówienia (jeśli oczywiście zamówienie zostanie
 * przyjęte). Ma to działać podobnie jak ValueSetInterface z przykładu RMITest.
 * 
 * Numery zamówień i treści ogłoszeń przyjętych przez Menadżera powinny być
 * widoczne na interfejsie Klienta. Klient powinien sam zadbać o usuwanie
 * wpisów, których okres wyświetlania zakończył się (brak synchronizacji w tym
 * względzie z menadżerem)
 * 
 * Uwaga: Klient powinien być sparametryzowany numerem portu i hostem rejestru
 * rmi, w którym zarejestrowano namiastkę Menadżera, oraz nazwa, pod którą
 * zarejestrowano tę namiastkę.
 * 
 * @author tkubik
 *
 */
public interface IClient extends Remote { // host, port, nazwa
	/**
	 * Metoda służąca do przekazania numeru przyjętego zamówienia (wywoływana przez
	 * Menadżera na namiastce klienta przekazanej w zamówieniu)
	 * 
	 * @param orderId
	 * @throws RemoteException
	 */
	public void setOrderId(int orderId) throws RemoteException;
}
////////////////////////////
package bilboards;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfejs, który powinna implementować aplikacja pełniąca rolę menadżera
 * tablic (nazwijmy ją Menadżer). Menadżer powinien wyświetlać wszystkie
 * dowiązane do niego tablice oraz ich bieżący stan. Tablice dowiązują się do i
 * odwiązują od menadżera wywołując jego metody bindBillboard oraz
 * unbindBillboard. Z menadżerem może połączyć się Klient przekazując mu
 * zamówienie wyświetlania danego ogłoszenia przez zadany czas. Robi to
 * wywołując metodę placeOrder. Menadżer, jeśli przyjmie zamówienie, zwraca
 * Klientowi numer zamówienia wykorzystując przy tym przekazaną w zamówieniu
 * namiastke. Klient rozpoznaje, czy przyjęto zamówienie, sprawdzając wynik
 * zwracany z metody placeOrder.
 * Zamówienia natychmiast po przyjęciu trafiają na dowiązane Tablice mogące w danej chwili przyjąć ogłoszenie do wyświetlania.
 * Jeśli w danej chwili nie ma żadnej wolnej Tablicy zamówienie nie powinno być przyjęte do realizacji.
 * Aby przekonać się o stanie tablic Menadżer wywołuje metody ich namiastek getCapacity.
 * Wystarczy, że istnieje jedna wolna tablica by przyjąć zamówienie.
 * Na ile tablic trafi dane zamówienie decyduje dostępność wolnych miejsc w chwili zamówienia.  
 * 
 * Uwaga: Menadżer powinien utworzyć lub połączyć się z rejestrem rmi o
 * wskazanym numerze portu. Zakładamy, że rejestr rmi działa na tym samym
 * komputerze, co Menadżer (może być częścią aplikacji Menadżera).
 * Menadżer rejestruje w rejestrze rmi posiadaną
 * namiastke IManager pod zadaną nazwą (nazwa ta nie może być na twardo
 * zakodowanym ciągiem znaków). Nazwa namiastki menadżera, host i port na którym
 * działa rejest rmi powinny być dostarczone Klientowi (jako parmetry) oraz Tablicom.
 * 
 * @author tkubik
 *
 */
public interface IManager extends Remote { // port, nazwa, GUI

	/**
	 * Metoda dowiązywania namiastki Tablicy do Menadżera (wywoływana przez Tablicę)
	 * 
	 * @param billboard - dowiązywana namiastka
	 * @return - zwraca numer przyznany namiastce w Menadżerze
	 * @throws RemoteException
	 */
	public int bindBillboard(IBillboard billboard) throws RemoteException;

	/**
	 * Metoda odwiązująca namiastkę Tablicy z Menadżera (wywoływana przez Tablicę)
	 * @param billboardId - numer odwiązywanej namiastki
	 * @return
	 * @throws RemoteException
	 */
	public boolean unbindBillboard(int billboardId) throws RemoteException;

	/**
	 * Metoda służąca do składania zamówienia wyświetlania ogłoszenia (wywoływana przez Klienta)
	 * @param order - szczegóły zamówienia (wraz z tekstem ogłoszenia, czasem jego wyświetlania i namiastką klienta)
	 * @return - zwraca true jeśli przyjęto zamówienie oraz false w przeciwnym wypadku
	 * @throws RemoteException
	 */
	public boolean placeOrder(Order order) throws RemoteException;

	/**
	 * Metoda służąca do wycofywania zamówienia (wywoływana przez Klienta)
	 * @param orderId - numer wycofywanego zamówienia 
	 * @return - zwraca true jeśli wycofano zamówienie oraz false w przeciwnym wypadku
	 * @throws RemoteException
	 */
	public boolean withdrawOrder(int orderId) throws RemoteException;
}
////////////////////////////
```

### Lab07

Napisz program, który pozwoli zasymulować działanie narzędzia do obsługi klientów firmy będącej dostawcą Internetu (zakładamy, że program będzie działał w kontekście dostawcy Internetu).

Zadanie należy zrealizować wykorzystując relacyjną bazę danych. 
Zalecane jest użycie sqlite (jest to baza danych w pliku, nie ma potrzeby istalowania żadnego dodatkowego serwisu). 

Podczas realizacji zadania należy skorzystać z wzorca projektowego DAO oraz możliwości, jakie daje JDBC. Proszę pamiętać, by parametryzować zapytania SQL (nie wolno budować zapytań poprzez "sklejanie" kolejnych ciągów znaków). Proszę pamiętać o przewijalności zbioru wynikowego.

Opcjonalnie można skorzystać z mapowania ORM (technologia JPA, do czego można użyć framework Hibernate). Wtedy jednak należy zastosować wzorzec projektowy z użyciem serwisów.

Zakładamy, że w bazie danych będą przechowywane następujące informacje (jest to model mocno uproszczony w porównaniu do modeli spotykanych w rzeczywistości):

```txt
    Kient - imię, nazwisko, numer klienta
    Instalacja - adres, numer routera, typ usługi
    Naliczone należności - termin płatności, kwota do zapłaty
    Dokonane wpłaty - termin wpłaty, kwota wpłaty
    Cennik - typ usługi, cena
``` 

Dla jednego klienta może być założonych wiele instalacji. 
Należności i wpłaty dotyczą danej instalacji. 
Należności naliczane są w trybie miesięcznym. 
Cennik dotyczy wszystkich typów usługi (aktywnych, wygasłych, nowych).

Program powinien:

    - umożliwiać ręczne zarządzanie danymi klientów, danymi instalacji oraz cennikiem
    - automatycznie naliczać należności i wysyłać monity o kolejnych płatnościach (wystarczy, że będzie pisał do pliku z logami monitów, upływ czasu należy zasymulować).
    - automatycznie eskalować monity w przypadku braku terminowej wpłaty (wystarczy, że będzie pisał do pliku z logami ekalowanych monitów, upływ czasu należy zasymulować)
    - umożliwiać ręczne rejestrowanie wpłat oraz nanoszenie korekt
    - umożliwiać przeglądanie należności i wpłat
 
Program może działać w trybie konsolowym, choć dużo lepiej wyglądałoby stworzeni interfejsu graficznego.

### Lab08

Zaimplementuj serwis komunikujący się protokołem SOAP, który pozwoli wykorzystać logikę biznesową (z warstwą persystencji) zaprojektowaną w ramach lab07. Serwis ten ma udostępniać API pozwalające, w wersji minimum, na:
 
    - utworzenie w bazie danych nowego klienta
    - pobranie wszystkich klientów
    - wpisane do bazy danych nowej instalacji
    - pobranie informacje o wszystkich instalacjach danego klienta
    - pobranie informacje o wszystkich naliczonych należnościach z zadanego okresu dla danego klienta
  
Podczas implementacji można wykorzystać dowolną z metod przetwarzania komunikatów soapowych. Z uwagi jednak na dużą automatyzację zalecane jest wykorzystanie JAX-WS/Apache CXF (można wybrać podejście bottomUp lub topDown).
Tworzony projekt ma być projektem eclipsowym (DynamicWebProject) lub jeszcze lepiej - projektem mavenowym.
Clientem do serwisu może być narzędzie SoapUI (nie trzeba go implementować).

Istnieje niezły tutorial o tym, jak stworzyć projekt mavenowy, który korzysta z CXF (```https://www.cse.unsw.edu.au/~cs9322/labs/lab01/index.html```).
Jest też (może nawet lepszy) tutorial na stronie: ```https://www.tutorialspoint.com/apache_cxf/apache_cxf_with_wsdl_first.htm```

### Lab09

Zaimplementuj aplikację pełniącą rolę czatu. Wystarczy, że aplikacja ta pozwoli na komunikację pomiędzy dwoma użytkownikami. Na interfejsie graficznym tej aplikacji może pojawić się pole tekstowe na wprowadzanie wiadomości oraz przycisk "Wyślij" oraz pole tekstowe, na którym pojawiać się będą przychodzące wiadomości. Aplikacja ta powinna być parametryzowana (tj. na jej interfejsie powinno dać się zdefiniować host i port użytkownika, z którym dany użytkownik chce wymieniać informacje). Komunikacja może odbywać się porzez gniazda TCP/IP.

Program o podobnej funkcji był zaprezentowany podczas omawiania gniazd w trakcie kursu Języki programowania (wraz ze źródłami kodu). Może on służyć jako inspiaracja co własnej implementacji. Jednak w niniejszym ćwiczeniu chodzi o coś więcej.

Mianowicie:

    - cała logika związana z komunikacją powinna być zaimplementowana jako osobna biblioteka, eksportowana do pliku jar. Plik ten powinien być podpisany cyfrowo (sprawę tworzenia bibliotek podpisywanych cyfrowo omówiono na wykładzie).
    - w bibliotece powinny być dostępne funkcje pozwalające zaszyfrować przesyłane komunikaty kluczem publicznym odbierającego i odszyfrowywać otrzymane komunikaty kluczem prywatnym nadającego. 
    - oczywiście by zaproponowane asymetryczne szyfrowanie było możliwe wysyłający wiadomość musi otrzymać najpierw klucz publiczny odbierającego, sam zaś wysyłający powinien mieć dostęp do własnego klucza prywatnego. Należy więc zaproponować odpowiedni sposób parametryzacji. 
    - obsługa komunikacji powinna odbywać się za pośrednictwem graficznego interfejsu użytkownika dostarczanego przez zaimplementowaną osobno aplikację. Aplikacja ta powinna pracować pod kontrolą menadżera bezpieczeństwa (musi korzystać z pliku polityki) oraz wykorzystywać wspomnianą bibliotekę podpisaną cyfrowo. Proszę się zastanowić, jak uwzględnić w niej przekazywanie/wskazywanie kluczy.
    - sama aplikacja powinna być wyeksportowana do wykonywalnego pliku jar podpisanego cyfrowo. Proszę zastanowić się, jak spełnić to wymaganie przy obecności zależności od biblioteki dostarczonej jako podpisany cyfrowo plik jar oraz jak definiować pliki polityki.

Proszę w gitowym repozytorium kodu w gałęzi sources/releases stworzyć osobne podkatalogi: na bibliotekę (biblioteka) oraz na aplikację (aplikacja).

### Lab10

Wykorzystując kod z lab09 (celem uproszczenie można zrezygnować z podpisów cyfrowych) przygotuj wielowydaniową wersję aplikacji. Wymagane jest, by aplikacja uruchamiała się w środowiskach, w których działa Java 8, 9, 10, 11. Ponadto wykorzystując dowolne narzędzie przygotuj instalator aplikacji (o narzędziach umożliwiających stworzenie instalatorów wspomniano na wykładzie).

### Lab11

Napisz program z wykorzystaniem JNI. Działanie tego programu polegać ma na przetestowaniu dziaania trzech natywnych metod służących do obliczania iloczynu skalarnego. Poniżej zamieszczono schemat implementacji odpowiedniej klasy Java wraz z opisem interfejsów metod.

Schemat implementacji klasy Java:

```java
class .....{
.....
public Double[] a;
public Double[] b;
public Double c;

public native Double multi01(Dobuble[] a, Double[] b);
// zakładamy, że po stronie kodu natywnego wyliczony zostanie iloczyn skalarny dwóch wektorów
public native Double multi02(Dobuble[] a); 
// zakładamy, że drugi atrybut będzie pobrany z obiektu przekazanego do metody natywnej 
public native void multi03();
// zakładamy, że po stronie natywnej utworzone zostanie okienko na atrybuty,
// a po ich wczytaniu i przepisaniu do a,b obliczony zostanie wynik. 
// Wynik powinna wyliczać metoda Javy multi04 
// (korzystająca z parametrów a,b i wpisująca wynik do c).

public void multi04(){
 ..... // mnoży a i b, wynik wpisuje do c
 }

}
```

### Lab13

W trakcie semestru część zadań była zrealizowana przez niektórych uczestników kursu z wykorzystaniem JavaFX. Zwykle były to aplikacje posiadające opis interfejsu w pliku fxml, który obsługiwany był przez kontroler napisany w języku Java. Niniejsze zadanie ma na celu zapoznanie się z możliwościami JavaFX, które wybiegają poza takie standardowe zastosowania.

Zadanie polega na stworzeniu aplikacji korzystającej z JavaFX w niestandardowy sposób. Ten niestandardowy sposób ma polegać na oskryptowaniu całej logiki aplikacji w pliku fxml zamiast w kontrolerze pisanym w języku Java. W języku Java ma pojawić się tylko ładowanie fxmla. Proszę spojrzeć na przykłady zamieszczone poniżej (Sample1.fxml, Sample1.java, application.css). Oczywiście dużo łatwiej i wygodniej byłoby oprogramować logikę w jakimś kontrolerze. Niemniej w zadaniu chodzi o przećwiczenie powiązania skryptów z kodem Java.

Sama aplikacja ma pozwalać na generowanie życzeń (imieninowych, urodzinowych, ...). Ma pełnić rolę narzędzia dostarczającego teksty na podstawie szablonów, które można będzie gdzieś wykorzystać (przez skopiuj i wklej).

Na interfejsie aplikacji powinny pojawić się:

   - pole na wprowadzanie Imienia osoby.
   - opcje wyboru strategii wyboru życzenia (losowo, kolejno, najdłuższe, najkrótsze).
   - lista wyboru szablonów życzeń (szablony życzeń powinny być składowane w plikach, których nazwy wyświetlane są na liście, można zastanowić się, czy do realizacji tego ćwiczenia nie wykorzystać boundli językowych - z nawiasami klamrowymi).
   - pole tekstowe z życzeniami (wypełniane automatycznie po wprowadzeniu imienia, wyborze strategii i szablonu - z tego pola można skopiować tekst - jeśli ktoś chciałby np. wrzucić go do maila)
   - lista wyboru stylu interfejsu (do wyboru powinny być przynajmniej dwa style css)

Proszę w gitowym repozytorium w gałęzi sources umieścić wszystkie źródła plus plik Readme.md (z dołączonymi zrzutami i opisem, jak uruchomić aplikację), w gałęzi releases - wykonywalny plik jar (z przygotowaniem jara może być problem - trzeba sprawdzić, czy pliki fxml, css oraz szablonów będą się ładować z tego jara, ostatecznie można je dystrybuować osobno).

```xml
########### Sample1.fxml  ###########
<?xml version="1.0" encoding="UTF-8"?>
<?language javascript?>
<?import javafx.scene.control.*?>
<?import javafx.scene.layout.*?>

<VBox fx:id="vbox" layoutX="10.0" layoutY="10.0" prefHeight="250.0"
	prefWidth="300.0" spacing="10" xmlns:fx="http://javafx.com/fxml/1"
	xmlns="http://javafx.com/javafx/2.2">
	
	<fx:script>
	var System = Java.type("java.lang.System")
		function buttonAction(event){
		java.lang.System.out.println("finally we get to print something.");
		outputLbl.setText("Your Input please:");
		}
	</fx:script>
	<children>

		<Label fx:id="inputLbl" alignment="CENTER_LEFT" cache="true"
			cacheHint="SCALE" prefHeight="30.0" prefWidth="200.0"
			text="Please insert Your Input here:" textAlignment="LEFT" />
		<TextField fx:id="inputText" prefWidth="100.0" />
		<Button fx:id="okBtn" alignment="CENTER_RIGHT"
			contentDisplay="CENTER" mnemonicParsing="false"
			onAction="buttonAction(event);" text="OK" textAlignment="CENTER" />

		<Label fx:id="outputLbl" alignment="CENTER_LEFT" cache="true"
			cacheHint="SCALE" prefHeight="30.0" prefWidth="200.0"
			text="Your Input:" textAlignment="LEFT" />
		<TextArea fx:id="outputText" prefHeight="100.0"
			prefWidth="200.0" wrapText="true" />
	</children>
</VBox>
```

```java
########### Sample1.java  ###########
package application;

import java.io.FileInputStream;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Sample1 extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		String fxmlDocPath = "src/Sample1.fxml";
		FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);

		VBox root = (VBox) loader.load(fxmlStream);

		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.setTitle("A FXML Example without any Controller");
		stage.show();

	}
}
```

```css
########### application.css  ###########
/* JavaFX CSS - Leave this comment until you have at least create one rule which uses -fx-Property */
.pane {
    -fx-background-color: #1d1d1d;
}

.root {
		-fx-padding: 10;
		-fx-border-style: solid inside;
		-fx-border-width: 2;
		-fx-border-insets: 5;
		-fx-border-radius: 5;
		-fx-border-color: #2e8b57;
		-fx-background-color: #1d1d1d;
}
.label {
    -fx-font-size: 11pt;
    -fx-font-family: "Segoe UI Semibold";
    -fx-text-fill: white;
    -fx-opacity: 0.6;
}

.label-bright {
    -fx-font-size: 11pt;
    -fx-font-family: "Segoe UI Semibold";
    -fx-text-fill: white;
    -fx-opacity: 1;
}

.label-header {
    -fx-font-size: 32pt;
    -fx-font-family: "Segoe UI Light";
    -fx-text-fill: white;
    -fx-opacity: 1;
}

.text-field {
    -fx-font-size: 12pt;
    -fx-font-family: "Segoe UI Semibold";
}

.button {
    -fx-padding: 5 22 5 22;   
    -fx-border-color: #e2e2e2;
    -fx-border-width: 2;
    -fx-background-radius: 0;
    -fx-background-color: #1d1d1d;
    -fx-font-family: "Segoe UI", Helvetica, Arial, sans-serif;
    -fx-font-size: 11pt;
    -fx-text-fill: #d8d8d8;
    -fx-background-insets: 0 0 0 0, 0, 1, 2;
}

.button:hover {
    -fx-background-color: #3a3a3a;
}

.button:pressed, .button:default:hover:pressed {
  -fx-background-color: white;
  -fx-text-fill: #1d1d1d;
}

.button:focused {
    -fx-border-color: white, white;
    -fx-border-width: 1, 1;
    -fx-border-style: solid, segments(1, 1);
    -fx-border-radius: 0, 0;
    -fx-border-insets: 1 1 1 1, 0;
}

.button:disabled, .button:default:disabled {
    -fx-opacity: 0.4;
    -fx-background-color: #1d1d1d;
    -fx-text-fill: white;
}

.button:default {
    -fx-background-color: -fx-focus-color;
    -fx-text-fill: #ffffff;
}

.button:default:hover {
    -fx-background-color: derive(-fx-focus-color,30%);
}
```

### Lab14

Aby usprawnić obsługę klientów w wielu sklepach czy też instytucjach wdrożono systemy kolejkowe. Zasada działania tych systemów jest prosta: użykownik wchodząc do sklepu czy instytucji pobiera numerek z automatu, wybierając wcześniej kategorię sprawy, z którą przyszedł. Numerek zwykle zawiera, oprócz wartości całkowitej, również symbol kategorii sprawy (np. B0001). Następnie klient trafia do poczekalni, gdzie przebywa aż do otrzymania ukazania się zaproszenie do załatwienia sprawy. Sprawy załatwiane są na wyznaczonych do tego celu stanowiskach przez znajdujących się tam pracowników. Aktualnie obsługiwane sprawy widoczne są zwykle na tablicy informacyjnej. Lista ta jest aktualizowana po każdorazowym załatwieniu jakiejś sprawy.

W trakcie laboratoriów należy zasymulować, w dużym uproszczeniu, działanie takiego systemu kolejkowego. Należy przy tym wykorzystac technologię JMX.

W ramach zajęć powstać ma aplikacja, która pozwoli na generowanie kolejnych numerków oraz będzie pełnić rolę tablicy informacyjnej. Niech więc na interfejsie tej aplikacji pojawią się dwa wydzielone obszary:
i) obszar "Automatu biletowego" (TicketMachine). 
ii) obszar "Tablicy informacyjnej" (InformationBoard).

Niech w obszarze "Automatu biletowego" będzie można wybrać kategorię sprawy (A, B, ...) oraz wyenerować numerek (odpowiednio do wybranej sprawy: A0001, B0001, ....).

Niech w obszarze "Tablicy informacyjnej" będą pojawiać się listy z aktulnie obsługiwanymi sprawami w podziale na kategorie.

Zakładamy, że obsługa spraw będzie się odbywać według prostego algorytmu bazującego na priorytetach przypisanych do kategorii spraw. Im wyższy priorytet ma dana kategoria sprawy, tym częściej sprawa z tej kategorii powinna być obsługiwana. Zakładamy, że wszystkie sprawy obsługiwane są z tym samym średnim czasem (obsługa spraw niech toczy się w osobnym wątku).

Niech lista kategorii spraw oraz przyznane im priorytety będą parametrami, którymi można zarządzać albo poprzez ziarno JMX zanurzone w aplikacji (do czego trzeba klienta), albo poprzez interfejs samej aplikacji. 

Ziarno JMX powinno generować notyfikacje przy każdej zmianie parametrów inicjowanej przez użytkownika aplikacji. Nie powinno tych notyfikacji generować, jeśli zmiana parametrów odbywać się będzie zdalnie (przez klienta korzystającego z tego ziarna).

Niech klient będzie drugą, osobną aplikacją komunikującą się z ziarnem JMX. Na interfejsie klienta powinno dać się zmienić listę kategorii spraw i ich priorytetów. Ponadto na tym interfejsie powiny pojawiać się informacje o zmianach atrybutów dokonanych przez użytkownika pierwszej aplikacji (tj. notyfikacje przychodzące z ziarna).

Proszę w gitowym repozytorium w gałęzi sources umieścić wszystkie źródła (aplikacja i jej klient) plus plik Readme.md (z dołączonymi zrzutami i opisem, jak uruchomić aplikację i klienta).
