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
        for(int i = 0; i < boardSizeX; i++) {
            for(int j = 0; j < boardSizeY; j++) {
                JButton b = new JButton("");
                board[i][j] = b;
                gridPanel.add(b);
                int finalI = i;
                int finalJ = j;
                b.addActionListener(e->scrabble.place(finalI, finalJ));
            }
        }

        this.add(handPanel, BorderLayout.SOUTH);
        for(int i = 0; i < 7; i++) {
            int finalI = i;
            JButton b = new JButton(String.valueOf(scrabble.getCurrentPlayer().getLetters().get(finalI).getLetter()));
            hand[i] = b;
            handPanel.add(b);
            b.addActionListener(e->scrabble.selectLetter(finalI));

        }
        optionPanel.add(Box.createVerticalGlue());
        this.add(optionPanel, BorderLayout.EAST);

        JButton submitButton = new JButton("Submit");
        optionPanel.add(submitButton);
        submitButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, submitButton.getMinimumSize().height));
        submitButton.addActionListener(e->scrabble.submit());
        JButton drawButton = new JButton("Draw");
        optionPanel.add(drawButton);
        drawButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, drawButton.getMinimumSize().height));
        drawButton.addActionListener(e->scrabble.draw());
        JButton passButton = new JButton("Pass");
        passButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, passButton.getMinimumSize().height));
        optionPanel.add(passButton);
        passButton.addActionListener(e->scrabble.pass());
        JButton resetButton = new JButton("Reset");
        resetButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, resetButton.getMinimumSize().height));
        optionPanel.add(resetButton);
        resetButton.addActionListener(e->scrabble.reset());
        optionPanel.add(Box.createVerticalGlue());

        this.setSize(700, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }
    @Override
    public void update(ScrabbleEvent e) {
        if(e.getCurrentLetter() != null) {
            String label = e.getCurrentLetter().toString();
            board[e.getX()][e.getY()].setText(label);
        }
    }

    public static void main(String[] args) {
        new ScrabbleFrame();
    }

}
