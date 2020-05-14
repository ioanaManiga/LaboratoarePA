import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    ControlPanel controlPanel;
    DesignPanel designPanel;

    public MainFrame() {
        super("My Drawing Application");
        init();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        designPanel = new DesignPanel(this);
        controlPanel = new ControlPanel(this,designPanel);


        setLayout (new BorderLayout());
        controlPanel.setPreferredSize(new Dimension(100,75));
        controlPanel.setMaximumSize(new Dimension(100,150));
        add(designPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.NORTH);

        pack();
        setVisible(true);
    }
}
