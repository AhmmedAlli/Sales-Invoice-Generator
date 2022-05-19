package invoiceController;
import invoiceModel.InvoiceHeader;
import invoiceModel.InvoiceLine;
import invoiceModel.InvoiceLineTable;
import invoiceModel.InvoiceHeaderTable;
import invoiceView.InvoiceDialogView;
import invoiceView.InvoiceFrame;
import invoiceView.LineDialogView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ControllerClass implements ActionListener , ListSelectionListener {
    private InvoiceFrame frame ;
    private InvoiceDialogView invoiceDialog;
    private LineDialogView lineDialog;
    
    public ControllerClass (InvoiceFrame frame)
    {
    this.frame = frame;
    }
        
    // Action mehtod to handle buttons 
    @Override
    public void actionPerformed(ActionEvent e) {
        
            String actionCommand = e.getActionCommand();
            ArrayList<InvoiceHeader> invoices = new ArrayList<>();
            switch(actionCommand)
            {
                case "Load File":
                    loadFile();
                    break;
                case "Save File":
                    saveFile();
                    break;
                case "Create New Invoice":
                    createNewInvoice();
                    break;
                case "Delete Invoice":
                    deleteInvoice();
                    break;
                case "Create New Item":
                    createNewItem();
                    break;
                case "Delete Item":
                    deleteItem();
                    break;
                case "invoiceCreatedOk" :   
                    invoiceCreatedOk();
                    break;
                case "invoiceCanceld":   
                    invoiceCanceld();
                    break;
                case "lineCreatedOk":
                    lineCreating();
                    break;
                case "lineCanceld":  
                    lineCanceling();
                    break;

                    
                    
            }
        } 
           
    
    // Method to update data for tables
    @Override
    public void valueChanged(ListSelectionEvent e) {
        int selectedIndex = frame.getInvoiceTable().getSelectedRow();
        if(selectedIndex != -1) {
        System.out.println("you have selected row: " + selectedIndex );
        InvoiceHeader invoiceUse = frame.getInvoices().get(selectedIndex);
        frame.getInvoiceNum().setText(""+invoiceUse.getNumber());
        frame.getInvoiceDate().setText(invoiceUse.getDate());
        frame.getCustomerName().setText(invoiceUse.getName());
        frame.getInvoiceTotalCost().setText(""+invoiceUse.getTotalInvoice());
        
        InvoiceLineTable linesTableModel = new InvoiceLineTable(invoiceUse.getLines());
        frame.getTableLine().setModel(linesTableModel);
        linesTableModel.fireTableDataChanged();
        }
    }
    
    // Method to Load File to update and modify the data 
    private void loadFile() {
        
        JFileChooser FileChooser = new JFileChooser();
        try {
        int result = FileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION)
        {
           File headerFile = FileChooser.getSelectedFile();
           Path headerPath = Paths.get(headerFile.getAbsolutePath());
           List<String> headerLines = Files.readAllLines(headerPath);
           ArrayList<InvoiceHeader> invoicesArray = new ArrayList<>();
           
                for (String lineHeader : headerLines){
                    String [] headerParts = lineHeader.split(",");
                    int invoiceNumber = Integer.parseInt(headerParts[0]);
                    String invoiceDate = headerParts[1];
                    String nameCustomer = headerParts[2];

                    InvoiceHeader invoice = new InvoiceHeader(invoiceNumber, invoiceDate, nameCustomer);
                    invoicesArray.add(invoice);

                }
                
           System.out.println("Check Point");
           result = FileChooser.showOpenDialog(frame);
           if (result == JFileChooser.APPROVE_OPTION){
                File linesInvoice = FileChooser.getSelectedFile();
                Path linesPath = Paths.get(linesInvoice.getAbsolutePath());
                List<String> linesParts = Files.readAllLines(linesPath);
                System.out.println("Lines have been read");
                for (String linesInvoiceSelected : linesParts)
                {
                    String lines [] = linesInvoiceSelected.split(",");
                    int invoiceNumberOfLines = Integer.parseInt(lines[0]);
                    String nameItem = lines[1];
                    double priceItem  = Double.parseDouble(lines[2]);
                    int counter = Integer.parseInt(lines[3]);
                    InvoiceHeader invo = null;
                    for (InvoiceHeader invoice : invoicesArray){
                     if (invoice.getNumber() == invoiceNumberOfLines){

                         invo = invoice;
                         break;

                     }
                  }        
                  InvoiceLine lineInvoiceLines = new InvoiceLine(nameItem, priceItem, counter, invo);
                  invo.getLines().add(lineInvoiceLines);      
            }
               System.out.println("hello");
        }     
                frame.setInvoices(invoicesArray);
                InvoiceHeaderTable invoiceTable = new InvoiceHeaderTable(invoicesArray);
                frame.setInvoiceTableModel(invoiceTable);
                frame.getInvoiceTable().setModel(invoiceTable);
                frame.getInvoiceTableModel().fireTableDataChanged();
                       
        }
     } catch (IOException e){
         e.printStackTrace();
        }
   }

    // Method To save the updated file when it modified and edited **
    private void saveFile() {
            
        ArrayList<InvoiceHeader> invoices = frame.getInvoices();
        String invoiceHeader = "";
        String invoiceLines = "";
            for (InvoiceHeader invoice : invoices){
                invoiceHeader += invoice.getFileCSV();
                invoiceHeader += "\n";

                for (InvoiceLine line : invoice.getLines()){
                     String lineCSV = line.getFileCSV();
                     invoiceLines += lineCSV ;
                     invoiceLines += "\n"; 
              }
          }
        System.out.println("Say Hi");
        try {
            
            JFileChooser fc = new JFileChooser(); 
            int result = fc.showSaveDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION){ 
                    File headerFileInvoice = fc.getSelectedFile(); 
                    FileWriter  writeFile = new FileWriter(headerFileInvoice); 
                    writeFile.write(invoiceHeader); 
                    writeFile.flush(); 
                    writeFile.close();
                    result = fc.showSaveDialog(frame);
                    
                        if(result == JFileChooser.APPROVE_OPTION){
                            File invoicesLine = fc.getSelectedFile(); 
                            FileWriter writeFileLine = new FileWriter(invoicesLine);
                            writeFileLine.write(invoiceLines); 
                            writeFileLine.flush(); 
                            writeFileLine.close(); 

                    }
            }          
        } catch (Exception e) {
            }
    }
    
    //Method for creating dialog to make user to create new invoice
    private void createNewInvoice() {
                invoiceDialog = new InvoiceDialogView(frame);
                invoiceDialog.setVisible(true);
                System.out.println("hi ahmed");
          }

    //Method for deleting an invoice
    private void deleteInvoice() {
        int selectedRow = frame.getInvoiceTable().getSelectedRow();
            if (selectedRow != -1 ){
                frame.getInvoices().remove(selectedRow);
                frame.getInvoiceTableModel().fireTableDataChanged();
            }
         }

     //Method for show adding an item to an invoice dialog
    private void createNewItem() {
            lineDialog = new LineDialogView(frame);
            lineDialog.setVisible(true);

          }

      //Method for deleting an item to an invoice
    private void deleteItem() {
        int selectedInvoice = frame.getInvoiceTable().getSelectedRow();
        int selectedLine = frame.getTableLine().getSelectedRow();
        
            if (selectedInvoice != -1 && selectedLine != -1 ){
                InvoiceHeader invoice = frame.getInvoices().get(selectedInvoice);
                invoice.getLines().remove(selectedLine);
                InvoiceLineTable invoiceLine = new InvoiceLineTable(invoice.getLines());
                frame.getTableLine().setModel(invoiceLine);
                invoiceLine.fireTableDataChanged();           
                frame.getInvoiceTableModel().fireTableDataChanged(); 
            }
         }
  //Method for adding process an item to an invoice
    private void invoiceCreatedOk() {
        String date = invoiceDialog.getInvDateField().getText();
        String customerName = invoiceDialog.getCustNameField().getText();
        int number = frame.getNextInvoiceNumber();
        
        InvoiceHeader newInvoice  = new InvoiceHeader(number, date, customerName);
        frame.getInvoices().add(newInvoice);
        frame.getInvoiceTableModel().fireTableDataChanged();
        invoiceDialog.setVisible(false);
        invoiceDialog.dispose();
        invoiceDialog = null ;

        
    }
