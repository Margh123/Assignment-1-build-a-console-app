package enrolment;

import java.util.ArrayList;
/**
 * <h1>Creation of Enrolled data</h1>
 * <code> CREATE TABLE Enrolled (
 * 			sid CHAR(7),
 * 			cid VARCHAR(10),
 * 			sem CHAR(5),
 * 			PRIMARY KEY(sid, cid),
 * 			FOREIGN KEY(sid) REFERENCES Student(sid),
 *			FOREIGN KEY(cid) REFERENCES Course(cid)
 * 		);</code>
 * <p>Those are the elements which are used as conventions to design this class.</p>
 */
class StudentEnrolment {
	private static ArrayList<StudentEnrolment> enList = new ArrayList<StudentEnrolment>();
	private static ArrayList<String> visited = new ArrayList<String>(); //Prevent second instantiation of the same object.
	StudentEnrolment(Student std,Course crs, String sem){
		this.std=std;
		this.crs=crs;
		this.sem=sem;
		enList.add(this);
	}
	private Student std;
	private Course crs;
	private String sem; // Semester (year + [A/B/C])
	
	static { // Populating enrolment list.
		int[] std = {0,1,2,3,4,5,6,7,8,9,0,6,4,1,0};
		int[] crs = {0,0,1,2,1,3,3,1,2,1,2,0,3,3,1};
		String[] sem = {"2020C","2020C","2020B","2021A","2020C","2021A","2021A","2020C","2021A","2020B","2021A","2020C","2021A","2021A","2020C"};
		for(int i=0; i<15;i++) {
		StudentEnrolment.add(Student.getList("s3836278").get(std[i]), Course.getList("s3836278").get(crs[i]), sem[i], "s3836278");
		}
		
	}
	
	//Encapsulation
	Student getStd() {
		return std;
	}
	Course getCrs() {
		return crs;
	}
	String getSem() {
		return sem;
	}
	static ArrayList<StudentEnrolment> getList(String password) { 
		if (password.equals("s3836278")) {
		return enList;}
		throw new AccessDeniedException("Wrong Password!");
	}
	// Factory method
	static StudentEnrolment add(Student std,Course crs, String sem, String adminPassword) throws PrimaryKeyException {
		if (!adminPassword.equals("s3836278")) {
			throw new AccessDeniedException("Wrong Password!");}
		String regSem = "[0-9]{4}[A|B|C]";
		int getStdBirthYear = Integer.parseInt(std.getBirthday().substring(std.getBirthday().length()-4, std.getBirthday().length()));
		int getSemYear = Integer.parseInt(sem.substring(0, 4));
		String vs = std.getId() + crs.getId(); //To prevent duplication and a same student with same course can't be enrolled again
		if (std != null && crs != null && sem.matches(regSem) && getSemYear >= getStdBirthYear + 18) { //18 years of learning lower schools.
			if (visited.contains(vs)) {
				throw new RelationException("Duplication detected");
			}
			visited.add(vs);
			return new StudentEnrolment(std,crs,sem);
		}
		
		throw new IllegalArgumentException();
		
	}
}
