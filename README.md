# Go Home Spiel

## Über Das Spiel
Das **Go Home Spiel** wurde für ein Informatik Schulprojekts im Leistungskurs Q2 des Paulsen Gymnasiums entwickelt. Dieses Projekt wurde von **Philipp Nguyen (NamakiXVI)** und **Falk Milbrodt (YourError1504)** programmiert. Ziel des Spiels ist es, die eigenen Figuren strategisch zum "Haus" zu bewegen, während der Gegner daran gehindert wird.

---

## Inhaltsverzeichnis
1. [Spielaufbau](#spielaufbau)
2. [Anleitung](#anleitung)
3. [Funktionen](#funktionen)
4. [Steuerung](#steuerung)
5. [Installation](#installation)
6. [Verwendung](#verwendung)
7. [Technologien](#technologien)
8. [Autoren](#autoren)

---

## Spielaufbau
Das Spiel ist ein rundenbasiertes Brettspiel, bei dem zwei Spieler gegeneinander antreten:
- Spieler 1: Blaue Figur
- Spieler 2: Rote Figur

Das Spielfeld ist ein 5x5-Raster, in dessen Mitte das "Haus" liegt. Das Ziel ist es, die eigene Figur auf das Haus-Feld (Position: 2,2) zu bewegen.

## Anleitung 
Ein Spieler hat das Ziel die rote Figur ins Zentrum zu bewegen, der andere Spieler die blaue Figur. Wer das zuerst schafft, hat gewonnen.

Welcher Spieler beginnen darf, wird zufällig ermittelt.

Der Spieler, der an der Reihe ist, wirft beide Münzen und bestimmt damit die Figuren, die er bewegen muss, egal ob es sich dabei um die eigene Figur oder die des Gegners handelt. Die eigene Figur wird man natürlich versuchen besser zu positionieren, die Figur des Gegenspielers wird man versuchen schlechter zu positionieren.

Dazu werden die beiden aktuellen Figuren um genau ein Feld nach oben, unten, links oder rechts bewegt. Beide Figuren müssen in die gleiche Richtung bewegt werden. Wirft man zwei mal die gleiche Farbe, dann wird die entsprechende Figur um genau zwei Felder in eine Richtung bewegt.

### Ziel
- **Blau gewinnt**, wenn die blaue Figur das Haus erreicht.
- **Rot gewinnt**, wenn die rote Figur das Haus erreicht.
- **Unentschieden**, wenn beide Figuren gleichzeitig das Haus erreichen.

---

## Funktionen
1. **Zufällige Münzwürfe**: Bestimmen, welche Spieler in einer Runde ihre Figuren bewegen dürfen.
2. **Bewegung**: Spieler können ihre Figuren in vier Richtungen bewegen: Hoch, Runter, Links, Rechts.
3. **Icons**: Darstellung von Figuren, leeren Feldern, dem Haus und Kollisionen.
4. **Statusanzeige**: Informiert über den aktuellen Spielstatus (z. B. "Blau ist am Zug").
5. **Spielende**: Automatische Überprüfung, ob das Spiel beendet ist, und Anzeige der Ergebnisse.

---

## Steuerung
Das Spiel hat eine einfache Oberfläche, die ihr nutzen könnt:  
- **Bewegungsbuttons**: Damit könnt ihr eure Figuren in die Richtung bewegen, die ihr wollt.  
- **Münzwurf-Icons**: Die zeigen euch, was beim Münzwurf in der Runde rausgekommen ist.  

---

## Installation
1. **Code herunterladen**:
   - Klone das Repository:  
     ```bash
     git clone https://github.com/NamakiXVI/GoHomeSpiel.git
     ```
   - Alternativ: Lade den Code als ZIP-Datei herunter und entpacke ihn.

2. **Java-Entwicklungsumgebung**: Stelle sicher, dass eine Java IDE wie IntelliJ IDEA oder Eclipse installiert ist.

3. **Projekt importieren**:
   - Öffne die IDE und importiere das Projekt als Maven/Gradle-Projekt (falls benötigt).

4. **Starten**: Führe die Hauptklasse `Spiel` aus.

---

## Verwendung
1. **Spiel starten**: Führe die Anwendung aus, um das Spielfeld und die Benutzeroberfläche zu laden.
2. **Züge ausführen**: Nutze die Bewegungsbuttons, um die Figuren zu bewegen.
3. **Gewinnen**: Bewege deine Figur strategisch zum Haus, bevor dein Gegner es schafft.

---

## Technologien
- **Programmiersprache**: Java
- **Bibliotheken**:
  - Swing (für die GUI)
  - AWT (für grundlegende Grafikelemente)

---

## Autoren
Dieses Projekt wurde von folgenden Schülern entwickelt:
- **Philipp Nguyen (NamakiXVI)**
- **Falk Milbrodt (YourError1504)**

Lehrer: Herr Wessel  
Schule: Paulsen Gymnasium, Leistungskurs Informatik Q2

