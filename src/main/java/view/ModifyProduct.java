package view;

import entity.ProductsEntity;
import exceptions.LackOfProductException;
import exceptions.UnknownProductException;
import performAction.PerformAction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyProduct {
    private static JFrame mainFrame;
    private JTextField idTextField;
    private JLabel idLabel;
    private JButton loadButton;
    private JTextField nameTextField;
    private JTextField priceTextField;
    private JTextField amountTextField;
    private JButton changeButton;
    private JLabel nameLabel;
    private JLabel priceLabel;
    private JLabel amountLabel;
    private JPanel mainPanel;
    ProductsEntity productsEntity;
    PerformAction performAction;

    public ModifyProduct() {
        performAction = new PerformAction();
        mainFrame = new JFrame("Add product");

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String temp = idTextField.getText();

                try {
                    productsEntity = performAction.getProductById(Integer.parseInt(temp));
                    nameTextField.setText(productsEntity.getProductName());
                    priceTextField.setText(String.valueOf(productsEntity.getPrice()));
                    amountTextField.setText(String.valueOf(productsEntity.getAmount()));
                } catch (NumberFormatException | UnknownProductException err) {
                    JOptionPane.showMessageDialog(
                            mainFrame,
                            "! Unknown ID !",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performAction.modifyProduct(
                        Integer.parseInt(idTextField.getText()),
                        nameTextField.getText(),
                        Double.parseDouble(priceTextField.getText()),
                        Integer.parseInt(amountTextField.getText())
                );
            }
        });

        mainFrame.setContentPane(mainPanel);
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}
