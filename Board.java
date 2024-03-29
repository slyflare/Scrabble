import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.*;
import java.io.*;
/**
 * Board class responsible for keeping board state
 * @author Riya
 * */
public class Board extends DefaultHandler implements Serializable{
   private char[][] board;
   private int row;
   private int col;
   private char input;

    private HashMap<ArrayList<Integer>,Integer> premium;

    public Board(){
        this.board();
        row = 0;
        col = 0;
        premium = new HashMap<>();
        //3LS
        premium.put(new ArrayList<>(Arrays.asList(1,5)),3);
        premium.put(new ArrayList<>(Arrays.asList(5,5)),3);
        premium.put(new ArrayList<>(Arrays.asList(9,5)),3);
        premium.put(new ArrayList<>(Arrays.asList(13,5)),3);
        premium.put(new ArrayList<>(Arrays.asList(5,1)),3);
        premium.put(new ArrayList<>(Arrays.asList(9,1)),3);
        premium.put(new ArrayList<>(Arrays.asList(1,9)),3);
        premium.put(new ArrayList<>(Arrays.asList(5,9)),3);
        premium.put(new ArrayList<>(Arrays.asList(9,9)),3);
        premium.put(new ArrayList<>(Arrays.asList(13,9)),3);
        premium.put(new ArrayList<>(Arrays.asList(5,13)),3);
        premium.put(new ArrayList<>(Arrays.asList(9,13)),3);


        //2LS
        premium.put(new ArrayList<>(Arrays.asList(0,3)),2);
        premium.put(new ArrayList<>(Arrays.asList(0,11)),2);
        premium.put(new ArrayList<>(Arrays.asList(2,6)),2);
        premium.put(new ArrayList<>(Arrays.asList(2,8)),2);
        premium.put(new ArrayList<>(Arrays.asList(3,0)),2);
        premium.put(new ArrayList<>(Arrays.asList(3,7)),2);
        premium.put(new ArrayList<>(Arrays.asList(3,14)),2);
        premium.put(new ArrayList<>(Arrays.asList(6,2)),2);
        premium.put(new ArrayList<>(Arrays.asList(6,6)),2);
        premium.put(new ArrayList<>(Arrays.asList(6,8)),2);
        premium.put(new ArrayList<>(Arrays.asList(6,12)),2);
        premium.put(new ArrayList<>(Arrays.asList(7,3)),2);
        premium.put(new ArrayList<>(Arrays.asList(7,11)),2);
        premium.put(new ArrayList<>(Arrays.asList(8,2)),2);
        premium.put(new ArrayList<>(Arrays.asList(8,6)),2);
        premium.put(new ArrayList<>(Arrays.asList(8,8)),2);
        premium.put(new ArrayList<>(Arrays.asList(8,12)),2);
        premium.put(new ArrayList<>(Arrays.asList(11,0)),2);
        premium.put(new ArrayList<>(Arrays.asList(11,7)),2);
        premium.put(new ArrayList<>(Arrays.asList(11,14)),2);
        premium.put(new ArrayList<>(Arrays.asList(12,6)),2);
        premium.put(new ArrayList<>(Arrays.asList(12,8)),2);
        premium.put(new ArrayList<>(Arrays.asList(14,3)),2);
        premium.put(new ArrayList<>(Arrays.asList(14,11)),2);

        //2WS
        int[] x = {1,2,3,4,10,11,12,13,1,2,3,4,10,11,12,13};
        int[] y = {1,2,3,4,4,3,2,1,13,12,11,10,10,11,12,13};
        for(int i = 0; i < 16; i++) {
            premium.put(new ArrayList<>(Arrays.asList(x[i], y[i])), 4); //4 means 2WS
        }

        //3WS
        int[] x1 = {0,7,14,0,14,0,7,14};
        int[] y1 = {0,0,0,7,7,14,14,14};
        for(int i = 0; i < 8; i++) {
            premium.put(new ArrayList<>(Arrays.asList(x1[i], y1[i])), 5); //5 means 3WS
        }

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

    public HashMap<ArrayList<Integer>, Integer> getPremium() {
        return premium;
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

    public boolean isEmpty(){
        for ( int  row = 0;  row < 15; row++){
            for (int col = 0; col < 15; col ++) {
                if(board[row][col] != ' '){
                    return false;
                }
            }
        }
        return true;
    }

    public char[][] getBoard() {

        return board;
    }
    public ArrayList<Character> getLetters() {
        ArrayList<Character> charList = new ArrayList<>();
        for (int  row = 0;  row < 15; row++){
            for (int col = 0; col < 15; col ++) {
                if(board[row][col] != ' '){
                    charList.add(board[row][col]);
                }
            }
        }
        return charList;
    }

    public ArrayList<Integer> checkPlaceable(String word, char c) {
        ArrayList<Integer> x = new ArrayList<>();
        ArrayList<Integer> y = new ArrayList<>();
        for (int  row = 0;  row < 15; row++){
            for (int col = 0; col < 15; col ++) {
                if(board[row][col] == c){
                    x.add(row);
                    y.add(col);
                }
            }
        }
        int index = word.indexOf(c);
        boolean vertical = true;
        boolean horizontal = true;
        //try vertical
        int min = y.get(0) - index;
        int max = y.get(0) + word.length() - index;
        if(max > 14 || min < 0) {
            return null;
        }
        for(int tempY = y.get(0) - 1; tempY >= min; tempY--) { //check up
            if(board[x.get(0)][tempY] != ' ') {
                vertical = false;
            }
        }
        for(int tempY = y.get(0) + 1; tempY <= max; tempY++) { //check down
            if(board[x.get(0)][tempY] != ' ') {
                vertical = false;
            }
        }
        if(vertical) {
            ArrayList<Integer> a = new ArrayList<>();
            a.add(x.get(0));
            a.add(min);
            a.add(0); //0 means vertical
            return a;
        }

        //try horizontal
        min = x.get(0) - index;
        max = x.get(0) + word.length() - index;
        if(max > 14 || min < 0) {
            return null;
        }
        for(int tempX = x.get(0) - 1; tempX >= min; tempX--) { //check up
            if(board[tempX][y.get(0)] != ' ') {
                horizontal = false;
            }
        }
        for(int tempX = x.get(0) + 1; tempX <= max; tempX++) { //check down
            if(board[tempX][y.get(0)] != ' ') {
                horizontal = false;
            }
        }
        if(horizontal) {
            ArrayList<Integer> a = new ArrayList<>();
            a.add(min);
            a.add(y.get(0));
            a.add(1); //1 means horizontal
            return a;
        }

        return null;
    }

    public void setPremium(HashMap<ArrayList<Integer>, Integer> premium) {
        this.premium = premium;
    }

    public String boardToXML(){
        StringBuilder xml = new StringBuilder();
        for ( int  row = 0;  row < 15; row++){
            xml.append("    <board>\n");
            xml.append("        <row>\n");
            for (int col = 0; col < 15; col ++) {
                xml.append("        <column> ").append(board[row][col]).append("</column>\n");
            }
            xml.append("        </row>\n");
            xml.append("    </board>\n");
        }
        return xml.toString();
    }

    public String premiumToXML(){
        StringBuilder xml = new StringBuilder();
        xml.append("<premium>\n");
        for ( int  row = 0;  row < 15; row++){
            xml.append("    <row>\n");
            for (int col = 0; col < 15; col ++) {
                xml.append("        <column>").append(premium.get(new ArrayList<>(Arrays.asList(row,col)))).append("</column>\n");
            }
            xml.append("    </row>\n");
        }
        xml.append("</premium>\n");
        return xml.toString();
    }

    //
    public void premiumLoad(String fileName) throws ParserConfigurationException, SAXException, IOException {
        File file = new File(fileName+".xml");
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser s = spf.newSAXParser();

        s.parse(file, this);
    }

    public void startElement(String u, String ln, String qName, Attributes a){
    }
    public void endElement(String uri, String localName, String qName){
        if(qName.equals("row")){
            row++;
        }
        if(qName.equals("column")){
            col++;
            col = col%15;
        }
    }
    public void characters(char[] ch, int start, int length){
        String strip = new String(ch, start, length).strip();
        if(strip.equals("2")){
            premium.remove(new ArrayList<>(Arrays.asList(row, col)));
            premium.put(new ArrayList<>(Arrays.asList(row, col)), 2);
        }
        if(strip.equals("3")){
            premium.remove(new ArrayList<>(Arrays.asList(row, col)));
            premium.put(new ArrayList<>(Arrays.asList(row, col)), 3);
        }
        if(strip.equals("4")){
            premium.remove(new ArrayList<>(Arrays.asList(row, col)));
            premium.put(new ArrayList<>(Arrays.asList(row, col)), 4);
        }
        if(strip.equals("5")){
            premium.remove(new ArrayList<>(Arrays.asList(row, col)));
            premium.put(new ArrayList<>(Arrays.asList(row, col)), 5);
        }
        if(strip.equals("null")){
            premium.remove(new ArrayList<>(Arrays.asList(row, col)));
        }
    }
}
