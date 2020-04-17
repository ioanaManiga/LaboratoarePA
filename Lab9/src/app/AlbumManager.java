package app;

import repo.AlbumRepository;
import repo.ArtistRepository;
import util.PersistenceUtil;

class AlbumManager {
    public static void main(String args[]){
        ArtistRepository artist = new ArtistRepository();
        AlbumRepository album = new AlbumRepository();

        artist.create("Cantaret","DinTaraLui");
        album.create("ColajManeleVechi",1,2002);


        System.out.println(album.findByName("ColajManeleVechi"));
        System.out.println(album.findById(1));
        System.out.println(artist.findById(1));
        System.out.println(album.findByArtist(1));
        System.out.println(artist.findByName("Cantaret"));


        PersistenceUtil.emf.close();
    }
}