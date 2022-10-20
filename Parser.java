import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Parser class responsible for parsing user commands.
 * */
public class Parser {
    private Scanner scanner;

    public Parser(){
        this.scanner = new Scanner(System.in);
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
}
