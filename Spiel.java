public class Spiel
{
    int[][] Feld = {
        { 0, 1, 2, 3, 4},
        { 0, 1, 2, 3, 4},
        { 0, 1, 2, 3, 4},
        { 0, 1, 2, 3, 4},
        { 0, 1, 2, 3, 4},
    }

    public static void main(String[] args)
    {
        System.out.println(Feld.length);
    }

    public void printFeld()
    {
        for(int i = 0; i < Feld.length; i++)
        {
            System.out.println(".");
        }
    }
}