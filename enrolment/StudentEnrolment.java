package enrolment;

import java.util.ArrayList;
import java.util.StringTokenizer;

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
		if (std != null && crs != null && sem.matches(regSem) && getSemYear >= getStdBirthYear + 18) { //18 years of learning in lower schools.
			if (visited.contains(vs)) {
				throw new RelationException("Duplication detected");
			}
			visited.add(vs);
			return new StudentEnrolment(std,crs,sem);
		}
		
		throw new IllegalArgumentException();
		
	}
}
