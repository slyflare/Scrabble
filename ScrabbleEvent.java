import java.util.EventObject;

public class ScrabbleEvent extends EventObject {
    private int x;
    private int y;
    private Player currentPlayer;
    public ScrabbleEvent(Scrabble scrabble, int x, int y, Player currentPlayer) {
        super(scrabble);
        this.x = x;
        this.y = y;
        this.currentPlayer = currentPlayer;
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
}
