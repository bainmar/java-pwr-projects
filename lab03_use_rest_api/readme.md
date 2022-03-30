# lab03_client_api <!-- omit in toc -->

- [1. Diagramy klas w pakietach](#1-diagramy-klas-w-pakietach)
  - [1.1. com.bartoszek.jtz.rest.api (app)](#11-combartoszekjtzrestapi-app)
  - [1.2. com.bartoszek.jtz.rest.api (test)](#12-combartoszekjtzrestapi-test)
- [2. Implementacja](#2-implementacja)
- [3. Uruchomienie](#3-uruchomienie)
- [4. Przypadek użycia](#4-przypadek-użycia)

## 1. Diagramy klas w pakietach

### 1.1. com.bartoszek.jtz.rest.api (app)

![](img/readme/2022-03-30-16-37-36.png)

### 1.2. com.bartoszek.jtz.rest.api (test)

![](img/readme/2022-03-30-16-39-21.png)

## 2. Implementacja

Klasa ```GeoDbApiClient``` wysyła zapytania do endpoint'ów. API nie zawiera ```languageCode``` umożliwiającego zadawania zapytań w języku polskim. W aplikacji nie mapowano nazw krajów z języka angielskiego na polski. Projekt zrealizowano w architekturze MVC. Widok zawiera obiekt typu ```ResourceBundle```, który przechowuje informacje dotyczące języka. Jest również odpowiedzialny za ponowne renderowanie komponentów w przypadku zmiany języka. API pozwala na zadawanie 1000 zapytań dziennie z częstotliwością jednej sekundy. W aplikacji uwzględnioniono renderowanie tekstu z wariantami - ```ChoiceFormat```.

## 3. Uruchomienie

Należy wyksportować klucz ```API``` do zmiennej systemowej o nazwie ```GEODB_API_KEY```.

W przypadku systemu ubuntu wpisać

```vim /etc/environment```

i dodać ```GEODB_API_KEY=_wartość_klucza```

Po uruchomieniu polecenia mavena ```mvn install``` z głównego folderu jest tworzony "fat jar" o nazwie ```jtz-rest-api-1.0-SNAPSHOT-jar-with-dependencies.jar``` w ścieżce ```${root}/target```

W celu uruchomienia wykonalnego pliku jar należy wpisać w konsoli ```java -jtz-rest-api-1.0-SNAPSHOT-jar-with-dependencies.jar```

## 4. Przypadek użycia

Główne okno aplikacji 

![](img/readme/2022-03-30-17-09-14.png)

W przypadku odpowiedzi na pytanie pojawiają okna dialogowe informujące o poprawności i prawidłowym wyniku.

![](img/readme/2022-03-30-17-10-29.png)

![](img/readme/2022-03-30-17-10-59.png)

Poniżej  ten sam przykład przy zmianie języka na polski

![](img/readme/2022-03-30-17-12-02.png)

![](img/readme/2022-03-30-17-12-27.png)