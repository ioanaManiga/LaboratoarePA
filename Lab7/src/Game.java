public class Game {
    Board board;

    public Game(int nrOfTokens) {
        board = new Board(nrOfTokens);
    }

    public void startPlayer(String name) {
        Runnable runnable = new Player(name);
        new Thread(runnable).start();
    }
}
