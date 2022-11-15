package performAction;

import entity.ProductsEntity;
import exceptions.LackOfProductException;
import exceptions.UnknownProductException;

public interface IPerformAction {
    void addProduct(String productName, double price, int amount);
    ProductsEntity getProductById(int id) throws LackOfProductException, UnknownProductException;
    void modifyProduct(int id, String productName, double price, int amount);
    boolean checkAvailability(ProductsEntity productsEntity);
    void deleteProduct(ProductsEntity productsEntity, int amount) throws LackOfProductException;
}
