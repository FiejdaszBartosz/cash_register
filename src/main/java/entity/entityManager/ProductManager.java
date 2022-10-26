package entity.entityManager;

import entity.ProductsEntity;
import jakarta.persistence.*;

public class ProductManager {
    /**
     * Creates new product. You are not able to add product with the same name.
     * @param name product's name
     * @param price product's price
     * @param amount amount of new product
     */
    public static void createProduct(String name, double price, int amount) {
        TypedQuery<ProductsEntity> productByName;
        int check;
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        productByName = entityManager.createNamedQuery("ProductsEntity.ByName", ProductsEntity.class);
        productByName.setParameter(1, name);
        check = productByName.getResultList().size();

        if (check == 0) {
            try {
                entityTransaction.begin();

                ProductsEntity newProduct = new ProductsEntity();
                newProduct.setProductName(name);
                newProduct.setPrice(price);
                newProduct.setAmount(amount);

                entityManager.persist(newProduct);
                entityTransaction.commit();
            } finally {
                if (entityTransaction.isActive()) entityTransaction.rollback();
                entityManager.close();
                entityManagerFactory.close();
            }
        }
    }

    public static void deleteProductById(int id) {
        TypedQuery<ProductsEntity> productById;
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        productById = entityManager.createNamedQuery("ProductsEntity.ById", ProductsEntity.class);
        productById.setParameter(1, id);
        ProductsEntity productsEntity = productById.getSingleResult();

        if (productsEntity != null) {
            try {
                entityTransaction.begin();

                entityManager.remove(productsEntity);

                entityManager.flush();
                entityManager.clear();
                entityTransaction.commit();
            } finally {
                if (entityTransaction.isActive()) entityTransaction.rollback();
                entityManager.close();
                entityManagerFactory.close();
            }
        }
    }



}
