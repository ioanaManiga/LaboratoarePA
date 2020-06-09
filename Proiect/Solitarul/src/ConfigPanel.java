import javax.swing.*;
import java.awt.*;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel pointsLabel = new JLabel("Number of points: " + 0);
    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }
    private void init() {
        pointsLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        add(pointsLabel);
    }
    public void setPoints(int points){
        pointsLabel.setText("Number of points: " + points);

        revalidate();
    }

}
