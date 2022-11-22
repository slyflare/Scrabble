import java.nio.charset.CharsetEncoder;
import java.util.*;
import java.io.*;
/**
 * Board class responsible for keeping board state
 * @author Riya
 * */
public class Board {
   private char[][] board;
   private int row;
   private int col;
   private char input;

    public Board(){
        this.board();
        //this.updateBoard();


    }
    /**Initialising the scrabble board with 15 numbered rows and 15 alphabetized columns.
     * @author Riya
     */
    private void board() {
        this.board = new char[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                this.board[i][j] = ' ';
            }
        }
    }

    /**
     * Puts the words on the board
     * @param playerPlacement the command containing the word to place
     * @return true if the word is placed, false if placement is invalid
     * @author Matthew
     */
    public boolean updateBoard(HashMap<ArrayList<Integer>, Character> playerPlacement) {
        for(ArrayList<Integer> yx : playerPlacement.keySet()) {
            int x = yx.get(0);
            int y = yx.get(1);
            board[x][y] = playerPlacement.get(yx);
        }
        return true;
    }

    /**
     * Prints out the current board.
     * @author Riya
     * */
    public void printBoard(){
        String output = "";
        for ( int  row = 0;  row < 15; row++){
            for (int col = 0; col < 15; col ++){
                if(col == 0)
                { output = output + "------------------------------------------------------------"+'\n';
                }
                output += "| ";
                output += board[row][col] + " ";
                if(col == 14){
                    output += "|"+'\n';
                }
                if( row == 14 && col == 14)
                { output = output + "-------------------------------------------------------------"+'\n';
                }
            }
        }
        System.out.println(output);
    }

    public char[][] getBoard() {
        return board;
    }
}
