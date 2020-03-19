import java.awt.*;
import java.io.*;

public class CatalogUtil {
    public static void save(Catalog catalog) {
        try {
            FileOutputStream fileCatalog = new FileOutputStream(catalog.getPath());
            ObjectOutputStream objectCatalog = new ObjectOutputStream(fileCatalog);

            objectCatalog.writeObject(catalog);

            objectCatalog.close();
            fileCatalog.close();

        } catch (Exception exception) {
            System.out.println("Exception for saving: " + exception);
        }
    }

    public static Catalog load(String location) {
        Catalog inputCatalog = new Catalog();
        try {
            FileInputStream catalogFile = new FileInputStream(location);
            ObjectInputStream catalogObject = new ObjectInputStream(catalogFile);

            inputCatalog = (Catalog)catalogObject.readObject();

            catalogObject.close();
            catalogFile.close();


        } catch (Exception exception) {
            System.out.println("Exception for loading" + exception);
        }

        return inputCatalog;
    }

    public static void view(Document document) {
        try {
            File file = new File(document.getLocation());
            Desktop.getDesktop().open(file);
        } catch (Exception exception) {
            System.out.println("Exception for viewing" + exception);
        }
    }
}
