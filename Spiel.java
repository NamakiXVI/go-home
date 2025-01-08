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
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //  Erstellt das Spielfeld
        JPanel boardPanel = new JPanel(new GridLayout(groesse, groesse));
        spielfeld = new JButton[groesse][groesse];
        
        // Spielfeld initialisieren
        for (int y = 0; y < groesse; y++) 
        {
            for (int x = 0; x < groesse; x++) 
            {
                JButton button = new JButton();
                button.setEnabled(false);
                button.setFont(new Font("Arial", Font.PLAIN, 25));
                spielfeld[y][x] = button;
                boardPanel.add(button);
            }
        }

        // Erstellt den Status Panel
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusLabel = new JLabel("Spiel gestartet. Blau ist am Zug.");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        statusPanel.add(statusLabel, BorderLayout.CENTER);

        // Erstellt den Coin Panel
        JPanel CoinPanel = new JPanel(new BorderLayout());
        CoinLabel = new JLabel("");
        CoinLabel.setFont(new Font("Arial", Font.BOLD, 20));
        CoinPanel.add(CoinLabel, BorderLayout.CENTER);

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

        // Add panels to the frame
        add(boardPanel, BorderLayout.CENTER);
        add(statusPanel, BorderLayout.NORTH);
        add(CoinPanel, BorderLayout.PAGE_START);
        add(controlPanel, BorderLayout.SOUTH);

        // Initialize the game board
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
        }else
        {
            Ergebnis += "Rot ";
        }

        if(zahl2 == 0)
        {
            Ergebnis += "Blau ";
        }else
        {
            Ergebnis += "Rot ";
        }
        
        CoinLabel.setText(Ergebnis);
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
            aktuelleFigur1 = f2;
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
        SwingUtilities.invokeLater(() -> 
        {
            Spiel app = new Spiel();
            app.setVisible(true);
        });
        
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
                    spielfeld[2][2].setText("X");
                else if (f1.x == x && f1.y == y && f2.x == x && f2.y == y)
                    spielfeld[y][x].setText("BR");
                else if(f1.x == x && f1.y == y)
                    spielfeld[f1.y][f1.x].setText("B");
                else if(f2.x == x && f2.y == y)
                    spielfeld[f2.y][f2.x].setText("R");
                else
                    spielfeld[y][x].setText("");

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

//        Scanner scanner1 = new Scanner(System.in);
//        System.out.print("Wohin möchtest du gehen? Hoch(w), Rechts(d), Runter(s) oder Links(a): ");
//        String string_input = scanner1.nextLine();


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
//        System.out.println(aktuelleFigur1.x + " " + aktuelleFigur1.y);
//        System.out.println(aktuelleFigur2.x + " " + aktuelleFigur2.y);
//        System.out.println(f1.x + " " + f1.y);
//        System.out.println(f2.x + " " + f2.y);

        if (spielIstFertig()) return;

        updateSpielfeld();
    }
}