// Philipp Nguyen
// Go-Home Java Projekt
// Informatik LK 2024 Q2
// Lehrer: Herr Wessel
import javax.swing.*; //Importe für den UI als app
import java.awt.*;
import java.util.Random;//Import für die zufällige Zahlen

public class Spiel extends JFrame //erbt von JFrame wodurch sie ein Fenster erstellt das die Benutzeroberfläche des Spiels darstellt
{
    //variablen für die UI für JFrame
    int groesse = 5; //größe des Spielfeldes
    JButton[][] spielfeld; //das Spielfeld als 2D Array buttons
    JLabel statusLabel;
    
    //Alle benutzten Bilder als Icon Variablen
    Icon Haus = new ImageIcon(this.getClass().getResource("img/Haus.png"));
    Icon FRot = new ImageIcon("img/FigurRed.png");
    Icon FBlau = new ImageIcon("img/FigurBlue.png");
    Icon FigurBR = new ImageIcon("img/BRFigur.png");
    Icon Street = new ImageIcon("img/Street.png");
    Icon BCoin = new ImageIcon("img/BlueCoin.png");
    Icon RCoin = new ImageIcon("img/RedCoin.png");
    Icon blue_on_house = new ImageIcon("img/B_on_house.png");
    Icon red_on_house = new ImageIcon("img/R_on_house.png");
    Icon br_on_house = new ImageIcon("img/BR_on_house.png");
    Icon go_home = new ImageIcon("img/go_home.png");

    //JButtons mit den Icon von der Variable
    JButton Coin1 = new JButton(RCoin);
    JButton Coin2 = new JButton(BCoin);
    JButton Go_Home = new JButton(go_home);
    JButton[] bewegungsButtons; // Array für Bewegungs-Buttons

    //münzenzahlen
    int zahl1;
    int zahl2;

    //erstellte Figuren (Blau und Rot)
    Figur f1 = new Figur("Blau", 0, 0);
    Figur f2 = new Figur("Rot", 4, 4);

    //aktuelle Figuren
    Random r = new Random();
    int randomInt = r.nextInt(2); //wählt eine zufällige zahl zwischen 0 und 1
    Figur aktuelleFigur1; 
    Figur aktuelleFigur2;
    Figur aktuellerSpieler = randomInt == 0 ? f1 : f2; // zufällige Zahl bestimmt, wer als erstes der aktuelle Spieler wird
    
    //ein Konstrukter für das Spiel
    public Spiel()
    {
        //Einstellungen für das Programm
        setTitle("Go Home Spiel");
        setSize(670, 865);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
        setBackground(Color.LIGHT_GRAY);

        //  Erstellt das Spielfeld
        JPanel boardPanel = new JPanel(new GridLayout(groesse, groesse));
        spielfeld = new JButton[groesse][groesse];
        
        // Spielfeld erstellt indem man ein 5x5 feld von Buttons erstellt
        for (int y = 0; y < groesse; y++) 
        {
            for (int x = 0; x < groesse; x++) 
            {
                JButton button = new JButton(); //erstellt ein neues Button als Feld
                spielfeld[y][x] = button; //fügt den Button in den bestimmten punkt
                boardPanel.add(button); //fügt es in den Boardpanel hinzu
            }
        }

        // Erstellt den Status Panel
        JPanel statusPanel = new JPanel(new BorderLayout());
        statusPanel.setBackground(Color.LIGHT_GRAY);

        //Erstellt das Logo im Status Panel und setzt einstellungen dazu ein
        JPanel homePanel = new JPanel(new BorderLayout());
        homePanel.add(Go_Home);
        Go_Home.setBackground(Color.LIGHT_GRAY);
        Go_Home.setBorderPainted(false);
        Go_Home.addActionListener(e-> restartGame());
        statusPanel.add(homePanel, BorderLayout.WEST);

        //Erstellt das Status Panel und setzt einstellungen dazu ein        
        statusPanel.add(Go_Home,BorderLayout.WEST);
        statusLabel = new JLabel("Spiel gestartet. Blau ist am Zug.");
        statusLabel.setFont(new Font("Kino MT", Font.BOLD, 25));
        statusPanel.add(statusLabel, BorderLayout.CENTER);

        //Erstellt einen Panel für Coins wobei es eine art tabelle mit einer Zeile und zwei Spalten für jeweils die beiden Coins erstellt
        JPanel CoinPanel = new JPanel(new GridLayout(1, 2));
        CoinPanel.add(Coin1,BorderLayout.EAST);
        Coin1.setBorderPainted(false);
        Coin1.setBackground(Color.LIGHT_GRAY);

        CoinPanel.add(Coin2,BorderLayout.EAST);
        Coin2.setBorderPainted(false);
        Coin2.setBackground(Color.LIGHT_GRAY);

        statusPanel.add(CoinPanel, BorderLayout.EAST); //Coin Panel zu den Status Panel hinzugefügt

        // Fügt das Movement als buttons hinzu
        bewegungsButtons = new JButton[4]; // Array mit fester Größe (4 Buttons)
        JPanel controlPanel = new JPanel(new GridLayout(1, 4));
        String[] richtung = {"LINKS", "HOCH", "RUNTER", "RECHTS"};// Bewegungsbuttons für die Richtungen LINKS, HOCH, RUNTER, RECHTS

        //erstellt so viele Buttons wie es in dem String Richtung gibt und werden alle gleich eingstellt
        for (int i = 0; i < richtung.length; i++) 
        {
            bewegungsButtons[i] = new JButton(richtung[i]);
            bewegungsButtons[i].setFont(new Font("Kino MT", Font.BOLD, 20));
            bewegungsButtons[i].setBackground(Color.LIGHT_GRAY);
            String eingabe = richtung[i];
            bewegungsButtons[i].addActionListener(e -> Bewegung(aktuelleFigur1, aktuelleFigur2, eingabe)); //Beim drücken des buttons wird die Bewegung() ausgeführt
            controlPanel.add(bewegungsButtons[i]); //werden in den  Control Panel hinzugefügt
        }

        //Fügt die Panels in den Frame
        add(boardPanel, BorderLayout.CENTER);
        add(statusPanel, BorderLayout.NORTH);
        add(controlPanel, BorderLayout.SOUTH);

        // erstellt das spielfeld
        updateSpielfeld();
    }

