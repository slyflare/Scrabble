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

    public ScrabbleController(){
        this.scrabble = new Scrabble(2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        scrabble.play(); //edit once play is updated
    }
}
