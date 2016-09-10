# punchtower

<h2>Ändringar för högre betyg</h2>

<b>EJ FÄRDIG</b></br>
Jag tycker att det är bättre att placera Enums nära de klasser de används mest i, t.ex. AttackType kunde ligga i entity. 

</br><b>FÄRDIG</b></br>
Det är bra att ni låter bilderna vara static så att de inte laddas mer än en gång. Det går att ta detta ett steg längre och göra dem till konstanter
genom att lägga till final och ta bort null-tilldelningarna.

</br><b>EJ FÄRDIG</b></br>
Det hade varit en fördel att använa Properties för att spara viktiga spelvariabler. Då kan man lätt ändra värden för att balancera spelet om problem uppstår utan att man behöver bygga om. T.ex. om det visar sig att man inte får critical tillräckligt kan ni ändra parametern CRIT_MIN utan att behöva bygga om spelet.

</br><b>FÄRDIG</b></br>
Ni använder inte getResource för att läsa in bilder, det innebär att det blir problem med t.ex. jar filer. Jag får ERROR bilden på min windowsmaskin när jag testar JAR-filen.


</br><b>FÄRDIG</b></br>
Lämplig plats att extrahera en variabel som pekar på temp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).

</br><b>EJ FÄRDIG</b></br>
```java
public class SaveFailedException extends Exception
{
	public SaveFailedException(String filename, Exception e)
	{
		super("Could not save file: "+filename);
		setStackTrace(e.getStackTrace());
	}
}
```
Fungerar detta?  Det ser ut som att ni sätter en stacktrace för en SFE när den skapas, men sedan när ni *kastar* detta objekt (efter att konstruktorn har returnerats) borde detta skrivas över av den nya stacktracen.  Hur som helst, det finns annan funktionalitet för det jag tror ni vill göra:  Använd exception chaining med hjälp av super(..., e).
