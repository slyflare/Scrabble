import javax.swing.*;
import java.awt.*;

public class ScrabbleFrame extends JFrame implements ScrabbleView {
    private JButton[][] board;
    private JButton[] hand;
    private static final int boardSizeX = 15;
    private static final int boardSizeY = 15;


    public ScrabbleFrame() {
        super("Scrabble");

        Scrabble scrabble = new Scrabble(2);
        scrabble.addScrabbleView(this);

        this.board = new JButton[boardSizeX][boardSizeY];
        this.hand = new JButton[7];
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        JPanel handPanel = new JPanel();
        handPanel.setLayout(new GridLayout());
        JPanel optionPanel = new JPanel();
        optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(15, 15));
        this.add(gridPanel, BorderLayout.CENTER);

        ScrabbleController controller = new ScrabbleController(scrabble);

        for(int i = 0; i < boardSizeX; i++) {
            for(int j = 0; j < boardSizeY; j++) {
                JButton b = new JButton("");
                board[i][j] = b;
                gridPanel.add(b);
                int finalI = i;
                int finalJ = j;
                b.addActionListener(e->scrabble.play(finalI, finalJ, 0, Scrabble.Command.PLACE));
            }
        }

        this.add(handPanel, BorderLayout.SOUTH);
        for(int i = 0; i < 7; i++) {
            int finalI = i;
            JButton b = new JButton(String.valueOf(scrabble.getCurrentPlayer().getLetters().get(finalI).getLetter()));
            hand[i] = b;
            handPanel.add(b);
            b.addActionListener(e->scrabble.play(0,0, finalI, Scrabble.Command.SELECT));

        }
        optionPanel.add(Box.createVerticalGlue());
        this.add(optionPanel, BorderLayout.EAST);

        JButton submitButton = new JButton("Submit");
        optionPanel.add(submitButton);
        submitButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, submitButton.getMinimumSize().height));
        submitButton.addActionListener(e->scrabble.play(0,0,0,Scrabble.Command.SUBMIT));
        JButton drawButton = new JButton("Draw");
        optionPanel.add(drawButton);
        drawButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, drawButton.getMinimumSize().height));
        drawButton.addActionListener(e->scrabble.play(0,0,0,Scrabble.Command.DRAW));
        JButton passButton = new JButton("Pass");
        passButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, passButton.getMinimumSize().height));
        optionPanel.add(passButton);
        passButton.addActionListener(e->scrabble.play(0,0,0,Scrabble.Command.PASS));
        JButton resetButton = new JButton("Reset");
        resetButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, resetButton.getMinimumSize().height));
        optionPanel.add(resetButton);
        resetButton.addActionListener(e->scrabble.play(0,0,0,Scrabble.Command.RESET));
        optionPanel.add(Box.createVerticalGlue());

        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }
    @Override
    public void update(ScrabbleEvent e) {
        if(e.getCommand() == Scrabble.Command.PLACE && e.getCurrentLetter() != null) {
            String label = e.getCurrentLetter().toString();
            board[e.getX()][e.getY()].setText(label);
        }

        if(e.getCommand() == Scrabble.Command.RESET){
            for(int i = 0; i < boardSizeX; i++) {
                for(int j = 0; j < boardSizeY; j++) {
                    board[e.getX()][e.getY()].setText(String.valueOf(e.getBoard().getBoard()[e.getX()][e.getY()]));
                }
            }
        }

        if(e.getCommand() == Scrabble.Command.PASS){
            for(int i = 0; i < 7; i++) {
                hand[i].setText(String.valueOf(e.getCurrentPlayer().getLetters().get(i).getLetter()));
            }
        }

        if(e.getCommand() == Scrabble.Command.DRAW){
            for(int i = 0; i < 7; i++) {
                hand[i].setText(String.valueOf(e.getCurrentPlayer().getLetters().get(i).getLetter()));
            }
        }
    }

    public static void main(String[] args) {
        new ScrabbleFrame();
    }

}
