import static org.junit.Assert.*;
import org.junit.*;

import java.util.ArrayList;
import java.util.Collection;

/**
 * ScrabbleTest class responsible for Unit Testing
 * @author Riya and Ashwitha
 * */
public class ScrabbleTest {

    ArrayList = new ArrayList<String>();
    this.letterBag = new ArrayList<>();

    @Test
    public void pointsScored() {
        Scrabble test1 = new Scrabble(2);
        for(int i=0;i<7;i++){
            test1.getCurrentPlayer().getLetters().remove(i);
        }
        //test word 'Dices'
        test1.getCurrentPlayer().addLetterTile(new LetterTile('E',1));
        test1.getCurrentPlayer().addLetterTile(new LetterTile('D',2));
        test1.getCurrentPlayer().addLetterTile(new LetterTile('I',1));
        test1.getCurrentPlayer().addLetterTile(new LetterTile('B',3));
        test1.getCurrentPlayer().addLetterTile(new LetterTile('A',1));
        test1.getCurrentPlayer().addLetterTile(new LetterTile('S',1));
        test1.getCurrentPlayer().addLetterTile(new LetterTile('C',3));
        //D
        test1.play(7,7,1, Scrabble.Command.SELECT);
        test1.play(7,7,1, Scrabble.Command.PLACE);
        //I
        test1.play(7,8,2, Scrabble.Command.SELECT);
        test1.play(7,8,2, Scrabble.Command.PLACE);
        //C
        test1.play(7,9,6, Scrabble.Command.SELECT);
        test1.play(7,9,6, Scrabble.Command.PLACE);
        //E
        test1.play(7,10,0, Scrabble.Command.SELECT);
        test1.play(7,10,0, Scrabble.Command.PLACE);
        //S
        test1.play(7,7,5, Scrabble.Command.SELECT);
        test1.play(7,7,5, Scrabble.Command.PLACE);
        test1.play(7,7,5, Scrabble.Command.PASS);
        assertEquals("Points scored by player1:", test1.scoredPoints(),8 );



        for(int i=0;i<7;i++){
            test1.getCurrentPlayer().getLetters().remove(i);
        }
        //test word 'Sad'
        test1.getCurrentPlayer().addLetterTile(new LetterTile('W',4));
        test1.getCurrentPlayer().addLetterTile(new LetterTile('E',1));
        test1.getCurrentPlayer().addLetterTile(new LetterTile('D',2));
        test1.getCurrentPlayer().addLetterTile(new LetterTile('A',1));
        test1.getCurrentPlayer().addLetterTile(new LetterTile('S',1));
        test1.getCurrentPlayer().addLetterTile(new LetterTile('G',2));
        test1.getCurrentPlayer().addLetterTile(new LetterTile('H',4));
        //S
        test1.play(7,7,6, Scrabble.Command.SELECT);
        test1.play(7,7,6, Scrabble.Command.PLACE);
        //A
        test1.play(7,8,4, Scrabble.Command.SELECT);
        test1.play(7,8,4, Scrabble.Command.PLACE);
        //D
        test1.play(7,7,6, Scrabble.Command.SELECT);
        test1.play(7,7,6, Scrabble.Command.PLACE);
        test1.play(7,7,6, Scrabble.Command.PASS);










    }

    @Test
    public void checkWord(){
        Scrabble test2 = new Scrabble(2);
        for(int i = 0; i < 7; i++){
            test2.getCurrentPlayer().getLetters().remove(i);
        }
        //Test word 'Ashes'
        test2.getCurrentPlayer().addLetterTile(new LetterTile('S',1));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('D',2));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('A',1));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('H',4));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('A',1));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('S',1));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('E',1));
        //A
        test2.play(7,7,3, Scrabble.Command.SELECT);
        test2.play(7,7,3, Scrabble.Command.PLACE);

        //S
        test2.play(7,8,0, Scrabble.Command.SELECT);
        test2.play(7,8,0, Scrabble.Command.PLACE);

        //H
        test2.play(7,9,3, Scrabble.Command.SELECT);
        test2.play(7,9,3, Scrabble.Command.PLACE);
        //E
        test2.play(7,10,6, Scrabble.Command.SELECT);
        test2.play(7,10,6, Scrabble.Command.PLACE);
        //S
        test2.play(7,7,5, Scrabble.Command.SELECT);
        test2.play(7,7,5, Scrabble.Command.PLACE);
        test2.play(7,7,5, Scrabble.Command.PASS);

        for(int i=0;i<7;i++){
            test2.getCurrentPlayer().getLetters().remove(i);
        }
        //test word 'Diary'
        test2.getCurrentPlayer().addLetterTile(new LetterTile('A',1));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('R',1));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('D',2));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('I',1));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('Y',4));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('G',2));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('H',4));

        //D
        test2.play(5,7,2, Scrabble.Command.SELECT);
        test2.play(5,7,2, Scrabble.Command.PLACE);
        //I
        test2.play(6,7,3, Scrabble.Command.SELECT);
        test2.play(6,7,3, Scrabble.Command.PLACE);
        //A
        test2.play(7,7,0, Scrabble.Command.SELECT);
        test2.play(7,7,0, Scrabble.Command.PLACE);
        //R
        test2.play(8,7,1, Scrabble.Command.SELECT);
        test2.play(8,7,1, Scrabble.Command.PLACE);
        //Y
        test2.play(9,7,5, Scrabble.Command.SELECT);
        test2.play(9,7,5, Scrabble.Command.PLACE);
        test2.play(7,7,5, Scrabble.Command.PASS);


    }

}