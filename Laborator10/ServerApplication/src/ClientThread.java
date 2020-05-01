import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ClientThread extends Thread {
    private Socket socket = null;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        //am pus un text care sa anunte cand un client s-a conectat
        System.out.println("Client connected");
        while (true) {
            try {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                String request = in.readLine();
                //am citit comanda de la client si verificam cazurile
                if (request.equals("exit")) {
                    //daca requestul este exit se inchide socketul si se face si break pt a intrerupe procesul
                    try {
                        socket.close();
                    } catch (IOException e) {
                        System.err.println(e);
                    }
                    break;
                }
                //pentru alta comanda, se afiseaza numai care este comanda efectuata de client
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                String raspuns = "Server received the request " + request;
                //in cazul in care comanda a fost 'stop', raspunsul serverului se schimba si am facut exit(asa am gasit pe net ca se face)
                if (request.equals("stop")) {
                    raspuns = "Server stopped";
                    out.println(raspuns);
                    System.exit(1);
                }
                //la final se afiseaza raspunsul, in cazul in care se poate, si se elibereaza bufferul
                out.println(raspuns);
                out.flush();

            } catch (IOException e) {
                System.err.println("Communication error... " + e);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.err.println(e);
                }
            }
        }
    }
}