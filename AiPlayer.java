import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * AI Player Class
 * @author Vimal and Ashwitha
 */

public class AiPlayer extends Player implements Serializable {

    private ArrayList<String> wordBank;
    private Board board;
    private Scrabble scrabble;

    public AiPlayer(Scrabble scrabble, String name, ArrayList<String> wordBank) {
        super(name);
        this.wordBank = wordBank;
        this.board = new Board();
        this.scrabble = scrabble;
    }

    public Board makeMove(Board board){
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

        System.out.println(hWord);
        System.out.println(vWord);
        System.out.println(horizontal.toString());
        System.out.println(vertical.toString());

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
        else if(highestHorizontalScore <= highestVerticalScore){
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
        scrabble.setAiPlacement(play);

        return this.board;
    }

    private HashMap<ArrayList<Integer>, String> checkHorizontalPossibilities(){
        ArrayList<LetterTile> hand = getLetters();
        ArrayList<Character> letters = new ArrayList<>();
        for(LetterTile t : hand) {
            letters.add(t.getLetter());
        }
        boolean wordValid = true;
        HashMap<ArrayList<Integer>, String> possibilities = new HashMap<>();

        //If board is empty find a word to start the game.
        if(board.isEmpty()){
            for(String s : wordBank){
                for(int i = 0; i < s.length(); i++){
                    if(!letters.contains(Character.toUpperCase(s.charAt(i)))){
                        wordValid = false;
                    }
                }
                if(wordValid){
                    possibilities.put(new ArrayList<Integer>(Arrays.asList(7,7)),s.toUpperCase());
                }
                wordValid = true;
            }
        }
        else { //must build off a letter on the board
            ArrayList<Character> charList = board.getLetters(); //existing letters on board
            for(char c : charList) {
                for(String s : getPossibleWords(c)) {
                    ArrayList<Integer> temp = board.checkPlaceable(s.toUpperCase(), c);
                    ArrayList<Integer> placeable = new ArrayList<>();
                    if(temp != null && temp.get(2) == 1) {
                        placeable.add(temp.get(0));
                        placeable.add(temp.get(1));
                        possibilities.put(placeable, s.toUpperCase());
                    }
                }
            }
        }

        return possibilities;
    }

    private HashMap<ArrayList<Integer>, String> checkVerticalPossibilities(){

        ArrayList<LetterTile> hand = getLetters();
        boolean wordValid = true;
        ArrayList<Character> letters = new ArrayList<>();
        for(LetterTile t : hand) {
            letters.add(t.getLetter());
        }
        HashMap<ArrayList<Integer>, String> possibilities = new HashMap<>();

        //If board is empty find a word to start the game.
        if(board.isEmpty()){
            for(String s : wordBank){
                for(int i = 0; i < s.length(); i++){
                    if(!letters.contains(Character.toUpperCase(s.charAt(i)))){
                        wordValid = false;
                    }
                }
                if(wordValid){
                    possibilities.put(new ArrayList<Integer>(Arrays.asList(7,7)),s.toUpperCase());
                }
                wordValid = true;
            }
        }
        else { //must build off a letter on the board
            ArrayList<Character> charList = board.getLetters(); //existing letters on board
            for(char c : charList) {
                for(String s : getPossibleWords(c)) {
                    ArrayList<Integer> temp = board.checkPlaceable(s.toUpperCase(), c);
                    ArrayList<Integer> placeable = new ArrayList<>();
                    if(temp != null && temp.get(2) == 0) {
                        placeable.add(temp.get(0));
                        placeable.add(temp.get(1));
                        possibilities.put(placeable, s.toUpperCase());
                    }
                }
            }
        }

        return possibilities;
    }

    private ArrayList<String> getPossibleWords(char c) {
        ArrayList<LetterTile> letterTiles = getLetters(); //existing letters on board
        ArrayList<Character> chars = new ArrayList<>();
        ArrayList<String> words = new ArrayList<>();
        //chars.add(c);
        for(LetterTile t : letterTiles) {
            chars.add(t.getLetter());
        }
        boolean wordValid;
        ArrayList<Character> tempChars;
        for(String s : wordBank) {
            wordValid = true;
            if(!s.toUpperCase().contains(String.valueOf(c))) {
                wordValid = false;
            }
            if(s.length() > 15){
                wordValid = false;
            }
            tempChars = (ArrayList<Character>) chars.clone();
            boolean usedC = false;
            for(int i = 0; i < s.length(); i++){
                if (tempChars.contains(s.toUpperCase().charAt(i))) {
                    tempChars.remove(tempChars.indexOf(s.toUpperCase().charAt(i)));
                    continue;
                }
                else if(s.toUpperCase().charAt(i) == c && !usedC) {
                    usedC = true;
                    continue;
                }
                wordValid = false;
                break;
            }
            if(wordValid) {
                words.add(s);
            }
        }
        for(LetterTile t : this.getLetters()) {
            System.out.print(t.getLetter());

        }
        System.out.println();
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