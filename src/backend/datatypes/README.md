# Data kerättynä olioihin

 Olioilta löytyy **getterit**, jotka hakevat automaattisesti puuttuvaa dataa tietokannasta.
 **EI SETTEREITÄ!!** Tiedon päivittäminen tapahtuu BackendAPI-luokan funtioilla, jolloin
 olion tiedot voi muuttaa vaihtamalla viittauksen uuteen tietokannasta tulevaan dataan.
 
Esim:
```
Mokki vanhaMokki = BackendAPI.updateMokki({uudet tiedot HashMapissa}, String mokinID);
```
#### Tällöin mökin tiedot päivittyvät käyttöliittymäänkin vain, jos tieto on mennyt läpi tietokantaan.

Olioile voi lisätä myöhemin myös sisäistä toiminnallisuutta, kuten esim Lasku-oliolle voisi lisätä metodin 
```generateLasku()``` -tai vastaavaa, joka kasaisi laskun tiedot asiakkaalle esitettävään muotoon.