import java.util.Random;

public class Main
{
    Random r = new Random();
    int zahl1;
    int zahl2;
    zahl1 = r.nextInt(2);
    zahl2 = r.nextInt(2);

    int aktuelleFigur1;

    public static void main(String[] args)
    {
        System.out.println(zahl1, zahl2);
        Figur f1 = new Figur("Blau", 0, 0);
        Figur f1 = new Figur("Rot", 4, 4);
    }

    int[][] Feld =  {
                        { 0, 1, 2, 3, 4},
                        { 0, 1, 2, 3, 4},
                        { 0, 1, 2, 3, 4},
                        { 0, 1, 2, 3, 4},
                        { 0, 1, 2, 3, 4},
                    };
}
