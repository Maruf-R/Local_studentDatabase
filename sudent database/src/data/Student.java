package data;

/**
 * Creates a new student object using the students name, number, email and tutor email
 * @author Maruf Rahman - k1461976*/

public class Student {
	private String sName;
	private Integer sNumber;
	private String TAemail;
	private String sEmail;
	private String markingCode;
	private String sGrade;
	
	public Student(String name, Integer number, String TAe, String sE){
		sName = name;
		sNumber = number;
		TAemail = TAe;
		sEmail = sE;
		markingCode = "No record";
		sGrade = "No Grade";
	}
	
	/**
	 * @return
	 * Returns the students name and number*/
	public String toString(){
		return sName + " (" + Integer.toString(sNumber) + ")";
	}
	
	/**
	 * Sets the students marking code*/
	public void setMarkingCode(String mc){
		markingCode = mc;
	}
	
	/**
	 * Sets the students grade*/
	public void setGrade(String grade){
		sGrade = grade;
	}
	
	/**
	 * @return
	 * Returns the students marking code*/
	public String getMarkingCode(){
		return markingCode;
	}
	
	/**
	 * @return
	 * Returns students name*/
	public String getName(){
		return sName;
	}
	
	/**
	 * @return
	 * Returns students student number in string format*/
	public String getNumber(){ 
		return Integer.toString(sNumber);
	}
	
	/**
	 * @return
	 * Returns the students tutors email address*/
	public String getTAEmail(){
		return TAemail;
	}
	
	/**
	 * @return
	 * Returns the students email address*/
	public String getStudEmail(){
		return sEmail;
	}
}