    //Diese Funktion gibt den variablen zahl1 und zahl2 zufällig eine 0 oder 1 und dazu wird nach bedingung die Icons für die Coins erstellt
    public void coinThrow()
    {
        Random r = new Random();
        zahl1 = r.nextInt(2);
        zahl2 = r.nextInt(2);

        Coin1.setIcon(zahl1 == 0 ? BCoin : RCoin); //Wenn zahl1 0 ist dann BCoin sonst RCoin
        Coin2.setIcon(zahl2 == 0 ? BCoin : RCoin); //Wenn zahl2 0 ist dann BCoin sonst RCoin
    }

    //Wählt aus welcher die aktuellen FIguren sind hinsichtlich der geworfenen münzen
    public void druckeAktuellenSpieler() 
    {
        if(zahl1 == 1 && zahl2 == 1)
        {
            aktuelleFigur1 = f2;
            aktuelleFigur2 = f2;
        }else if(zahl1 == 0 && zahl2 == 1)
        {
            aktuelleFigur1 = f1;
            aktuelleFigur2 = f2;
        }else if(zahl1 == 1 && zahl2 == 0)
        {
            aktuelleFigur1 = f2;
            aktuelleFigur2 = f1;
        }else if (zahl1 == 0 && zahl2 == 0)
        {
            aktuelleFigur1 = f1;
            aktuelleFigur2 = f1;
        }
    }

    //Main funktion
    public static void main(String[] args)
    {
            Spiel gohome = new Spiel(); //startet das Spiel
            gohome.setVisible(true);
    }

    //checkt ob das Spiel fertig ist oder nicht
    private boolean spielIstFertig() 
    {   //Spiel wird mithilfe eines optionsFenster beendet wo sie entscheiden können ob sie nochmal spielen wollen oder nicht
        if ((f2.x == 2 && f2.y == 2) && (f1.x == 2 && f1.y == 2)) 
        {   //Beide Figuren befinden sich auf dem Haus 
            int userChoice = JOptionPane.showConfirmDialog(null,"Es gibt ein Unentschieden \n \n Wollen sie nochmal spielen?","Es gibt ein Unentschieden", JOptionPane.YES_NO_OPTION);
            if(userChoice == JOptionPane.YES_OPTION) restartGame(); //Wenn Ja ausgewählt wird dnn wird restartGame() ausgeführt
            dispose();//wird nein oder das Fenster geschlossen, dann wird das Programm geschlossen
        }
        else if (f1.x == 2 && f1.y == 2) //Blau hat gewonnen und befindet sich im Haus
        {
            int userChoice = JOptionPane.showConfirmDialog(null, f1.farbe + " hat gewonnen! \n \n Wollen sie nochmal spielen?", f1.farbe + " hat gewonnen!", JOptionPane.YES_NO_OPTION);
            if(userChoice == JOptionPane.YES_OPTION) restartGame(); //Wenn Ja ausgewählt wird dnn wird restartGame() ausgeführt
            dispose();//wird nein oder das Fenster geschlossen, dann wird das Programm geschlossen
        }
        else if (f2.x == 2 && f2.y == 2) //Rot hat gewonnen und befindet sich im Haus
        {
            int userChoice = JOptionPane.showConfirmDialog(null, f2.farbe + " hat gewonnen! \n \n Wollen sie nochmal spielen?", f2.farbe + " hat gewonnen!", JOptionPane.YES_NO_OPTION);
            if(userChoice == JOptionPane.YES_OPTION) restartGame(); //Wenn Ja ausgewählt wird dnn wird restartGame() ausgeführt
            dispose();//wird nein oder das Fenster geschlossen, dann wird das Programm geschlossen
        }
        return false; 
    }

