package edu.gsu.cs1302.list;
/**
 * 
 * @author Jireh Bethely
 *
 */
public class Student extends Person {
	private String college; //The name of the college that the student goes to
	/**
	 * Creates a new student
	 */
	public Student() {
		super();
	}
	/**
	 * Creates a student given a first name, last name, date of birth, id, and college
	 * @param fname The first name of the student
	 * @param lname The last name of the student
	 * @param dob The date of birth of the student
	 * @param id the ID of the student
	 * @param college The college that the student goes to
	 */
	public Student(String fname, String lname, String dob, int id, String college) {
		super(fname, lname, dob, id);
		this.college = college;
	}
	/**
	 * Returns a string representation of the Student
	 * @return A string representation of the student
	 */
	public String toString() {
		return super.toString() + " [" + college + "]";
	}
	/**
	 * Returns the college the student goes to
	 * @return Returns the college the student goes to
	 */
	public String getCollege() {
		return college;
	}
	/**
	 * Sets the college of the student
	 * @param c The college to change to
	 */
	public void setCollege(String c) {
		college = c;
	}
}