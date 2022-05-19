
package invoiceModel;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class InvoiceLineTable extends AbstractTableModel{

    private ArrayList<InvoiceLine> lines ;
    String [] columnLines = {"No.","Item Name","Item Price","Count","Item Total"};

    public InvoiceLineTable(ArrayList<InvoiceLine> lines) {
        this.lines = lines;
    }

       
    @Override
    public int getRowCount() {
        return lines.size();
         }

    @Override
    public int getColumnCount() {
    return columnLines.length;
    }

    @Override
    public String getColumnName(int column) {
      return columnLines[column];      
    }

    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    
        InvoiceLine line = lines.get(rowIndex);
        switch(columnIndex)
        {
            case 0: return line.getInvoice().getNumber();
            case 1: return line.getItemName();
            case 2: return line.getPrice();
            case 3: return line.getCount();
            case 4: return line.getTotalLines();
            default: return "";
        }
        
        
    }

    public ArrayList<InvoiceLine> getLines() {
        return lines;
    }
    
    
    
}
