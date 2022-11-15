package performAction;

import entity.ProductsEntity;
import entity.entityManager.ProductManager;
import exceptions.LackOfProductException;
import exceptions.UnknownProductException;
import jakarta.persistence.NoResultException;

public class PerformAction implements IPerformAction{

    @Override
    public void addProduct(String productName, double price, int amount) {
        ProductManager.createProduct(productName, price, amount);
    }

    @Override
    public ProductsEntity getProductById(int id) throws UnknownProductException {
        ProductsEntity temp;

        try {
            return ProductManager.getProductById(id);
        } catch (NoResultException e) {
            throw new UnknownProductException();
        }
    }
    @Override
    public void modifyProduct(int id, String productName, double price, int amount) {
        ProductManager.modifyProductById(id, productName, price, amount);
    }

    @Override
    public boolean checkAvailability(ProductsEntity productsEntity) {
        return productsEntity.getAmount() > 0;
    }

    @Override
    public void deleteProduct(ProductsEntity productsEntity, int amount) throws LackOfProductException{
        int newAmount;

        newAmount = productsEntity.getAmount() - amount;
        if (newAmount >= 0)
            ProductManager.modifyAmountProductById(productsEntity.getProductId(), newAmount);
        else
            throw new LackOfProductException();
    }

    public void returnProductToMagazine(ProductsEntity productsEntity) {
        ProductsEntity productInMagazine;
        int newAmount;

        productInMagazine = ProductManager.getProductById(productsEntity.getProductId());
        newAmount = productInMagazine.getAmount() + productsEntity.getAmount();
        ProductManager.modifyAmountProductById(productsEntity.getProductId(), newAmount);
    }
}
