import javax.swing.*;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel label;
    public static JSpinner sidesField;
    public static JComboBox colorCombo;
    public static JComboBox shapesCombo;
    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }
    private void init() {
        JLabel sidesLabel = new JLabel("Number of sides:");
        sidesField = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        sidesField.setValue(6); //default number of sides
        String[] colorOptions = {"Random", "Black"};
        colorCombo= new JComboBox(colorOptions);
        colorCombo.setSelectedItem(0);
        String[] shapeOptions = {"Triangle", "Square", "Potato"};
        shapesCombo = new JComboBox(shapeOptions);
        shapesCombo.setSelectedItem(2);
        add(sidesLabel);
        add(sidesField);
        add(colorCombo);
        add(shapesCombo);
    }
}
