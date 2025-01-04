import java.util.Random;

public class Spiel
{


        Random r = new Random();
        int zahl1;
        int zahl2;

    public void coinThrow()
    {
        zahl1 = r.nextInt(2);
        zahl2 = r.nextInt(2);
    }

    public void druckeAktuellenSpieler() 
    {
            System.out.println("Am Zug ist " + aktuellerSpieler.farbe);
    }

    public static void main(String[] args)
    {
        Figur f1 = new Figur("Blau", 0, 0);
        Figur f2 = new Figur("Rot", 4, 4);

        druckeSpielfeld();
    }

    public void druckeSpielfeld() 
    {
        for(int y = 0; y <= 4; y++) 
        {

            for(int x = 0; x <= 4; x++) 
            {

                if (x == 2 && y == 2 && /*!spielIstFertig()*/)
                    System.out.print("X ");
                else if (f1.x == x && f1.y == y && f2.x == x && f2.y == y)
                    System.out.print("BR");
                else if(f1.x == x && f1.y == y)
                    System.out.print("B ");
                else if(f2.x == x && f2.y == y)
                    System.out.print("R ");
                else
                    System.out.print(". ");

            }
            System.out.println(".");
        }
    }
}