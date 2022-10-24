package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "products", schema = "cash_register")

@NamedQuery(name = "ProductsEntity.ById", query = "select p from  ProductsEntity p where p.idProducts = ?1")
@NamedQuery(name = "ProductsEntity.ByName", query = "select p from  ProductsEntity p where p.productName = ?1")
@NamedQuery(name = "DeleteProduct", query = "delete from ProductsEntity p where p.idProducts = ?1")

public class ProductsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_products")
    private int idProducts;
    @Basic
    @Column(name = "product_name")
    private String productName;
    @Basic
    @Column(name = "price")
    private double price;
    @OneToOne(mappedBy = "productsByIdMagazineProducts")
    private MagazineEntity magazineByIdProducts;

    public int getIdProducts() {
        return idProducts;
    }

    public void setIdProducts(int idProducts) {
        this.idProducts = idProducts;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductsEntity that = (ProductsEntity) o;

        if (idProducts != that.idProducts) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (productName != null ? !productName.equals(that.productName) : that.productName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = idProducts;
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public MagazineEntity getMagazineByIdProducts() {
        return magazineByIdProducts;
    }

    public void setMagazineByIdProducts(MagazineEntity magazineByIdProducts) {
        this.magazineByIdProducts = magazineByIdProducts;
    }
}
