import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    ConfigPanel configPanel;
    ControlPanel controlPanel;
    DrawingPanel canvas;
    public String playerName;

    public MainFrame(String playerName) {
        super(playerName);
        this.playerName = playerName;
        init();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        configPanel = new ConfigPanel(this);
        controlPanel = new ControlPanel(this);
        canvas = new DrawingPanel(this);

        add(configPanel, BorderLayout.NORTH);
        add(controlPanel, BorderLayout.SOUTH);
        add(canvas, BorderLayout.CENTER);
        setVisible(true);
        pack();
    }
}