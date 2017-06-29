package dataStructures;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.AbstractListModel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import data.Student;

/**
 * The list which stores all the Student objects
 * @author Maruf Rahman - k1461976*/

public class SList extends AbstractListModel{
	/**Array list which stores Student objects*/
	private ArrayList<Student> _studList;
	private ArrayList<Student> backupList;
	
	public SList(){
		_studList = new ArrayList<Student>();
		backupList = new ArrayList<Student>();
	}

	/**
	 * @param _studList
	 * Creates a student object, adds it to the ArrayList, then updates the list*/
	public void addElement(String name, Integer number, String TAe, String sE){
		Student temp = new Student(name, number, TAe, sE);
		_studList.add(temp);
		backupList.add(temp);
		update();
	}
	
	/**
	 * This updates the list*/
	public void update(){
		this.fireContentsChanged(this, 0, _studList.size()-1);
	}
	
	/**
	 * Returns all the information on the student
	 * @return
	 * @param sName
	 * @param sNumber
	 * @param sEmail
	 * @param TAemail*/
	public String getInformation(int ind){
		return _studList.get(ind).getName() + "\n" + "<html><i>" + _studList.get(ind).getStudEmail()
				+ "\n\n" + "Student No.: " + _studList.get(ind).getNumber()
				+ "\n" + "Tutor:             " + _studList.get(ind).getTAEmail();
	}
	
	/**
	 * Filters students with the corresponding name/number*/
	public void studFilter(String _search, boolean _integer){
		for(int i=0; i<getSize(); i++){
			String temp = _studList.get(i).getName().toLowerCase();	//convert all characters in name to lowercase
			
			/*if the search variable is an integer and students number at index i does not contain
			 	the same values in the search variable, then it is removed from the student list*/
			if(_integer && !_studList.get(i).getNumber().contains(_search)){
				//System.out.println(_studList.get(i).toString());
				_studList.remove(i);
				update();
				i--;
			}
			
			/*if the search variable is not an integer and the students name at index i does not contain
			  	the same values in the search variable, then it is removed from the student list*/
			else if(!_integer && !temp.contains(_search)){
				_search = _search.toLowerCase();	//convert all characters in search to lower case
				//System.out.println(_studList.get(i).toString());
				_studList.remove(i);
				update();
				i--;
			}
		}
	}
	
	/**
	 * Restores the student list from the back up list*/
	public void restoreList(){
		_studList.clear();	//removes all elements from the list
		
		//adds all the elements back to the list from the backup list
		for(int i=0; i<backupList.size(); i++){
			_studList.add(backupList.get(i));
			update();
		}
	}
	
	/**
	 * @return
	 * Loads anonymous marking codes
	 * Checks if the marking codes match any students and either adds it to the student or makes a note*/
	public int[] loadAnonymousMarkingCodes(String selectedFile){
		int lines = 0;
		int[] records = new int[2];
		int unknownRecords = 0;
		int knownRecords = 0;
		FileInputStream fileStream = null;
		try {	//try pass the file through to a filestream
			fileStream = new FileInputStream(selectedFile);
		} 
		catch (FileNotFoundException e1) {	//catch exception if file is not found
			e1.printStackTrace();
		}
		
		restoreList();	//restores the list
		BufferedReader bReader = new BufferedReader(new InputStreamReader(fileStream));
		String lineReader;

		try {
			while ((lineReader = bReader.readLine()) != null) {
				for(int i=0; i<getSize(); i++){
					String currMarkingCode = lineReader.substring(lineReader.indexOf(",") +1);
					if((lineReader.substring(0, lineReader.indexOf(",")).equals(getNumber(i))) && !(getMarkingCode(i).contains(currMarkingCode))){
						setMarkingCode(i, currMarkingCode);
						knownRecords +=1;
						lines +=1;
					}
					
					else{
						unknownRecords = lines - knownRecords;
					}
				}
				records[0] = knownRecords;
				records[1] = unknownRecords;
			}
			
			/**
			 * For debugging purposes*/
			for(int i=0; i<getSize(); i++){
				System.out.println(getElementAt(i) + "   " + getMarkingCode(i));
			}
			
		} 
		catch (IOException e1) {
			e1.printStackTrace();
		}
		
		finally{
			try{
				bReader.close();
			}
			catch(IOException cE){
				cE.printStackTrace();
			}
		}
		return records;
	}
	
	/**
	 * Calls the set marking code method in the student object to set its marking code*/
	public void setMarkingCode(int ind, String mc){
		_studList.get(ind).setMarkingCode(mc);
	}
	
	/**
	 * Calls the set grade method in the student object to set its grade*/
	public void setGrade(int ind, String grade){
		_studList.get(ind).setMarkingCode(grade);
	}
	
	/**
	 * @return
	 * Gets the students marking code*/
	public String getMarkingCode(int ind){
		return _studList.get(ind).getMarkingCode();
	}
	
	/**
	 * @return
	 * Calls the get method from the student object to return the students name*/
	public String getName(int ind){
		return _studList.get(ind).getName();
	}
	
	/**
	 * @return
	 * Calls the get method from the student object to return the students number*/
	public String getNumber(int ind){
		return _studList.get(ind).getNumber();
	}
	
	/**
	 * @return
	 * Returns the Student object with its name and number at specified index*/
	@Override
	public String getElementAt(int ind) {
		return _studList.get(ind).toString();
	}

	/**
	 * @return
	 * Returns the size of the arraylist*/
	@Override
	public int getSize() {
		return _studList.size();
	}
}