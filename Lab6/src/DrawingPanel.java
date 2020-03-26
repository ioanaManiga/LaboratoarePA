import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class DrawingPanel extends JPanel {
    final MainFrame frame;
    final static int W = 1000, H = 800;
    BufferedImage image;
    public static Graphics2D graphics;
    public static List<Shape> shapes = new ArrayList<>();
    public DrawingPanel(MainFrame frame) {
        this.frame = frame; createOffscreenImage(); init();
    }
    private void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, W, H);
    }
    private void init() {
        setPreferredSize(new Dimension(W, H));
        setBorder(BorderFactory.createEtchedBorder());
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                drawShape(e.getX(), e.getY()); repaint();
            }
        });
    }

    public static void paint(Shape shape){ graphics.setColor(shape.color);
        graphics.fill(new RegularPolygon(shape.x,shape.y,shape.radius,shape.numberOfSides)); }

    public void setImage(BufferedImage image) {
        this.image = image;
        graphics = image.createGraphics();
    }

    public BufferedImage getImage() {
        return image;
    }

    private void drawShape(int x, int y) {
        int radius = (int)(Math.random()*120);
        int sides = (int) ConfigPanel.sidesField.getValue();
        int colorIndex = ConfigPanel.colorCombo.getSelectedIndex();
        int shapeIndex = ConfigPanel.shapesCombo.getSelectedIndex();
        Color color;
        if(colorIndex==0){
            color = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*200));
        }
        else {
            color= new Color(0,0,0);
        }
        int sidesNumber;
        if(shapeIndex==0||shapeIndex==1)
        { sidesNumber= shapeIndex+3;}
        else {sidesNumber=16;}
        graphics.setColor(color);
        graphics.fill(new RegularPolygon(x, y, radius, sidesNumber));
        shapes.add(new Shape(sidesNumber,color,x,y,radius));
    }
    @Override
    public void update(Graphics g) { } //Why did I do that?

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }
}