import java.util.ArrayList;
import java.lang.Object.*;
/**
 * Scrabble class creates the game, players, letterBag, Parser, board
 */
public class Scrabble {
    private Board board;
    private ArrayList<Players> players;
    private Parser p;
    private ArrayList<Character> letterBag;

    /**
     * Constructor for class scrabble
     */
    public void Scrabble() {
        this.board = new Board();
        this.p = new Parser();
        createLetterBag();
    }

    public void createLetterBag() {

    }

}
