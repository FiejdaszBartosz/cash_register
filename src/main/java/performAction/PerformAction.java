package performAction;

import entity.ProductsEntity;
import entity.entityManager.ProductManager;
import jakarta.persistence.NoResultException;

public class PerformAction implements IPerformAction{

    @Override
    public void addProduct(String productName, double price, int amount) {
        ProductManager.createProduct(productName, price, amount);
    }

    @Override
    public ProductsEntity getProductById(int id) {
        try {
            return ProductManager.getProductById(id);
        } catch (NoResultException e) {
            return null;
        }
    }

    public void modifyProduct(int id, String productName, double price, int amount) {
        ProductManager.modifyProductById(id, productName, price, amount);
    }
}
