# Backend 

### GET, POST, DELETE (select, insert, delete): 
doSQL(_String_ **metodi**, _String_ **taulu**, _HashMap<String><String>_ **parametrit**);

### PUT (update): 
doSQL(_String_ **metodi**, _String_ **taulu**, _HashMap<String><String>_ **parametrit**, _String[ ]_{ "idSarake", "id" } **id**);

#
### Esimerkkejä
doSQL("**GET**", "mokki", {parametrit HashMapissa})
* palauttaa parametreja vastaavat mökit **kaksiuloitteisessa ArrayList:ssä [ [mökin x data], [mökin y data] ]... jne**.

doSQL("**POST**", "**asiakas", {uuden asiakkaan tiedot HashMapissa})
* lisää asiakkaan tietokantaan
* palauttaa tietoa ArrayList:ssä **[[ "viesti", "lisättyjen rivien määrä (1 tai 0)", lisätyn rivin id ]]**

doSQL("**DELETE**", "lasku", {parametrit HashMapissa (_todnäk vain id?_)})
* poistaa parametreja vastaavat laskut tietokannasta
* palauttaa tietoa ArrayList:ssä **[[ "viesti", poistettujen rivien määrä ]]**

doSQL("**PUT**", "varaus", {muokattavan varauksen tiedot HashMapissa}, {muokattavan idSarake ja id String[] taulukossa})
* muokkaa annettuja tietoja varaukselle, joka vastaa id:tä.
* palauttaa muokatun varauksen ArrayList:ssä **[[ muokatun rivin data ]]**

#
### GET, POST ja DELETE käyttäessä VOI laittaa neljänneksi parametriksi Myöskin String[] -taulukon, mutta se ei tee mitään.
Jos metodeja GET, POST tai DELETE, käyttäessä antaa parametrina
PUT-metodin vaatiman String[] -taulukon, niin ignooraa sen: 

doSQL("**GET**", "mokki", {parametrit HashMapissa}, **String[]**)
* palauttaa parametreja vastaavat mökit kaksiuloitteisessa ArrayList:ssä **[[mökin x data], [mökin y data]]... jne.**
