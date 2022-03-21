package enrolment; // Nho de link GitHub
import java.util.StringTokenizer;
import java.util.ArrayList;

/**
 * <h1>Relation schema of student data</h1>
 *<code> Student(sid:  CHAR(7), 
 *			  sname:  VARCHAR(30),  
 *			  birthday:  DATE); </code>
 * <h1>Creation of student data</h1>
 * <code> CREATE TABLE Student (
 * 			sid CHAR(7) PRIMARY KEY,
 * 			sname VARCHAR(30) NOT NULL,
 * 			birthday DATE NOT NULL
 * 		);</code>
 * <p>Those are the elements which are used as conventions to design this class.</p>
 */
	class Student {
	private static ArrayList<String> visited = new ArrayList<String>(); //Handling Primary Key
	private Student(String id, String name, String birthday) {
		this.id = id;
		this.name = name;
		this.birthday = birthday;
		visited.add(id);
	}
	private String id; // id starts with uppercase "S" and follows by 6 digits.
	private String name; // name can contains up to 30 any characters.
	private String birthday;// birthday should be in MM/DD/YYYY format.
	
	//Encapsulation 
	String getId() {
		return id;
	}
	String getName() {
		return name;
	}
	String getBirthday() {
		return birthday;
	}

	// Factory method
	Student add(String id,String name, String birthday) {
		String regEx = "S[0-9]{6}";
		String dateRegEx = "([0-9]{2})/([0-9]{2})/([0-9]{4})";
		StringTokenizer st = new StringTokenizer(birthday,"/");
		boolean validDate = VersatileKit.checkDate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		if (id.matches(regEx) && name.length()==30 && validDate && birthday.matches(dateRegEx)) { 
			if (visited.contains(id)) {
				//Exception
			}
			return new Student(id,name,birthday);
		}
		return null; //Exception
		
	}

}
