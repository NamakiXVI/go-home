import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Spiel extends JFrame
{
    //variablen für die UI für JFrame
    private final int groesse = 5;
    private JButton[][] spielfeld;
    private JLabel statusLabel;
    
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

    
    JButton Coin1 = new JButton(RCoin);
    JButton Coin2 = new JButton(BCoin);
    JButton Go_Home = new JButton(go_home);

    //münzenzahlen
    int zahl1;
    int zahl2;


    Figur f1 = new Figur("Blau", 0, 0);
    Figur f2 = new Figur("Rot", 4, 4);

    Random r = new Random();
    int randomInt = r.nextInt(2);
    Figur aktuelleFigur1;
    Figur aktuelleFigur2;
    Figur aktuellerSpieler = randomInt == 0 ? f1 : f2;
    
    public Spiel()
    {
        setTitle("Go Home Spiel");
        setSize(670, 865);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
        setBackground(Color.LIGHT_GRAY);

        //  Erstellt das Spielfeld
        JPanel boardPanel = new JPanel(new GridLayout(groesse, groesse));
        spielfeld = new JButton[groesse][groesse];
        
        // Spielfeld erstellen
        for (int y = 0; y < groesse; y++) 
        {
            for (int x = 0; x < groesse; x++) 
            {
                JButton button = new JButton();
                spielfeld[y][x] = button;
                boardPanel.add(button);
            }
        }

        // Erstellt den Status Panel
        JPanel statusPanel = new JPanel(new BorderLayout());
        statusPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        statusPanel.setBackground(Color.LIGHT_GRAY);

        JPanel homePanel = new JPanel(new BorderLayout());
        homePanel.add(Go_Home);
        Go_Home.setBackground(Color.LIGHT_GRAY);
        Go_Home.setBorderPainted(false);
        Go_Home.addActionListener(e-> restartGame());
        statusPanel.add(homePanel, BorderLayout.WEST);

        statusPanel.add(Go_Home,BorderLayout.WEST);
        statusLabel = new JLabel("Spiel gestartet. Blau ist am Zug.");
        statusLabel.setFont(new Font("Kino MT", Font.BOLD, 20));
        statusPanel.add(statusLabel, BorderLayout.CENTER);

        JPanel CoinPanel = new JPanel(new GridLayout(1, 2));
        CoinPanel.add(Coin1,BorderLayout.EAST);
        Coin1.setBorderPainted(false);
        Coin1.setBackground(Color.LIGHT_GRAY);

        CoinPanel.add(Coin2,BorderLayout.EAST);
        Coin2.setBorderPainted(false);
        Coin2.setBackground(Color.LIGHT_GRAY);

        statusPanel.add(CoinPanel, BorderLayout.EAST);

        // Fügt das Movement als buttons hinzu
        JPanel controlPanel = new JPanel(new GridLayout(1, 4));
        String[] directions = {"LINKS", "HOCH", "RUNTER", "RECHTS"};

        for (int i = 0; i < directions.length; i++) 
        {
            JButton moveButton = new JButton(directions[i]);
            moveButton.setFont(new Font("Kino MT", Font.BOLD, 15));
            moveButton.setBackground(Color.LIGHT_GRAY);
            String eingabe = directions[i];
            moveButton.addActionListener(e -> Bewegung(aktuelleFigur1, aktuelleFigur2, eingabe));
            controlPanel.add(moveButton);
        }

        //Fügt die Panels in den Frame
        add(boardPanel, BorderLayout.CENTER);
        add(statusPanel, BorderLayout.NORTH);
        add(controlPanel, BorderLayout.SOUTH);

        // erstellt das spielfeld
        updateSpielfeld();
    }

    public void coinThrow()
    {
        Random r = new Random();
        zahl1 = r.nextInt(2);
        zahl2 = r.nextInt(2);

        if(zahl1 == 0)
        {
            Coin1.setIcon(BCoin);
        }else
        {
            Coin1.setIcon(RCoin);
        }

        if(zahl2 == 0)
        {
            Coin2.setIcon(BCoin);
        }else
        {
            Coin2.setIcon(RCoin);
        }
    }

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

    public static void main(String[] args)
    {
            Spiel gohome = new Spiel();
            gohome.setVisible(true);
    }

    private boolean spielIstFertig() 
    {
        if ((f2.x == 2 && f2.y == 2) && (f1.x == 2 && f1.y == 2)) 
        {
            int userChoice = JOptionPane.showConfirmDialog(null,"Es gibt ein Unentschieden \n \n Wollen sie nochmal spielen?","Es gibt ein Unentschieden", JOptionPane.YES_NO_OPTION);
            if(userChoice == JOptionPane.YES_OPTION) restartGame();
            dispose();
        }
        else if (f1.x == 2 && f1.y == 2) 
        {
            int userChoice = JOptionPane.showConfirmDialog(null, f1.farbe + " hat gewonnen! \n \n Wollen sie nochmal spielen?", f1.farbe + " hat gewonnen!", JOptionPane.YES_NO_OPTION);
            if(userChoice == JOptionPane.YES_OPTION) restartGame();
            dispose();
        }
        else if (f2.x == 2 && f2.y == 2) 
        {
            int userChoice = JOptionPane.showConfirmDialog(null, f2.farbe + " hat gewonnen! \n \n Wollen sie nochmal spielen?", f2.farbe + " hat gewonnen!", JOptionPane.YES_NO_OPTION);
            if(userChoice == JOptionPane.YES_OPTION) restartGame();
            dispose();
        }
        return false;
    }

    public void updateSpielfeld() 
    {
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
                        spielfeld[2][2].setIcon(Haus); // Nur Haus
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
                    spielfeld[y][x].setIcon(Street);
                }
            }
        }
        if (spielIstFertig()) return;

        statusLabel.setText(aktuellerSpieler == f1 ? "Blau ist am Zug." : "Rot ist am Zug.");

        coinThrow();
        druckeAktuellenSpieler();
    }

    public void Bewegung(Figur f_1, Figur f_2, String Richtung)
    {
        if (spielIstFertig()) return;

        Figur figur = aktuellerSpieler == null ? f1 : aktuellerSpieler;

        if (f_1 == f_2) 
        {
            switch (Richtung) 
            {
                case "HOCH": aktuelleFigur1.bewegeHoch(); aktuelleFigur1.bewegeHoch(); break;
                case "LINKS": aktuelleFigur1.bewegeLinks(); aktuelleFigur1.bewegeLinks(); break;
                case "RUNTER": aktuelleFigur1.bewegeRunter(); aktuelleFigur1.bewegeRunter(); break;
                case "RECHTS": aktuelleFigur1.bewegeRechts(); aktuelleFigur1.bewegeRechts(); break;
            }
        } else 
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

        System.out.println("Blaue Figur Coords: " + f1.x + " " + f1.y);
        System.out.println("Rote Figur Coords: " + f2.x + " " + f2.y);
        System.out.println("Aktuelle Figur 1 Coords: " + aktuelleFigur1.x + " " + aktuelleFigur1.y);
        System.out.println("Aktuelle Figur 2 Coords: " + aktuelleFigur2.x + " " + aktuelleFigur2.y);

        aktuellerSpieler = (figur == f1) ? f2 : f1;

        updateSpielfeld();
    }

    public void restartGame()
    {
        dispose();
        main(new String[0]);
    }
}