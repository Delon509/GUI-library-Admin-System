//Cheung Tin Long 19055971d Intellij
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;


public class Mytable extends JScrollPane  {
    private JTable table;
    private TextField tfISBN, tfTitle;
    public void setISBN(TextField tfISBN){
        this.tfISBN=tfISBN;
    }
    public void setTitle(TextField tfTitle){
        this.tfTitle=tfTitle;
    }
    public void update(Object[][] data){
        String[] columnNames = {"ISBN","Title", "Available"
        };
        table= new JTable(data,columnNames);
        ListSelectionModel cellSelectionModel = table.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                int[] selectedRow = table.getSelectedRows();

                tfISBN.setText((String) table.getValueAt(selectedRow[0],0));
                tfTitle.setText((String) table.getValueAt(selectedRow[0],1));
            }

        });
        setViewportView(table);
    }
    public Mytable(){
        String[] columnNames = {"ISBN","Title", "Available"
                };
        Object[][] data = {

        };
        table= new JTable(data,columnNames);
        ListSelectionModel cellSelectionModel = table.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                int[] selectedRow = table.getSelectedRows();

                tfISBN.setText((String) table.getValueAt(selectedRow[0],0));
                tfTitle.setText((String) table.getValueAt(selectedRow[0],1));
            }

        });
        setViewportView(table);
    }

}
