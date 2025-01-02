private class Figur{
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
    public static void main(String[]args){
        Figur f1 = new Figur ("blau", 0, 0);
        Figur f2 = new Figur ("rot", 4, 4);
    }
}