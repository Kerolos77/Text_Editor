/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Compiler_project;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Objects;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;
import javax.swing.text.Element;
import static Compiler_project.SCANNER.error;

/**
 *
 * @author Abokhadiga
 */

public class Editor extends JFrame implements ActionListener {
    // Text component 
	 public JTextArea t;
         public static JTextArea lines = new JTextArea("1");
        
         public File fi ;
	// Frame 
	JFrame f; 
	// Constructor 
	Editor() 
	{ 
		// Create a frame 
		f = new JFrame("Compiler Project"); 
		// Text component 
		t = new JTextArea(); 

		// Create a menubar 
		JMenuBar mb = new JMenuBar(); 

		// Create amenu for menu 
		JMenu m1 = new JMenu("File"); 

		// Create menu items 
		JMenuItem mi1 = new JMenuItem("New"); 
		JMenuItem mi2 = new JMenuItem("Open"); 
		JMenuItem mi3 = new JMenuItem("Save"); 
		JMenuItem mi9 = new JMenuItem("Print"); 

		// Add action listener 
		mi1.addActionListener(this); 
		mi2.addActionListener(this); 
		mi3.addActionListener(this); 
		mi9.addActionListener(this); 
                

		m1.add(mi1); 
		m1.add(mi2); 
		m1.add(mi3); 
		m1.add(mi9); 

		// Create amenu for menu 
		JMenu m2 = new JMenu("Edit"); 

		// Create menu items 
		JMenuItem mi4 = new JMenuItem("cut"); 
		JMenuItem mi5 = new JMenuItem("copy"); 
		JMenuItem mi6 = new JMenuItem("paste"); 

		// Add action listener 
		mi4.addActionListener(this); 
		mi5.addActionListener(this); 
		mi6.addActionListener(this); 
                

		m2.add(mi4); 
		m2.add(mi5); 
		m2.add(mi6); 

		JMenuItem mc = new JMenuItem("close"); 

		mc.addActionListener(this); 
                
                
                JMenuItem comment = new JMenuItem("Comment");
                comment.addActionListener(this);
                
                JMenuItem Uncomment = new JMenuItem("Uncomment");
                Uncomment.addActionListener(this);
                
                JMenuItem Scan = new JMenuItem("Scan");
                Scan.addActionListener(this);

		mb.add(m1); 
		mb.add(m2);
		mb.add(mc);
                mb.add(Scan);
                mb.add(comment);
                mb.add(Uncomment);
                
      // new 
      JScrollPane jsp = new JScrollPane();
      lines.setBackground(Color.LIGHT_GRAY);
      lines.setEditable(false);
      //  Code to implement line numbers inside the JTextArea
      t.getDocument().addDocumentListener(new DocumentListener() {
         public String getText() {
            int caretPosition = t.getDocument().getLength();
            Element root = t.getDocument().getDefaultRootElement();
            String text = "1" + System.getProperty("line.separator"); // or using printWiter
               for(int i = 2; i < root.getElementIndex(caretPosition) + 2; i++) {
                  text += i + System.getProperty("line.separator");
               }
            return text;
         }
         @Override
         public void changedUpdate(DocumentEvent de) {
            lines.setText(getText());
         }
         @Override
         public void insertUpdate(DocumentEvent de) {
            lines.setText(getText());
         }
         @Override
         public void removeUpdate(DocumentEvent de) {
            lines.setText(getText());
         }
      });
      jsp.getViewport().add(t);
      jsp.setRowHeaderView(lines);
      f.setJMenuBar(mb); 
      f.setSize(800, 800);
      f.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      f.setLocationRelativeTo(this);
      f.setVisible(true);
      f.add(jsp);
	} 

	// If a button is pressed 
	public void actionPerformed(ActionEvent e) 
	{ 
		String s = e.getActionCommand(); 

		if (s.equals("cut")) { 
                    Cut(); 
		} 
		else if (s.equals("copy")) { 
                    Copy();
		} 
		else if (s.equals("paste")) { 
                    Paste(); 
		} 
		else if (s.equals("Save")) { 
                    Save();
		} 
		else if (s.equals("Print")) { 
                    Print();
		} 
		else if (s.equals("Open")) { 
                    Open();
		} 
		else if (s.equals("New")) { 
                    New();
		} 
		else if (s.equals("close")) { 
                    Close();
		}
                else if(s.equals("Comment")){
                    Comment();
                }
                else if(s.equals("Uncomment")){
                    Uncommect();
                }
                else if(s.equals("Scan")){
                    Save();
                    Scan();
                }
	} 

