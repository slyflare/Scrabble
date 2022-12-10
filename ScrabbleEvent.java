import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashMap;
import java.util.List;

/**
 * ScrabbleEvent class responsible for keeping track of each change in the model
 * @author Vimal
 *
 * */
public class ScrabbleEvent extends EventObject {
    private int x;
    private int y;
    private int turn;
    private Board board;
    private Player currentPlayer;
    private Player previousPlayer;
    private Character currentLetter;
    private Scrabble.Command command;
    private HashMap<ArrayList<Integer>,Integer> premium;
    public ScrabbleEvent(Scrabble scrabble, int x, int y, int turn, Board board, HashMap<ArrayList<Integer>,Integer> premium, Player currentPlayer, Player previousPlayer, Character currentLetter,
                        Scrabble.Command command) {
        super(scrabble);
        this.x = x;
        this.y = y;
        this.premium = premium;
        this.turn = turn;
        this.board = board;
        this.currentPlayer = currentPlayer;
        this.previousPlayer = previousPlayer;
        this.currentLetter = currentLetter;
        this.command = command;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getPreviousPlayer() {
        return previousPlayer;
    }

    public Board getBoard() {
        return board;
    }

    public Character getCurrentLetter() {
        return currentLetter;
    }

    public Scrabble.Command getCommand() {
        return command;
    }

    public int getTurn() {
        return turn;
    }

    public HashMap<ArrayList<Integer>, Integer> getPremium() {
        return premium;
    }
}
