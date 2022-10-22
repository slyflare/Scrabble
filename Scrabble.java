import java.util.ArrayList;
import java.util.Random;

/**
 * Scrabble class creates the game, players, letterBag, Parser, board
 */
public class Scrabble {
    private Board board;
    private ArrayList<Player> players;
    private Parser parser;
    private ArrayList<LetterTile> letterBag;
    private Player currentPlayer;

    /**
     * Constructor for class scrabble
     */
    public Scrabble() {
        this.board = new Board();
        this.parser = new Parser();
        this.players = new ArrayList<>();
        players.add(new Player("Player 1"));
        players.add(new Player("Player 2"));
        this.letterBag = new ArrayList<>();
        createLetterBag();
        for(Player p : players) { //give first seven letters to each player
            addLetterTiles(p, 7);
            p.SetScore(0);
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

    public void addLetterTiles(Player player, int numLetters) {
        for(int i = 0; i < numLetters; i++) {
            player.addLetterTile(getLetterTile());
        }
    }

    /**
     * Calculate and return the points earned by the word that was placed -
     * Rules:
     * Using all 7 letters results in a 50 point bonus - "bingo"
     * Letter premiums are calculated before word premiums
     * Premium squares apply only on first use
     * Multiple word premiums do stack
     * @return points earned by word placement
     */
    public int scoredPoints(ArrayList<String> command) {
        //score of word + score of all other words created by placement
        int sum = 0;
        String word = command.get(1);
        int n = word.length();
        for(int i = 0; i < n; i++) {
            sum += (new LetterTile(word.charAt(i))).getNum();
        }
        return sum;
    }

    /**
     * Play the game
     */
    public void play() {
        boolean running = true;
        int i = 0;
        while(running) {
            currentPlayer = players.get(i);
            System.out.println("Its " + currentPlayer.getName() + "'s turn!");
            board.printBoard();
            System.out.println("Your hand: " + currentPlayer.printHand());
            ArrayList<String> command = parser.getCommand();    //get command
            //command has to be valid
            board.updateBoard(command);                         //Add letters to the board
            currentPlayer.addScore(scoredPoints(command));      //Update player score

            i = (i + 1) % players.size();                       //Switch to next player

        }

    }



    public static void main(String[] args) {
        Scrabble s = new Scrabble();
        s.play();

    }


}
