// Philipp Nguyen, Falk Milbrodt
//Unser GitHub Repository: https://github.com/NamakiXVI/go-home
// Go-Home Java Projekt
// Informatik LK 2024 Q2
// Lehrer: Herr Wessel
public class Figur{//erstellt die Klasse Figur
    //gibt der Klasse Figur Attribute
    String farbe;
    int x;
    int y;
    //Konnstruktor für die Figur
    public Figur(String farbe, int x, int y){
        this.farbe = farbe;
        this.x = x;
        this.y = y;
    }
    //Defoult Werte beim erstellen einer neuen Figur
    public Figur(){
        this.farbe = "farblos";
        this.x = 0;
        this.y = 0;
    }
    //eine Methode die checkt ob eine Figur gewonnen hat
    public boolean gewonnen()
    {
        if(this.x == 2 && this.y == 2)
        {
            //Text der mit dem namen der Farbe gedruckt wird die Gewonnen hat
            System.out.println(this.farbe + " hat Gewonnen");
            return true;
        }
        
        return false;
    }
    //eine Methode die dafür sorgt das die Figur sich um ein Feld nach oben bewegt
    public void bewegeHoch()
    {
        this.y = this.y - 1;
    }
    //eine Methode die dafür sorgt das die Figur sich um ein Feld nach unten bewegt
    public void bewegeRunter()
    {
        this.y = this.y + 1;
    }
    //eine Methode die dafür sorgt das die Figur sich um ein Feld nach links bewegt
    public void bewegeLinks()
    {
        this.x = this.x - 1;
    }
    //eine Methode die dafür sorgt das die Figur sich um ein Feld nach rechts bewegt
    public void bewegeRechts()
    {
        this.x = this.x + 1;
    }
    //sorgt dafür dass das Spielfeld keine Grenzen hat und die Figur wieder auf der gegenüberliegenden Seite ins Spielfeld kommt 
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