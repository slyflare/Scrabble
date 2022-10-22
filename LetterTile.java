import java.util.HashMap;

public class LetterTile {
    private char letter;
    private int num;
    private static HashMap<Character, Integer> map;
    public LetterTile(char letter) {
        map = new HashMap<>();
        createMap();
        this.letter = letter;
        this.num = map.get(letter);

    }
    public LetterTile(char letter, int points) {
        this.letter = letter;
        this.num = points;
    }
    public char getLetter() {
        return this.letter;
    }
    public int getNum() {
        return this.num;
    }
    public void createMap () {
        int[] vals = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
        char[] letters = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N',
            'O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        for(int i = 0; i < 26; i++) {
            map.put(letters[i], vals[i]);
        }


    }
}
