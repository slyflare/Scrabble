import static org.junit.Assert.*;
import org.junit.*;
import java.util.Collection;

/**
 * ScrabbleTest class responsible for Unit Testing
 * @author Riya and Ashwitha
 * */
public class ScrabbleTest {
    @Test
    public void pointsScored() {
        Scrabble test = new Scrabble(2);
        for(int i=0;i<7;i++){
            test.getCurrentPlayer().getLetters().remove(i);
        }
        test.getCurrentPlayer().addLetterTile(new LetterTile('E',1));
        test.getCurrentPlayer().addLetterTile(new LetterTile('D',2));
        test.getCurrentPlayer().addLetterTile(new LetterTile('I',1));
        test.getCurrentPlayer().addLetterTile(new LetterTile('B',3));
        test.getCurrentPlayer().addLetterTile(new LetterTile('A',1));
        test.getCurrentPlayer().addLetterTile(new LetterTile('S',1));
        test.getCurrentPlayer().addLetterTile(new LetterTile('C',3));

        test.play(7,7,1, Scrabble.Command.SELECT);
        test.play(7,7,1, Scrabble.Command.PLACE);

        test.play(7,8,2, Scrabble.Command.SELECT);
        test.play(7,8,2, Scrabble.Command.PLACE);

        test.play(7,9,6, Scrabble.Command.SELECT);
        test.play(7,9,6, Scrabble.Command.PLACE);

        test.play(7,10,0, Scrabble.Command.SELECT);
        test.play(7,10,0, Scrabble.Command.PLACE);

        test.play(7,7,5, Scrabble.Command.SELECT);
        test.play(7,7,5, Scrabble.Command.PLACE);
        test.play(7,7,5, Scrabble.Command.PASS);

        for(int i=0;i<7;i++){
            test.getCurrentPlayer().getLetters().remove(i);
        }
        test.getCurrentPlayer().addLetterTile(new LetterTile('W',4));
        test.getCurrentPlayer().addLetterTile(new LetterTile('E',1));
        test.getCurrentPlayer().addLetterTile(new LetterTile('D',2));
        test.getCurrentPlayer().addLetterTile(new LetterTile('A',1));
        test.getCurrentPlayer().addLetterTile(new LetterTile('S',1));
        test.getCurrentPlayer().addLetterTile(new LetterTile('G',2));
        test.getCurrentPlayer().addLetterTile(new LetterTile('H',4));

        test.play(7,7,6, Scrabble.Command.SELECT);
        test.play(7,7,6, Scrabble.Command.PLACE);

        test.play(7,8,4, Scrabble.Command.SELECT);
        test.play(7,8,4, Scrabble.Command.PLACE);

        test.play(7,7,6, Scrabble.Command.SELECT);
        test.play(7,7,6, Scrabble.Command.PLACE);
        test.play(7,7,6, Scrabble.Command.PASS);










    }

    @Test
    public void checkWord(){
        Scrabble test = new Scrabble(2);
        for(int i = 0; i < 7; i++){
            test.getCurrentPlayer().getLetters().remove(i);
        }
        test.getCurrentPlayer().addLetterTile(new LetterTile('E',1));
        test.getCurrentPlayer().addLetterTile(new LetterTile('D',2));
        test.getCurrentPlayer().addLetterTile(new LetterTile('I',1));
        test.getCurrentPlayer().addLetterTile(new LetterTile('B',3));
        test.getCurrentPlayer().addLetterTile(new LetterTile('A',1));
        test.getCurrentPlayer().addLetterTile(new LetterTile('S',1));
        test.getCurrentPlayer().addLetterTile(new LetterTile('C',3));

        test.play(7,7,1, Scrabble.Command.SELECT);
        test.play(7,7,1, Scrabble.Command.PLACE);

        test.play(7,8,2, Scrabble.Command.SELECT);
        test.play(7,8,2, Scrabble.Command.PLACE);

        test.play(7,9,6, Scrabble.Command.SELECT);
        test.play(7,9,6, Scrabble.Command.PLACE);

        test.play(7,10,0, Scrabble.Command.SELECT);
        test.play(7,10,0, Scrabble.Command.PLACE);

        test.play(7,7,5, Scrabble.Command.SELECT);
        test.play(7,7,5, Scrabble.Command.PLACE);
        test.play(7,7,5, Scrabble.Command.PASS);


    }

}