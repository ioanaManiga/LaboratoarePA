package repo;

import entity.ArtistsEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ArtistRepository {
    public void create(String name, String country){
        EntityManager em = util.PersistenceUtil.getEmf().createEntityManager();
        em.getTransaction().begin();

        long artistIdMax =
                (long) em.createNamedQuery("Artists.maxId")
                        .getSingleResult();

        ArtistsEntity newArtistEntity = new ArtistsEntity();

        newArtistEntity.setId(artistIdMax+1);
        newArtistEntity.setName(name);
        newArtistEntity.setCountry(country);

        em.persist(newArtistEntity);
        em.getTransaction().commit();

        em.close();
    }
    public List<ArtistsEntity> findByName(String name){
        EntityManager em = util.PersistenceUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        Query query =
                em.createNamedQuery("Artists.findByName")
                        .setParameter("name", name);

        List<ArtistsEntity> artists = query.getResultList();
        em.getTransaction().commit();
        em.close();
        return artists;
    }
    public ArtistsEntity findById(long id){
        EntityManager em = util.PersistenceUtil.getEmf().createEntityManager();
        em.getTransaction().begin();

        ArtistsEntity artistEntity = em.find(ArtistsEntity.class, id);

        em.getTransaction().commit();
        em.close();
        return artistEntity;
    }
}