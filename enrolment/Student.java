package enrolment;
/**
 * <h1>Relation schema of student data</h1>
 *<code> Student(id:  CHAR(7), 
 *			  name:  VARCHAR(30),  
 *			  birthday:  DATE); </code>
 * <h1>Creation of student data</h1>
 * <code> CREATE TABLE Student (
 * 			id CHAR(7) PRIMARY KEY,
 * 			name VARCHAR(30) NOT NULL,
 * 			birthday DATE NOT NULL
 * 		);</code>
 * <p>Those are the elements which are used as conventions to design this class.</p>
 */
	class Student {

	private String id; // id starts with uppercase "S" and follows by 6 digits.
	private String name; // name can contains up to 30 any characters.
	private String birthday;// birthday should be in MM/DD/YYYY format.
	
	//Encapsulation (
	String getId() {
		return id;
	}
	String getName() {
		return name;
	}
	String getBirthday() {
		return birthday;
	}
	//Encapsulation )

}
