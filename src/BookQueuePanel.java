//Cheung Tin Long 19055971d Intellij
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookQueuePanel extends JPanel implements ActionListener {
    private Book current;
    private JTextArea bookinfo=new JTextArea();
    private JPanel btnPanel;
    private JTextArea systemMessage=new JTextArea();
    private JButton btnBorrow = new JButton("Borrow"),
    btnReturn = new JButton("Return"),
    btnReserve = new JButton("Reserve"),
    btnWQ = new JButton("Waiting Queue");

    public MyQueue<String> getReservedQueue(){return this.current.getReservedQueue();}
    public BookQueuePanel(Book current){
        this.current=current;
        GridLayout gl = new GridLayout(3, 1, 0, 0);
        setLayout(gl);
        bookinfo.setText(getbookinfo());
        add(bookinfo);
        btnPanel = getBtnPanel();
        add(btnPanel);
        systemMessage.setText("Testing");
        add(systemMessage);

    }
    public boolean getAvailable(){
        return this.current.isAvailable();
    }
    public String getbookinfo(){

        String info = "ISBN:" + current.getISBN() +"\n"+
                "Title:"+ current.getTitle() + "\n"+
                "Available:"+ current.isAvailable();


        return info;
    }
    public JPanel getBtnPanel(){
        JPanel temp= new JPanel();
        btnBorrow.addActionListener(this);
        btnBorrow.setEnabled(current.isAvailable());
        temp.add(btnBorrow);

        btnReturn.addActionListener(this);
        btnReturn.setEnabled(!current.isAvailable());
        temp.add(btnReturn);

        btnReserve.addActionListener(this);
        btnReserve.setEnabled(!current.isAvailable());
        temp.add(btnReserve);

        btnWQ.addActionListener(this);
        btnWQ.setEnabled(!current.isAvailable());
        temp.add(btnWQ);
        return temp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == btnBorrow) {
            current.setAvailable(false);
            btnBorrow.setEnabled(current.isAvailable());
            btnReturn.setEnabled(!current.isAvailable());
            btnReserve.setEnabled(!current.isAvailable());
            btnWQ.setEnabled(!current.isAvailable());
            systemMessage.setText("The book is borrowed");
        }
        else if(obj == btnReturn){
            String message= "The book is returned.\n";
            //no reserve
            if(current.getReservedQueue().getSize()==0){
                current.setAvailable(true);
                btnBorrow.setEnabled(current.isAvailable());
                btnReturn.setEnabled(!current.isAvailable());
                btnReserve.setEnabled(!current.isAvailable());
                btnWQ.setEnabled(!current.isAvailable());

            }
            //get new user from queue
            else{
                String name = current.getReservedQueue().dequeue();
                message +="The book is now borrowed by"+name+".";
            }
            systemMessage.setText(message);
        }
        else if(obj == btnReserve){
            String userName = JOptionPane.showInputDialog("What's your name?");
            current.getReservedQueue().enqueue(userName);
            systemMessage.setText("The book is reserved by "+ userName+ ".");
        }
        else if(obj == btnWQ){
            String waitingqueue = "The waiting queue:\n";
            Object[] store = current.getReservedQueue().getList().toArray();
            for(int i =0 ;i<store.length;++i){
                String temp= (String) store[i] +"\n";
                waitingqueue+= temp;
            }
            systemMessage.setText(waitingqueue);
        }
        bookinfo.setText(getbookinfo());

    }
}
