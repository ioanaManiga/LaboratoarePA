import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
// am luat mare parte din cod de pe slide-urile laboratorului
public class GameClient {
    public static void main(String[] args) throws IOException {
        String serverAddress = "127.0.0.1";
        //aici am initializat un scanner pt a putea citi comenzile introduse de client
        Scanner scanner = new Scanner(System.in);
        int PORT = 8100;
        while (true) {
            //am adaugat textul pentru a fi anuntat faptul ca serverul asteapta o comanda
            System.out.println("Introdu o comanda:");
            try (  //urmeaza initializarea socketului
                    Socket socket = new Socket(serverAddress, PORT);
                    PrintWriter out =
                            new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(socket.getInputStream()))) {
                String request = scanner.next();
                out.println(request);
                //daca requestul este exit sau stop, se face break inainte de a citi un raspuns
                //pentru orice altceva, nu se intample nimic altceva decat afisarea raspunsului
                if(request.equals("exit")){
                    break;
                }
                if(request.equals("stop")){
                    break;
                }
                String response = in.readLine();
                System.out.println(response);


            } catch (UnknownHostException e) {
                System.err.println("No server listening... " + e);
            }
        }
    }
}