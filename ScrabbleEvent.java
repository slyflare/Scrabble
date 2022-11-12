import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashMap;
import java.util.List;

public class ScrabbleEvent extends EventObject {
    private int x;
    private int y;
    private Board board;
    private int currentPlayer;
    private Character currentLetter;
    private HashMap<ArrayList<Integer>,Character> playerPlacement;
    private Scrabble.Command command;
    public ScrabbleEvent(Scrabble scrabble, int x, int y, Board board, int currentPlayer, Character currentLetter,
                         HashMap<ArrayList<Integer>, Character> playerPlacement, Scrabble.Command command) {
        super(scrabble);
        this.x = x;
        this.y = y;
        this.board = board;
        this.currentPlayer = currentPlayer;
        this.playerPlacement = playerPlacement;
        this.command = command;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public Board getBoard() {
        return board;
    }

    public HashMap<ArrayList<Integer>, Character> getPlayerPlacement() {
        return playerPlacement;
    }

    public Character getCurrentLetter() {
        return currentLetter;
    }

    public Scrabble.Command getCommand() {
        return command;
    }
}
