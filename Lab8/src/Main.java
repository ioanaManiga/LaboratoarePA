import java.sql.*;

public class Main {
    public static Connection myConnection = Database.establishConnection();

    public static void main(String[] args) {
        ArtistController artistController = new ArtistController();
        artistController.create("Nume", "Tara");
        artistController.findByName("Nume");

        AlbumController albumController = new AlbumController();
        albumController.create("Nume", 1, 1999);
        albumController.findByArtist(1);

        try {
            myConnection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
