package entity.entityManager;

import entity.ProductsEntity;
import jakarta.persistence.*;
import org.hibernate.Session;

public class ProductManager {
    /**
     * Creates new product. You are not able to add product with the same name.
     *
     * @param name   product's name
     * @param price  product's price
     * @param amount amount of new product
     */
    public static void createProduct(String name, double price, int amount) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        ProductsEntity productsEntity = null;

        try {
            productsEntity = getProductByName(name, entityManager);
        } catch (NoResultException e) {
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

    /**
     * Delete product with given id
     *
     * @param id product's id
     */
    public static void deleteProductById(int id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        ProductsEntity productsEntity = null;

        try {
            productsEntity = getProductById(id, entityManager);
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
        } catch (NoResultException e) {
            System.out.println("Specified id doesn't exist");
        }
    }

    /**
     * Modify product's amount by id
     *
     * @param id        product's id
     * @param newAmount new amount of product
     */
    public static void modifyAmountProductById(int id, int newAmount) {
        TypedQuery<ProductsEntity> productById;
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        Session session = entityManager.unwrap(org.hibernate.Session.class);

        ProductsEntity productsEntity = null;

        try {
            productsEntity = getProductById(id, entityManager);
            try {
                entityTransaction.begin();

                productsEntity.setAmount(newAmount);

                session.merge(productsEntity);
                entityTransaction.commit();
            } finally {
                if (entityTransaction.isActive()) entityTransaction.rollback();
                entityManager.close();
                entityManagerFactory.close();
            }
        } catch (NoResultException e) {
            System.out.println("Specified id doesn't exist");
        }
    }

    /**
     * Modify product's amount by id
     *
     * @param id       product's id
     * @param newPrice new price of product
     */
    public static void modifyPriceProductById(int id, double newPrice) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        Session session = entityManager.unwrap(org.hibernate.Session.class);

        ProductsEntity productsEntity = null;

        try {
            productsEntity = getProductById(id, entityManager);
            try {
                entityTransaction.begin();

                productsEntity.setPrice(newPrice);

                session.merge(productsEntity);
                entityTransaction.commit();
            } finally {
                if (entityTransaction.isActive()) entityTransaction.rollback();
                entityManager.close();
                entityManagerFactory.close();
            }
        } catch (NoResultException e) {
            System.out.println("Specified id doesn't exist");
        }
    }

    /**
     * Modify product by id
     *
     * @param id        product's id
     * @param newAmount new amount of product
     */
    public static void modifyProductById(int id, String newProductName, Double newPrice, int newAmount) {
        TypedQuery<ProductsEntity> productById;
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        Session session = entityManager.unwrap(org.hibernate.Session.class);

        ProductsEntity productsEntity = null;

        try {
            productsEntity = getProductById(id, entityManager);
            try {
                entityTransaction.begin();

                productsEntity.setProductName(newProductName);
                productsEntity.setPrice(newPrice);
                productsEntity.setAmount(newAmount);

                session.merge(productsEntity);
                entityTransaction.commit();
            } finally {
                if (entityTransaction.isActive()) entityTransaction.rollback();
                entityManager.close();
                entityManagerFactory.close();
            }
        } catch (NoResultException e) {
            System.out.println("Specified id doesn't exist");
        }
    }

    /**
     * Select product by id
     *
     * @param id product's id
     * @return product or null
     */
    public static ProductsEntity getProductById(int id) {
        TypedQuery<ProductsEntity> productById;
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        productById = entityManager.createNamedQuery("ProductsEntity.ById", ProductsEntity.class);
        productById.setParameter(1, id);
        return productById.getSingleResult();
    }

    /**
     * Select product by id. Use when you have to modify entity.
     *
     * @param id            product's id
     * @param entityManager entity manager
     * @return product or null
     */
    public static ProductsEntity getProductById(int id, EntityManager entityManager) {
        TypedQuery<ProductsEntity> productById;

        productById = entityManager.createNamedQuery("ProductsEntity.ById", ProductsEntity.class);
        productById.setParameter(1, id);
        return productById.getSingleResult();
    }

    /**
     * Select product by id
     *
     * @param name product's name
     * @return product or null
     */
    public static ProductsEntity getProductByName(String name) {
        TypedQuery<ProductsEntity> productByName;
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        productByName = entityManager.createNamedQuery("ProductsEntity.ByName", ProductsEntity.class);
        productByName.setParameter(1, name);
        return productByName.getSingleResult();
    }

    /**
     * Select product by id. Use when you have to modify entity.
     *
     * @param name          product's name
     * @param entityManager entity manager
     * @return product or null
     */
    public static ProductsEntity getProductByName(String name, EntityManager entityManager) {
        TypedQuery<ProductsEntity> productByName;

        productByName = entityManager.createNamedQuery("ProductsEntity.ByName", ProductsEntity.class);
        productByName.setParameter(1, name);
        ProductsEntity productsEntity = null;

        return productByName.getSingleResult();
    }

}
