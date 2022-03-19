package enrolment;
/**
 * <h1>Relation schema of course data</h1>
 *<code> Course(id:  CHAR(10), 
 *			  name:  VARCHAR(50),
 *			  numOfCredits:  BIT(10));  </code>
 * <h1>Creation of course data</h1>
 * <code> CREATE TABLE Course (
 * 			id CHAR(10) PRIMARY KEY,
 * 			name VARCHAR(50) NOT NULL,
 * 			numOfCredits BIT(10) NOT NULL
 * 		);</code>
 * <p>Those are the elements which are used as conventions to design this class.</p>
 */
	class Course {

	private String id; 
	private String name; // name can contains up to 50 any characters.
	private byte numOfCredits;
	
	//Encapsulation (
	String getId() {
		return id;
	}
	String getName() {
		return name;
	}
	byte getNumOfCredits() {
		return numOfCredits;
	}
	//Encapsulation )

}
