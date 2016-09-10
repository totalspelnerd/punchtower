# punchtower

<h2>Ändringar för högre betyg</h2>

Jag tycker att det är bättre att placera Enums nära de klasser de används mest i, t.ex. AttackType kunde ligga i entity. 

<b>[KLAR]</b> Det är bra att ni låter bilderna vara static så att de inte laddas mer än en gång. Det går att ta detta ett steg längre och göra dem till konstanter
genom att lägga till final och ta bort null-tilldelningarna.

Det hade varit en fördel att använa Properties för att spara viktiga spelvariabler. Då kan man lätt ändra värden för att balancera spelet om problem uppstår utan att man behöver bygga om. T.ex. om det visar sig att man inte får critical tillräckligt kan ni ändra parametern CRIT_MIN utan att behöva bygga om spelet.

<b>[KLAR]</b> Ni använder inte getResource för att läsa in bilder, det innebär att det blir problem med t.ex. jar filer. Jag får ERROR bilden på min windowsmaskin när jag testar JAR-filen.
