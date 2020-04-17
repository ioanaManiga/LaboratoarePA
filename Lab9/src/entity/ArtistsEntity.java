package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ARTISTS", schema = "DBA")
@NamedQueries({
        @NamedQuery(name = "Artists.maxId",
                query = "SELECT MAX(id) FROM ArtistsEntity"),
        @NamedQuery(name = "Artists.findByName",
                query = "SELECT a FROM ArtistsEntity a WHERE a.name=:name")})
public class ArtistsEntity {
    private long id;
    private String name;
    private String country;

    @Id
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ArtistsEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
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
    @Column(name = "COUNTRY")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtistsEntity that = (ArtistsEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, country);
    }
}
