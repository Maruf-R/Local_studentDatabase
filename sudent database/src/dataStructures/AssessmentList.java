package dataStructures;

import java.util.ArrayList;

/**
 * Holds a collection of assessment objects
 * @author Maruf Rahman - k1461976*/

public class AssessmentList {
	private ArrayList<Assessment> assessment;
	
	public AssessmentList(){
		assessment = new ArrayList<Assessment>();
	}
	
	/**
	 * @param assessment
	 * Creates a assessment object, and adds it to the assessment ArrayList*/
	public void addElement(String file){
		assessment.add(new Assessment());
		assessment.get(assessment.size()-1).loadResults(file);
	}
	
	/**
	 * De-anonymises the data, replacing anonymous marking codes with
	 * known Students where possible*/
	public SList deAnonymise(SList data){
		for(int i=0; i<data.getSize(); i++){
			for(int j=0; j<getSize(); j++){
				for(int k=0; k<assessment.get(j).getResultsList().size(); k++){
					if(!assessment.get(j).getCandKey(k).contains("/")){
						if(assessment.get(j).getCandKey(k).contains(data.getMarkingCode(i))){
							data.setGrade(i, assessment.get(j).getGrade(k));
							assessment.get(j).replaceMCWithSNumber(k, data.getNumber(i));
							assessment.get(j).setSName(k, data.getName(i));
						}
					}
				}
			}
		}
		return data;
	}
	
	/**
	 * Returns the assessment at specified index*/
	public Assessment getAssessment(int ind){
		return assessment.get(ind);
	}
	
	/**
	 * Returns the assessment number of the assessment at specified index*/
	public String getAssessmentNum(int ind){
		return assessment.get(ind).getAssessment();
	}
	
	/**
	 * Returns the assessment module of the assessment at specified index*/
	public String getAssessmentModName(int ind){
		return assessment.get(ind).getModule();
	}
	
	/**
	 * @return
	 * Returns the size of the list of assessments*/
	public int getSize(){
		return assessment.size();
	}
}
