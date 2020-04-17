package repo;

import entity.AlbumsEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class AlbumRepository {
    public void create(String name, long artistId, long releaseYear){
        EntityManager em = util.PersistenceUtil.getEmf().createEntityManager();
        em.getTransaction().begin();

        long albumsIdMax =
                (long) em.createNamedQuery("Albums.maxId")
                        .getSingleResult();

        AlbumsEntity newAlbumEntity = new AlbumsEntity();

        newAlbumEntity.setId(albumsIdMax+1);
        newAlbumEntity.setArtistId(artistId);
        newAlbumEntity.setName(name);
        newAlbumEntity.setReleaseYear((long) releaseYear);

        em.persist(newAlbumEntity);
        em.getTransaction().commit();

        em.close();
    }
    public List<AlbumsEntity> findByArtist(long artistId){
        EntityManager em = util.PersistenceUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        Query query =
                em.createNamedQuery("Albums.findByArtist")
                        .setParameter("artistId", artistId);

        List<AlbumsEntity> albums = query.getResultList();
        em.getTransaction().commit();
        em.close();
        return albums;
    }

    public List<AlbumsEntity> findByName(String name){
        EntityManager em = util.PersistenceUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        Query query =
                em.createNamedQuery("Albums.findByName")
                        .setParameter("name", name);

        List<AlbumsEntity> albums = query.getResultList();
        em.getTransaction().commit();
        em.close();
        return albums;
    }

    public AlbumsEntity findById(long id){
        EntityManager em = util.PersistenceUtil.getEmf().createEntityManager();
        em.getTransaction().begin();

        AlbumsEntity albumEntity = em.find(AlbumsEntity.class, id);

        em.getTransaction().commit();
        em.close();
        return albumEntity;
    }
}