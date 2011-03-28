package circuit;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Pallet extends JApplet implements ActionListener{

    public static final String home = "/home/emma";
    public static final String proj_dir = "/dev/java/compilers";
    protected JTextArea textEditor;
    protected JPanel componentLibrary;
    protected static String[] buttonLabels = {"Language Specification", "Compile", "Reset", "Open", "Save"};
    protected static JButton[] buttons = new JButton[buttonLabels.length];

    public JMenuBar makeMenuBar(){
	JMenuBar menuBar = new JMenuBar();
	for (int i = 0; i<buttons.length; i++){
	    buttons[i]=new JButton(buttonLabels[i]);
	    buttons[i].addActionListener(this);
	    menuBar.add(buttons[i]);
	}
	//override actionlistener for spec (doesn't rely on this class)
	return menuBar;
    }

    public void compile(String text){
	
    }
    
    public void actionPerformed(ActionEvent e){
	if (e.getSource() == buttons[0]){
	    String specs = "", line = "", failure = "";
	    try{
		//defaults to the home directory
		BufferedReader br = new BufferedReader(new FileReader(home+proj_dir+"/langspec.txt"));
		while ((line = br.readLine())!=null)
		    specs+=line+"\n";
		br.close();
	    }catch(Exception ex){
		failure=ex.toString();
	    }
	    JOptionPane.showMessageDialog(new JFrame(),specs.equals("")?failure:specs,"Language Specification", JOptionPane.PLAIN_MESSAGE, null);
	}else if (e.getSource() == buttons[1])
	    compile(textEditor.getText());
	else if (e.getSource() == buttons[2])
	    textEditor.setText("");
	else if (e.getSource() == buttons[3]){
	    JFileChooser openDialog = new JFileChooser(home+proj_dir+"/user");
	    openDialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
	    int returnVal = openDialog.showOpenDialog(new JPanel());
	    if (returnVal == JFileChooser.APPROVE_OPTION){
		File chosenFile = openDialog.getSelectedFile();
		String specs = "", line = "", failure = "";
		try{
		    BufferedReader br = new BufferedReader(new FileReader(chosenFile));
		    while ((line = br.readLine())!=null)
			specs+=line+"\n";
		    br.close();
		    textEditor.setText(specs);
		}catch(Exception ex){
		    JOptionPane.showMessageDialog(new Frame(), ex.toString());
		}
	    }
	}else if (e.getSource() == buttons[4]){
	    JFileChooser openDialog = new JFileChooser(home+proj_dir+"/user");
	    openDialog.setDialogType(JFileChooser.SAVE_DIALOG);
	    openDialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
	    int returnVal = openDialog.showSaveDialog(new JPanel());
	    if (returnVal == JFileChooser.APPROVE_OPTION){
		try{
		    BufferedWriter bw = new BufferedWriter(new FileWriter(openDialog.getSelectedFile()));
		    bw.write(textEditor.getText());
		    bw.close();
		}catch(Exception ex){
		    JOptionPane.showMessageDialog(new Frame(), ex.toString());
		}
	    }
	}
    }

    
    public void init(){
	try{
	    SwingUtilities.invokeAndWait(new Runnable(){
		    public void run(){
			JTabbedPane leftSide = new JTabbedPane();
			textEditor = new JTextArea();	      
			textEditor.setFont(new Font("Monospaced", 1, 14));
			leftSide.addTab("Text Editor", textEditor);
			componentLibrary = new JPanel();
			leftSide.addTab("Component Library", componentLibrary);
			JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT
							      , leftSide
							      , new JPanel());
			splitPane.setOneTouchExpandable(true);
			splitPane.setDividerLocation(384);
			splitPane.setBorder(BorderFactory.createLineBorder(Color.black));
			setContentPane(splitPane);
			setJMenuBar(makeMenuBar());			
		    }
		});
	} catch (Exception e) {
	    System.err.println(e.toString());
	}		
	    
    }

}