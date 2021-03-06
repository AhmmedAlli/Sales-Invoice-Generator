package invoiceView;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

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
        itemNameLable = new JLabel("Item_Name");
        
        itemCountField = new JTextField(20);
        itemCountLable = new JLabel("Item_Count");
        
        itemPriceField = new JTextField(20);
        itemPriceLable = new JLabel("Item_Price");
        
        createBtn = new JButton("OK");
        cancelBtn = new JButton("Cancel");
        
        createBtn.setBackground(Color.green);
        cancelBtn.setBackground(Color.red);
        
        
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