    //aktualisiert Icons auf dem Spielfeld
    public void updateSpielfeld() 
    {
        //checkt alle felder, wo die figuren und das haus ist
        for (int y = 0; y < groesse; y++) 
        {
            for (int x = 0; x < groesse; x++) 
            {
                // Prüfe ob jemand gewonnen hat 
                if (x == 2 && y == 2) 
                {
                    if (f1.x == 2 && f1.y == 2 && f2.x == 2 && f2.y == 2) 
                    {
                        spielfeld[2][2].setIcon(br_on_house); // Beide Figuren auf Haus
                    } else if (f1.x == 2 && f1.y == 2) 
                    {
                        spielfeld[2][2].setIcon(blue_on_house); // Blaue Figur auf Haus
                    } else if (f2.x == 2 && f2.y == 2) 
                    {
                        spielfeld[2][2].setIcon(red_on_house); // Rote Figur auf Haus
                    } else 
                    {
                        spielfeld[2][2].setIcon(Haus); // Haus
                    }
                }
                // Prüfe auf beide Figuren auf demselben Feld
                else if (f1.x == x && f1.y == y && f2.x == x && f2.y == y) 
                {
                    spielfeld[y][x].setIcon(FigurBR); // Beide Figuren
                }
                // Prüfe auf die blaue Figur
                else if (f1.x == x && f1.y == y) 
                {
                    spielfeld[y][x].setIcon(FBlau); // Blaue Figur
                }
                // Prüfe auf die rote Figur
                else if (f2.x == x && f2.y == y) 
                {
                    spielfeld[y][x].setIcon(FRot); // Rote Figur
                }
                // sonst: Straßen-Icon
                else {
                    spielfeld[y][x].setIcon(Street);// Straße
                }
            }
        }
        if (spielIstFertig()) return;

        //passt die Bewegungstasten und den statusLabel an die Farbe des aktuellen Spielers an
        Color spielerFarbe = aktuellerSpieler == f1 ? Color.BLUE : Color.RED;
        for (JButton button : bewegungsButtons) 
        {
            button.setBackground(spielerFarbe); //Setzt den Hintergrund als Die farbe des aktuellen Spielers
            button.setForeground(Color.WHITE); // Setzt den Text Weiß
            statusLabel.setForeground(spielerFarbe);
        }

        //wenn der Aktuelle Spieler f1/Blau ist, dann "Blau ist am Zug." sonst "Rot ist am Zug."
        statusLabel.setText(aktuellerSpieler == f1 ? "Blau ist am Zug." : "Rot ist am Zug.");

        coinThrow();
        druckeAktuellenSpieler();
    }

    //Bewegt die Figuren basierend auf der Richtung (HOCH, RUNTER, LINKS, RECHTS)
    public void Bewegung(Figur f_1, Figur f_2, String Richtung)
    {
        if (spielIstFertig()) return;

        //Bewegung wird doppelt ausgeführt, wenn die selbe farbe gemünzt wird
        if (f_1 == f_2) 
        {
            switch (Richtung) 
            {
                case "HOCH": aktuelleFigur1.bewegeHoch(); aktuelleFigur1.bewegeHoch(); break;
                case "LINKS": aktuelleFigur1.bewegeLinks(); aktuelleFigur1.bewegeLinks(); break;
                case "RUNTER": aktuelleFigur1.bewegeRunter(); aktuelleFigur1.bewegeRunter(); break;
                case "RECHTS": aktuelleFigur1.bewegeRechts(); aktuelleFigur1.bewegeRechts(); break;
            }
        } else //sonst halt beide Farben in die selbe richtung
        {
            switch (Richtung) 
            {
                case "HOCH": aktuelleFigur1.bewegeHoch(); aktuelleFigur2.bewegeHoch(); break;
                case "LINKS": aktuelleFigur1.bewegeLinks(); aktuelleFigur2.bewegeLinks(); break;
                case "RUNTER": aktuelleFigur1.bewegeRunter(); aktuelleFigur2.bewegeRunter(); break;
                case "RECHTS": aktuelleFigur1.bewegeRechts(); aktuelleFigur2.bewegeRechts(); break;
            }
        }

        aktuelleFigur1.checkFeld();
        aktuelleFigur2.checkFeld();

        //zeigt im terminal die Positionen von f1 und f2 und die Positionen von den aktuellen figuren
        System.out.println("Blaue Figur Coords: " + f1.x + " " + f1.y);
        System.out.println("Rote Figur Coords: " + f2.x + " " + f2.y);
        System.out.println("Aktuelle Figur 1 Coords: " + aktuelleFigur1.x + " " + aktuelleFigur1.y);
        System.out.println("Aktuelle Figur 2 Coords: " + aktuelleFigur2.x + " " + aktuelleFigur2.y);

        aktuellerSpieler = (aktuellerSpieler == f1) ? f2 : f1; //wechselt den aktuellen Spieler auf die andere Farbe

        updateSpielfeld(); //Aktualisiert das Spielfeld nach der Bewegung
    }

    //Schießt das aktuelle Fenster und erstellt ein neues
    public void restartGame()
    {
        dispose();
        main(new String[0]);
    }
}