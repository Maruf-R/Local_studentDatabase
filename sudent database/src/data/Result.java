package data;

/**
 * This holds the data in a single line from a results file
 * 
 * @author Maruf Rahman - k1461976*/

public class Result {
	private String yearCol;
	private String periodCol;
	private String moduleCol;
	private String occCol;
	private String mapCol;
	private String assCol;
	private String candKeyCol;
	private String nameCol;
	private String cdCol;
	private String markCol;
	private String gradeCol;
	private String studName;

	public Result(String _yearCol, String _periodCol, String _moduleCol, String _occCol, 
			String _mapCol, String _assCol, String _candKeyCol, String _nameCol, String _cdCol, 
			String _markCol, String _gradeCol){
		yearCol= _yearCol;
		periodCol = _periodCol;
		moduleCol = _moduleCol;
		occCol = _occCol;
		mapCol = _mapCol;
		assCol = _assCol;
		candKeyCol = _candKeyCol;
		nameCol = _nameCol;
		cdCol = _cdCol;
		markCol = _markCol;
		gradeCol = _gradeCol;
	}

	/**
	 * This method is used to set a student number in to the cand key field*/
	public void setSNumber(String sNumber){
		candKeyCol = sNumber;
	}
	
	/**
	 * This method is used to set the student name*/
	public void setName(String sName){
		studName = sName;
	}
	
	/**
	 * @return
	 * Returns the year
	 */
	public String getYear() {
		return yearCol;
	}

	/**
	 * @return
	 * Returns the period
	 */
	public String getPeriod() {
		return periodCol;
	}

	/**
	 * @return
	 * Returns the module code
	 */
	public String getModule() {
		return moduleCol;
	}

	/**
	 * @return
	 * Returns the Occ
	 */
	public String getOcc() {
		return occCol;
	}

	/**
	 * @return
	 * Returns the map
	 */
	public String getMap() {
		return mapCol;
	}

	/**
	 * @return
	 * Returns the assessment unit on the module
	 */
	public String getAss() {
		return assCol;
	}

	/**
	 * @return
	 * Returns the students anonymous marking code
	 */
	public String getCandKey() {
		return candKeyCol;
	}

	/**
	 * @return
	 * Returns the students name
	 */
	public String getName() {
		return nameCol;
	}

	/**
	 * @return
	 * Returns CD*/
	public String getCD() {
		return cdCol;
	}

	/**
	 * @return
	 * Returns awarded mark
	 */
	public String getMark() {
		return markCol;
	}

	/**
	 * @return
	 * Returns the grade 
	 * If a student slept through the examination, this is
	 * “AB”. If a student has had an NEA accepted, to Defer their exam to the
	 * next opportunity (e.g. the August resit), this is “D”. If a student has
	 * withdrawn from the college, this is “W”. If the student was present at
	 * the exam, but received a 0 mark, this is (sometimes) marked as “F”.
	 * Otherwise, it will be blank.
	 */
	public String getGrade() {
		return gradeCol;
	}
	
	/**
	 * @return
	 * Returns the data stored in this obeject*/
	public String toString(){
		return  yearCol + ", " + periodCol + ", " + moduleCol + ", " + occCol + ", " + 
					mapCol + ", \n" + assCol + ", " + candKeyCol + ", " + nameCol + 
					", " + cdCol + ", " + markCol + ", " + gradeCol;
	}
}