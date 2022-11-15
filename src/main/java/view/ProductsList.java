package view;

import entity.ProductsEntity;
import performAction.PerformAction;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class ProductsList extends DefaultTableModel {

    private PerformAction performAction;
    private static final  String[] COLUMN_NAMES = new String[] { "id", "name", "price", "amount" };
    List<ProductsEntity> data = new ArrayList<>();

    ProductsList() {
        performAction = new PerformAction();
    }
    
    @Override
    public int getRowCount() {
        if (data == null)
            return 0;
        else
            return data.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public String getColumnName(int col) {
        return COLUMN_NAMES[col];
    }


    @Override
    public Object getValueAt(int row, int colum) {
        ProductsEntity productsEntity = data.get(row);

        switch(colum){
            case 0: return productsEntity.getProductId();
            case 1: return productsEntity.getProductName();
            case 2: return productsEntity.getPrice();
            case 3: return productsEntity.getAmount();
            default : return null;
        }
    }

    public void addProduct(ProductsEntity product) throws NullPointerException {
        if(product == null)
            throw new NullPointerException();
        else{
            product.setAmount(1);
            data.add(product);
        }

    }

    public List<ProductsEntity> getProductList() { return data; }

    public void clearList() {
        data = new ArrayList<>();
    }
}
