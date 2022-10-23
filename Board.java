
import java.util.*;
import java.io.*;
public class Board {
   private char[][] board;
   private int row;
   private int col;
   private char input;

    public Board(){
        this.board();
        //this.updateBoard();


    }
    /**Initialising the scrabble board with 15 numbered rows and 15 alphabetized
     columns */
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
     * @param command the command containing the word to place
     * @return true if the word is placed, false if placement is invalid
     */
    public boolean updateBoard(ArrayList<String> command) {
        int x = Integer.parseInt(command.get(1));
        int y = Integer.parseInt(command.get(2));
        int len = command.size() - 4;
        for(int i = 0; i < len; i++) {
            if(command.get(3).compareTo("RIGHT") == 0) {
                if(Character.compare(board[x - 1][y + i - 1], ' ') == 0 ||
                        Character.compare(command.get(i+4).charAt(0), '(') == 0 &&
                        Character.compare(command.get(i+4).charAt(1), board[x - 1][y + i - 1]) == 0){
                    //board[x - 1][y + i - 1] = command.get(i + 4).charAt(0);
                }
                else {return false;}
            }
            else if(command.get(3).compareTo("DOWN") == 0 ||
                    Character.compare(command.get(i+4).charAt(0), '(') == 0 &&
                    Character.compare(command.get(i+4).charAt(1), board[x + i - 1][y - 1]) == 0) {
                if(Character.compare(board[x - 1][y + i - 1], ' ') == 0) {
                    //board[x + i - 1][y - 1] = command.get(i + 4).charAt(0);
                }
                else{return false;}
            }
        }
        for(int i = 0; i < len; i++) {
            if(command.get(3).compareTo("RIGHT") == 0) {
                if(Character.compare(command.get(i + 4).charAt(0), '(') == 0) {
                    board[x - 1][y + i - 1] = command.get(i + 4).charAt(1);
                }
                else {
                    board[x - 1][y + i - 1] = command.get(i + 4).charAt(0);
                }
            }
            else if(command.get(3).compareTo("DOWN") == 0) {
                if(Character.compare(command.get(i + 4).charAt(0), '(') == 0) {
                    board[x + i - 1][y - 1] = command.get(i + 4).charAt(1);
                }
                else {
                    board[x + i - 1][y - 1] = command.get(i + 4).charAt(0);
                }
            }
        }
        return true;
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
