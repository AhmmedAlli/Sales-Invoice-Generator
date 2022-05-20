
package invoiceView;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;



public class InvoiceDialogView extends JDialog {
    private JTextField customerNameField;
    private JTextField invoiceDataField;
    private JLabel customerNameLable;
    private JLabel invoiceDataLable;
    private JButton createBtn;
    private JButton cancelBtn;


    public InvoiceDialogView(InvoiceFrame frame) {
        customerNameLable = new JLabel("Customer Name:");
        customerNameField = new JTextField(20);
        invoiceDataLable = new JLabel("Invoice Date:");
        invoiceDataField = new JTextField(20);
        createBtn = new JButton("OK");
        cancelBtn = new JButton("Cancel");
        
        
        // set acction command text
        createBtn.setActionCommand("invoiceCreatedOk");
        cancelBtn.setActionCommand("invoiceCanceld");
        
        // set color to buttons
        createBtn.setBackground(Color.green);
        cancelBtn.setBackground(Color.red);
        
        // For Know how form of enter date
        invoiceDataField.setText("10-01-2010");
        
         
  
        
        
        // but controller as a acction listener 
        createBtn.addActionListener(frame.getController());
        cancelBtn.addActionListener(frame.getController());
        setLayout(new GridLayout(3, 2));
        
        add(invoiceDataLable);
        add(invoiceDataField);
        add(customerNameLable);
        add(customerNameField);
        add(createBtn);
        add(cancelBtn);
        
        pack();
        
    }
    
      public JTextField getInvoiceDataField() {
        return invoiceDataField;
    }

    public JTextField getCustomerNameField() {
        return customerNameField;
    }

  
    
}
