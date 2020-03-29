import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton saveBtn = new JButton("Save");
    JButton loadBtn = new JButton("Load");
    JButton resetBtn = new JButton("Reset");
    JButton exitBtn = new JButton("Exit");
    JButton deleteBtn = new JButton("Delete");

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        setLayout(new GridLayout(1, 4));
        add(saveBtn);
        add(loadBtn);
        add(resetBtn);
        add(exitBtn);
        add(deleteBtn);
        saveBtn.addActionListener(this::save);
        loadBtn.addActionListener(this::load);
        resetBtn.addActionListener(this::reset);
        exitBtn.addActionListener(this::exit);
        deleteBtn.addActionListener(this::delete);
    }

    private void save(ActionEvent e) {
        try {
            JFileChooser chooser = new JFileChooser();
            int returnValue = chooser.showSaveDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File fileToSave = chooser.getSelectedFile();
                String filePath = fileToSave.getPath();
                String extension = new String();
                int i = filePath.lastIndexOf('.');
                if (i > 0) {
                    extension = filePath.substring(i + 1);
                }
                ImageIO.write(frame.canvas.image, extension,
                        fileToSave);
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    private void load(ActionEvent e) {
        try {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter1 = new FileNameExtensionFilter("png", "png");
            FileNameExtensionFilter filter2 = new FileNameExtensionFilter("jpeg", "jpeg");
            FileNameExtensionFilter filter3 = new FileNameExtensionFilter("gif", "gif");
            chooser.addChoosableFileFilter(filter1);
            chooser.addChoosableFileFilter(filter2);
            chooser.addChoosableFileFilter(filter3);
            int returnValue = chooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                BufferedImage image = ImageIO.read(new File(chooser.getSelectedFile().getPath()));
                frame.canvas.setImage(image);
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    private void reset(ActionEvent e) {
        Graphics2D graphics = frame.canvas.getImage().createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, DrawingPanel.W, DrawingPanel.H);
    }

    private void exit(ActionEvent e) {
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }

    private void delete ( ActionEvent e){
        if(DrawingPanel.shapes.size() > 0){
            int index = DrawingPanel.shapes.size() - 1;
            Shape shapeToDelete = DrawingPanel.shapes.get(index);
            DrawingPanel.shapes.remove(shapeToDelete);
            Graphics2D graphics = frame.canvas.getImage().createGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, DrawingPanel.W, DrawingPanel.H);
            for(Shape shape : DrawingPanel.shapes){
                DrawingPanel.paint(shape);
            }
        }
    }
}