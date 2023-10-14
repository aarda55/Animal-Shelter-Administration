import java.util.Scanner;

/**
 *
 * @author Arda Aksu
 */
@SuppressWarnings("SpellCheckingInspection")
public class NewScanner {

    public NewScanner(){}

    /**
     * root root
     * @return Die Eingabe der nächsten Zeile als Int bei Zahlenwerten
     */
    public int nextInt2(){
        int intInput;
        try {
            Scanner scanner = new Scanner(System.in);
            intInput = scanner.nextInt();
        }catch (Exception e){
        System.out.println("Nur Zahlen sollten verwendet werden!\n");
        return -1;
        }
        return intInput;
    }

    /**
     *
     * @return Die Eingabe der nächsten Zeile als String
     */
    public String nextLine2() {
        String StringInput;
        try {
            Scanner scanner = new Scanner(System.in);
            StringInput = scanner.nextLine();
        } catch (Exception e) {
            System.out.println("\nNur Buchstaben sollten verwendet werden!\n");
            return null;
        }
        return StringInput;
    }
}
