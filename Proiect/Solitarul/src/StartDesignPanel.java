import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class StartDesignPanel extends JPanel {
    final StartFrame frame;
    JLabel enterNameLabel = new JLabel("Enter your nickname: ", JLabel.CENTER);
    JTextField nameField = new JTextField(20);
    JButton play = new JButton("Play");
    JButton topTen = new JButton("Top");

    public StartDesignPanel(StartFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {

        JPanel enterNamePanel = new JPanel();
        enterNamePanel.setLayout(new BoxLayout(enterNamePanel, BoxLayout.PAGE_AXIS));
        enterNamePanel.setBorder(BorderFactory.createEmptyBorder(70, 5, 5, 5));
        JPanel panelField = new JPanel();
        panelField.setLayout(new BoxLayout(panelField, BoxLayout.PAGE_AXIS));
        panelField.setBorder(BorderFactory.createEmptyBorder(10, 200, 10, 200));
        JPanel panelButton = new JPanel();
        panelButton.setLayout(new BoxLayout(panelButton, BoxLayout.PAGE_AXIS));
        panelButton.setBorder(BorderFactory.createEmptyBorder(10, 200, 10, 200));
        JPanel panelButtonTop = new JPanel();
        panelButtonTop.setLayout(new BoxLayout(panelButtonTop, BoxLayout.PAGE_AXIS));
        panelButtonTop.setBorder(BorderFactory.createEmptyBorder(10, 200, 200, 200));
        enterNameLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        enterNamePanel.add(enterNameLabel);
        nameField.setPreferredSize(new Dimension(60, 30));
        nameField.setFont(new Font("Serif", Font.PLAIN, 18));
        panelField.add(nameField);
        play.setBackground(new Color(14, 134, 150));
        play.setFont(new Font("Serif", Font.PLAIN, 20));
        play.setForeground(new Color(255, 255, 255));
        topTen.setBackground(new Color(14, 134, 150));
        topTen.setFont(new Font("Serif", Font.PLAIN, 20));
        topTen.setForeground(new Color(255, 255, 255));
        panelButton.add(play);
        panelButtonTop.add(topTen);
        play.addActionListener(this::play);
        topTen.addActionListener(this::top);
        add(enterNamePanel);
        add(panelField);
        add(panelButton);
        add(panelButtonTop);
    }

    private void play(ActionEvent e) {
        if (!nameField.getText().equals("")) {
            frame.dispose();
            MainFrame mainFrame = new MainFrame(nameField.getText());
        }
    }

    private void top(ActionEvent e) {
        frame.dispose();
        TopTen top = new TopTen();
    }


}