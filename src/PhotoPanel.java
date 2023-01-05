//Cheung Tin Long 19055971d Intellij
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PhotoPanel extends JPanel  {

    JButton loadpic = new JButton("Load Picture");
    JLabel picLabel = new JLabel();
    private Book current;
    public PhotoPanel(Book current){
        this.current=current;
        add(loadpic);
        File tempFile = new File(current.getPath());
        if(tempFile.exists()){
            //System.out.println("Getting photo based on db");
            try {
                BufferedImage picture = ImageIO.read(tempFile);
                picLabel.setIcon(new ImageIcon(picture));
                current.setPath(tempFile.getAbsolutePath());

            }catch (IOException ioe) {
                ioe.printStackTrace();
                JOptionPane.showMessageDialog(null, "ERROR");
            }
        }
        else{
            //System.out.println("Cant find photo");
        }
        loadpic.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION)
                {
                    File selectedFile = fileChooser.getSelectedFile();
                    //System.out.println(selectedFile.getAbsolutePath());
                    try {
                        BufferedImage picture = ImageIO.read(selectedFile);
                        picLabel.setIcon(new ImageIcon(picture));
                        add(picLabel);
                        current.setPath(selectedFile.getAbsolutePath());

                    }catch (IOException ioe) {
                        ioe.printStackTrace();
                        JOptionPane.showMessageDialog(null, "ERROR");
                    }

                }
            }
        });
        setVisible(true);
        add(picLabel);

    }
    public Book getCurrent(){
        return current;
    }


}
