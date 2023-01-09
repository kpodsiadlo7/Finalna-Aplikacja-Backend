

# BACKEND APPLICATION INFORMATION
#### Jest to mój w pełni samodzielny projekt aplikacji składającej się z backendu oraz frontendu.


#### Poniżej znajdują się technologie użyte w aplikacji
* Spring boot
* Spring web
* Spring Data JPA
* RestAPI
* Rest template for NBP API and WeatherAPI
* Hibernate
* JUnit tests
* Mockito
* Swagger
* Gson
* Lombok
* Gradle
* Dependency Injection
* SOLID
* Interface
* OOP
* Enums
* Scheduler
* Loops
* Inheritance
* Datastructures
* MySQL
* H2


###
### Backend udostępnia 30 endpointów, dzięki którym możemy:
1. Zewnetrzne API
* Pobrać aktualną pogodę dla lokalizacji z WeatherApi
* Pobrać aktualne kursy walut EUR i USD z api narodowego banku polskiego
2. Pacjent
* Utworzyć pacjenta
* Pobrać listę wszystkich pacjentów
* Pobrać pacjentów za pomocą id
* Pobrać pacjentów podając id historii choroby danego pacjenta
* Edytować dane personalne pacjenta
* Ocenić klinikę jako pacjent(id pacjenta zostanie przypisane do konkretnej oceny)
* Ocenić personel jako pacjent(id pacjenta zostanie przypisane do konkretnej oceny)
3. Personel
* Utworzyć personel
* Pobrać listę całego personelu
* Pobrać personel za pomocą id
* Pobrać wszystkie historie chorób
* Dodać opis choroby konkretnemu pacjentowi
* Przypisać pacjenta do konkretnej osoby personelu, przy czym jest aktualizowana liczba pacjentów którą zajmuje się dana osoba z personelu
4. Klinika
* Utworzyć klinkę
* Pobrać listę wszystkich klinik
* Pobrać klinkę za pomocą id
* Pobrać cały personel z konkretej kliniki
* Zmienić nazwę kliniki
* Zarejestrować konkretnego pacjenta w klinice przy czym jest aktualizowana liczba hospitalizowanych osób
* Dodawać personel do kliniki, przy czym aktualizuje się liczba personelu w klinice
* Usuwać pojedyńczo personel za pomocą id
5. Rezerwacja
* Pobrać wszystkie rezerwacje
* Pobrać wszystkie zakończone rezerwacje przy pomocy statusu
* Pobrać wszystkie oczekujące rezerwacje przy pomocy statusu
* Pobrać rezerwacje dla konkretnego pacjenta za pomocą jego id
* Utworzyć nową rezerwacje
* Zamknąć rezerwacje (już się odbyła)
* Zmienić datę rezerwacji
* Zmienić metodę płatności
6. Oceny
* Pobrać wszystkie oceny
* Pobrać ocene za pomocą id
* Stworzyć ocene
* Zaktualizować ocene
####
### Frontend dla aplikacji
Frontend jest napisany w osobnym projekcie w oparciu o framework Vaadin.
https://github.com/kpodsiadlo7/Finalna-Aplikacja-Klinika-Frontend
