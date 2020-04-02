import java.util.ArrayList;
import java.util.List;

public class Board {
    private int numberOfTokens;
    public static List<Token> tokenList = new ArrayList<>();

    public Board(int numberOfTokens) {
        this.numberOfTokens = numberOfTokens;
        for (int i = 1; i <= numberOfTokens; i++) {
            tokenList.add(new Token(i, false));
        }
        tokenList.add(new Token(0, true));
    }
}
