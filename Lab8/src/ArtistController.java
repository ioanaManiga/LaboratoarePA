import java.sql.*;

public class ArtistController {
    public void create(String name, String country) {
        int maxId = 0;
        try {
            Statement statement = Main.myConnection.createStatement();
            ResultSet result;

            result = statement.executeQuery("SELECT max(Id) FROM Artists ");
            while (result.next()) {
                maxId = result.getInt(1) + 1;
            }


            String query = "INSERT INTO artists(id,name,country) VALUES(?,?,?)";
            PreparedStatement statement2 = Main.myConnection.prepareStatement(query);
            statement2.setInt(1, maxId);
            statement2.setString(2, name);
            statement2.setString(3, country);
            statement2.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void findByName(String name) {
        try {
            Statement statement = Main.myConnection.createStatement();
            ResultSet result;
            String query = "SELECT id,name,country FROM Artists Where name = '" + name + "'";
            result = statement.executeQuery(query);
            while (result.next()) {
                System.out.println(result.getInt(1) + " " + result.getString(2) + " " + result.getString(3));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
