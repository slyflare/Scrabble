import javax.swing.*;
import java.awt.*;

public class ScrabbleFrame extends JFrame {
    private JButton[][] board;

    public ScrabbleFrame() {
        super("Scrabble");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        JPanel handPanel = new JPanel();
        handPanel.setLayout(new GridLayout());
        JPanel optionPanel = new JPanel();
        optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));
        optionPanel.setBounds(61, 11, 81, 140);
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(15, 15));
        this.add(gridPanel, BorderLayout.CENTER);
        for(int i = 0; i < 15 * 15; i++) {
            gridPanel.add(new JButton(" "));
        }
        this.add(handPanel, BorderLayout.SOUTH);
        for(int i = 0; i < 7; i++) {
            handPanel.add(new JButton("A"));
        }
        optionPanel.add(Box.createVerticalGlue());
        this.add(optionPanel, BorderLayout.EAST);

        JButton submitButton = new JButton("Submit");
        optionPanel.add(submitButton);
        submitButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, submitButton.getMinimumSize().height));
        JButton drawButton = new JButton("Draw");
        optionPanel.add(drawButton);
        drawButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, drawButton.getMinimumSize().height));
        JButton passButton = new JButton("Pass");
        passButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, passButton.getMinimumSize().height));
        optionPanel.add(passButton);
        JButton resetButton = new JButton("Reset");
        resetButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, resetButton.getMinimumSize().height));
        optionPanel.add(resetButton);
        optionPanel.add(Box.createVerticalGlue());



        this.setSize(700, 600);
        this.setVisible(true);

    }



    public static void main(String[] args) {
        new ScrabbleFrame();
    }

}
