import java.util.Random;

public class Main
{
    Random r = new Random();
    int zahl1;
    int zahl2;
    zahl1 = r.nextInt(2);
    zahl2 = r.nextInt(2);

    public static void main(String[] args)
    {
        System.out.println(zahl1, zahl2);
    }

    int[][] Feld =  {
                        { 1, 2, 3, 4, 5},
                        { 1, 2, 3, 4, 5},
                        { 1, 2, 3, 4, 5},
                        { 1, 2, 3, 4, 5},
                        { 1, 2, 3, 4, 5},
                    };
}