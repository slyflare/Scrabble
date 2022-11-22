import javax.swing.*;
import java.awt.*;

/**
 * ScrabbleFrame class responsible for making the Scrabble Frame
 * @author Vimal, Matthew and Riya
 *
 * */
public class ScrabbleFrame extends JFrame implements ScrabbleView {
    private JButton[][] board;
    private JButton[] hand;
    private JButton[] info;
    private static final int boardSizeX = 15;
    private static final int boardSizeY = 15;

    /**
     * ScrabbleFrame constructor
     */
    public ScrabbleFrame() {

        super("Scrabble");

        Scrabble scrabble = new Scrabble(2);
        scrabble.addScrabbleView(this);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.board = new JButton[boardSizeX][boardSizeY];
        this.hand = new JButton[7];
        this.info = new JButton[3];
        this.setLayout(new BorderLayout());
        JPanel handPanel = new JPanel();
        handPanel.setLayout(new GridLayout());
        JPanel optionPanel = new JPanel();
        optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));
        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(15, 15));
        this.add(gridPanel, BorderLayout.CENTER);

        ScrabbleController controller = new ScrabbleController(scrabble);

        for(int i = 0; i < boardSizeX; i++) {
            for(int j = 0; j < boardSizeY; j++) {
                JButton b = new JButton(" ");
                Color color1 = new Color(200, 200 ,200);
                Color color2 = new Color(255, 255 ,255);
                if ((i+j)%2 == 0){
                    b.setBackground(color1);
                }
                else{
                    b.setBackground(color2);
                }
                b.setOpaque(true);
                board[i][j] = b;
                gridPanel.add(b);
                int finalI = i;
                int finalJ = j;
                b.setActionCommand(finalJ + " " + finalI + " " + 0 + " PLACE");
                b.addActionListener(controller);
            }
        }

        this.add(handPanel, BorderLayout.SOUTH);
        for(int i = 0; i < 7; i++) {
            int finalI = i;
            JButton b = new JButton(String.valueOf(scrabble.getCurrentPlayer().getLetters().get(finalI).getLetter()));
            Color color = new Color(203, 120 ,112);
            b.setBackground(color);
            b.setOpaque(true);
            b.setBorderPainted(false);

            hand[i] = b;
            handPanel.add(b);
            b.setActionCommand(0 + " " + 0 + " " + finalI + " SELECT");
            b.addActionListener(controller);

        }
        optionPanel.add(Box.createVerticalGlue());
        this.add(optionPanel, BorderLayout.EAST);
        JButton submitButton = new JButton("Submit");
        submitButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, submitButton.getMinimumSize().height));
        submitButton.setActionCommand(0 + " " + 0 + " " + 0 + " SUBMIT");
        submitButton.addActionListener(controller);
        Color c2 = new Color(21, 224 ,140);
        submitButton.setBackground(c2);
        submitButton.setOpaque(true);
        submitButton.setBorderPainted(false);
        optionPanel.add(submitButton);
        JButton passButton = new JButton("Pass");
        passButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, passButton.getMinimumSize().height));
        passButton.setBackground(c2);
        passButton.setOpaque(true);
        passButton.setBorderPainted(false);
        optionPanel.add(passButton);
        passButton.setActionCommand(0 + " " + 0 + " " + 0 + " PASS");
        passButton.addActionListener(controller);
        JButton resetButton = new JButton("Reset");
        resetButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, resetButton.getMinimumSize().height));
        optionPanel.add(resetButton);
        resetButton.setActionCommand(0 + " " + 0 + " " + 0 + " RESET");
        resetButton.addActionListener(controller);
        resetButton.setBackground(c2);
        resetButton.setOpaque(true);
        resetButton.setBorderPainted(false);
        optionPanel.add(Box.createVerticalGlue());
        Color c1 = new Color(123, 123, 255);
        optionPanel.setBackground(c1);

        this.add(scorePanel, BorderLayout.WEST);
        scorePanel.add(Box.createVerticalGlue());
        JButton turnButton = new JButton("Turn: 1");
        info[0] = turnButton;
        turnButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, resetButton.getMinimumSize().height));
        scorePanel.add(turnButton);
        turnButton.setBackground(c2);
        turnButton.setOpaque(true);
        turnButton.setBorderPainted(false);
        turnButton.setEnabled(false);
        JButton scoreButton = new JButton("Your Score: 0");
        info[1] = scoreButton;
        scoreButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, resetButton.getMinimumSize().height));
        scorePanel.add(scoreButton);
        scoreButton.setBackground(c2);
        scoreButton.setOpaque(true);
        scoreButton.setBorderPainted(false);
        scoreButton.setEnabled(false);
        JButton playerButton = new JButton("Player 1");
        info[2] = playerButton;
        playerButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, resetButton.getMinimumSize().height));
        scorePanel.add(playerButton);
        playerButton.setBackground(c2);
        playerButton.setOpaque(true);
        playerButton.setBorderPainted(false);
        playerButton.setEnabled(false);
        scorePanel.add(Box.createVerticalGlue());
        scorePanel.setBackground(c1);

        this.setSize(900, 700);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }
    @Override
    public void update(ScrabbleEvent e) {
        if(e.getCommand() == Scrabble.Command.PLACE && e.getCurrentLetter() != null) {
            String label = e.getCurrentLetter().toString();
            board[e.getY()][e.getX()].setText(label);
        }

        if(e.getCommand() == Scrabble.Command.RESET){
            //clear anything temporary on board
            for(int i = 0; i < boardSizeX; i++) {
                for(int j = 0; j < boardSizeY; j++) {
                    if(board[j][i].isEnabled()) {
                        board[j][i].setText(String.valueOf(e.getBoard().getBoard()[i][j]));
                    }
                }
            }

            //reset hand
            for(int i = 0; i < e.getCurrentPlayer().getLetters().size(); i++) {
                hand[i].setEnabled(true);
            }
        }

        if(e.getCommand() == Scrabble.Command.SELECT) {
            for (JButton button : hand) {
                if (button.getText().equals(e.getCurrentLetter().toString())) {
                    button.setEnabled(false);
                    break;
                }
            }
        }

        if(e.getCommand() == Scrabble.Command.PASS){
            //clear anything temporary on board
            for(int i = 0; i < boardSizeX; i++) {
                for(int j = 0; j < boardSizeY; j++) {
                    board[j][i].setText(String.valueOf(e.getBoard().getBoard()[i][j]));
                }
            }
            //load next players hand
            for(int i = 0; i < e.getCurrentPlayer().getLetters().size(); i++) {
                hand[i].setText(String.valueOf(e.getCurrentPlayer().getLetters().get(i).getLetter()));
                hand[i].setEnabled(true);
            }

            //update turn
            info[0].setText("Turn: " + e.getTurn());
            info[1].setText("Your Score: " + e.getCurrentPlayer().score);
            info[2].setText(e.getCurrentPlayer().getName());
        }

        if(e.getCommand() == Scrabble.Command.SUBMIT) {
            for(int i = 0; i < boardSizeX; i++) {
                for(int j = 0; j < boardSizeY; j++) {
                    if (e.getBoard().getBoard()[i][j] != ' ') {
                        /* debug
                        System.out.println("buttons[" + i + "][" + j + "]: " + board[j][i].getText());
                        System.out.println("board:[" + i + "][" + j + "]: " + e.getBoard().getBoard()[i][j]); */
                        board[j][i].setText(String.valueOf(e.getBoard().getBoard()[i][j]));
                        board[j][i].setForeground(new Color(0,0,0));
                        board[j][i].setEnabled(false);
                    }
                }
            }
            for(int i = 0; i < e.getCurrentPlayer().getLetters().size(); i++) {
                hand[i].setText(String.valueOf(e.getCurrentPlayer().getLetters().get(i).getLetter()));
                hand[i].setEnabled(true);
            }

            //update turn
            info[0].setText("Turn: " + e.getTurn());
            info[1].setText("Your Score: " + e.getCurrentPlayer().score);
            info[2].setText(e.getCurrentPlayer().getName());
        }
    }
    public char getBlankTileInput() {
        return JOptionPane.showInputDialog("Enter letter").charAt(0);
    }

    public static void main(String[] args) {
        new ScrabbleFrame();

        try{
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
