public interface ScrabbleView {
    void update(ScrabbleEvent event);
    char getBlankTileInput();
    String getUserInput(String prompt);
}
