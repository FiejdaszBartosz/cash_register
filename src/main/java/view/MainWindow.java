package view;

import entity.ProductsEntity;
import exceptions.LackOfProductException;
import exceptions.UnknownProductException;
import performAction.PerformAction;

import javax.swing.*;
import java.awt.event.*;

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
    private JButton finishTransactionButton;
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
                addProductToListFunction();
            }
        });

        modifyProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifyProduct = new ModifyProduct();
            }
        });

        finishTransactionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productsList.clearList();
                total = 0;
                sumLabel.setText("Suma: " + total);
                productsList.fireTableDataChanged();
            }
        });

        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showOptionDialog(
                        null, "Are You Sure to Close Application?",
                        "Exit Confirmation", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirm == 0) {
                    closeOperation();
                    System.exit(0);
                }
            }
        };

        mainFrame.setContentPane(mainPanel);
        mainFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        mainFrame.addWindowListener(exitListener);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    private void addProductToListFunction() {
        ProductsEntity temp;
        int newAmount;

        try {
            temp = performAction.getProductById(Integer.parseInt(idTextField.getText()));
            performAction.deleteProduct(temp, 1);
            productsList.addProduct(temp);
            total += temp.getPrice();
            sumLabel.setText("Suma: " + total);
            idTextField.setText("");
            productsList.fireTableDataChanged();

        } catch (UnknownProductException unknownProductException) {
            JOptionPane.showMessageDialog(
                    mainFrame,
                    "! Unknown ID !",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (LackOfProductException lackOfProductException) {
            JOptionPane.showMessageDialog(
                    mainFrame,
                    "! Lack of product !",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException nullPointerException) {
            JOptionPane.showMessageDialog(
                    mainFrame,
                    "! Error !",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void closeOperation() {
        if (productsList.getRowCount() != 0) {
            for (ProductsEntity i : productsList.getProductList()) {
                performAction.returnProductToMagazine(i);
            }
        }
    }

}
