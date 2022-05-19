/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invoiceView;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author DELL
 */
public class LineDialogView extends JDialog{
    private JTextField itemNameField;
    private JTextField itemCountField;
    private JTextField itemPriceField;
    private JLabel itemNameLable;
    private JLabel itemCountLable;
    private JLabel itemPriceLable;
    private JButton createBtn;
    private JButton cancelBtn;
    
    public LineDialogView(InvoiceFrame frame) {
        itemNameField = new JTextField(20);
        itemNameLable = new JLabel("Item Name");
        
        itemCountField = new JTextField(20);
        itemCountLable = new JLabel("Item Count");
        
        itemPriceField = new JTextField(20);
        itemPriceLable = new JLabel("Item Price");
        
        createBtn = new JButton("OK");
        cancelBtn = new JButton("Cancel");
        
        createBtn.setActionCommand("lineCreatedOk");
        cancelBtn.setActionCommand("lineCanceld");
        
        createBtn.addActionListener(frame.getController());
        cancelBtn.addActionListener(frame.getController());
        setLayout(new GridLayout(4, 2));
        
        add(itemNameLable);
        add(itemNameField);
        add(itemCountLable);
        add(itemCountField);
        add(itemPriceLable);
        add(itemPriceField);
        add(createBtn);
        add(cancelBtn);
        
        pack();
    }

    public JTextField getItemNameField() {
        return itemNameField;
    }
    
    public JTextField getItemPriceField() {
        return itemPriceField;
    }

    public JTextField getItemCountField() {
        return itemCountField;
    }

}
