import javax.swing.*;
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
    private int turn;
    private Character currentLetter;
    private ArrayList<String> WordBank;
    private HashMap<ArrayList<Integer>,Character> playerPlacement;

    public enum Command {PLACE, DRAW, PASS, SELECT, SUBMIT, RESET}
    private Command command;
    private List<ScrabbleView> views;
    JFrame frame;

    /**
     * Constructor for class scrabble
     * @author Matthew
     */
    public Scrabble(int numPlayers) {
        this.board = new Board();
        this.players = new ArrayList<>();
        this.currentPlayer = 0;
        this.currentLetter = null;
        this.turn = 1;
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
     * 2 blank tiles (scoring 0 points) (now implemented)
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
                'O','P','Q','R','S','T','U','V','W','X','Y','Z', ' '};
        int[] numTiles = {9,2,2,4,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,4,2,2,1,2,1,2};
        for(i = 0; i < 27; i++) {
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

    public Board getBoard(){
        return this.board;
    }

    /**
     * Calculates the total score of all the individual letters played in a round
     * @author Ashwitha and Riya
     * @return total score of letters played
     */
    public int calculatePoints(String word){
        int score = 0;
        HashMap<ArrayList<Integer>, Integer> p = board.getPremium();
        for(int i = 0; i < word.length(); i++) {
            score += new LetterTile(word.charAt(i)).getNum();
            }
        for(ArrayList<Integer> xy : playerPlacement.keySet()) {
            if(p.containsKey(xy)) {
                int i = p.get(xy);
                if(i == 2) { //2LS
                    score += new LetterTile(playerPlacement.get(xy)).getNum();
                }
                else if(i == 3) { //3LS
                    score += 2 * new LetterTile(playerPlacement.get(xy)).getNum();;
                }
            }
        }
        //check for word premiums
        for(ArrayList<Integer> xy : playerPlacement.keySet()) {
            if(p.containsKey(xy)) {
                int i = p.get(xy);
                if(i == 4) { //2WS
                    score *= 2;
                }
                else if(i == 5) { //3WS
                    score *= 3;
                }
            }
        }
        //if all 7 letters are used
        if(playerPlacement.size() == 7){
            score += 50;
        }
        return score;
    }

    /**
     * Checks word legality.
     * @author Vimal
     * */
    private boolean wordCheck(String word) {
        return WordBank.contains(word.toLowerCase());
    }

    private void draw() {
        if(!(letterBag.size() > 7)) {
            System.out.println("Not enough tiles in bag");
        }
        else {
            addLetterTiles(getCurrentPlayer(), 7-getCurrentPlayer().getLetters().size());
        }
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
        turn++;
    }

    private void place(int x, int y) {
        if (currentLetter != null && !playerPlacement.containsKey(new ArrayList<>(Arrays.asList(x, y)))) {
            if(currentLetter == ' ') {
                currentLetter = views.get(0).getBlankTileInput();
            }
            playerPlacement.put(new ArrayList<>(Arrays.asList(x, y)), currentLetter);
        }
        else {
            currentLetter = null;
        }
    }

    private void selectLetter(int i) {
        currentLetter = players.get(currentPlayer).getLetters().get(i).getLetter();
    }

    public boolean submit() {
        //check word is straight line
        int tempX = -1;
        int tempY = -1;
        boolean valid = true;
        boolean second = false;
        boolean horizontal = false;
        boolean vertical = false;
        for(ArrayList<Integer> xy : playerPlacement.keySet()) {
            if(tempX == -1) {
                tempX = xy.get(0);
                tempY = xy.get(1);
                second = true;
            }
            else if(second) {
                if(xy.get(0) == tempX) {
                    vertical = true;
                }
                else if (xy.get(1) == tempY) {
                    horizontal = true;
                }
                else { valid = false; }
                second = false;
            }
            else if(horizontal && !(xy.get(1) == tempY)) {
                valid = false;
            }
            else if(vertical && !(xy.get(0) == tempX)) {
                valid = false;
            }
        }

        if(valid) {
            System.out.println("Word is a straight line");
        }
        else {
            System.out.println("Word is NOT a straight line");
        }

        ArrayList<String> words = getWords();
        //checks if it builds off another word and checks if there is a word at 7,7
        //assuming that if multiple words are detect for scoring, player must have built off another word.
        if(words.size() == 1){
            if(!buildOff()){
                System.out.println("Word does not start at 8,8 or word does not build off another");
                reset();
                return false;
            }
        }

        //check valid word
        for(String w : words) {
            if(!wordCheck(w) || w.length() <= 1){
                System.out.println("Word is NOT legal");
                reset();
                frame = new JFrame();
                JOptionPane.showMessageDialog(frame,"Word is NOT legal!");
                return false;
            }
        }
        //add points
        if(valid) {
            this.board.updateBoard(playerPlacement);
            for(String w : words) {
                getCurrentPlayer().addScore(calculatePoints(w));
            }
            for(Character c : playerPlacement.values()) {
                getCurrentPlayer().removeLetterTile(c);
            }

            draw();

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
            turn++;
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

    private String getWordHorizontal(int x, int y) {
        char[] word = new char[15];
        for(int i = 0; i < 15; i++) {word[i] = ' ';}
        char[][] b = board.getBoard();
        word[x] = playerPlacement.get((new ArrayList<>(Arrays.asList(x, y))));
        int i = x - 1;
        while(b[i][y] != ' ') {
            word[i] = b[i][y];
            i--;
        }
        int j = x + 1;
        while(b[j][y] != ' ') {
            word[j] = b[j][y];
            j++;
        }
        return String.valueOf(word).trim();
    }

    private String getWordVertical(int x, int y) {
        char[] word = new char[15];
        for(int i = 0; i < 15; i++) {word[i] = ' ';}
        char[][] b = board.getBoard();
        word[y] = playerPlacement.get((new ArrayList<>(Arrays.asList(x, y))));
        int i = y - 1;
        while(b[x][i] != ' ') {
            word[i] = b[x][i];
            i--;
        }
        int j = y + 1;
        while(b[x][j] != ' ') {
            word[j] = b[x][j];
            j++;
        }
        return String.valueOf(word).trim();
    }

    public boolean buildOff(){
        if(board.getBoard()[7][7] == ' ' && playerPlacement.containsKey(new ArrayList<>(Arrays.asList(7, 7)))){
            return true;
        }
        char[][] b = board.getBoard();
        ArrayList<ArrayList<Integer>> temp = new ArrayList<>(playerPlacement.keySet());
        if(getWordDirection().equals("horizontal")) { //horizontal
            //check left
            int minX = 15;
            int maxX = 0;
            for(ArrayList<Integer> i : temp) {
                if(i.get(0) < minX) {minX = i.get(0);}
                if(i.get(0) > maxX) {maxX = i.get(0);}
            }
            if(b[minX-1][temp.get(0).get(1)] != ' '){
                return true;
            }
            //check right
            if(b[maxX + 1][temp.get(0).get(1)] != ' '){
                return true;
            }
            //check all verticals
            for(ArrayList<Integer> xy : temp) {
                if(b[xy.get(0)][xy.get(1) + 1] != ' ' || b[xy.get(0)][xy.get(1) - 1] != ' ') {
                    return true;
                }
            }
            //check in between
            int count = 0;
            for(int i = minX + 1; i < maxX; i++) {
                if(b[i][temp.get(0).get(1)] == ' ') { //not already placed
                    if(!temp.contains(new ArrayList<>(Arrays.asList(i, temp.get(0).get(1))))) { //also not in new placements
                        return false;
                    }
                    else{
                        count++;
                    }
                }
            }
            if(count == maxX-minX){
                return true;
            }
            return false;
        }
       else { //vertical
           //check up
            int minY = 15;
            int maxY = 0;
            for(ArrayList<Integer> i : temp) {
                if(i.get(0) < minY) {minY = i.get(0);}
                if(i.get(0) > maxY) {maxY = i.get(0);}
            }
            if(b[temp.get(0).get(0)][maxY - 1] != ' '){
                return true;
            }
            //check down
            if(b[temp.get(0).get(0)][maxY + 1] != ' '){
                return true;
            }
            //check all horizontals
            for(ArrayList<Integer> xy : temp) {
                if(b[xy.get(0)+1][xy.get(1)] != ' ' || b[xy.get(0) - 1][xy.get(1)] != ' ') {
                    return true;
                }
            }
            //check in between
            int count = 0;
            for(int i = minY + 1; i < maxY; i++) {
                if(b[temp.get(0).get(0)][i] == ' ') { //not already placed
                    if(!temp.contains(new ArrayList<>(Arrays.asList(temp.get(0).get(0), i)))) { //also not in new placements
                        return false;
                    }
                }
                else{
                    count++;
                }
            }
            if(count == maxY-minY){
                return true;
            }
            return false;
        }
    }

    public ArrayList<String> getWords() {
        ArrayList<String> words = new ArrayList<>();
        //main word
        char[] word = new char[15];
        for(int i = 0; i < 15; i++) {word[i] = ' ';}
        if(getWordDirection().equals("horizontal")) {
            int leftmostX = 14;
            int rightmostX = 0;
            int y = 0;
            for(ArrayList<Integer> xy : playerPlacement.keySet()) {
                if(xy.get(0) < leftmostX) { leftmostX = xy.get(0); }
                if(xy.get(0) > rightmostX) { rightmostX = xy.get(0); }
                y = xy.get(1);
                word[xy.get(0)] = playerPlacement.get(xy);
            }
            int currentX = leftmostX - 1;
            while(currentX >= 0) { //add characters to the left of the placed letters
                if(board.getBoard()[currentX][y] != ' ') {
                    word[currentX] = board.getBoard()[currentX][y]; //add to word
                }
                else { currentX = -1;} //continue
                currentX--;
            }
            currentX = rightmostX + 1;
            while(currentX <= 14) { //add characters to the right of the placed letters
                if(board.getBoard()[currentX][y] != ' ') {
                    word[currentX] = board.getBoard()[currentX][y]; //add to word
                }
                else { currentX = 15;}
                currentX++;
            }
            //add characters in the middle of placed letters
            for(int i = leftmostX + 1; i < rightmostX; i++) {
                if(!playerPlacement.containsKey(new ArrayList<>(Arrays.asList(i, y)))) {
                    word[i] = board.getBoard()[i][y]; //not sure if this works
                }
            }
            String s = String.valueOf(word).trim();
            words.add(s);

            for (int i = 0; i < 15; i++) {
                word[i] = ' ';
            }

            //check verticals from each letter
            for(ArrayList<Integer> xy : playerPlacement.keySet()) {
                String tempV = getWordVertical(xy.get(0), xy.get(1));
                if(tempV.length() > 1){
                    words.add(tempV);
                }
            }

            for (String w: words) {
                System.out.println(w);
            }
        }

        else { //vertical
            int topmostY = 14;
            int botmostY = 0;
            int x = 0;
            for(ArrayList<Integer> xy : playerPlacement.keySet()) {
                if(xy.get(1) < topmostY) { topmostY = xy.get(1); }
                if(xy.get(1) > botmostY) { botmostY = xy.get(1); }
                x = xy.get(0);
                word[xy.get(1)] = playerPlacement.get(xy);
            }
            int currentY = topmostY - 1;
            while(currentY <= 14) { //add characters to the top of the placed letters
                if(board.getBoard()[x][currentY] != ' ') {
                    word[currentY] = board.getBoard()[x][currentY]; //not sure if this works
                }
                else { currentY = 15;}
                currentY++;
            }
            currentY = botmostY + 1;
            while(currentY >= 0) { //add characters to the bottom of the placed letters
                if(board.getBoard()[x][currentY] != ' ') {
                    word[currentY] = board.getBoard()[x][currentY]; //not sure if this works
                }
                else { currentY = -1;}
                currentY--;
            }
            //add characters in the middle of placed letters
            for(int i = topmostY + 1; i < botmostY; i++) {
                if(!playerPlacement.containsKey(new ArrayList<>(Arrays.asList(x, i)))) {
                    word[i] = board.getBoard()[x][i]; //not sure if this works
                }
            }
            String s = String.valueOf(word).trim();
            words.add(s);

            for (int i = 0; i < 15; i++) {
                word[i] = ' ';
            }

            //check horizontals from each letter
            for(ArrayList<Integer> xy : playerPlacement.keySet()) {
                String tempH = getWordHorizontal(xy.get(0), xy.get(1));
                if(tempH.length() > 1){
                    words.add(tempH);
                }
            }



            for (String w: words) {
                System.out.println(w);
            }
        }
        return words;
    }

    private String getWordDirection() {
        int tempX = -1;
        int tempY = -1;
        boolean second = false;
        for(ArrayList<Integer> xy : playerPlacement.keySet()) {
            if (tempX == -1) {
                tempX = xy.get(0);
                tempY = xy.get(1);
                second = true;
            } else if (second) {
                if (xy.get(0) == tempX) {
                    return "vertical";
                } else if (xy.get(1) == tempY) {
                    return "horizontal";
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
                //reset();
                this.command = Command.RESET;
            }
        }

        for(ScrabbleView v : views){
            v.update(new ScrabbleEvent(this, x, y, turn, board, getCurrentPlayer(), currentLetter, this.command));
        }

        //prevents copying letters
        if(command == Command.PLACE){
            currentLetter = null;
        }

        if(getCurrentPlayer().isAI()){
            //do ai stuff
        }
    }
}
