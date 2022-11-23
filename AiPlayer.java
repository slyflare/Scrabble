import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * AI Player Class
 * @author Vimal and Ashwitha
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
        else { //must build off a letter on the board
            ArrayList<Character> charList = board.getLetters(); //existing letters on board
            for(char c : charList) {
                for(String s : getPossibleWords(c)) {
                    //if(placmement is possible){ possibilities.put(new ArrayList(x, y), s); }
                }

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
        else { //must build off a letter on the board
            ArrayList<Character> charList = board.getLetters(); //existing letters on board
            for(char c : charList) {
                for(String s : getPossibleWords(c)) {
                    //if(placmement is possible){ possibilities.put(new ArrayList(x, y), s); }
                }

            }

        }

        return possibilities;
    }

    private ArrayList<String> getPossibleWords(char c) {
        ArrayList<LetterTile> letterTiles = getLetters(); //existing letters on board
        ArrayList<Character> chars = new ArrayList<>();
        ArrayList<String> words = new ArrayList<>();
        for(LetterTile t : letterTiles) {
            chars.add(t.getLetter());
        }
        boolean wordValid;
        for(String s : wordBank) {
            wordValid = true;
            if(!s.contains(String.valueOf(c))) {
                wordValid = false;
            }
            for(int i = 0; i < s.length(); i++){
                if (!chars.contains(s.charAt(i))) {
                    wordValid = false;
                    break;
                }
            }
            if(wordValid) {
                words.add(s);
            }
        }
        for(String s : words) {
            System.out.println(s);
        }
        return words;
    }
    @Override
    public boolean isAI(){
        return true;
    }

}
