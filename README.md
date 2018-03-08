# MoneyFlow cashier system - English

#System requirements
 - OS: Windows XP Service Pack 2 or higher
 - RAM: 4 GB or more
 - Java 1.8 installed

#Start
 - MoneyFlow.jar has to be in the same directory as the /cfg/ and /lib/ folders
 - Execute MoneyFlow.jar to start the cashier system

#Adding a product to the database
 - Via "Tools" -> "Database" -> "Add product" you can add a product to the database
 - Product name, unit, category, sales tax and price can be set
 - After successfully adding a new product, the following message will appear: "Added product successfully!"

#Changing a product in the database
 - Via "Tools" -> "Database" -> "Change product" you can select a product and change it
 - You can change the following attributes: Product name, unit, category, sales tax and price
 - After successfully changing a product, the following message will appear: "Changed product successfully!"

#Deleting a product from the database
 - Via "Tools" -> "Database" -> "Delete product" you can select a product and delete it
 - After successfully changing a product, the following message will appear: "Deleted product successfully!"

#Displaying all products
 - All products are displayed when opening the windows to change or delete a product from the database

#Changing the imprint
 - Via "Tools" -> "Impressum ändern" you can change the imprint that is displayed on the receipt

#Properties file
 - Language: Selecting the programs language (de,en)
 - Impressum: Imprint that is displayed on the receipt
 - BonCharset: Charset that is used for the receipts
 - BonDirectory: Path to where the generated receipts are saved
 - BarcodeDirectory: Path to where the generated barcodes are saved
 - BarcodeScannerPrefix/BarcodeScannerSuffix: Pre- und Suffix of the barcode scanner
 - DatabaseFile: Database file of the program

# MoneyFlow Kassensystem - Deutsch

#Systemvoraussetzungen
 - OS: Windows XP Service Pack 2 oder neuer
 - RAM: 4 GB oder mehr
 - Java 1.8 installiert

#Start
 - MoneyFlow.jar muss im gleichen Verzeichnis wie die Ordner /cfg/ und /lib/ sein
 - MoneyFlow.jar ausführen um Kassensystem zu starten

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
 - Language: Festlegen der Sprache (de,en)
 - Impressum: Impressum des Unternehmens (wird auf dem Bon angezeigt)
 - BonCharset: Zeichensatz der für die Bons verwendet wird
 - BonDirectory: Pfad zu den generierten Bons
 - BarcodeDirectory: Pfad zu den generierten Barcodes
 - BarcodeScannerPrefix/BarcodeScannerSuffix: Prä- und Suffix des Scanners
 - DatabaseFile: Datenbankdatei der Anwendung
