import java.util.ArrayList;
import java.util.List;

public class Player implements Runnable {
    private List<Token> chosenTokens = new ArrayList<>();
    private int ration = 0;
    private String name;

    public Player(String name) {
        this.name = name;
    }

    @Override
    public synchronized void run() {
        int index = 0;
        while (Board.tokenList.get(index).isUsed()) {
            index++;
        }
        Board.tokenList.get(index).setUsed(true);
        chosenTokens.add(Board.tokenList.get(index));
        System.out.println("Player " + name + " took " + chosenTokens.get(chosenTokens.size() - 1).getValue() + " from the board");

        while (Board.tokenList.get(index).isUsed()) {
            index++;
        }
        Board.tokenList.get(index).setUsed(true);
        chosenTokens.add(Board.tokenList.get(index));
        System.out.println("Player " + name + " took " + chosenTokens.get(chosenTokens.size() - 1).getValue() + " from the board");

        ration = chosenTokens.get(1).getValue() - chosenTokens.get(0).getValue();
        for (Token token : Board.tokenList) {
            if (token.getValue() == chosenTokens.get(chosenTokens.size() - 1).getValue() + ration && !token.isUsed()) {
                token.setUsed(true);
                chosenTokens.add(token);
                System.out.println("Player " + name + " took " + chosenTokens.get(chosenTokens.size() - 1).getValue() + " from the board");
            } else if (token.getValue() == chosenTokens.get(chosenTokens.size() - 1).getValue() + ration) {
                if (!Board.tokenList.get(Board.tokenList.size() - 1).isUsed()) {
                    Board.tokenList.get(Board.tokenList.size() - 1).setUsed(true);
                    chosenTokens.add(new Token(chosenTokens.get(chosenTokens.size() - 1).getValue() + ration, true));
                    System.out.println("Player " + name + " took BLANK as: " + chosenTokens.get(chosenTokens.size() - 1).getValue() + " from the board");
                }
            }
        }
    }
}
