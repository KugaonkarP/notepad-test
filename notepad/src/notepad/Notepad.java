

package notepad;
    
import java.awt.Font;
import javax.swing.*;  
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Notepad extends JFrame implements ActionListener{
    JMenuBar menubar= new JMenuBar();
    JMenu file=new JMenu("file");
    JMenu edit=new JMenu("edit");
    JMenu close=new JMenu("close");
    JMenu help=new JMenu("help");
    
    JMenuItem newFile=new JMenuItem("new");
    JMenuItem openFile=new JMenuItem("open");
    JMenuItem saveFile=new JMenuItem("save");
    JMenuItem closeFile=new JMenuItem("close");
    
    JTextArea textarea=new JTextArea();
     Notepad(){
    setTitle("First application");
    setBounds(200,200,400,400);   
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    setLayout(null);
    setJMenuBar(menubar);
    menubar.add(file);
    menubar.add(edit);
    menubar.add(close);
    menubar.add(help);
    
    file.add(newFile);
    file.add(openFile);
    file.add(closeFile);
    file.add(saveFile);
    
    JScrollPane scroll=new JScrollPane(textarea);
    add(scroll);
    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    
    textarea.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,16));
    textarea.setLineWrap(true);
    textarea.setWrapStyleWord(true);
    
    newFile.addActionListener(this);
    openFile.addActionListener(this);
    closeFile.addActionListener(this);
    saveFile.addActionListener(this);
    
    }

    
    /**
     * @param args the command line arguments
     */
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("New")) {
            textarea.setText(null);
        }else if (e.getActionCommand().equalsIgnoreCase("Open")) {
            JFileChooser fileChooser=new JFileChooser();
            FileNameExtensionFilter textFilter=new FileNameExtensionFilter("only txt file", "txt");
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(textFilter);
            
            int action=fileChooser.showOpenDialog(null);
            if (action!=JFileChooser.APPROVE_OPTION) {
                return;
            }else{
               try{
                    BufferedReader reader=new BufferedReader(new FileReader(fileChooser.getSelectedFile()));
                    textarea.read(reader,null);
                }catch(IOException ex){
                    ex.printStackTrace();
                } 
            }
            
            
        }else if (e.getActionCommand().equalsIgnoreCase("Save")) {
            JFileChooser fileChooser=new JFileChooser();
            FileNameExtensionFilter textFilter=new FileNameExtensionFilter("only txt file", "txt");
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(textFilter);
            
            int action=fileChooser.showSaveDialog(null);
            
            if (action!=JFileChooser.APPROVE_OPTION) {
                return; 
            }else{
                String filename=fileChooser.getSelectedFile().getAbsolutePath().toString();
                if (!filename.contains(".txt")) {
                    filename=filename+".txt";
                     
                try{
                    BufferedWriter writer=new BufferedWriter(new FileWriter(filename));
                    textarea.write(writer);
                }catch(IOException ex){
                    ex.printStackTrace();
                }
                }
            }
            
            
        }else if (e.getActionCommand().equalsIgnoreCase("close")) {
            
        }
    
    }
    
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
       
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        
        new Notepad().setVisible(true);
        
    }
    
}
