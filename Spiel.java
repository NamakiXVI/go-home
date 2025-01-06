import java.util.Random;
import java.util.Scanner;

public class Spiel
{
    int zahl1;
    int zahl2;

    Figur f1 = new Figur("Blau", 0, 0);
    Figur f2 = new Figur("Rot", 4, 4);

    Figur aktuelleFigur1;
    Figur aktuelleFigur2;
    Figur aktuellerSpieler;
    
    public void coinThrow()
    {
        Random r = new Random();
        zahl1 = r.nextInt(2);
        zahl2 = r.nextInt(2);

        String Ergebnis = "";

        if(zahl1 == 0)
        {
            Ergebnis += "Blau";
        }else
        {
            Ergebnis += "Rot";
        }

        if(zahl2 == 0)
        {
            Ergebnis += "Blau";
        }else
        {
            Ergebnis += "Rot";
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

        System.out.println("Am Zug ist " + aktuellerSpieler.farbe);

        Bewegung(aktuelleFigur1, aktuelleFigur2);
    }

    public static void main(String[] args)
    {
        Spiel spiel = new Spiel();
        spiel.druckeSpielfeld();
        spiel.druckeAktuellenSpieler();
        
    }

    public void druckeSpielfeld() 
    {
        for(int y = 0; y <= 4; y++) 
        {

            for(int x = 0; x <= 4; x++) 
            {

                if (x == 2 && y == 2 /* && !spielIstFertig()*/)
                    System.out.print("X ");
                else if (f1.x == x && f1.y == y && f2.x == x && f2.y == y)
                    System.out.print("BR ");
                else if(f1.x == x && f1.y == y)
                    System.out.print("B ");
                else if(f2.x == x && f2.y == y)
                    System.out.print("R ");
                else
                    System.out.print(". ");

            }
            System.out.println();
        }
        coinThrow();
        druckeAktuellenSpieler();
    }

    public void Bewegung(Figur f_1, Figur f_2)
    {
        Scanner scanner1 = new Scanner(System.in);
        System.out.print("Wohin möchtest du gehen? Hoch(w), Rechts(d), Runter(s) oder Links(a): ");
        String string_input = scanner1.nextLine();

        System.out.println(string_input);

        if(string_input.equals("w")){
            f_1.bewegeHoch();
            f_2.bewegeHoch();
        }
        else if(string_input.equals("a")){
            f_1.bewegeRechts();
            f_2.bewegeRechts();
        }
        else if(string_input.equals("s")){
            f_1.bewegeRunter();
            f_2.bewegeRunter();
        }
        else if(string_input.equals("d")){
            f_1.bewegeLinks();
            f_2.bewegeLinks();
        }
        druckeSpielfeld();
    }
}