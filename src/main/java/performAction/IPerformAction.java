package performAction;

import entity.ProductsEntity;

public interface IPerformAction {
    void addProduct(String productName, double price, int amount);
    ProductsEntity getProductById(int id);
}
