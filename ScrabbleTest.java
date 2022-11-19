import static org.junit.Assert.*;
import org.junit.*;

import java.util.ArrayList;
import java.util.Collection;

/**
 * ScrabbleTest class responsible for Unit Testing
 * @author Riya and Ashwitha
 * */
public class ScrabbleTest {

   // ArrayList = new ArrayList<String>();
    //this.letterBag = new ArrayList<>();

    @Test
    public void pointsScored() {
        Scrabble test1 = new Scrabble(2);
        ArrayList<LetterTile> a = test1.getCurrentPlayer().getLetters();
        for(int j = 0; j < 7; j++) {
            test1.getCurrentPlayer().removeLetterTile(a.get(0).getLetter());
        }
        //test word 'Dice'
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

        test1.play(7,11,5, Scrabble.Command.SUBMIT);
        test1.play(7, 7, 1, Scrabble.Command.PASS);
        assertEquals("Points scored by player1:", 7, test1.getCurrentPlayer().GetScore());
        test1.play(7, 7, 1, Scrabble.Command.PASS);


        a = test1.getCurrentPlayer().getLetters();
        for(int j = 0; j < 7; j++) {
            test1.getCurrentPlayer().removeLetterTile(a.get(0).getLetter());
        }
        //test word 'Met' extended from E
        test1.getCurrentPlayer().addLetterTile(new LetterTile('M',3));
        test1.getCurrentPlayer().addLetterTile(new LetterTile('E',1));
        test1.getCurrentPlayer().addLetterTile(new LetterTile('D',2));
        test1.getCurrentPlayer().addLetterTile(new LetterTile('A',1));
        test1.getCurrentPlayer().addLetterTile(new LetterTile('T',1));
        test1.getCurrentPlayer().addLetterTile(new LetterTile('G',2));
        test1.getCurrentPlayer().addLetterTile(new LetterTile('H',4));
        //M
        test1.play(6,10,0, Scrabble.Command.SELECT);
        test1.play(6,10,0, Scrabble.Command.PLACE);
        //T
        test1.play(8,10,4, Scrabble.Command.SELECT);
        test1.play(8,10,4, Scrabble.Command.PLACE);

        test1.play(7,7,6, Scrabble.Command.SUBMIT);

        assertEquals("Points scored by player2:", 5, test1.getCurrentPlayer().GetScore());

    }

