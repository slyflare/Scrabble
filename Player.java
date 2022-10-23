import java.util.*;
/**
 * Player class
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
    public void removeLetterTile(char c){
        for(LetterTile t : letters) {
            if(Character.compare(t.getLetter(), c) == 0) {
                this.letters.remove(t);
                return;
            }
        }
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
}
