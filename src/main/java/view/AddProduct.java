package view;

import performAction.PerformAction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddProduct {
    private JPanel mainPanel;
    private JTextField productNameTextField;
    private JTextField priceTextField;
    private JTextField amountTextField;
    private JLabel productNameLabel;
    private JLabel priceLabel;
    private JLabel amountLabel;
    private JButton addButton;
    private PerformAction performAction;

    public AddProduct() {
        performAction = new PerformAction();

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    performAction.addProduct(
                            productNameTextField.getText(),
                            Double.parseDouble(priceTextField.getText()),
                            Integer.parseInt(amountTextField.getText()));
                } catch (NumberFormatException err) {
                    clearTextFields();
                }
                clearTextFields();
            }
        });
    }

    private void clearTextFields() {
        productNameTextField.setText("");
        priceTextField.setText("");
        amountTextField.setText("");
    }

}
