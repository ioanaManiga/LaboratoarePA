import java.io.*;
import java.net.*;
import java.util.LinkedHashMap;
import java.util.Map;

class ClientThread extends Thread {
    private Socket socket = null;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    public String apiRequestAllGames() throws Exception {
        String response = new String();
        URL api = new URL("https://localhost:443/api/game/getAll");
        URLConnection connection = api.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        connection.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null)
            response = response + inputLine;
        in.close();
        return response;
    }
    public void createGame(String content, String playersNumber) throws Exception {
        URL url = new URL("https://localhost:443/api/game/addGame");
        Map<String,Object> params = new LinkedHashMap<>();
        params.put("content", content);
        params.put("playersNumber", playersNumber);

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
    }

    public void createPlayer(String name) throws Exception {
        URL url = new URL("https://localhost:443/api/players/addPlayer");
        Map<String,Object> params = new LinkedHashMap<>();
        params.put("name", name);

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
    }

    public void run() {
        //am pus un text care sa anunte cand un client s-a conectat
        System.out.println("Client connected");
        try {
            createPlayer("Player nou");
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                String request = in.readLine();
                //am citit comanda de la client si verificam cazurile
                if(request!= null){
                    if (request.equals("exit")) {
                        //daca requestul este exit se inchide socketul si se face si break pt a intrerupe procesul
                        try {
                            socket.close();
                        } catch (IOException e) {
                            System.err.println(e);
                        }
                        break;
                    }
                if (request.equals("game")) {
                    try {
                        createGame("Joc de carti", "4");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


                String raspuns = "Server received the request " + request;

                if (request.equals("allGames")) {
                    try {
                        raspuns = apiRequestAllGames();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                    //pentru alta comanda, se afiseaza numai care este comanda efectuata de client
                    PrintWriter out = new PrintWriter(socket.getOutputStream());
                    //in cazul in care comanda a fost 'stop', raspunsul serverului se schimba si am facut exit(asa am gasit pe net ca se face)
                    if (request.equals("stop")) {
                        raspuns = "Server stopped";
                        out.println(raspuns);
                        System.exit(1);
                    }
                    //la final se afiseaza raspunsul, in cazul in care se poate, si se elibereaza bufferul
                    out.println(raspuns);
                    out.flush();
                }


            } catch (IOException e) {
                System.err.println("Communication error... " + e);
            }
        }
    }
}