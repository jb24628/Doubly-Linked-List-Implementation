package edu.gsu.cs1302.list;
/**
 * 
 * @author Jireh Bethely
 *
 */
public class Person implements Comparable<Person>, java.io.Serializable {
	private String fname, lname, dob;
	private int id;
	/**
	 * Creates a Person
	 */
	public Person() {
		fname = "";
		lname = "";
		dob = "";
		id = 0;
	}
	/**
	 * Creates a person given a first name, last name, date of birth, and ID
	 * @param fname The first name of the Person
	 * @param lname The last name of the Person
	 * @param dob The date of birth of the Person
	 * @param id the ID of the person
	 */
	public Person(String fname, String lname, String dob, int id) {
		this.fname = fname;
		this.lname = lname;
		this.dob = dob;
		this.id = id;
	}
	/**
	 * Returns a string representation of the Person
	 * @return The string representation of the Person
	 */
	public String toString() {
		return id + " " + fname + " " + lname + " " + dob;
	}
	/**
	 * Compares the referenced Prson to the Person in the parameter.
	 * @return Returns -1 if the referenced person's ID is greater than the other's. Returns 0 if their IDs are the same. And returns 1 if the referenced person's ID is less than the other's
	 */
	public int compareTo(Person p) {
		int x = -1;
		if (p.id == this.id) {
			x = 0;
		}
		else if (p.id > this.id) {
			x = 1;
		}
		return x;
	}
	/**
	 * Sets the first name of the person.
	 * @param first The name to change the first name to
	 */
	public void setfName(String first) {
		fname = first;
	}
	/**
	 * Sets the last name of the Person
	 * @param first The name to change the second name to
	 */
	public void setlName(String first) {
		lname = first;
	}
	/**
	 * Sets the date of birth of the Person
	 * @param date The string format of the birthday
	 */
	public void setDOB(String date) {
		dob = date;
	}
	/**
	 * Sets the ID of the Person
	 * @param id The number to change the ID to
	 */
	public void setID(int id) {
		this.id = id;
	}
	/**
	 * Returns a string of the Person's first name
	 * @return The String of the Person's first name
	 */
	public String getfName() {
		return fname;
	}
	/**
	 * Returns a string of the Person's last name
	 * @return The String of the Person's last name
	 */
	public String getlName() {
		return lname;
	}
	/**
	 * Returns a string of the Person's Date of Birth
	 * @return The String of the Person's date of birth
	 */
	public String getDOB() {
		return dob;
	}
	/**
	 * Returns the id of a Person
	 * @return The ID
	 */
	public int getID() {
		return id;
	}
}