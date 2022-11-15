package view;

import entity.ProductsEntity;
import performAction.PerformAction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow {
    private JPanel mainPanel;
    private JButton addProductToList;
    private JTable productsTable;
    private JButton addProductToMagazineButton;
    private JPanel listManagementPanel;
    private JPanel addingProductToListPanel;
    private JLabel idLabel;
    private JTextField idTextField;
    private JLabel sumLabel;
    private JButton modifyProductButton;
    private static JFrame mainFrame;

    private ProductsList productsList;
    private AddProduct addProductForm;
    private ModifyProduct modifyProduct;
    private PerformAction performAction;
    private double total;

    public MainWindow() {
        total = 0.0;
        productsList = new ProductsList();
        performAction = new PerformAction();
        productsTable.setModel(productsList);
        mainFrame = new JFrame("Manager");

        addProductToMagazineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProductForm = new AddProduct();
            }
        });

        addProductToList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductsEntity temp;
                temp = performAction.getProductById(Integer.parseInt(idTextField.getText()));

                if (!productsList.addProduct(temp)) {
                    JOptionPane.showMessageDialog(
                            mainFrame,
                            "! Unknown ID !",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    total += temp.getPrice();
                    sumLabel.setText("Suma: " + total);
                    idTextField.setText("");
                    productsList.fireTableDataChanged();
                }
            }
        });

        modifyProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifyProduct = new ModifyProduct();
            }
        });

        mainFrame.setContentPane(mainPanel);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }


}
