import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * AI Player Class
 */

public class AiPlayer extends Player{

    private ArrayList<String> wordBank;
    private Board board;
    private Scrabble scrabble;

    public AiPlayer(Scrabble scrabble, String name, ArrayList<String> wordBank) {
        super(name);
        this.wordBank = wordBank;
        this.board = new Board();
        this.scrabble = scrabble;
    }

    private Board makeMove(Board board){
        this.board = board;

        String hWord = "";
        String vWord = "";
        int highestHorizontalScore = 0;
        int highestVerticalScore = 0;
        ArrayList<Integer> horizontalPosition = new ArrayList<>();
        ArrayList<Integer> verticalPosition = new ArrayList<>();

        HashMap<ArrayList<Integer>, String> horizontal = checkHorizontalPossibilities();
        HashMap<ArrayList<Integer>, String> vertical = checkVerticalPossibilities();

        //calculate horizontals
        for(String s: horizontal.values()){
            int score = 0;
            for(char c: s.toCharArray()){
                score += new LetterTile(c).getNum();
            }
            if(score > highestHorizontalScore){
                hWord = s;
                highestHorizontalScore = score;
            }
        }

        //calculate verticals
        for(String s: vertical.values()){
            int score = 0;
            for(char c: s.toCharArray()){
                score += new LetterTile(c).getNum();
            }
            if(score > highestVerticalScore){
                vWord = s;
                highestVerticalScore = score;
            }
        }

        //play
        HashMap<ArrayList<Integer>, Character> play = new HashMap<>();
        if(highestHorizontalScore > highestVerticalScore){
            for(ArrayList<Integer> xy : horizontal.keySet()){
                if(horizontal.get(xy).equals(hWord)){
                    horizontalPosition = xy;
                    break;
                }
            }

            for(int i = 0; i < hWord.length(); i++){
                play.put(new ArrayList<Integer>(Arrays.asList(horizontalPosition.get(0) + i, horizontalPosition.get(1))), hWord.toCharArray()[i]);
            }

            this.board.updateBoard(play);
        }
        if(highestHorizontalScore < highestVerticalScore){
            for(ArrayList<Integer> xy : vertical.keySet()){
                if(vertical.get(xy).equals(vWord)){
                    verticalPosition = xy;
                    break;
                }
            }

            for(int i = 0; i < vWord.length(); i++){
                play.put(new ArrayList<Integer>(Arrays.asList(verticalPosition.get(0), verticalPosition.get(1) + i)), vWord.toCharArray()[i]);
            }

            this.board.updateBoard(play);
        }

        return board;
    }

    private HashMap<ArrayList<Integer>, String> checkHorizontalPossibilities(){
        ArrayList<LetterTile> hand = getLetters();
        boolean wordValid = true;
        HashMap<ArrayList<Integer>, String> possibilities = new HashMap<>();

        //If board is empty find a word to start the game.
        if(board.isEmpty()){
            for(String s : wordBank){
                for(int i = 0; i < s.length(); i++){
                    if(!hand.contains(new LetterTile(s.toCharArray()[i]))){
                        wordValid = false;
                    }
                }
                if(wordValid){
                    possibilities.put(new ArrayList<Integer>(Arrays.asList(7-(s.length()/2),7)),s);
                }
                wordValid = true;
            }
        }

        return possibilities;
    }

    private HashMap<ArrayList<Integer>, String> checkVerticalPossibilities(){

        ArrayList<LetterTile> hand = getLetters();
        boolean wordValid = true;
        HashMap<ArrayList<Integer>, String> possibilities = new HashMap<>();

        //If board is empty find a word to start the game.
        if(board.isEmpty()){
            for(String s : wordBank){
                for(int i = 0; i < s.length(); i++){
                    if(!hand.contains(new LetterTile(s.toCharArray()[i]))){
                        wordValid = false;
                    }
                }
                if(wordValid){
                    possibilities.put(new ArrayList<Integer>(Arrays.asList(7-(s.length()/2),7)),s);
                }
                wordValid = true;
            }
        }

        return possibilities;
    }

    @Override
    public boolean isAI(){
        return true;
    }

}
