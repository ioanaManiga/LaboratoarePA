import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class StartFrame extends JFrame {
    final static int  W=500, H=400;
    StartDesignPanel startDesignPanel;
    public StartFrame() {
        super("Solitarul(NU ARE NUME IN ENGLEZA)");
        setPreferredSize(new Dimension(W,H));
        init();
    }

    private void init() {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        startDesignPanel = new StartDesignPanel(this);

        add(startDesignPanel, BorderLayout.CENTER);
        setVisible(true);
        pack();
    }
}