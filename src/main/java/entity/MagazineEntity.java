package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "magazine", schema = "cash_register")

@NamedQuery(name = "MagazineEntity.ById", query = "select p from  MagazineEntity p where p.idMagazineProducts = ?1")

public class MagazineEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_magazine_products")
    private int idMagazineProducts;
    @Basic
    @Column(name = "amount")
    private int amount;

    public int getIdMagazineProducts() {
        return idMagazineProducts;
    }

    public void setIdMagazineProducts(int idMagazineProducts) {
        this.idMagazineProducts = idMagazineProducts;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MagazineEntity that = (MagazineEntity) o;

        if (idMagazineProducts != that.idMagazineProducts) return false;
        if (amount != that.amount) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idMagazineProducts;
        result = 31 * result + amount;
        return result;
    }
}
