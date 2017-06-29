package UI;

import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import data.*;
import dataStructures.Assessment;
import dataStructures.AssessmentList;
import dataStructures.SList;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * This manages the applications window and all activities which go on in it
 * 
 * @author Maruf Rahman - k1461976
 */

public class AppWindow {
	JFrame _window;
	JTextField searchBar; // search bar to search for students by name/student
	JScrollPane scrollBar;
	JList studList;
	SList data;
	JMenuBar menuBar;
	JMenu fileMenu;
	JMenuItem _exit;
	JMenuItem _loadAnonymousMarkingC;
	JMenuItem _loadExamResults;
	int quitCheck;
	JPanel jp_Top;
	JPanel jp_center;
	AssessmentList assessments;

	public AppWindow() {
		widgitInit();
	}

	/**
	 * Initialises all the widgets*/
	private void widgitInit() {
		_window = new JFrame("Application");
		_window.setSize(650, 450);
		quitCheck = 1;
		
		assessments = new AssessmentList();
		
		// adding things to the window
		setMenu();
		setSearchBar();
		setStudList();

		// window layout ----(may move it in to its own method later)----
		_window.setLayout(new BorderLayout());
		_window.add(scrollBar, BorderLayout.WEST);
		jp_Top = new JPanel(new GridLayout(2, 1));
		_window.add(jp_Top, BorderLayout.NORTH);
		jp_Top.add(menuBar); // adding the menu bar to the top of the window
		jp_Top.add(searchBar); // adding the search bar to the top of the window

		_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		_window.setVisible(true);
	}

	/**
	 * This method contains everything for the menu bar and its contents*/
	private void setMenu() {
		menuBar = new JMenuBar();
		_window.setJMenuBar(menuBar);

		fileMenu = new JMenu("File");
		menuBar.add(fileMenu);

		_exit = new JMenuItem("Exit");
		_loadAnonymousMarkingC = new JMenuItem("Load Anonymous Marking Codes");
		_loadExamResults = new JMenuItem("Load Exam Results");

		fileMenu.add(_loadAnonymousMarkingC);
		fileMenu.add(_loadExamResults);
		fileMenu.add(_exit);

		ExitListener(_exit);
		loadAnonymousMarkingListener(_loadAnonymousMarkingC);
		loadExamResultsListener(_loadExamResults);
	}

	/**
	 * This method contains everything for the search bar*/
	private void setSearchBar() {
		searchBar = new JTextField();

		/**
		 * Listens for change in the value in the search bar and calls the
		 * student filter method in the instance of SList*/
		searchBar.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent docE) {
				_filterList();
			}

			@Override
			public void insertUpdate(DocumentEvent docE) {
				_filterList();
			}

			@Override
			public void removeUpdate(DocumentEvent docE) {
				data.restoreList();
				_filterList();
			}

			public void _filterList() {
				try {
					Integer temp = Integer.parseInt(searchBar.getText());
					data.studFilter(searchBar.getText(), true);
				} catch (NumberFormatException E) {
					data.studFilter(searchBar.getText(), false);
				}
			}
		});
	}

	/**
	 * This method contains everything for the list of students*/
	private void setStudList() {
		data = new SList();
		studList = new JList(data);
		scrollBar = new JScrollPane(studList);

		studList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		studList.setLayoutOrientation(JList.VERTICAL);

		ListMouseListener();
	}

	/**
	 * This method calls the necessary methods in the SList instance to add new
	 * student objects and update the list*/
	public void setData(String name, Integer number, String TAe, String sE) {
		data.addElement(name, number, TAe, sE);
		studList = new JList(data);
		scrollBar = new JScrollPane(studList);
	}
	
	
	/**
	 * @return
	 * Creates a file dialogue and returns the directory + file name of the file selected as a string*/
	private String fileDialogW(){
		FileDialog CSVFileOpener = new FileDialog(_window, "CSV file chooser", FileDialog.LOAD);
		CSVFileOpener.setVisible(true);
		
		return CSVFileOpener.getDirectory() + CSVFileOpener.getFile();
	}
	
	
	//---------------------------------------------Action Listeners------------------------------------------------------------
	
	/**
	 * For the file menu exit item to close the programme*/
	private void ExitListener(final JMenuItem exit){
		ActionListener exitListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == exit) {
					quitCheck = JOptionPane.showConfirmDialog(_window,
							"Do you want to quit?", "quit", JOptionPane.YES_NO_OPTION);

					if (quitCheck == 0) {
						_window.dispatchEvent(new WindowEvent(_window, WindowEvent.WINDOW_CLOSING));
					}
				}
			}
		};
		exit.addActionListener(exitListener);
	}
	
	/**
	 * For the file menu load anonymous markings code item*/
	private void loadAnonymousMarkingListener(final JMenuItem loadAnonymousMarkingC){
		/**
		 * Holds all the code regarding the loading of the anonymous marking code*/
		ActionListener loadAMC = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == loadAnonymousMarkingC){
					int[] records;	/*record[0] holds the number of known records returned by the student list,
					 					and record [1] holds the number of unknown records returned by the student list*/
					
					String selectedFile = fileDialogW();	//contains the directory + file name of the file selected
					
					//checks to see if the selected file is a csv file
					if(selectedFile.substring(selectedFile.lastIndexOf(".")+1).equalsIgnoreCase("csv")){
						records = data.loadAnonymousMarkingCodes(selectedFile);
						
						//Message dialog informs the user of how many anonymous marking codes were for known/unknown students
						JOptionPane.showMessageDialog(_window, "Anonymous marking codes imported. " + records[0] + " codes were for known students;\n"
								+ records[1] + " codes were for unknown students.");
					}
					
					//tells the user to select a csv file
					else{
						JOptionPane.showMessageDialog(_window, "Please select a .csv file");
					}
					
				}
			}
		};
		loadAnonymousMarkingC.addActionListener(loadAMC);
	}
	
	/**
	 * For the file menu load exam results item*/
	private void loadExamResultsListener(final JMenuItem loadExamResults){
		ActionListener loadER = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if(e.getSource() == loadExamResults){
					FileInputStream fileStream = null;
					String selectedFile = fileDialogW();	//contains the directory + file name of the file selected
					
					//checks to see if the selected file is a csv file
					if(selectedFile.substring(selectedFile.lastIndexOf(".")+1).equalsIgnoreCase("csv")){
						assessments.addElement(selectedFile);
						data = assessments.deAnonymise(data);
						
						//Message dialog informs the user that the results were loaded successfully
						JOptionPane.showMessageDialog(_window, "Results were loaded");
					}
					
					//tells the user to select a csv file
					else{
						JOptionPane.showMessageDialog(_window, "Please select a .csv file");
					}
				}
			}
		};
		loadExamResults.addActionListener(loadER);
	}
	
	/**
	 * Displays window with student details when clicked on form list*/
	public void ListMouseListener(){
		MouseListener mouseListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent mE) {
				if (mE.getClickCount() == 1) {
					int index = studList.locationToIndex(mE.getPoint());
					System.out.println("clicked on Item " + index);
					JOptionPane.showMessageDialog(_window,
							data.getInformation(index));
				}
			}
		};
		studList.addMouseListener(mouseListener);
	}
}