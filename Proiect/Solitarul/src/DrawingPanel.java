import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DrawingPanel extends JPanel {
    final MainFrame frame;
    final static int W = 700, H = 700;
    final static int dotsPerRow = 7;
    Color point = new Color(14, 134, 150);
    Color white = new Color(255, 255, 255);
    Color selected = new Color(96, 9, 107);
    Color line = new Color(110, 116, 125);
    BufferedImage image;
    Board board;
    int points = 0;
    int clickCounter = 0;
    int lastX;
    int lastY;
    boolean gameOver = false;
    public static Graphics2D graphics;
    public static List<Shape> shapes = new ArrayList<>();

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        createOffscreenImage();
        this.board = new Board(dotsPerRow);

        init();
    }

    private void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, W, H);
    }

    public void postRequest(String name, String score) throws Exception {
        URL url = new URL("http://localhost:8083/api/score/addScore");
        Map<String,Object> params = new LinkedHashMap<>();
        params.put("name", name);
        params.put("points", score);

        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String,Object> param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes);

        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

        for (int c; (c = in.read()) >= 0;)
            System.out.print((char)c);
        System.out.println();
    }

    private void init() {
        setPreferredSize(new Dimension(W, H));
        setBorder(BorderFactory.createEtchedBorder());
        int aux = W / dotsPerRow;
        for (int i = 0; i < dotsPerRow - 1; i++) {
            drawLine(aux * (i + 1), 0, aux * (i + 1), H);
        }
        for (int i = 0; i < dotsPerRow - 1; i++) {
            drawLine(0, aux * (i + 1), W, aux * (i + 1));
        }
        for (int i = 0; i < dotsPerRow; i++) {
            for (int j = 0; j < dotsPerRow; j++) {
                if (!(i == j && i == dotsPerRow - j - 1)) {
                    drawPoint(i * 100, j * 100, point);
                }
            }
        }
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!gameOver) {
                    if (clickCounter % 2 == 0) {
                        int currentX = (e.getX() / aux) * aux;
                        int currentY = (e.getY() / aux) * aux;

                        if (!board.isZero(currentY / 100 + 1, currentX / 100 + 1)) {
                            drawPoint(currentX, currentY, selected);
                            lastX = (e.getX() / aux) * aux;
                            lastY = (e.getY() / aux) * aux;
                            clickCounter++;
                        }
                    } else {
                        int currentX = (e.getX() / aux) * aux;
                        int currentY = (e.getY() / aux) * aux;
                        if (board.isZero(currentY / 100 + 1, currentX / 100 + 1)) {
                            if ((Math.abs(currentX - lastX) / 100 == 2 || Math.abs(currentY - lastY) / 100 == 2) && (!(Math.abs(currentX - lastX) / 100 == 2) || !(Math.abs(currentY - lastY) / 100 == 2))) {
                                if (!board.isZero((currentY / 100 + lastY / 100) / 2 + 1, (currentX / 100 + lastX / 100) / 2 + 1)) {
                                    addPoint();
                                    int mediaX = (currentX / 100 + lastX / 100) / 2;
                                    int mediaY = (currentY / 100 + lastY / 100) / 2;
                                    drawPoint(currentX, currentY, point);
                                    drawPoint(mediaX * 100, mediaY * 100, white);
                                    drawPoint(lastX, lastY, white);
                                    board.setValueByPosition(mediaY + 1, mediaX + 1);
                                    board.setValueByPosition(lastY / 100 + 1, lastX / 100 + 1);
                                    board.setValueByPosition(currentY / 100 + 1, currentX / 100 + 1);
                                } else {
                                    drawPoint(lastX, lastY, point);
                                }


                            } else {
                                drawPoint(lastX, lastY, point);
                            }
                        } else {
                            drawPoint(lastX, lastY, point);
                        }
                        clickCounter++;
                    }
                    if (board.gameOver()) {
                        gameOver = true;
                        JLabel t = new JLabel(" Game over!");
                        t.setBackground(new Color(252, 240, 199));
                        t.setOpaque(true);
                        t.setLocation(200, 200);
                        t.setMinimumSize(new Dimension(500, 200));
                        t.setPreferredSize(new Dimension(500, 200));
                        t.setMaximumSize(new Dimension(500, 200));
                        t.setFont(new Font("Serif", Font.PLAIN, 100));
                        t.setForeground(new Color(181, 14, 51));
                        add(t);
                        t.setBounds(200, 200, 400, 200);
                        try {
                            postRequest(frame.playerName,points+"");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        revalidate();
                        frame.controlPanel.addPlayAgain();
                    }
                    repaint();
                }
            }
        });
    }

    private void addPoint() {
        points++;
        frame.configPanel.setPoints(points);
        frame.configPanel.repaint();
    }

    private void drawLine(int x1, int y1, int x2, int y2) {
        Line2D lin = new Line2D.Float(x1, y1, x2, y2);
        graphics.setColor(line);
        graphics.setStroke(new BasicStroke(5));
        graphics.draw(lin);
    }

    private void drawPoint(int x, int y, Color color) {
        Ellipse2D.Double circle = new Ellipse2D.Double(x + (W / 28) - 3, y + (W / 28) - 3, 60, 60);
        graphics.setColor(color);
        graphics.fill(circle);
    }

    @Override
    public void update(Graphics g) {
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }
}