public class Figur{
    String farbe;
    int x;
    int y;
    public Figur(String farbe, int x, int y){
        this.farbe = farbe;
        this.x = x;
        this.y = y;
    }
    public Figur(){
        this.farbe = "farblos";
        this.x = 0;
        this.y = 0;
    }
    public static void main(String[]args)
    {
    }

    public boolean gewonnen()
    {
        if(this.x == 2 && this.y == 2)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void bewegeHoch()
    {
        this.y = this.y + 1;
    }

    public void bewegeRunter()
    {
        this.y = this.y - 1;
    }

    public void bewegeLinks()
    {
        this.x = this.x - 1;
    }

    public void bewegeRechts()
    {
        this.x = this.x + 1;
    }
}