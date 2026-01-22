# Task List App

Yksinkertainen Android-sovellus, joka on toteutettu Kotlinilla ja Jetpack Composella. Sovelluksen avulla käyttäjä voi lisätä tehtäviä (tasks), merkitä ne tehdyiksi ja tarkastella tehtävälistaa eri suodatuksilla.

## Ominaisuudet

Tehtävien (task) listaaminen

Uuden tehtävän lisääminen:

otsikko

kuvaus

prioriteetti (low / medium / high)

eräpäivä

Tehtävän merkitseminen tehdyksi (done)

Tehtävän poistaminen listasta (delete)

## Tehtävien suodatus:

näytä kaikki tehtävät

näytä vain tehdyt tehtävät

Tehtävälista scrollattavana (LazyColumn)

## Rakenne ja tärkeimmät funktiot
Task (data class)

Kuvaa yksittäistä tehtävää:

- id

- title

- description

- priority

- dueDate

- done

addTask(list, newTask)

 - Lisää uuden tehtävän olemassa olevaan listaan ja palauttaa uuden listan.

toggleTaskDone(list, taskId)

- Vaihtaa tietyn tehtävän done-arvon (true/false) id:n perusteella.

sortTasks(list)

- Järjestää tehtävät done-tilan perusteella.

removeTask(taskId)

- Poistaa taskin listasta id:n perusteella.

mockTasks

- Testidataa sovelluksen alkuun.

### Compose-tilanhallinta

Compose-tilanhhallinta toimii niin, että kun jokin tila (State) muuttuu, niin kaikki sovelluksen
composable funktiot, joissa sitä käytetään piirretään automaattisesti uudelleen, jolloin UI pysyy
synkassa sovelluksen datan kanssa.

### ViewModel

ViewModel on parempi kuin remember, koska remember tallentaa tilan vain yksittäisen Composable-
funktion läpikäynnin ajaksi, ja tila resetoi kun composable-funktio runnataan uudestaan, esimerkiksi
näyttöä kääntäessä. ViewModel taas säilyttää tilan muutosten, esimerkiksi näytön kääntämisen yli.
Se myös mahdollistaa tilan jakamisen useiden Composable-funktioiden välillä.

## Käyttöliittymä

Tehtävälista toteutettu LazyColumnilla

Lomake (tekstikentät ja napit) pysyy näkyvissä listan alapuolella

UI reagoi state-muutoksiin automaattisesti

## Sovelluksen ajaminen

Avaa projekti Android Studiolla

Varmista, että Android-emulaattori tai fyysinen laite on käytössä

Paina Run (▶)

Sovellus käynnistyy laitteessa tai emulaattorissa

## Teknologiat

Kotlin

Jetpack Compose

Android Studio
