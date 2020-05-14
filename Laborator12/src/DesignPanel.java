import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DesignPanel extends JPanel {
    final MainFrame frame;
    final static int W = 1200, H = 800;
    BufferedImage image;
    public static Graphics2D graphics;

    public DesignPanel(MainFrame frame) {
        this.frame = frame;
        createOffscreenImage();
        setPreferredSize(new Dimension(W, H));
        setBorder(BorderFactory.createEtchedBorder());
    }

    public void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, W, H);
    }

    public void createComponent(String name, String text) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class clazz = Class.forName(name);
        Object element = clazz.getConstructor().newInstance();
        Method method = clazz.getMethod("setText", String.class);
        if (method != null) {
            method.invoke(element, text);
        }
        add((Component) element);
    }

    @Override
    public void update(Graphics g) {
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }

}
