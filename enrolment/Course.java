package enrolment;

import java.util.ArrayList;

/**
 * <h1>Relation schema of course data</h1>
 *<code> Course(cid:  VARCHAR(10), 
 *			  cname:  VARCHAR(50),
 *			  numOfCredits:  BIT(10));  </code>
 * <h1>Creation of course data</h1>
 * <code> CREATE TABLE Course (
 * 			cid VARCHAR(10) PRIMARY KEY,
 * 			cname VARCHAR(50) NOT NULL,
 * 			numOfCredits BIT(10) NOT NULL
 * 		);</code>
 * <p>Those are the elements which are used as conventions to design this class.</p>
 */
	class Course {
	private static ArrayList<Course> cList = new ArrayList<Course>();
	private static ArrayList<String> visited = new ArrayList<String>(); //Handling duplication 
	private String id; // id can contains up to 10 any characters.
	private String name; // name can contains up to 50 any characters.
	private byte numOfCredits;// A positive number smaller than 10
	private Course(String id, String name, byte numOfCredits) {
		this.id = id;
		this.name = name;
		this.numOfCredits = numOfCredits;
		visited.add(this.id);
		cList.add(this);
	}
	static { // No for loop
		Course.add("COSC4030", "Theory of Computation", (byte)5);//[0]
		Course.add("BUS2232", "Business Law", (byte)3);//[1]
		Course.add("PHYS1230", "Introductory Human Physiology", (byte)4);//[2]
		Course.add("COSC3321", "Artificial Intelligence", (byte)3);//[3]
	}
	//Encapsulation 
	String getId() {
		return id;
	}
	String getName() {
		return name;
	}
	byte getNumOfCredits() {
		return numOfCredits;
	}
	/**<p>Using a password we prevent user from accessing the stdList to modify the ArrayList itself.</p>*/
	static ArrayList<Course> getList(String password) { 
		if (password.equals("s3836278")) {
		return cList;}
		throw new AccessDeniedException("Wrong Password!");
	}
	// Factory method
	static Course add(String id, String name, byte numOfCredits) {
		if((id.length()<=10 && id.length()>0)&&(name.length()<=50 && name.length()>0)&&(numOfCredits<=10 && numOfCredits>0 )) {
			if (visited.contains(id)) {
				throw new PrimaryKeyException("Duplication detected");
			}
			return new Course(id,name,numOfCredits);
		}
		throw new IllegalArgumentException();
	}

}
