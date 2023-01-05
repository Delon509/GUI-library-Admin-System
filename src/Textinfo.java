//Cheung Tin Long 19055971d Intellij
import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class Textinfo extends JPanel {
Textinfo(){
    Date today = new Date();
    JTextArea textArea = new JTextArea(
            "Student Name and ID: Cheung Tin Long (19055971D)\n" +
                    "This is a single person project!\n"
            + today.toString()
    );
    setBackground(Color.white);
    setLayout(new FlowLayout(FlowLayout.LEFT));
    add(textArea);
}
}
