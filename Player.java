import java.util.*;
/**
 * Player class
 * @author Ashwitha
 */
public class Player{
    int score;
    private String name;
    private ArrayList<LetterTile> letters;
    public Player(String name)
    {
        this.name = name;
        this.letters = new ArrayList<>(7);
    }

    public String getName(){
        return this.name;
    }

    public String setName(String playerName){
        this.name = playerName;
        return playerName;
    }

    public int GetScore(){
        return this.score;
    }

    public int SetScore(int scoreVal){
        this.score = scoreVal;
        return scoreVal;
    }
    public void addLetterTile(LetterTile t) {
        this.letters.add(t);
    }
    public boolean removeLetterTile(char c){
        for(LetterTile t : letters) {
            if(Character.compare(t.getLetter(), c) == 0) {
                this.letters.remove(t);
                return true;
            }
        }
        return false;
    }
    public void addScore(int score) {
        this.score += score;
    }
    public String printHand() {
        String s ="";
        for(LetterTile l : letters) {
            s += l.getLetter() + "(" + l.getNum() + ") ";
        }
        return s;
    }

    /**
     * Check if the hand of letters contains all required letters for a word to be placed
     * @author Matthew
     * @param command the command word to be tested
     * @return true if the hand contains all the letters, false otherwise
     */
    public boolean hasLetters(ArrayList<String> command) {
        boolean has = false;
        for(int i = 0; i < command.size(); i++) {
            for(LetterTile t : letters) {
                if(Character.compare(t.getLetter(), command.get(i).charAt(0)) == 0) {
                    has = true;
                }
                else if(Character.compare(command.get(i).charAt(0), '(') == 0) {
                    has = true;
                }
            }
            if(has == false) {return false;}
            has = false;
        }
        return true;
    }
    public ArrayList<LetterTile> getLetters() {
        return letters;
    }
}
