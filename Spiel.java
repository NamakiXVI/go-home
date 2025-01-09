import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Spiel extends JFrame
{
    //variablen für die UI für JFrame
    private final int groesse = 5;
    private JButton[][] spielfeld;
    private JLabel statusLabel;
    private JLabel CoinLabel;
    
    Icon Haus = new ImageIcon(this.getClass().getResource("img/Haus.png"));
    Icon FRot = new ImageIcon("img/FigurRed.png");
    Icon FBlau = new ImageIcon("img/FigurBlue.png");
    Icon Street = new ImageIcon("img/Street.png");
    Icon BCoin = new ImageIcon("img/BlueCoin.png");
    Icon RCoin = new ImageIcon("img/RedCoin.png");
    
    JButton Coin1 = new JButton(RCoin);
    JButton Coin2 = new JButton(BCoin);

    //münzenzahlen
    int zahl1;
    int zahl2;


    Figur f1 = new Figur("Blau", 0, 0);
    Figur f2 = new Figur("Rot", 4, 4);

    Figur aktuelleFigur1;
    Figur aktuelleFigur2;
    Figur aktuellerSpieler;
    
    public Spiel()
    {
        setTitle("Go Home Spiel");
        setSize(670, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        //  Erstellt das Spielfeld
        JPanel boardPanel = new JPanel(new GridLayout(groesse, groesse));
        spielfeld = new JButton[groesse][groesse];
        
        // Spielfeld starten
        for (int y = 0; y < groesse; y++) 
        {
            for (int x = 0; x < groesse; x++) 
            {
                JButton button = new JButton();
                button.setFocusPainted(true);
                spielfeld[y][x] = button;
                boardPanel.add(button);
            }
        }

        // Erstellt den Status Panel
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        statusLabel = new JLabel("Spiel gestartet. Blau ist am Zug.");
        statusLabel.setFont(new Font("Pixelify Sans", Font.BOLD, 20));
        statusPanel.add(statusLabel, BorderLayout.CENTER);
        statusPanel.add(Coin1,BorderLayout.EAST);
        Coin1.setBorderPainted(false);
        Coin1.setBackground(Color.WHITE);
        statusPanel.add(Coin2,BorderLayout.EAST);
        Coin2.setBorderPainted(false);
        Coin2.setBackground(Color.WHITE);

        // Fügt das Movement als buttons hinzu
        JPanel controlPanel = new JPanel(new GridLayout(1, 4));
        String[] directions = {"Hoch", "Links", "Runter", "Rechts"};

        for (int i = 0; i < directions.length; i++) 
        {
            JButton moveButton = new JButton(directions[i]);
            String command = directions[i];
            moveButton.addActionListener(e -> Bewegung(aktuelleFigur1, aktuelleFigur2, command));
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

        String Ergebnis = " ";

        if(zahl1 == 0)
        {
            Ergebnis += "Blau ";
            Coin1.setIcon(BCoin);
        }else
        {
            Ergebnis += "Rot ";
            Coin1.setIcon(RCoin);
        }

        if(zahl2 == 0)
        {
            Ergebnis += "Blau ";
            Coin2.setIcon(BCoin);
        }else
        {
            Ergebnis += "Rot ";
            Coin2.setIcon(RCoin);
        }
        
        System.out.println(Ergebnis);
    }

    public void druckeAktuellenSpieler() 
    {
        if(zahl1 == 1 && zahl2 == 1)
        {
            aktuelleFigur1 = f2;
            aktuelleFigur2 = f2;
            aktuellerSpieler = f2;
        }else if(zahl1 == 0 && zahl2 == 1)
        {
            aktuelleFigur1 = f1;
            aktuelleFigur2 = f2;
            aktuellerSpieler = f1;
        }else if(zahl1 == 1 && zahl2 == 0)
        {
            aktuelleFigur1 = f2;
            aktuelleFigur2 = f1;
            aktuellerSpieler = f2;
        }else if (zahl1 == 0 && zahl2 == 0)
        {
            aktuelleFigur1 = f1;
            aktuelleFigur2 = f1;
            aktuellerSpieler = f1;
        }

        statusLabel.setText(aktuellerSpieler.farbe + " ist am Zug");
        System.out.println(aktuellerSpieler.farbe + " ist am Zug");
    }

    public static void main(String[] args)
    {
            Spiel gohome = new Spiel();
            gohome.setVisible(true);
    }

    private boolean spielIstFertig() 
    {
        if (f1.x == 2 && f1.y == 2) 
        {
            JOptionPane.showMessageDialog(this, f1.farbe + " hat gewonnen!");
            return true;
        }
        if (f2.x == 2 && f2.y == 2) 
        {
            JOptionPane.showMessageDialog(this, f2.farbe + " hat gewonnen!");
            return true;
        }
        return false;
    }   

    public void updateSpielfeld() 
    {
        if (spielIstFertig()) return;

        for(int y = 0; y <= 4; y++) 
        {

            for(int x = 0; x <= 4; x++) 
            {

                if (x == 2 && y == 2 && !spielIstFertig())
                    spielfeld[2][2].setIcon(Haus);
                else if (f1.x == x && f1.y == y && f2.x == x && f2.y == y)
                    spielfeld[y][x].setText("BR");
                else if(f1.x == x && f1.y == y)
                    spielfeld[f1.y][f1.x].setIcon(FBlau);
                else if(f2.x == x && f2.y == y)
                    spielfeld[f2.y][f2.x].setIcon(FRot);
                else
                    spielfeld[y][x].setIcon(Street);

            }
        }

        System.out.println(f1.x + " " + f1.y);
        System.out.println(f2.x + " " + f2.y);

        coinThrow();
        druckeAktuellenSpieler();
    }

    public void Bewegung(Figur f_1, Figur f_2, String Richtung)
    {
        if (spielIstFertig()) return;

        if (f_1 == f_2) 
        {
            switch (Richtung) 
            {
                case "Hoch": aktuelleFigur1.bewegeHoch(); aktuelleFigur1.bewegeHoch(); break;
                case "Links": aktuelleFigur1.bewegeLinks(); aktuelleFigur1.bewegeLinks(); break;
                case "Runter": aktuelleFigur1.bewegeRunter(); aktuelleFigur1.bewegeRunter(); break;
                case "Rechts": aktuelleFigur1.bewegeRechts(); aktuelleFigur1.bewegeRechts(); break;
            }
        } else 
        {
            switch (Richtung) 
            {
                case "Hoch": aktuelleFigur1.bewegeHoch(); aktuelleFigur2.bewegeHoch(); break;
                case "Links": aktuelleFigur1.bewegeLinks(); aktuelleFigur2.bewegeLinks(); break;
                case "Runter": aktuelleFigur1.bewegeRunter(); aktuelleFigur2.bewegeRunter(); break;
                case "Rechts": aktuelleFigur1.bewegeRechts(); aktuelleFigur2.bewegeRechts(); break;
            }
        }

        aktuelleFigur1.checkFeld();
        aktuelleFigur2.checkFeld();

        if (spielIstFertig()) 
        {
            statusLabel.setText(aktuellerSpieler.farbe + " hat das Spiel gewonnen!");
            return;
        }

        if (spielIstFertig()) return;

        updateSpielfeld();
    }
}