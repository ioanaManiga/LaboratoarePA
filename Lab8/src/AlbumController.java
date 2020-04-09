import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AlbumController {
    public void create(String name, int artistId, int releaseYear) {
        int maxId = 0;
        try {
            Statement statement = Main.myConnection.createStatement();
            ResultSet result;

            result = statement.executeQuery("SELECT max(Id) FROM Albums ");
            while (result.next()) {
                maxId = result.getInt(1) + 1;
            }

            String query = "INSERT INTO albums(id,name,artist_id,release_year) VALUES(?,?,?,?)";
            PreparedStatement statement2 = Main.myConnection.prepareStatement(query);
            statement2.setInt(1, maxId);
            statement2.setString(2, name);
            statement2.setInt(3, artistId);
            statement2.setInt(4, releaseYear);
            statement2.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void findByArtist(int artistId) {
        try {
            Statement statement = Main.myConnection.createStatement();
            ResultSet result;
            String query = "SELECT id,name,artist_id,release_year FROM Albums Where artist_id = " + artistId;
            result = statement.executeQuery(query);
            while (result.next()) {
                System.out.println(result.getInt(1) + " " + result.getString(2) + " " + result.getString(3) + " " + result.getString(4));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
