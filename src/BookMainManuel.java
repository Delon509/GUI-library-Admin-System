//Cheung Tin Long 19055971d Intellij
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BookMainManuel extends Frame {

    private Textinfo textinfo = new Textinfo();
    private Mytable myTable = new Mytable();
    private Input input;
    public BookMainManuel() {
        GridLayout gl = new GridLayout(3, 1, 0, 0);
        setLayout(gl);
        setTitle("Library Admin System");
        setSize(900, 800);
        add(textinfo);
        add(myTable);
        input = new Input(myTable, this);
        add(input);
        myTable.setISBN(input.getTfISBN());
        myTable.setTitle(input.getTfTitle());
        setVisible(true);
        addWindowListener(new CloseHandler());
    }


    public static void main(String[] args) {
        BookMainManuel app = new BookMainManuel();

    }
    //Closing by clicking x button
    protected  class CloseHandler extends WindowAdapter {
        public void windowClosing(final WindowEvent event) {
            try {
                save();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.exit(0);
        }
    }
    public void save() throws IOException {
        File libraray = new File("C:\\library");
        if(!libraray.exists()){
            File database = new File("C:\\library\\database");
            File photo = new File("C:\\library\\static");
            database.mkdirs();
            photo.mkdirs();
        }
        BufferedWriter bufw = new BufferedWriter(new FileWriter(new File("C:\\library\\database\\store.txt")));
        Object[] temp=input.getBookstore().toArray();
        for(int i=0;i<temp.length;++i){
            Book book= (Book) temp[i];
            StringBuilder str= new StringBuilder();
            str.append(book.getISBN());
            str.append("`!");

            str.append(book.getTitle());
            str.append("`!");

            if(book.isAvailable()){
                str.append("1");
            }
            else {
                str.append("0");
            }
            str.append("`!");

            Object[] queue = book.getReservedQueue().getList().toArray();
            if(queue.length==0){
                str.append("e");
                str.append("`!");
            }
            else {
                for(int j=0;j<queue.length;++j){
                    String names= (String) queue[j];
                    str.append(names);
                    if(j != (queue.length-1)){
                        str.append(",");
                    }
                    else{
                        str.append("`!");
                    }
                }
            }
            if(book.getPath().length()==0){
                str.append("e");
            }
            else {
                str.append(book.getPath());
            }

            bufw.write(str.toString());
            bufw.newLine();
        }
        bufw.close();
    }
}
