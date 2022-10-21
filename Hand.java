import java.util.*;
/**
 *
 */
public class Hand
{
    private final List<Letter> letters;
    public Hand(String l)
    {
        letters = new ArrayList<Letter>();
        String[] split = l.split(" ");
        for (String s: split) {
            letters.add(new Letter(s));
        }
    }