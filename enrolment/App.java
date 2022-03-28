package enrolment;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

interface StudentEnrolmentManager{
	void add();
	void update();
	void delete();
	void getOne(boolean choice);
	void getAll();
}

class App implements StudentEnrolmentManager{ // This is a flatform for the user to use the software. Every property that the user uses is in here.
	// This is a singleton class in order to be still implements the interface without declare all the methods here as static.
	private static App turtle = new App();
	private App() {};
	public static App getApp() {
		return turtle;
		
	}
	@Override
	/**
	 * <p> Let X is the Student id, Y is the Course id, Z is the semester,</p>
	 * <code>INSERT INTO Enrolled
	 *	VALUES (X, Y, Z);</code>
	 */
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
	/**
	 * <p> Let X is the Student id, Y is the Course id. </p>
	 * <code> DELETE FROM Enrolled WHERE sid = X AND cid = Y;</code>
	 */
	public void delete() { // Delete a record from Enrolled relation
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter student id:");
		String sid = sc.next();
		System.out.print("Enter course id:");
		String cid = sc.next();
		sc.close();
		ArrayList<StudentEnrolment> arst = StudentEnrolment.getList("s3836278"); // Each Enrolled record is specified by the Student id and Course
		//id simultaneously.
		for (StudentEnrolment se : arst) {
			if ((se.getStd().getId()+se.getCrs().getId()).equals(sid+cid)){
				arst.remove(se);
				return;
			}
		}
		throw new NoSuchElementException("No enrolment is found in the list");
	}

	@Override
	public void getOne(boolean choice) {
		int counter = 0;
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace(); // We don't want update() mtethod to write to csv file but main method is fine.
		ArrayList<StudentEnrolment> arst = StudentEnrolment.getList("s3836278");
		//
		//
		if (choice) { //Print all courses for 1 student in 1 semester.
				Scanner sc = new Scanner(System.in); 
				System.out.print("Enter student id:");
				String sid = sc.next();
				System.out.print("Enter semester:");
				String sem = sc.next();
				sc.close();
			for (StudentEnrolment se : arst) {
				if ((se.getStd().getId()+se.getSem()).equals(sid+sem)) {
					counter++;
					if(stackTraceElements[2].getMethodName().equals("update")){ // If getOne() is called from update()
						// Only print for update()
						if(arst.indexOf(se)==arst.size()-1) {
							break;
						}
						continue;
					}
					// Print to the console and write to csv file (also ask user if they want to save to csv)
				}
			}
			if(counter == 0) {
			throw new NoSuchElementException("No student or semester is found in the list");}
		}
		//
		//

	}

	@Override
	public void getAll() {

	} 

}
