import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * BoardEditorFrame class responsible editing the premium tile on board and saving them as XMLs.
 * @author Vimal
 *
 * */
public class BoardEditorFrame extends JFrame {
    private JButton[][] grid;
    private Board board;
    private static final int boardSizeX = 15;
    private static final int boardSizeY = 15;
    private HashMap<ArrayList<Integer>,Integer> premium;

    public BoardEditorFrame(){
        super("Board Editor");

        this.grid = new JButton[boardSizeX][boardSizeY];
        board = new Board();
        premium = board.getPremium();

        JPanel optionPanel = new JPanel();
        optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));
        this.setLayout(new BorderLayout());
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(15, 15));
        this.add(gridPanel, BorderLayout.CENTER);

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
                    b.setEnabled(false);
                }
                b.setOpaque(true);
                grid[i][j] = b;
                gridPanel.add(b);
                int finalI = i;
                int finalJ = j;
                b.addActionListener(e -> {
                    String input = JOptionPane.showInputDialog(null,"Enter premium tile 2L, 3L, 2W, 3W, or clear");
                    if(input.equals("2L") || input.equals("3L") || input.equals("2W") || input.equals("3W")) {
                        b.setText(input);
                        this.premium.remove(new ArrayList<>(Arrays.asList(finalI, finalJ)));
                        switch (input) {
                            case "2L" -> {
                                b.setBackground(new Color(0, 183, 235));
                                this.premium.put(new ArrayList<>(Arrays.asList(finalI, finalJ)),2);
                            }
                            case "3L" -> {
                                b.setBackground(new Color(60, 150, 255));
                                this.premium.put(new ArrayList<>(Arrays.asList(finalI, finalJ)),3);
                            }
                            case "2W" -> {
                                b.setBackground(new Color(255, 105, 180));
                                this.premium.put(new ArrayList<>(Arrays.asList(finalI, finalJ)),4);
                            }
                            case "3W" -> {
                                b.setBackground(new Color(255, 0, 0));
                                this.premium.put(new ArrayList<>(Arrays.asList(finalI, finalJ)),5);
                            }
                        }
                    }
                    if(input.equals("clear")){
                        b.setText("");
                        this.premium.remove(new ArrayList<>(Arrays.asList(finalI, finalJ)));
                        if ((finalI + finalJ)%2 == 0){
                            b.setBackground(color1);
                        }
                        else{
                            b.setBackground(color2);
                        }
                    }
                });
            }
        }

        //Button to save
        JButton saveButton = new JButton("Save");
        saveButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, saveButton.getMinimumSize().height));
        Color c2 = new Color(21, 224 ,140);
        saveButton.setBackground(c2);
        saveButton.setOpaque(true);
        saveButton.setBorderPainted(false);
        saveButton.addActionListener(e -> {
            try {
                save();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        optionPanel.add(saveButton);
        this.add(optionPanel, BorderLayout.SOUTH);

        this.setSize(1100, 900);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * Saves the state of the premium tiles on the editor into an xml.
     * @author Vimal
     * */
    private void save() throws IOException {
        board.setPremium(premium);
        File file = new File("board.xml");
        FileWriter writer = new FileWriter(file);
        writer.write(board.premiumToXML());
        writer.close();

        System.out.println(board.premiumToXML());
    }
}
