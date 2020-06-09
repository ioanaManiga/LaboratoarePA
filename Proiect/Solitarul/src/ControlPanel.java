import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ControlPanel extends JPanel {
    MainFrame frame;
    TopTen frame1;
    JButton backBtn = new JButton("Back");
    JButton playAgainButton = new JButton("Play again");

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }
    public ControlPanel(TopTen frame) {
        this.frame1 = frame;
        init();
    }

    private void init() {
        setLayout(new GridLayout(1, 4));
        add(backBtn);
        backBtn.addActionListener(this::back);
    }

    public void addPlayAgain() {
        add(playAgainButton);
        revalidate();
        playAgainButton.addActionListener(this::playAgain);
    }
    private void back(ActionEvent e) {
        if(frame == null){
            frame1.dispose();
        } else {
            frame.dispose();
        }
        StartFrame startFrame = new StartFrame();
    }
    private void playAgain(ActionEvent e) {
          setVisible(false);
          frame.dispose();
          MainFrame mainFrame = new MainFrame(frame.playerName);
    }
}