    public void Cut(){
        t.cut();
    }
    public void Copy(){
        t.copy();
    }
    public void Paste(){
        t.paste();
    }
    public void Save(){
        // Create an object of JFileChooser class 
	JFileChooser j = new JFileChooser(""); 
	// Invoke the showsSaveDialog function to show the save dialog 
	int r = j.showSaveDialog(null);
        
	if (r == JFileChooser.APPROVE_OPTION) { 

	// Set the label to the path of the selected directory 
             this.fi = new File(j.getSelectedFile().getAbsolutePath()); 

            try { 
		// Create a file writer 
		FileWriter wr = new FileWriter(fi, false); 

		// Create buffered writer to write 
		BufferedWriter w = new BufferedWriter(wr); 

		// Write
		w.write(t.getText()); 

		w.flush(); 
		w.close(); 
            } 
            catch (Exception evt) { 
		JOptionPane.showMessageDialog(f, evt.getMessage()); 
            } 
	} 
	// If the user cancelled the operation 
	else
            JOptionPane.showMessageDialog(f, "the user cancelled the operation"); 
		
    }
    public void Print(){
        try { 
            // print the file 
            t.print(); 
	} 
	catch (Exception evt) { 
            JOptionPane.showMessageDialog(f, evt.getMessage()); 
	} 
    }
    public void Open(){
        // Create an object of JFileChooser class 
	JFileChooser j = new JFileChooser(""); 
        FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "TXT");
        j.setFileFilter(filter); 

	// Invoke the showsOpenDialog function to show the save dialog 
        int r = j.showOpenDialog(null); 

	// If the user selects a file 
	if (r == JFileChooser.APPROVE_OPTION) { 
            // Set the label to the path of the selected directory 
            this.fi = new File(j.getSelectedFile().getAbsolutePath()); 

            try { 
		// String 
		String s1 = "", sl = ""; 

		// File reader 
		FileReader fr = new FileReader(fi); 

		// Buffered reader 
		BufferedReader br = new BufferedReader(fr); 

		// Initilize sl 
		sl = br.readLine(); 

		// Take the input from the file 
		while ((s1 = br.readLine()) != null) { 
        		sl = sl + "\n" + s1; 
		} 

		// Set the text 
		t.setText(sl); 
            } 
            catch (Exception evt) { 
		JOptionPane.showMessageDialog(f, evt.getMessage()); 
            } 
	} 
	// If the user cancelled the operation 
	else
            JOptionPane.showMessageDialog(f, "the user cancelled the operation"); 
		
    }
    public void New(){
        t.setText("");
    }
    public void Close(){
        System.exit(0);
    }
    
    public void Comment()
    {
        if (t.getSelectedText() != null) 
        { // See if they selected something 
            String s = t.getSelectedText();
            
            String fin = "/@\n" + s + "\n@/";
            t.replaceSelection(fin);
        }
    }
    
    public void Uncommect(){
        String s = t.getSelectedText();
        String fin = s.substring(3,s.length()-3);
        String start,End ; 
        start = s.substring(0,2);
        End =s.substring(s.length()-2,s.length());
        
        if(Objects.equals(start,"/@") && !Objects.equals(End, "@/")){
            String fin2 = s.substring(2);
            fin2 += "\n/@";
            t.replaceSelection(fin2);
        }
        else if(!Objects.equals(start,"/@") && Objects.equals(End, "@/") ){
            String fin2 ="@/\n" + s.substring(0,s.length()-2);
            t.replaceSelection(fin2);
            
        }
        else if(!Objects.equals(start,"/@") && !Objects.equals(End, "@/")){
            String fin2= "@/\n" + s + "\n/@";
            t.replaceSelection(fin2);
        }
        else{
            t.replaceSelection(fin);
        }
        
        
    }
    
    public void Scan()
    {
        try 
            {
                Scanner s = new Scanner(this.fi);
                String source = " ";
                while (s.hasNext()) 
                {
                    source += s.nextLine() + "\n";
                }
                SCANNER l = new SCANNER(source);
                l.printTokens();
            } 
            catch(FileNotFoundException e) 
            {
                error(-1, -1, "Exception: " + e.getMessage(), "not match");
            }
    }
    
}

