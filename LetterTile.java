public class LetterTile {
    private String letter;
    private int num;

    public LetterTile(String letter, int points) {
        this.letter = letter;
        this.num = points;
    }
    public String getLetter() {
        return this.letter;
    }
    public int getNum() {
        return this.num;
    }
}
