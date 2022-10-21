
import java.util.*;
import java.io.*;
public class Board {
   private char[][] board;
   private int row;
   private int col;
   private char input;

    public Board(){
        this.board();
        this.updateBoard();


    }
    /**Initialising the scrabble board with 15 numbered rows and 15 alphabetized
     columns */
    private void board() {
        this.board = new char[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                this.board[i][j] = 'A';
            }
        }
    }

    public void updateBoard() {


    }

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

    public static void main(String[] args){
        Board b = new Board();
        b.printBoard();
    }

}
