# MoneyFlow Kassensystem

#Start
-MoneyFlow.jar ausführen um Kassensystem zu starten

#Anlegen eines Artikels in der Datenbank
 - Über "Tools" -> "Datenbank" -> "Artikel hinzufügen" können neue Artikel in der 
   Datenbank angelegt werden
 - Hier können dann Artikelname, Einheit, Kategorie, Mehrwertsteuerklasse und 
   Preis angegeben werden
 - Nach dem Bestätigen, wenn alle Eingaben korrekt waren erscheint die Meldung
   "Artikel erfolgreich angelegt" und der nächste Artikel kann angelegt werden

#Ändern vorhandener Artikel
 - Über "Tools" -> "Datenbank" -> "Artikel ändern" können Artikel mittels einer
   Ordnerstruktur ausgewählt und angepasst werden
 - Geändert werden können die Attribute "Artikel", "Einheit", "Kategorie", 
   "Mehrwertsteuer" und "Preis"
 - Nach Auswahl eines Artikels und ändern beliebiger Attribute können die Änderungen 
   über "Artikel ändern" gespeichert werden

#Löschen eines Artikels
 - Über "Tools" -> "Datenbank" -> "Artikel löschen" können Artikel über die Ordner-
   struktur ausgewählt und gelöscht werden
 - Über die Schaltfläche "Artikel löschen" im aktuellen Fenster wird der Artikel 
   nach Bestätigung der "Sind Sie sicher"-Frage gelöscht

#Anzeigen aller vorhandenen Artikel
 - Sowohl beim Löschen als auch beim Bearbeiten eines Artikels wird die Gesamtheit
   aller Artikel angezeigt

#Impressum ändern
 - Über "Tools" -> "Impressum ändern" kann das Impressum, welches auf den 
   Kassenbons erscheint bearbeitet werden

#Properties-Datei
- Impressum: Impressum des Unternehmens (wird auf dem Bon angezeigt)
- BonCharset: Zeichensatz der für die Bons verwendet wird
- BonDirectory: Pfad zu den generierten Bons
- BarcodeDirectory: Pfad zu den generierten Barcodes
- BarcodeScannerPrefix/BarcodeScannerSuffix: Prä- und Suffix des Scanners
- DatabaseFile: Datenbankdatei der Anwendung
