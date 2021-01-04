/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xtext.editor;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;

/**
 *
 * @author Abokhadiga
 */

public class Editor extends JFrame implements ActionListener {
    // Text component 
	 public static JTextArea t;

	// Frame 
	JFrame f; 

	// Constructor 
	Editor() 
	{ 
		// Create a frame 
		f = new JFrame("XTextEditor"); 

		try { 
			// Set metl look and feel 
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel"); 

			// Set theme to ocean 
			MetalLookAndFeel.setCurrentTheme(new OceanTheme()); 
		} 
		catch (Exception e) { 
		} 

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
                
                JMenuItem mf = new JMenuItem("font"); 

		mf.addActionListener(this);

		mb.add(m1); 
		mb.add(m2);
                mb.add(mf);
		mb.add(mc);

		f.setJMenuBar(mb); 
		f.add(t); 
		f.setSize(500, 500);
                f.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                f.setLocationRelativeTo(this);
		f.setVisible(true);
                
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
                else if (s.equals("font")) { 
                    font fs = new font();
                    
                    
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
            File fi = new File(j.getSelectedFile().getAbsolutePath()); 

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
            File fi = new File(j.getSelectedFile().getAbsolutePath()); 

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
}
