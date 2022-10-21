import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Parser class responsible for parsing user commands.
 * */
public class Parser {
    private Scanner scanner;
    private File WordBank;

    public Parser(){
        this.scanner = new Scanner(System.in);
        this.WordBank = new File("WordBank");
    }

    public ArrayList<String> getCommand() {
        String inputLine;
        ArrayList<String> command = new ArrayList<String>();

        System.out.print("> ");     // print prompt

        inputLine = scanner.nextLine();
        
        Scanner tokenizer = new Scanner(inputLine);
        while (tokenizer.hasNext()) {
            command.add(tokenizer.next());
        }

        return command;
    }

    private boolean wordCheck(ArrayList<String> command){
        if(command.get(0).length() != 1){
            //temp
            return false;
        }
        
        return false;
    }

}
