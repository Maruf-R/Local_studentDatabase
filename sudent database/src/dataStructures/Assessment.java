package dataStructures;

import java.awt.Dialog.ModalExclusionType;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import data.Result;

/**
 * Holds a collection of result objects, sorts them in to order after reading the header line of a .csv file
 * 
 *  @author Maruf Rahman - k1461976*/

public class Assessment {
	/** The module*/
	private String _module;
	/** The assessment*/
	private String assessment;
	/** Collection of results*/
	private ArrayList<Result> results;
	/** The lines of the .csv file are stored in this array*/
	private ArrayList<String> file;
	/** The header row of the file*/
	private String[] header;
	//------------------------------Keeps track of header column------------------------------
	private int yearCol;
	private int periodCol;
	private int moduleCol;
	private int occCol;
	private int mapCol;
	private int assCol;
	private int candKeyCol;
	private int nameCol;
	private int cdCol;
	private int markCol;
	private int gradeCol;
	
	public Assessment(){
		header = new String[13];
		file = new ArrayList<String>();
		results = new ArrayList<Result>();
	}
	
	/**
	 * @param results
	 * Creates a result object, and adds it to the results ArrayList*/
	private void addElement(String year, String period, String module, String occ, 
			String map, String ass, String candKey, String name, String cd, String mark, String grade){
		Result temp = new Result(year, period, module, occ, 
				map, ass, candKey, name, cd, mark, grade);
		results.add(temp);
		
		_module = temp.getModule();
		assessment = temp.getAss();
	}
	
	/**
	 * Finds which column holds what data after reading the header line*/
	private void setHeader(){
		header = file.get(0).split(",");
		
		for(int i=0; i<header.length; i++){
			System.out.println(header[i]);	//for debugging purposes
			
			if(header[i].toLowerCase().contains("year")){
				yearCol = i;
			}
			if(header[i].toLowerCase().contains("period")){
				periodCol = i;
			}
			if(header[i].toLowerCase().contains("module")){
				moduleCol = i;
			}
			if(header[i].toLowerCase().contains("occ")){
				occCol = i;
			}
			if(header[i].toLowerCase().contains("map")){
				mapCol = i;
			}
			if(header[i].toLowerCase().contains("ass")){
				assCol = i;
			}
			if(header[i].toLowerCase().contains("cand key")){
				candKeyCol = i;
			}
			if(header[i].toLowerCase().contains("name")){
				nameCol = i;
			}
			if(header[i].toLowerCase().contains("cd")){
				cdCol = i;
			}
			if(header[i].toLowerCase().contains("mark")){
				markCol = i;
			}
			if(header[i].toLowerCase().contains("grade")){
				gradeCol = i;
			}
		}
	}
	
	/**
	 * Loads the results from a file in to a list of lines creates a result object for each line of data*/
	public void loadResults(String selectedFile){
		String[] currLine = new String[header.length];	//temporarily holds the data of the current line
		FileInputStream fileStream = null;
		try {	//try pass the file through to a filestream
			fileStream = new FileInputStream(selectedFile);
		} 
		catch (FileNotFoundException e1) {	//catch exception if file is not found
			e1.printStackTrace();
		}
		
		BufferedReader bReader = new BufferedReader(new InputStreamReader(fileStream));
		String lineReader;

		try {
			while ((lineReader = bReader.readLine()) != null) {
				file.add(lineReader);
				//currLine = lineReader.split(",");
			}
			
			setHeader();	//sets the header list			
			/*addElement(currLine[yearCol], currLine[periodCol], currLine[moduleCol], currLine[occCol], 
					currLine[mapCol], currLine[assCol], currLine[candKeyCol], currLine[nameCol], 
					currLine[cdCol], currLine[markCol], currLine[gradeCol]);*/

			/**
			 * For debugging purposes*/
			for(int i=0; i<file.size(); i++){
				for(int j=0; j<currLine.length; j++){
					currLine = file.get(i).split(",");
					addElement(currLine[yearCol], currLine[periodCol], currLine[moduleCol], currLine[occCol], 
							currLine[mapCol], currLine[assCol], currLine[candKeyCol], currLine[nameCol], 
							currLine[cdCol], currLine[markCol], currLine[gradeCol]);
				}
				System.out.println(file.get(i));
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
	}
	
	/**
	 * Prints all results in results list*/
	public void printAllResults(){
		for(int i=0; i<results.size(); i++){
			System.out.println(results.get(i));
		}
	}
	
	/**
	 * Replaces the marking code with the student number*/
	public void replaceMCWithSNumber(int ind, String SNumber){
		results.get(ind).setSNumber(SNumber);
	}
	
	/**
	 * Sets the students name for the result*/
	public void setSName(int ind, String sName){
		results.get(ind).setName(sName);
	}
	
	/**
	 * @return
	 * Returns the result object at specified index*/
	public String getElementAt(int ind){
		return results.get(ind).toString();
	}
	
	/**
	 * @return
	 * Returns the cand key number stored in the specified result object*/
	public String getCandKey(int ind){
		return results.get(ind).getCandKey();
	}
	
	/**
	 * @return
	 * Returns the grade stored in the specified result object*/
	public String getGrade(int ind){
		return results.get(ind).getGrade();
	}
	
	/**
	 * @returns
	 * Returns the Module name*/
	public String getModule(){
		return _module;
	}
	
	/**
	 * @return
	 * Returns the module assessment*/
	public String getAssessment(){
		return assessment;
	}
	
	/**
	 * @return
	 * Returns the results list itself*/
	public ArrayList<Result> getResultsList(){
		return results; 
	}
}
