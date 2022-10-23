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

        System.out.println("Enter command. ('PLACE 1 1 DOWN B (A) D' or 'QUIT' or 'PASS' or 'DRAW A')");
        System.out.print("> ");

        inputLine = scanner.nextLine();
        
        Scanner tokenizer = new Scanner(inputLine);
        while (tokenizer.hasNext()) {
            command.add(tokenizer.next());
        }

        if(!commandCheck(command)){
            command.add(0, "ERROR");
            System.out.println("Invalid Command.");
            return command;
        }

        return command;
    }

    private boolean commandCheck(ArrayList<String> command){
        return command.get(0).compareTo("PLACE") == 0 || command.get(0).compareTo("QUIT") == 0 ||
                command.get(0).compareTo("PASS") == 0 || command.get(0).compareTo("DRAW") == 0;
    }

}
