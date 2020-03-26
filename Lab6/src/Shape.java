import java.awt.*;
import java.util.List;

public class Shape {
    public int numberOfSides;
    public Color color;
    public int radius;
    public int x;
    public int y;

    public Shape(int numberOfSides, Color color, int x, int y, int radius) {
        this.numberOfSides = numberOfSides;
        this.color = color;
        this.x = x;
        this.y = y;
        this.radius=radius;
    }
}
