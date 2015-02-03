
import java.io.EOFException;
import java.io.FileNotFoundException;

public class Project1 {

    public static void main (String[] args) throws FileNotFoundException, EOFException {
        ToyScanner scanner, scanner2;
        try {
            scanner = new ToyScanner(175);
            //scanner2 = new ToyScanner(275);
            System.out.println("PRIMARY TEST FILE OUTPUT\n--------------------");
            scanner.scanFile("ToyTestFile.toy", true);
            System.out.println("\nSECONDARY TEST FILE OUTPUT\n--------------------");
            //scanner2.scanFile("ToyTestFile2.toy", true);
            System.out.println("\nSYMBOL TABLE AFTER TEST FILE 1\n--------------------");
            scanner.showTables();
            System.out.println("\nSYMBOL TABLE AFTER TEST FILE 2\n--------------------");
            //scanner2.showTables();
        } catch (FileNotFoundException e){
            System.out.println("Couldn't find given file to parse. ");
            e.printStackTrace();
            return;
        } catch (EOFException e) {
            System.out.println("File ended unexpectedly. Unclosed multi-line comment exists.");
            e.printStackTrace();
            return;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        /*int val = -3;
        while(val != -2) {
            val = scanner.yylex();
            //do something with val
        }*/
    }
}