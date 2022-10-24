package entity.entityManager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class ProductManager {
    /**
     * Creates new product. You are not able to add product with the same name.
     * @param name product's name
     * @param price product's price
     * @param amount amount of new product
     */
    public static void createProduct(String name, double price, int amount) {
        int check;
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();


    }
}
