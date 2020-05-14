import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;


public class ControlPanel extends JPanel {
    final MainFrame frame;
    public static JTextField type;
    public static JTextField defaultText;
    DesignPanel designPanel;

    public ControlPanel(MainFrame frame, DesignPanel designPanel) {
        this.frame = frame;
        init();
        this.designPanel = designPanel;
    }
    private void init() {
        Font font = new Font("SansSerif", Font.CENTER_BASELINE, 16);
        type = new JTextField(30);
        type.setPreferredSize(new Dimension(10,30));
        type.setFont(font);
        defaultText= new JTextField(30);
        defaultText.setPreferredSize(new Dimension(10,30));
        defaultText.setFont(font);
	    JButton button = new JButton("create");
	    button.setFont(font);
        add(type);
        add(defaultText);
        add(button);
        button.addActionListener(this::onPress);
    }
    private void onPress(ActionEvent e) {
        try {
            this.designPanel.createComponent("javax.swing.J"+type.getText(),defaultText.getText());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException ex) {
            System.err.println(ex);
        }
    }
}