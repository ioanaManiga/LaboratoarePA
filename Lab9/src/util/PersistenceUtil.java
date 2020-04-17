package util;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
public class PersistenceUtil {
    public static EntityManagerFactory emf;
    public static EntityManagerFactory getEmf(){
        if(emf == null) {
            emf = Persistence.createEntityManagerFactory("MusicAlbumsPU");
        }
        return emf;
    }
}