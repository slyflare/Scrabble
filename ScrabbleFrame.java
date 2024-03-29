import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * ScrabbleFrame class responsible for making the Scrabble Frame
 * @author Vimal, Matthew and Riya
 *
 * */
public class ScrabbleFrame extends JFrame implements ScrabbleView {
    private JButton[][] board;
    private JButton[] hand;
    private JButton[] info;
    private int numPlayers;
    private static final int boardSizeX = 15;
    private static final int boardSizeY = 15;
    private HashMap<ArrayList<Integer>,Integer> premium;

    /**
     * ScrabbleFrame constructor
     */
    public ScrabbleFrame() {

        super("Scrabble");

        int numIRL = Integer.parseInt(getUserInput("Enter number of human players"));
        int numAI = Integer.parseInt(getUserInput("Enter number of AI players"));
        Scrabble scrabble = new Scrabble(numIRL, numAI);
        scrabble.addScrabbleView(this);
        this.numPlayers = numAI + numIRL;

        premium = scrabble.getBoard().getPremium();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.board = new JButton[boardSizeX][boardSizeY];
        this.hand = new JButton[7];
        this.info = new JButton[2+numPlayers];
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
                if(premium.containsKey(new ArrayList<>(Arrays.asList(i,j)))){
                    if(premium.get(new ArrayList<>(Arrays.asList(i,j)))==2){
                        b.setBackground(new Color(0,183,235));
                        b.setText("2L");
                    }else if(premium.get(new ArrayList<>(Arrays.asList(i,j)))==3){
                        b.setBackground(new Color(60,150,255));
                        b.setText("3L");
                    }else if(premium.get(new ArrayList<>(Arrays.asList(i,j)))==4){
                        b.setBackground(new Color(255,105,180));
                        b.setText("2W");
                    }else if(premium.get(new ArrayList<>(Arrays.asList(i,j)))==5){
                        b.setBackground(new Color(255,0,0));
                        b.setText("3W");
                    }
                }
                if(i == 7 && j == 7){
                    b.setBackground(new Color(255,228,181));
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
            b.setBorderPainted(true);

            hand[i] = b;
            handPanel.add(b);
            b.setActionCommand(0 + " " + 0 + " " + finalI + " SELECT");
            b.addActionListener(controller);

        }
        //save button top right
        JButton saveButton = new JButton("Save");
        saveButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, saveButton.getMinimumSize().height));
        saveButton.setActionCommand(0 + " " + 0 + " " + 0 + " SAVE");
        saveButton.addActionListener(e->scrabble.serialize(getUserInput("Enter file name(.txt)")));
        Color c2 = new Color(21, 224 ,140);
        saveButton.setBackground(c2);
        saveButton.setOpaque(true);
        saveButton.setBorderPainted(false);
        optionPanel.add(saveButton);
        //load button top right
        JButton loadButton = new JButton("Load");
        loadButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, loadButton.getMinimumSize().height));
        loadButton.setActionCommand(0 + " " + 0 + " " + 0 + " LOAD");
        loadButton.addActionListener(e->scrabble.load(getUserInput("Enter file name(.txt)")));
        loadButton.setBackground(c2);
        loadButton.setOpaque(true);
        loadButton.setBorderPainted(false);
        optionPanel.add(loadButton);
        JButton editorButton = new JButton("Editor");
        editorButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, loadButton.getMinimumSize().height));
        editorButton.addActionListener(e->new BoardEditorFrame());
        editorButton.setBackground(c2);
        editorButton.setOpaque(true);
        editorButton.setBorderPainted(false);
        optionPanel.add(editorButton);
        JButton boardButton = new JButton("Premium Import");
        boardButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, loadButton.getMinimumSize().height));
        boardButton.addActionListener(e-> {
            try {
                scrabble.importBoardPremium(JOptionPane.showInputDialog(null,"Enter file name(.xml)"));
            } catch (ParserConfigurationException | IOException | SAXException ex) {
                throw new RuntimeException(ex);
            }
        });
        boardButton.setBackground(c2);
        boardButton.setOpaque(true);
        boardButton.setBorderPainted(false);
        optionPanel.add(boardButton);

        optionPanel.add(Box.createVerticalGlue());
        this.add(optionPanel, BorderLayout.EAST);

        JButton submitButton = new JButton("Submit");
        submitButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, submitButton.getMinimumSize().height));
        submitButton.setActionCommand(0 + " " + 0 + " " + 0 + " SUBMIT");
        submitButton.addActionListener(controller);
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

        JButton redoButton = new JButton("Redo");
        redoButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, redoButton.getMinimumSize().height));
        optionPanel.add(redoButton);
        redoButton.setActionCommand(0 + " " + 0 + " " + 0 + " REDO");
        redoButton.addActionListener(controller);
        redoButton.setBackground(c2);
        redoButton.setOpaque(true);
        redoButton.setBorderPainted(false);

        JButton undoButton = new JButton("Undo");
        undoButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, undoButton.getMinimumSize().height));
        optionPanel.add(undoButton);
        undoButton.setActionCommand(0 + " " + 0 + " " + 0 + " UNDO");
        undoButton.addActionListener(controller);
        undoButton.setBackground(c2);
        undoButton.setOpaque(true);
        undoButton.setBorderPainted(false);

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

        for(int i = 1; i < numPlayers+1; i++){
            JButton scoreButton = new JButton("Player " + i + " Score: 0");
            info[i] = scoreButton;
            scoreButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, resetButton.getMinimumSize().height));
            scorePanel.add(scoreButton);
            scoreButton.setBackground(c2);
            scoreButton.setOpaque(true);
            scoreButton.setBorderPainted(false);
            scoreButton.setEnabled(false);
        }

        JButton playerButton = new JButton("Player 1's Turn");
        info[1+numPlayers] = playerButton;
        playerButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, resetButton.getMinimumSize().height));
        scorePanel.add(playerButton);
        playerButton.setBackground(c2);
        playerButton.setOpaque(true);
        playerButton.setBorderPainted(false);
        playerButton.setEnabled(false);
        scorePanel.add(Box.createVerticalGlue());
        scorePanel.setBackground(c1);

        this.setSize(1100, 900);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }
    @Override
    public void update(ScrabbleEvent e) {
        if(e.getCommand() == Scrabble.Command.PLACE && e.getCurrentLetter() != null) {
            String label = e.getCurrentLetter().toString().toUpperCase();
            board[e.getY()][e.getX()].setText(label);
        }

        if(e.getCommand() == Scrabble.Command.RESET){
            //clear anything temporary on board
            for(int i = 0; i < boardSizeX; i++) {
                for(int j = 0; j < boardSizeY; j++) {
                    if(board[j][i].isEnabled() && !(board[j][i].getText().equals("2L") || board[j][i].getText().equals("3L")
                            || board[j][i].getText().equals("2W") || board[j][i].getText().equals("3W"))) {
                        board[j][i].setText(String.valueOf(e.getBoard().getBoard()[i][j]));
                        if(premium.containsKey(new ArrayList<>(Arrays.asList(i,j)))){
                            if(premium.get(new ArrayList<>(Arrays.asList(i,j)))==2){
                                board[j][i].setText("2L");
                            }else if(premium.get(new ArrayList<>(Arrays.asList(i,j)))==3){
                                board[j][i].setText("3L");
                            }else if(premium.get(new ArrayList<>(Arrays.asList(i,j)))==4){
                                board[j][i].setText("2W");
                            }else if(premium.get(new ArrayList<>(Arrays.asList(i,j)))==5){
                                board[j][i].setText("3W");
                            }
                        }
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
                if (button.getText().equals(e.getCurrentLetter().toString()) && button.isEnabled()) {
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
                    if(premium.containsKey(new ArrayList<>(Arrays.asList(i,j))) &&
                            board[j][i].isEnabled()){
                        if(premium.get(new ArrayList<>(Arrays.asList(i,j)))==2){
                            board[j][i].setText("2L");
                        }else if(premium.get(new ArrayList<>(Arrays.asList(i,j)))==3){
                            board[j][i].setText("3L");
                        }else if(premium.get(new ArrayList<>(Arrays.asList(i,j)))==4){
                            board[j][i].setText("2W");
                        }else if(premium.get(new ArrayList<>(Arrays.asList(i,j)))==5){
                            board[j][i].setText("3W");
                        }
                    }
                }
            }
            //load next players hand
            for(int i = 0; i < e.getCurrentPlayer().getLetters().size(); i++) {
                hand[i].setText(String.valueOf(e.getCurrentPlayer().getLetters().get(i).getLetter()));
                hand[i].setEnabled(true);
            }

            //update turn
            info[0].setText("Turn: " + e.getTurn());
            for(int i = 1; i < numPlayers+1; i++){
                if(info[i].getText().split(" ")[1].equals(e.getPreviousPlayer().getName().split(" ")[1])){
                    info[i].setText(e.getPreviousPlayer().getName() + " Score: " + e.getPreviousPlayer().score);
                    break;
                }
            }
            info[1+numPlayers].setText(e.getCurrentPlayer().getName() + "'s turn");
        }

        if(e.getCommand() == Scrabble.Command.SUBMIT) {
            for(int i = 0; i < boardSizeX; i++) {
                for(int j = 0; j < boardSizeY; j++) {
                    if (e.getBoard().getBoard()[i][j] != ' ') {
                        /* debug
                        System.out.println("buttons[" + i + "][" + j + "]: " + board[j][i].getText());
                        System.out.println("board:[" + i + "][" + j + "]: " + e.getBoard().getBoard()[i][j]); */
                        board[j][i].setText(String.valueOf(e.getBoard().getBoard()[i][j]));
                        board[j][i].setBackground(new Color(0,0,0));
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
            for(int i = 1; i < numPlayers+1; i++){
                if(info[i].getText().split(" ")[1].equals(e.getPreviousPlayer().getName().split(" ")[1])){
                    info[i].setText(e.getPreviousPlayer().getName() + " Score: " + e.getPreviousPlayer().score);
                    break;
                }
            }
            info[1+numPlayers].setText(e.getCurrentPlayer().getName() + "'s turn");
        }

        if(e.getCommand() == Scrabble.Command.UNDO){
            board[e.getY()][e.getX()].setText(String.valueOf(e.getBoard().getBoard()[e.getX()][e.getY()]));
            if(premium.containsKey(new ArrayList<>(Arrays.asList(e.getX(),e.getY())))
            && !board[e.getX()][e.getY()].isEnabled()){
                if(premium.get(new ArrayList<>(Arrays.asList(e.getX(),e.getY())))==2){
                    board[e.getY()][e.getX()].setText("2L");
                }else if(premium.get(new ArrayList<>(Arrays.asList(e.getX(),e.getY())))==3){
                    board[e.getY()][e.getX()].setText("3L");
                }else if(premium.get(new ArrayList<>(Arrays.asList(e.getX(),e.getY())))==4){
                    board[e.getY()][e.getX()].setText("2W");
                }else if(premium.get(new ArrayList<>(Arrays.asList(e.getX(),e.getY())))==5){
                    board[e.getY()][e.getX()].setText("3W");
                }
            }

            for(int i = 0; i < e.getCurrentPlayer().getLetters().size(); i++) {
                if(!hand[i].isEnabled() && hand[i].getText().equals(e.getCurrentLetter().toString())){
                    hand[i].setEnabled(true);
                    break;
                }
            }
        }

        if(e.getCommand() == Scrabble.Command.REDO){
            board[e.getY()][e.getX()].setText(String.valueOf(e.getCurrentLetter()));

            for(int i = 0; i < e.getCurrentPlayer().getLetters().size(); i++) {
                if(hand[i].isEnabled() && hand[i].getText().equals(e.getCurrentLetter().toString())){
                    hand[i].setEnabled(false);
                    break;
                }
            }
        }

        if(e.getCommand() == Scrabble.Command.LOAD) {
            //clear board
            for(int i = 0; i < boardSizeX; i++) {
                for (int j = 0; j < boardSizeY; j++) {
                    board[j][i].setText(" ");
                    board[j][i].setEnabled(true);
                    if ((i + j) % 2 == 0) {
                        board[j][i].setBackground(new Color(200, 200, 200));
                    } else {
                        board[j][i].setBackground(new Color(255, 255, 255));
                    }
                    if (i == 7 && j == 7) {
                        board[j][i].setBackground(new Color(255, 228, 181));
                    }
                }
            }

            //update board gui
            System.out.println(e.getPremium().toString());
            premium = e.getPremium();
            System.out.println(premium.toString());

            for(int i = 0; i < boardSizeX; i++) {
                for(int j = 0; j < boardSizeY; j++) {
                    if (e.getBoard().getBoard()[i][j] != ' ') {
                        board[j][i].setText(String.valueOf(e.getBoard().getBoard()[i][j]));
                        board[j][i].setBackground(new Color(0,0,0));
                        board[j][i].setEnabled(false);
                    }
                    else {
                        if(premium.containsKey(new ArrayList<>(Arrays.asList(i,j)))){
                            if(premium.get(new ArrayList<>(Arrays.asList(i,j)))==2){
                                board[j][i].setBackground(new Color(0,183,235));
                                board[j][i].setText("2L");
                            }else if(premium.get(new ArrayList<>(Arrays.asList(i,j)))==3){
                                board[j][i].setBackground(new Color(60,150,255));
                                board[j][i].setText("3L");
                            }else if(premium.get(new ArrayList<>(Arrays.asList(i,j)))==4){
                                board[j][i].setBackground(new Color(255,105,180));
                                board[j][i].setText("2W");
                            }else if(premium.get(new ArrayList<>(Arrays.asList(i,j)))==5){
                                board[j][i].setBackground(new Color(255,0,0));
                                board[j][i].setText("3W");
                            }
                        }
                    }
                }
            }
            //update hand gui
            for(int i = 0; i < e.getCurrentPlayer().getLetters().size(); i++) {
                hand[i].setText(String.valueOf(e.getCurrentPlayer().getLetters().get(i).getLetter()));
                hand[i].setEnabled(true);
            }
        }
    }
    public char getBlankTileInput() {
        return JOptionPane.showInputDialog("Enter letter").toUpperCase().charAt(0);
    }
    public String getUserInput(String prompt) {
        return JOptionPane.showInputDialog(prompt);
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
