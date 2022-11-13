import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Scrabble class creates the game, players, letterBag, Parser, board
 * @author Matthew
 */
public class Scrabble {
    private Board board;
    private ArrayList<Player> players;
    private ArrayList<LetterTile> letterBag;
    private int currentPlayer;
    private Character currentLetter;
    private ArrayList<String> WordBank;
    private HashMap<ArrayList<Integer>,Character> playerPlacement;
    public enum Command {PLACE, DRAW, PASS, SELECT, SUBMIT, RESET}
    private Command command;
    private List<ScrabbleView> views;

    /**
     * Constructor for class scrabble
     * @author Matthew
     */
    public Scrabble(int numPlayers) {
        this.board = new Board();
        this.players = new ArrayList<>();
        this.currentPlayer = 0;
        this.currentLetter = null;
        this.playerPlacement = new HashMap<>();
        this.command = Command.RESET;
        this.views = new ArrayList<>();
        for(int i = 1; i < numPlayers + 1; i++) {
            players.add(new Player("Player " + i));
        }
        this.letterBag = new ArrayList<>();
        createLetterBag();
        for(Player p : players) { //give first seven letters to each player
            addLetterTiles(p, 7);
            p.SetScore(0);
        }

        //Load WordBank
        this.WordBank = new ArrayList<String>();
        File file = new File("WordBank.txt");

        Scanner tokenizer = null;
        try {
            tokenizer = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (tokenizer.hasNextLine()) {
            this.WordBank.add(tokenizer.nextLine());
        }

    }

    /**
     * Creates the letter bag that holds all the letter tiles to draw from
     * Scrabble contain 100 letter tiles, in the following distribution:
     * 2 blank tiles (scoring 0 points) (not yet implemented)
     * 1 point: E ×12, A ×9, I ×9, O ×8, N ×6, R ×6, T ×6, L ×4, S ×4, U ×4
     * 2 points: D ×4, G ×3
     * 3 points: B ×2, C ×2, M ×2, P ×2
     * 4 points: F ×2, H ×2, V ×2, W ×2, Y ×2
     * 5 points: K ×1
     * 8 points: J ×1, X ×1
     * 10 points: Q ×1, Z ×1
     * @author Matthew
     */
    public void createLetterBag() {
        int i;
        char[] letters = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N',
                'O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        int[] numTiles = {9,2,2,4,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,4,2,2,1,2,1};
        for(i = 0; i < 26; i++) {
            for(int j = 0; j < numTiles[i]; j++) {
                this.letterBag.add(new LetterTile(letters[i]));
            }
        }
    }

    /**
     * Removes and returns a random LetterTile from the letter bag
     * @author Matthew
     * @return LetterTile from the letterBag
     */
    public LetterTile getLetterTile() {
        if(letterBag.size() > 0) {
            Random r = new Random();
            int val = r.nextInt(letterBag.size());
            return letterBag.remove(val);
        }
        else {
            System.out.println("Letter bag empty");
            return new LetterTile('1', 0);
        }
    }

    /**
     * Adds random letterTiles to the player
     * @author Matthew
     * @param player the player to add the letters to
     * @param numLetters the number of letters to give to the player
     */
    public void addLetterTiles(Player player, int numLetters) {
        for(int i = 0; i < numLetters; i++) {
            player.addLetterTile(getLetterTile());
        }
    }

    public void addScrabbleView(ScrabbleView scrabbleView) {
        this.views.add(scrabbleView);
    }

    public Player getCurrentPlayer(){
        return players.get(currentPlayer);
    }

    /**
     * Calculate and return the points earned by the word that was placed -
     * Rules:
     * Using all 7 letters results in a 50 point bonus - "bingo"
     * Letter premiums are calculated before word premiums
     * Premium squares apply only on first use
     * Multiple word premiums do stack
     * @author Matthew and Vimal
     * @return points earned by word placement
     */
    public int scoredPoints(ArrayList<String> command) {
        //score of word + score of all other words created by placement
        int sum = 0;
        int count = 0;
        for(int i = 4; i < command.size(); i++) {
            if(!command.get(i).startsWith("(")) {
                sum += (new LetterTile(command.get(i).charAt(0))).getNum();
                count++;
            }
            else {
                sum += (new LetterTile(command.get(i).charAt(1))).getNum();
            }

        }
        if(count == 7) {sum += 50;} //place all 7 = 50 points
        System.out.println("SCORE: " + sum);
        return sum;
    }

    /**
     * Calculates the total score of all the individual letters played in a round
     * @author Ashwitha and Riya
     * @return total score of letters played
     */
    public int calculatePoints(String word){
        int score = 0;
        //for(String letter)
        // we want to create a for loop that checks the score of each individual letter played and returns the total points for the round
        return score;
    }

    /**
     * Checks word legality.
     * @author Vimal
     * */
    private boolean wordCheck(ArrayList<String> command) {
        StringBuilder word = new StringBuilder();
        for(int i = 4; i < command.size(); i++){
            word.append(command.get(i).replace("(", "").replace(")", ""));
        }
        return WordBank.contains(word.toString().toLowerCase());
    }

    /**
     * Checks if letters are in the players hand.
     * @author Matthew
     */
    private boolean handCheck(ArrayList<String> command, Player p) {
        return p.hasLetters(command);
    }

    private void draw() {
        /*
        old code just in case

        if(!(letterBag.size() > 7)) {
            System.out.println("Not enough tiles in bag");
            continue;
        }
        for(int k = 1; k < command.size(); k++) {
            if(!currentPlayer.removeLetterTile(command.get(k).charAt(0))) {
                System.out.println("Letter not in hand");
                errors++;
            }
            else { //add letter back to letterBag
                letterBag.add(new LetterTile(command.get(k).charAt(0)));
            }
        }
        addLetterTiles(currentPlayer, command.size() - 1 - errors);
        if(errors < 1) {
            i = (i + 1) % players.size();     //Switch to next player
        }
        */
    }

    private void pass() {
        if(currentLetter != null){
            currentLetter = null;
        }

        if(currentPlayer == players.size() - 1) {
            currentPlayer = 0;
        }
        else {
            currentPlayer++;
        }
    }

    private void place(int x, int y) {
        if (currentLetter != null && !playerPlacement.containsKey(new ArrayList<>(Arrays.asList(x, y)))) {
            playerPlacement.put(new ArrayList<>(Arrays.asList(x, y)), currentLetter);
        }
        else {
            currentLetter = null;
        }
    }

    private void selectLetter(int i) {
        currentLetter = players.get(currentPlayer).getLetters().get(i).getLetter();
    }

    private boolean submit() {
        //check word is straight line
        int tempX = -1;
        int tempY = -1;
        boolean valid = true;
        boolean second = false;
        boolean horizontal = false;
        boolean vertical = false;
        for(ArrayList<Integer> yx : playerPlacement.keySet()) {
            if(tempX == -1) {
                tempY = yx.get(0);
                tempX = yx.get(1);
                second = true;
            }
            else if(second) {
                if(yx.get(0) == tempY) {
                    horizontal = true;
                }
                else if (yx.get(1) == tempX) {
                    vertical = true;
                }
                else { valid = false; }
            }
            else if(horizontal && !(yx.get(0) == tempY)) {
                valid = false;
            }
            else if(vertical && !(yx.get(1) == tempX)) {
                valid = false;
            }
        }

        if(valid) {
            System.out.println("Word is a straight line");
        }
        else {
            System.out.println("Word is NOT a straight line");
        }
        //check builds off other word or off center if first word

        //check valid word
        ArrayList<String> words = getWords();
        for(String w : words) {
            //check wordBank
        }






        if(valid) {
            this.board.newUpdateBoard(playerPlacement);
            if(currentLetter != null){
                currentLetter = null;
            }

            if(currentPlayer == players.size() - 1) {
                currentPlayer = 0;
            }
            else {
                currentPlayer++;
            }
            playerPlacement.clear();
            return true;
        }
        if(currentLetter != null){
            currentLetter = null;
        }
        playerPlacement.clear();
        return false;
    }

    private void reset() {
        currentLetter = null;
        playerPlacement.clear();
    }
    public ArrayList<String> getWords() {
        ArrayList<String> words = new ArrayList<>();
        //main word
        int leftmostX = 14;
        int rightmostX = 0;
        int y = 0;
        char[] word = new char[15];
        for(int i =0; i < 15; i++) {word[i] = ' ';}
        if(getWordDirection().equals("horizontal")) {
            for(ArrayList<Integer> yx : playerPlacement.keySet()) {
                if(yx.get(1) < leftmostX) { leftmostX = yx.get(1); }
                if(yx.get(1) > rightmostX) { rightmostX = yx.get(1); }
                y = yx.get(0);
                word[yx.get(1)] = playerPlacement.get(yx);
            }
            int currentX = leftmostX - 1;
            while(currentX >= 0) { //add characters to the left of the placed letters
                if(board.getBoard()[y][currentX] != ' ') {
                    word[currentX] = board.getBoard()[y][currentX]; //not sure if this works
                }
                else { currentX = -1;}
                currentX--;
            }
            currentX = rightmostX + 1;
            while(currentX <= 14) { //add characters to the right of the placed letters
                if(board.getBoard()[y][currentX] != ' ') {
                    word[currentX] = board.getBoard()[y][currentX]; //not sure if this works
                }
                else { currentX = 15;}
                currentX++;
            }
            //add characters in the middle of placed letters
            for(int i = leftmostX + 1; i < rightmostX; i++) {
                if(!playerPlacement.containsKey(new ArrayList<>(Arrays.asList(y, i)))) {
                    word[i] = board.getBoard()[i][y]; //not sure if this works
                }
            }
            String s = String.valueOf(word).trim();
            words.add(s);
            System.out.println(words.get(0));

        }

        else { //vertical (not finished)
            int topmostY = 14;
            int botmostY = 0;
            int x = 0;
            for(ArrayList<Integer> yx : playerPlacement.keySet()) {
                if(yx.get(0) < topmostY) { topmostY = yx.get(0); }
                if(yx.get(0) > botmostY) { botmostY = yx.get(0); }
                x = yx.get(1);
                word[yx.get(0)] = playerPlacement.get(yx);
            }
            int currentY = topmostY - 1;
            while(currentY >= 0) { //add characters to the left of the placed letters
                if(board.getBoard()[x][currentY] != ' ') {
                    word[currentY] = board.getBoard()[currentY][x]; //not sure if this works
                }
                else { currentY = -1;}
                currentY--;
            }
            currentY = botmostY + 1;
            while(currentY <= 14) { //add characters to the right of the placed letters
                if(board.getBoard()[x][currentY] != ' ') {
                    word[currentY] = board.getBoard()[currentY][x]; //not sure if this works
                }
                else { currentY = 15;}
                currentY++;
            }
            //add characters in the middle of placed letters
            for(int i = topmostY + 1; i < botmostY; i++) {
                if(!playerPlacement.containsKey(new ArrayList<>(Arrays.asList(x, i)))) {
                    word[i] = board.getBoard()[i][x]; //not sure if this works
                }
            }
            String s = String.valueOf(word).trim();
            words.add(s);
            System.out.println(words.get(0));
        }

        //add other surrounding words to the list


        return words;
    }
    private String getWordDirection() {
        int tempX = -1;
        int tempY = -1;
        boolean second = false;
        for(ArrayList<Integer> yx : playerPlacement.keySet()) {
            if (tempX == -1) {
                tempY = yx.get(0);
                tempX = yx.get(1);
                second = true;
            } else if (second) {
                if (yx.get(0) == tempY) {
                    return "horizontal";
                } else if (yx.get(1) == tempX) {
                    return "vertical";
                }
            }
        }
        return "horizontal"; //1-letter words can be treated as horizontal
    }

    public void play(int x, int y, int index, Command command) {
        this.command = command;

        if(command == Command.DRAW){
            draw();
        }
        if(command == Command.PASS){
            pass();
        }
        if(command == Command.SELECT){
            selectLetter(index);
        }
        if(command == Command.RESET){
            reset();
        }
        if(command == Command.PLACE){
            place(x, y);
        }
        if(command == Command.SUBMIT){
            if(!submit()) {
                command = Command.RESET;
            }
        }

        for(ScrabbleView v : views){
            v.update(new ScrabbleEvent(this, x, y, board, getCurrentPlayer(), currentLetter, this.command));
        }

        //prevents copying letters
        if(command == Command.PLACE){
            currentLetter = null;
        }
    }
}
