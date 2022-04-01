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
	public class Student {
	private static ArrayList<Student> stdList = new ArrayList<Student>();
	private static ArrayList<String> visited = new ArrayList<String>(); //Handling duplication 
	private Student(String id, String name, String birthday) {
		this.id = id;
		this.name = name;
		this.birthday = birthday;
		visited.add(this.id);
		stdList.add(this);
	}
	private String id; // id starts with uppercase "S" and follows by 6 digits.
	private String name; // name can contains up to 30 any characters.
	private String birthday;// birthday should be in MM/DD/YYYY format.
	
	static { // No for loop
		Student.add("S101312","Alex Mike","10/13/1998"); // [0]
		Student.add("S102732","Mark Duong","08/28/2001");// [1]
		Student.add("S103723","Hai Hoang Vu","04/25/2000");// [2]
		Student.add("S103821","Son Minh Doan","11/15/1999");// [3]
		Student.add("S101163","Joseph Fergile","10/29/1998");// [4]
		Student.add("S101153","Jang Min Seon","09/25/2000");// [5]
		Student.add("S103817","Thuy Thu Nguyen","03/04/2000");//[6]
		Student.add("S103912","Son Thanh Le","02/09/2001");//[7]
		Student.add("S102192","Mark Patterson","06/05/2000");//[8]
		Student.add("S103192","Ngan Thu Vo","03/09/1998");	//[9]		
	}
	
	//Encapsulation 
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getBirthday() {
		return birthday;
	}
	/**<p>Using a password we prevent user from accessing the stdList to modify the ArrayList itself.</p>*/
	public static ArrayList<Student> getList(String password) { 
		if (password.equals("s3836278")) {
		return stdList;}
		throw new AccessDeniedException("Wrong Password!");
	}

	// Factory method
	static Student add(String id,String name, String birthday) throws PrimaryKeyException {
		String regEx = "S[0-9]{6}";
		String dateRegEx = "([0-9]{2})/([0-9]{2})/([0-9]{4})";
		StringTokenizer st = new StringTokenizer(birthday,"/");
		boolean validDate = CDate.checkDate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		if (id.matches(regEx) && (name.length()<=30 && name.length()>0) && validDate && birthday.matches(dateRegEx)) { 
			if (visited.contains(id)) {
				throw new PrimaryKeyException("Duplication detected");
			}
			return new Student(id,name,birthday);
		}
		
		throw new IllegalArgumentException();
		
	}

}
