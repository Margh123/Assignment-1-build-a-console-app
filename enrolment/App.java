package enrolment;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

interface StudentEnrolmentManager{
	void add();
	void update();
	void delete();
	void getOne();
	void getAll();
}

class App implements StudentEnrolmentManager{ // This is a flatform for the user to use the software. Every property that the user uses is in here.

	@Override
	public void add() { // or enroll
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter student id:");
		String sid = sc.next();
		System.out.print("Enter course id:");
		String cid = sc.next();
		System.out.print("Enter semester:");
		String sem = sc.next();
		sc.close();
		// We need to find the corresponding object based on the yeilded input.
		//Student
		Student st = null;
		Course crs = null;
		ArrayList<Student> arst = Student.getList("s3836278");
		for (Student k : arst) {
			if (k.getId().equals(sid)) {
				st = k;
				break;
			}
			if (arst.indexOf(k)==arst.size()-1) {
				throw new NoSuchElementException("No student is found in the list.");
			}
		}
		ArrayList<Course> arcrs = Course.getList("s3836278");
		for (Course k : arcrs) {
			if (k.getId().equals(cid)) {
				crs = k;
				break;
			}
			if (arcrs.indexOf(k)==arcrs.size()-1) {
				throw new NoSuchElementException("No course is found in the list.");
			}
		}
		StudentEnrolment.add(st, crs, sem, "s3836278");
	}

	@Override
	public void update() {
	
	}

	@Override
	public void delete() {
	
	}

	@Override
	public void getOne() {

	}

	@Override
	public void getAll() {

	} 

}
