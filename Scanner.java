import java.util.Scanner;
public class goHomeEingabe {
    public static void main(String[] args) {
        Scanner scanner1 = new Scanner(System.in);
        System.out.print("Wohin möchtest du gehen? Hoch(w), Rechts(d), Runter(s) oder Links(a): ");
        String string_input = scanner1.nextLine();
        if(string_input == "w"){
            bewegeHoch();
        }
        else if(string_input == "d"){
            bewegeRechts();
        }
        else if(string_input == "s"){
            bewegeRunter();
        }
        else if(string_input == "a"){
            bewegeLinks();
        }
    }
}