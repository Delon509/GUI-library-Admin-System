//Cheung Tin Long 19055971d
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Input extends JPanel implements  ActionListener{
    private BookMainManuel bookMainManuel;
    private MyLinkedList<Book> bookstore= new MyLinkedList<>();
    private String SelectedISBN="", SelectedTitle="";
    private boolean reverseDAISBN=false, reverseDATitle=false;
    private int SelectedIndex=-1;
    private Mytable mytable;
    private Label lblISBN, lblTitle;
    private TextField tfISBN, tfTitle;
    private JButton btnAdd = new JButton("Add"),
            btnEdit= new JButton("Edit"),
            btnSave= new JButton("Save"),
            btnDelete= new JButton("Delete"),
            btnSearch= new JButton("Search"),
            btnMore= new JButton("More>>"),
            btnLoad= new JButton("Load Test Data"),
            btnDisplayAll= new JButton("Display All"),
            btnDAISBN= new JButton("Display All by ISBN"),
            btnDATitle= new JButton("Display All by Title"),
            btnExit= new JButton("Exit"),
            btnLoadDB= new JButton("Load From Database");
    public TextField getTfISBN(){
        return tfISBN;
    }
    public TextField getTfTitle(){
        return tfTitle;
    }
    public Input(Mytable mytable, BookMainManuel bookMainManuel){
        this.bookMainManuel=bookMainManuel;
        this.mytable=mytable;
        GridLayout gl = new GridLayout(3, 1, 20, 20);
        setLayout(gl);
        JPanel row1 = new JPanel();

        lblISBN = new Label("ISBN: ");
        row1.add(lblISBN);
        tfISBN = new TextField("",10);
        tfISBN.setEditable(true);
        row1.add(tfISBN);

        lblTitle = new Label("Title: ");
        row1.add(lblTitle);
        tfTitle = new TextField("",10);
        tfTitle.setEditable(true);
        row1.add(tfTitle);
        JPanel row2 = new JPanel();
        btnAdd.addActionListener(this);
        row2.add(btnAdd);

        btnEdit.addActionListener(this);
        row2.add(btnEdit);
        btnSave.addActionListener(this);
        btnSave.setEnabled(false);
        row2.add(btnSave);
        btnDelete.addActionListener(this);
        row2.add(btnDelete);
        btnSearch.addActionListener(this);
        row2.add(btnSearch);
        btnMore.addActionListener(this);
        row2.add(btnMore);
        btnLoadDB.addActionListener(this);
        row2.add(btnLoadDB);
        JPanel row3 = new JPanel();
        btnLoad.addActionListener(this);
        row3.add(btnLoad);
        btnDisplayAll.addActionListener(this);
        row3.add(btnDisplayAll);
        btnDAISBN.addActionListener(this);
        row3.add(btnDAISBN);
        btnDATitle.addActionListener(this);
        row3.add(btnDATitle);
        btnExit.addActionListener(this);
        row3.add(btnExit);
        add(row1);
        add(row2);
        add(row3);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == btnAdd) {

            if(tfISBN.getText().length()==0 || tfTitle.getText().length()==0){
                JOptionPane.showMessageDialog(null,
                        "Can't empty ISBN / Title");
            }
            else {
                String Title= tfTitle.getText();
                String ISBN = tfISBN.getText();
                int index= indexofISBN(ISBN);
                //add successful
                if(index==-1){
                    add(ISBN
                            ,Title);
                }
                else{
                    JOptionPane.showMessageDialog(null,
                            Title+" Can't Use same ISBN");
                }
            }

        }else if(obj == btnLoad){
            int index= indexofISBN("0131450913");
            //add successful
            if(index==-1){
                add("0131450913"
                        ,"HTML How to Program");
            }
            else{
                JOptionPane.showMessageDialog(null,
                        "HTML How to Program Can't Use same ISBN");
            }
            //System.out.println("After HTML");
            index= indexofISBN("0131857576");
            //add successful
            if(index==-1){
                add( "0131857576","C++ How to Program");
            }
            else{
                JOptionPane.showMessageDialog(null,
                        "C++ How to Program Can't Use same ISBN");
            }
            //System.out.println("After C++");
            index= indexofISBN("0132222205");
            //add successful
            if(index==-1){
                add("0132222205","Java How to Program");
            }
            else{
                JOptionPane.showMessageDialog(null,
                        "Java How to Program Can't Use same ISBN");
            }
            //System.out.println("After JAVA");
            //System.out.println("bookstore size is"+bookstore.size);
        }
        else if( obj== btnEdit){
            SelectedISBN= tfISBN.getText();
            SelectedTitle=tfTitle.getText();
            SelectedIndex=indexofISBN(SelectedISBN);
            if(SelectedISBN.length()==0 ){
                JOptionPane.showMessageDialog(null,
                        "Can't empty the ISBN for editing function");
            }

            else if(bookstore.size()==0){
                JOptionPane.showMessageDialog(null,
                        "The bookstore is empty now");
            }
            else if ( SelectedIndex== -1){
                JOptionPane.showMessageDialog(null,
                        "Error: book ISBN doesn't exist in the database");
            }
            else{
                if(SelectedTitle.length()==0){
                    SelectedTitle=getBook(SelectedIndex).getTitle();
                    tfTitle.setText(SelectedTitle);
                }
                btnSave.setEnabled(true);
                btnAdd.setEnabled(false);
                btnEdit.setEnabled(false);
                btnDelete.setEnabled(false);
                btnSearch.setEnabled(false);
                btnMore.setEnabled(false);
                btnLoad.setEnabled(false);
                btnDisplayAll.setEnabled(false);
                btnDAISBN.setEnabled(false);
                btnDATitle.setEnabled(false);
                btnExit.setEnabled(false);
                btnLoadDB.setEnabled(false);
            }
        }
        else if(obj == btnSave){
            String editedISBN= tfISBN.getText();
            String editedTitle= tfTitle.getText();
            //Change new ISBN
            if(!editedISBN.equals(SelectedISBN) &&indexofISBN(editedISBN)!= -1){
                    JOptionPane.showMessageDialog(null,
                            "Error: book ISBN exist in the database");
            }
            else{
                Book current= bookstore.get(SelectedIndex);
                current.setISBN(editedISBN);
                current.setTitle(editedTitle);
                bookstore.set(SelectedIndex,current);
                tfTitle.setText("");
                tfISBN.setText("");
                Object[][] data = transfer(bookstore.toArray());
                mytable.update(data);
                btnSave.setEnabled(false);
                btnAdd.setEnabled(true);
                btnEdit.setEnabled(true);
                btnDelete.setEnabled(true);
                btnSearch.setEnabled(true);
                btnMore.setEnabled(true);
                btnLoad.setEnabled(true);
                btnDisplayAll.setEnabled(true);
                btnDAISBN.setEnabled(true);
                btnDATitle.setEnabled(true);
                btnExit.setEnabled(true);
                btnLoadDB.setEnabled(true);
            }
        }
        else if(obj == btnDelete){
            SelectedISBN= tfISBN.getText();
            SelectedTitle=tfTitle.getText();
            SelectedIndex=indexofISBN(SelectedISBN);
            if(SelectedISBN.length()==0 ){
                JOptionPane.showMessageDialog(null,
                        "Can't empty the ISBN for delete function");
            }

            else if(bookstore.size()==0){
                JOptionPane.showMessageDialog(null,
                        "The database is empty now");
            }
            else if ( SelectedIndex== -1){
                JOptionPane.showMessageDialog(null,
                        "Error: book ISBN doesn't exist in the database");
            }
            else{
                bookstore.remove(SelectedIndex);
                tfTitle.setText("");
                tfISBN.setText("");
                Object[][] data = transfer(bookstore.toArray());
                mytable.update(data);
            }
            //System.out.println("bookstore size is"+bookstore.size);
        }
        else if(obj == btnSearch){
            SelectedISBN= tfISBN.getText();
            SelectedTitle=tfTitle.getText();
            if(SelectedISBN.length()==0 && SelectedTitle.length()==0){
                JOptionPane.showMessageDialog(null,
                        "Error: Can't empty ISBN and Title in search function");
            }
            else{
                MyLinkedList<Book> result = new MyLinkedList<>();
                boolean searchISBN=SelectedISBN.length()>0, searchTitle=SelectedTitle.length()>0;
                Pattern patternISBN = Pattern.compile(SelectedISBN, Pattern.CASE_INSENSITIVE);
                Pattern patternTitle = Pattern.compile(SelectedTitle, Pattern.CASE_INSENSITIVE);
                Object[] temp = bookstore.toArray();
                for(int i=0;i< temp.length;++i){
                    Book current = (Book) temp[i];
                    boolean matchISBN =false, matchTitle= false;
                    Matcher matcherISBN = patternISBN.matcher(current.getISBN());
                    Matcher matcherTitle = patternTitle.matcher(current.getTitle());
                    matchISBN = searchISBN && matcherISBN.find();
                    matchTitle= searchTitle && matcherTitle.find();
                    if(matchISBN || matchTitle){
                        result.add(current);
                    }
                }
                tfTitle.setText("");
                tfISBN.setText("");
                Object[][] data = transfer(result.toArray());
                mytable.update(data);
            }
        }
        else if(obj == btnDisplayAll){
            Object[][] data = transfer(bookstore.toArray());
            mytable.update(data);
        }
        else if(obj == btnDAISBN){
            Book[] result=ObjecttoBook(bookstore.toArray());
            if(reverseDAISBN){


                Arrays.sort( result, Comparator.comparing(Book:: getISBN).reversed());

            }
            else{
                Arrays.sort( result, Comparator.comparing(Book:: getISBN));
                reverseDAISBN=true;
            }
            Object[][] data = transfer(result);
            mytable.update(data);
        }
        else if(obj == btnDATitle){
            Book[] result=ObjecttoBook(bookstore.toArray());
            if(reverseDATitle){


                Arrays.sort( result, Comparator.comparing(Book:: getTitle).reversed());

            }
            else{
                Arrays.sort( result, Comparator.comparing(Book:: getTitle));
                reverseDATitle=true;
            }
            Object[][] data = transfer(result);
            mytable.update(data);
        }
        else if(obj== btnMore){
            SelectedISBN= tfISBN.getText();
            SelectedIndex=indexofISBN(SelectedISBN);

            if(SelectedISBN.length()==0){
                JOptionPane.showMessageDialog(null,
                        "Error: Can't empty ISBN  in more function");
            }
            else if(SelectedIndex==-1){
                JOptionPane.showMessageDialog(null,
                        "Error: Can't find that books");
            }
            else{
                SelectedTitle=getBook(SelectedIndex).getTitle();
                bookMainManuel.setVisible(false);
                SpecificBookManuel specificBookManuel= new SpecificBookManuel(bookstore,SelectedISBN,SelectedTitle,SelectedIndex);
            }

        }
        else if(obj == btnLoadDB){
            try {
                bookstore= load();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            Object[][] data = transfer(bookstore.toArray());
            mytable.update(data);
        }
        else if (obj == btnExit) {
            try {
                bookMainManuel.save();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.exit(0);
        }

    }
    public Book[] ObjecttoBook(Object[] temp){
        Book[] result = new Book[temp.length];
        for(int i=0;i<temp.length;++i){
            Book current = (Book) temp[i];
            result[i]=current;
        }
        return result;
    }
    public Object[][] transfer(Object[] temp){
        Object[][] result = new Object[temp.length][3];
        for(int i=0;i< temp.length;++i){
            Book current = (Book) temp[i];
            result[i][0] = current.getISBN();
            result[i][1] = current.getTitle();
            result[i][2] = current.isAvailable();
        }
        return result;
    }
    public int indexofISBN(String ISBN){
        Object[] temp = bookstore.toArray();
        int index= -1;
        for(int i=0;i< temp.length;++i){
            Book current = (Book) temp[i];
            if(current.getISBN().equals(ISBN)){
                index=i;
                break;
            }
        }
        return index;
    }
    public Book getBook(int index){
        Object[] temp = bookstore.toArray();
        return (Book) temp[index];
    }
    public void add(String ISBN, String Title){
        //System.out.println("Before add function in Input,the size of bookstore is"+bookstore.size);
            tfTitle.setText("");
            tfISBN.setText("");
            bookstore.add(new Book(ISBN,Title));
            Object[][] data = transfer(bookstore.toArray());
            mytable.update(data);
            //System.out.println("After add function in Input,the size of bookstore is"+bookstore.size);
            }
    public MyLinkedList<Book> getBookstore(){
        return  bookstore;
    }
    public MyLinkedList<Book> load() throws IOException {
        MyLinkedList<Book> result = new MyLinkedList<>();
        File database = new File("C:\\library\\database\\store.txt");
        if(database.exists()){
            BufferedReader reader = new BufferedReader(new FileReader(database));
            String line;
            while ((line = reader.readLine()) != null) {
                //System.out.println("Loading from database:"+line);
                Book temp = new Book();
                String[] data = line.split("`!");
                //System.out.println("data 0:"+ data[0]);
                temp.setISBN(data[0]);
                //System.out.println("data 1:"+ data[1]);
                temp.setTitle(data[1]);
                //System.out.println("data 2:"+ data[2]);
                temp.setAvailable(data[2].equals("1"));
                //System.out.println("data 3:"+ data[3]);
                String[] queue = data[3].split(",");
                MyQueue<String> myQueue = new MyQueue<>();
                if(!(queue.length==1 && queue[0].equals("e"))){
                 for(String names : queue){
                     myQueue.enqueue(names);
                 }
                }
                temp.setReservedQueue(myQueue);
                //System.out.println("data 4:"+ data[4]);
                if(!(data[4].equals("e"))){
                    temp.setPath(data[4]);
                }
                result.add(temp);
            }

            reader.close();
            return result;
        }else {
            File photo = new File("C:\\library\\static");
            File db= new File("C:\\library\\database");
            db.mkdirs();
            photo.mkdirs();
            database.createNewFile();
            JOptionPane.showMessageDialog(null,
                    "Error: The database doesn't exist in your computer," +
                            "Now is recreating ...");


            return bookstore;
        }

    }
}
