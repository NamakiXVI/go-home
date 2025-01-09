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

    public boolean gewonnen()
    {
        if(this.x == 2 && this.y == 2)
        {
            System.out.println(this.farbe + " hat Gewonnen");
            return true;
        }
        
        return false;
    }

    public void bewegeHoch()
    {
        this.y = this.y - 1;
    }

    public void bewegeRunter()
    {
        this.y = this.y + 1;
    }

    public void bewegeLinks()
    {
        this.x = this.x - 1;
    }

    public void bewegeRechts()
    {
        this.x = this.x + 1;
    }

    public void checkFeld()
    {
        if(this.x > 4)
        {
            this.x = this.x - 5;
        }else if(this.x < 0)
        {
            this.x = this.x + 5;
        }else if(this.y > 4)
        {
            this.y = this.y - 5;
        }else if(this.y < 0)
        {
            this.y = this.y + 5;
        }
    }
}