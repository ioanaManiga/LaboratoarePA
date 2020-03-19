import java.io.IOException;

public class Main {
    public static void main(String args[]) throws IOException, InvalidCatalogException {
        Main app = new Main();
        app.testCreateSave();
        app.testLoadView();
    }

    private void testCreateSave() throws IOException {
        Catalog catalog = new Catalog("Java Resources", "C:/Users/MEOWANA/Desktop/JAVA/Laborator5/Problema1/Catalog/Catalog.txt");
        Document doc = new Document("java1", "Java Course 1", "C:/Users/MEOWANA/Desktop/JAVA/Laborator5/Problema1/Catalog/Doc.txt");
        doc.addTag("type", "Slides");
        catalog.add(doc);

        CatalogUtil.save(catalog);
    }

    private void testLoadView() throws InvalidCatalogException {
        Catalog catalog = CatalogUtil.load("C:/Users/MEOWANA/Desktop/JAVA/Laborator5/Problema1/Catalog/Catalog.txt");
        Document doc = catalog.findById("java1");
        CatalogUtil.view(doc);
    }
}