// Method to close the creating invoice dialog
    private void invoiceCanceld() {
            invoiceDialog.setVisible(false);
            invoiceDialog.dispose();
            invoiceDialog = null ;
    }
    
// Method to create new item to the invoice
    private void lineCreating() {
        String itemName = lineDialog.getItemNameField().getText();
        String countItem = lineDialog.getItemCountField().getText();
        String itemPrice = lineDialog.getItemPriceField().getText();
        
        int count = Integer.parseInt(countItem);
        double price = Double.parseDouble(itemPrice);
        int selectedInvoiceNumber = frame.getInvoiceTable().getSelectedRow();
            if (selectedInvoiceNumber != -1) {
                    InvoiceHeader invoice = frame.getInvoices().get(selectedInvoiceNumber);
                    InvoiceLine line = new InvoiceLine(itemName, price, count, invoice);
                    InvoiceLineTable lineTable = (InvoiceLineTable) frame.getTableLine().getModel();
                    lineTable.getLines().add(line);
                    lineTable.fireTableDataChanged();
                    frame.getInvoiceTableModel().fireTableDataChanged();

        }
        
        lineDialog.setVisible(false);
        lineDialog.dispose();
        lineDialog = null ;
   
    }

    // Method to close adding item to invoice dialog
    private void lineCanceling() {
        lineDialog.setVisible(false);
        lineDialog.dispose();
        lineDialog = null ;
              
    }




}