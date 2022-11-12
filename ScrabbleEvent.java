import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashMap;
import java.util.List;

public class ScrabbleEvent extends EventObject {
    private int x;
    private int y;
    private Board board;
    private Player currentPlayer;
    private Character currentLetter;
    private Scrabble.Command command;
    public ScrabbleEvent(Scrabble scrabble, int x, int y, Board board, Player currentPlayer, Character currentLetter,
                        Scrabble.Command command) {
        super(scrabble);
        this.x = x;
        this.y = y;
        this.board = board;
        this.currentPlayer = currentPlayer;
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

    public Board getBoard() {
        return board;
    }

    public Character getCurrentLetter() {
        return currentLetter;
    }

    public Scrabble.Command getCommand() {
        return command;
    }
}
