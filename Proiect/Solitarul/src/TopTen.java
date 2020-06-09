import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import org.json.*;

public class TopTen extends JFrame {
    ControlPanel controlPanel;

    public TopTen() {
        super("Top");
        init();
    }
    private void init() {
        setPreferredSize(new Dimension(600, 700));

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(60, 40, 5, 5));
        JLabel label = new JLabel( "Top 10", JLabel.CENTER);
        label.setFont(new Font("Arial Black", Font.PLAIN, 20));
        panel.add(label);
        label = new JLabel("---------------------------", JLabel.CENTER);
        label.setFont(new Font("Arial Black", Font.PLAIN, 20));
        panel.add(label);

        try{
            String jsonString =  apiRequest();
            jsonString = "{ \"json\":" + jsonString + "}";

            JSONObject obj = new JSONObject(jsonString);
            JSONArray arr = obj.getJSONArray("json");
            for (int i = 0; i < arr.length(); i++)
            {
                label = new JLabel((i+1) + "." + arr.getJSONObject(i).getString("playerName") + " --> scor: "
                        + arr.getJSONObject(i).getInt("score"), JLabel.CENTER);
                label.setFont(new Font("Arial Black", Font.PLAIN, 20));
                panel.add(label);
            }
        } catch (Exception e){
            System.out.println(e);
        }


        controlPanel = new ControlPanel(this);

        setLayout (new BorderLayout());
        add(panel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }
    public String apiRequest() throws Exception {
        String response = new String();
        URL api = new URL("http://localhost:8083/api/score/getTopTen");
        URLConnection yc = api.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        yc.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null)
            response = response + inputLine;
        in.close();

        return response;
    }
}
