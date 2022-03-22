package enrolment;

class StudentEnrolment {
	StudentEnrolment(Student std,Course crs, String sem){
		this.std=std;
		this.crs=crs;
		this.sem=sem;
	}
	private Student std;
	private Course crs;
	private String sem; // Semester
}
