import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Parser class responsible for parsing user commands.
 * @author Vimal
 * */
public class ScrabbleController implements ActionListener {
    private Scrabble scrabble;

    public ScrabbleController(Scrabble scrabble){
        this.scrabble = scrabble;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String[] input = e.getActionCommand().split(" ");
        int x = Integer.parseInt(input[0]);
        int y = Integer.parseInt(input[1]);
        int index = Integer.parseInt(input[2]);
        Scrabble.Command c = Scrabble.Command.valueOf(input[3]);
        scrabble.play(x,y,index,c);
    }
}
