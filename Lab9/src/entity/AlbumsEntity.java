package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ALBUMS", schema = "DBA")
@NamedQueries({
        @NamedQuery(name = "Albums.maxId",
                query = "SELECT MAX(id) FROM AlbumsEntity"),
        @NamedQuery(name = "Albums.findByName",
                query = "SELECT a FROM AlbumsEntity a WHERE a.name=:name"),
        @NamedQuery(name = "Albums.findByArtist",
                query = "SELECT a FROM AlbumsEntity a WHERE a.artistId=:artistId")})
public class AlbumsEntity {
    private long id;
    private String name;
    private long artistId;
    private Long releaseYear;

    @Id
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "ARTIST_ID")
    public long getArtistId() {
        return artistId;
    }

    public void setArtistId(long artistId) {
        this.artistId = artistId;
    }

    @Basic
    @Column(name = "RELEASE_YEAR")
    public Long getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Long releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlbumsEntity that = (AlbumsEntity) o;
        return id == that.id &&
                artistId == that.artistId &&
                Objects.equals(name, that.name) &&
                Objects.equals(releaseYear, that.releaseYear);
    }

    @Override
    public String toString() {
        return "AlbumsEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", artistId=" + artistId +
                ", releaseYear=" + releaseYear +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, artistId, releaseYear);
    }
}