    @Test
    public void checkWord(){
        Scrabble test2 = new Scrabble(2);

        ArrayList<LetterTile> a = test2.getCurrentPlayer().getLetters();
        for(int j = 0; j < 7; j++) {
            test2.getCurrentPlayer().removeLetterTile(a.get(0).getLetter());
        }
        //Test word 'flash' (valid word)
        test2.getCurrentPlayer().addLetterTile(new LetterTile('L',1));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('D',2));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('F',4));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('A',1));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('A',1));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('H',1));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('S',1));
        //F
        test2.play(7,7,2, Scrabble.Command.SELECT);
        test2.play(7,7,2, Scrabble.Command.PLACE);

        //L
        test2.play(7,8,0, Scrabble.Command.SELECT);
        test2.play(7,8,0, Scrabble.Command.PLACE);

        //A
        test2.play(7,9,3, Scrabble.Command.SELECT);
        test2.play(7,9,3, Scrabble.Command.PLACE);
        //S
        test2.play(7,10,6, Scrabble.Command.SELECT);
        test2.play(7,10,6, Scrabble.Command.PLACE);
        //H
        test2.play(7,11,5, Scrabble.Command.SELECT);
        test2.play(7,11,5, Scrabble.Command.PLACE);


        assertTrue("Word Check", test2.submit());



        a = test2.getCurrentPlayer().getLetters();
        for(int j = 0; j < 7; j++) {
            test2.getCurrentPlayer().removeLetterTile(a.get(0).getLetter());
        }
        //test word 'Dary' (invalid word)
        test2.getCurrentPlayer().addLetterTile(new LetterTile('A',1));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('R',1));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('D',2));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('I',1));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('Y',4));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('G',2));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('H',4));

        //D
        test2.play(6,9,2, Scrabble.Command.SELECT);
        test2.play(6,9,2, Scrabble.Command.PLACE);
        //R
        test2.play(8,9,1, Scrabble.Command.SELECT);
        test2.play(8,9,1, Scrabble.Command.PLACE);
        //Y
        test2.play(9,9,4, Scrabble.Command.SELECT);
        test2.play(9,9,4, Scrabble.Command.PLACE);

        assertFalse("Word Check", test2.submit());
    }

    @Test
    public void testPlacement(){
        Scrabble test2 = new Scrabble(2);

        ArrayList<LetterTile> a = test2.getCurrentPlayer().getLetters();
        for(int j = 0; j < 7; j++) {
            test2.getCurrentPlayer().removeLetterTile(a.get(0).getLetter());
        }
        //Test word 'flash' (valid word) (horizontal)
        test2.getCurrentPlayer().addLetterTile(new LetterTile('L',1));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('D',2));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('F',4));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('A',1));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('A',1));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('H',1));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('S',1));
        //F
        test2.play(7,7,2, Scrabble.Command.SELECT);
        test2.play(7,7,2, Scrabble.Command.PLACE);

        //L
        test2.play(8,7,0, Scrabble.Command.SELECT);
        test2.play(8,7,0, Scrabble.Command.PLACE);

        //A
        test2.play(9,7,3, Scrabble.Command.SELECT);
        test2.play(9,7,3, Scrabble.Command.PLACE);
        //S
        test2.play(10,7,6, Scrabble.Command.SELECT);
        test2.play(10,7,6, Scrabble.Command.PLACE);
        //H
        test2.play(11,7,5, Scrabble.Command.SELECT);
        test2.play(11,7,5, Scrabble.Command.PLACE);
        test2.play(11,7,5, Scrabble.Command.SUBMIT);


        //assertTrue(test2.submit());



        a = test2.getCurrentPlayer().getLetters();
        for(int j = 0; j < 7; j++) {
            test2.getCurrentPlayer().removeLetterTile(a.get(0).getLetter());
        }
        //test word 'HAT' down off H
        test2.getCurrentPlayer().addLetterTile(new LetterTile('A',1));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('R',1));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('T',2));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('I',1));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('Y',4));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('G',2));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('H',4));

        //A
        test2.play(11,8,0, Scrabble.Command.SELECT);
        test2.play(11,8,0, Scrabble.Command.PLACE);
        //T
        test2.play(11,9,2, Scrabble.Command.SELECT);
        test2.play(11,9,2, Scrabble.Command.PLACE);
        test2.play(11,9,2, Scrabble.Command.SUBMIT);
        //assertTrue("Word Check", test2.submit());
    }
    @Test
    public void testParallelPlacementHorizontal(){
        Scrabble test2 = new Scrabble(2);

        ArrayList<LetterTile> a = test2.getCurrentPlayer().getLetters();
        for(int j = 0; j < 7; j++) {
            test2.getCurrentPlayer().removeLetterTile(a.get(0).getLetter());
        }
        //Test word 'bow' (valid word) (horizontal)
        test2.getCurrentPlayer().addLetterTile(new LetterTile('W',1));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('B',2));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('F',4));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('O',1));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('A',1));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('H',1));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('S',1));
        //B
        test2.play(7,7,1, Scrabble.Command.SELECT);
        test2.play(7,7,1, Scrabble.Command.PLACE);

        //O
        test2.play(8,7,3, Scrabble.Command.SELECT);
        test2.play(8,7,3, Scrabble.Command.PLACE);

        //W
        test2.play(9,7,0, Scrabble.Command.SELECT);
        test2.play(9,7,0, Scrabble.Command.PLACE);

        test2.play(9,7,0, Scrabble.Command.SUBMIT);


        //assertTrue(test2.submit());



        a = test2.getCurrentPlayer().getLetters();
        for(int j = 0; j < 7; j++) {
            test2.getCurrentPlayer().removeLetterTile(a.get(0).getLetter());
        }
        //test word 'BOW
        //             EYE' (we and eye)
        test2.getCurrentPlayer().addLetterTile(new LetterTile('E',1));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('R',1));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('T',2));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('Y',1));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('Y',4));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('E',2));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('H',4));

        //E
        test2.play(9,8,0, Scrabble.Command.SELECT);
        test2.play(9,8,0, Scrabble.Command.PLACE);
        //Y
        test2.play(10,8,3, Scrabble.Command.SELECT);
        test2.play(10,8,3, Scrabble.Command.PLACE);
        //E
        test2.play(11,8,5, Scrabble.Command.SELECT);
        test2.play(11,8,5, Scrabble.Command.PLACE);

        test2.play(11,8,5, Scrabble.Command.SUBMIT);
        //assertTrue("Word Check", test2.submit());
    }
    @Test
    public void testParallelPlacementVertical(){
        Scrabble test2 = new Scrabble(2);

        ArrayList<LetterTile> a = test2.getCurrentPlayer().getLetters();
        for(int j = 0; j < 7; j++) {
            test2.getCurrentPlayer().removeLetterTile(a.get(0).getLetter());
        }
        //Test word 'bow' (valid word) (vertical)
        test2.getCurrentPlayer().addLetterTile(new LetterTile('W',1));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('B',2));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('F',4));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('O',1));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('A',1));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('H',1));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('S',1));
        //B
        test2.play(7,7,1, Scrabble.Command.SELECT);
        test2.play(7,7,1, Scrabble.Command.PLACE);

        //O
        test2.play(7,8,3, Scrabble.Command.SELECT);
        test2.play(7,8,3, Scrabble.Command.PLACE);

        //W
        test2.play(7,9,0, Scrabble.Command.SELECT);
        test2.play(7,9,0, Scrabble.Command.PLACE);

        test2.play(9,7,0, Scrabble.Command.SUBMIT);


        //assertTrue(test2.submit());



        a = test2.getCurrentPlayer().getLetters();
        for(int j = 0; j < 7; j++) {
            test2.getCurrentPlayer().removeLetterTile(a.get(0).getLetter());
        }
        //test word 'B
        //           O
        //           W E
        //             Y
        //             E' (we and eye)
        test2.getCurrentPlayer().addLetterTile(new LetterTile('E',1));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('R',1));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('T',2));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('Y',1));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('Y',4));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('E',2));
        test2.getCurrentPlayer().addLetterTile(new LetterTile('H',4));

        //E
        test2.play(8,9,0, Scrabble.Command.SELECT);
        test2.play(8,9,0, Scrabble.Command.PLACE);
        //Y
        test2.play(8,10,3, Scrabble.Command.SELECT);
        test2.play(8,10,3, Scrabble.Command.PLACE);
        //E
        test2.play(8,11,5, Scrabble.Command.SELECT);
        test2.play(8,11,5, Scrabble.Command.PLACE);

        test2.play(8,11,5, Scrabble.Command.SUBMIT);
        //assertTrue("Word Check", test2.submit());
    }

}