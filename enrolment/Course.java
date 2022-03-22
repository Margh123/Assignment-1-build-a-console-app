package enrolment;

import java.util.ArrayList;

/**
 * <h1>Relation schema of course data</h1>
 *<code> Course(cid:  CHAR(10), 
 *			  cname:  VARCHAR(50),
 *			  numOfCredits:  BIT(10));  </code>
 * <h1>Creation of course data</h1>
 * <code> CREATE TABLE Course (
 * 			cid CHAR(10) PRIMARY KEY,
 * 			cname VARCHAR(50) NOT NULL,
 * 			numOfCredits BIT(10) NOT NULL
 * 		);</code>
 * <p>Those are the elements which are used as conventions to design this class.</p>
 */
	class Course {
	static ArrayList<Course> cList = new ArrayList<Course>();
	private static ArrayList<String> visited = new ArrayList<String>(); //Handling duplication 
	private String id; 
	private String name; // name can contains up to 50 any characters.
	private byte numOfCredits;
	private Course(String id, String name, byte numOfCredits) {
		this.id = id;
		this.name = name;
		this.numOfCredits = numOfCredits;
		visited.add(this.id);
		cList.add(this);
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
	// Factory method
	static Course add(String id, String name, byte numOfCredits) {
		if(name.length()<=50 && name.length()>0) {
			if (visited.contains(id)) {
				throw new PrimaryKeyException("Duplication detected");
			}
			return new Course(id,name,numOfCredits);
		}
		throw new IllegalArgumentException();
	}

}
