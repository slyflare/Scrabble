import java.util.EventObject;

public class ScrabbleEvent extends EventObject {

    public ScrabbleEvent(Scrabble scrabble) {
        super(scrabble);
    }
}
