//Cheung Tin Long 19055971d Intellij
public class Book {
    private String title=""; // store the title of the book
    private String ISBN=""; // store the ISBN of the book
    private boolean available=true; // keep the status of whether the book is available;
    // initially should be true
    private MyQueue<String> reservedQueue=new MyQueue<>(); // store the queue of waiting list
    private String Path="";
    public void setTitle(String title){
        this.title=title;
    }
    public void setISBN(String ISBN){
        this.ISBN=ISBN;
    }
    public void setAvailable(boolean available){
        this.available=available;
    }
    public void setReservedQueue(MyQueue<String> reservedQueue){
        this.reservedQueue=reservedQueue;
    }
    public String getTitle(){return title;}
    public String getISBN(){return ISBN;}
    public boolean isAvailable(){return  available;}
    public MyQueue<String> getReservedQueue(){return reservedQueue;}
    public void setPath(String Path){
        this.Path=Path;
    }
    public String getPath(){
        return Path;
    }
    public Book(){}
    public Book(String ISBN, String Title){
        this.ISBN=ISBN;
        this.title=Title;
    }
}
