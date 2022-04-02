package enrolment;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

interface StudentEnrolmentManager{
	void add();
	void update();
	void delete();
	StudentEnrolment getOne();
	ArrayList<StudentEnrolment> getAll();
}

public class App implements StudentEnrolmentManager{ // This is a flatform for the user to use the software. Every property that the user uses is in here.
	// This is a singleton class in order to be still implements the interface without declare all the methods here as static.
	private static App turtle = new App();
	private App() {};
	public static App getApp() {
		return turtle;
		
	}
	private static String placeholder;
	private static String placeholder2;// placeholder is a single word in english
	/*public static String[] getPlaceholder() {
		String[] arr = {placeholder, placeholder2};
		return arr;
	}*/
	public static void setPlaceholder(String placeholder, String placeholder2, String adminPassword) {
		if (adminPassword.equals("s3836278")) {
			App.placeholder = placeholder;
			App.placeholder2 = placeholder2;
			return;
			}
			throw new AccessDeniedException("Wrong Password!");
	}
	@Override
	/**
	 * <p> Let X is the Student id, Y is the Course id, Z is the semester,</p>
	 * <code>INSERT INTO Enrolled
	 *	VALUES (X, Y, Z);</code>
	 */
	public void add() { // or enroll
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		String sid = null;
		String sem = null;
		Scanner sc = new Scanner(System.in);
		if(stackTraceElements[2].getMethodName().equals("main")){
			System.out.print("Enter student id you want to add:");
			sid = sc.next();
			System.out.print("Enter semester you want to add:");
			sem = sc.next();}
		if(stackTraceElements[2].getMethodName().equals("update")){
			sid = placeholder;
		    sem = placeholder2;}
		System.out.print("Enter course id you want to add:");
		String cid = sc.next();
		//sc.close(); It would we dangerous to close the scanner as it would close to input stream too
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
		printCourse();
		Scanner sc = new Scanner(System.in);
		System.out.println("Do you want to delete or add new courses for student " + placeholder +
				" in semester "+placeholder2+" in the list ? D for delete, A for add");
		char c = sc.next().charAt(0);
		switch(c) {
			case 'D': delete();
					  break;
			case 'A': add();
			  		  break;
			default: throw new IllegalArgumentException();
		}
	}

	@Override
	/**
	 * <p> Let X is the Student id, Y is the Course id. </p>
	 * <code> DELETE FROM Enrolled WHERE sid = X AND cid = Y;</code>
	 */
	public void delete() { // Delete a record from Enrolled relation
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		String sid = null;
		Scanner sc = new Scanner(System.in);
		if(stackTraceElements[2].getMethodName().equals("main")){
		System.out.print("Enter student id you want to delete:");
		sid = sc.next();}
		if(stackTraceElements[2].getMethodName().equals("update")){
				sid = placeholder;}
		System.out.print("Enter course id you want to delete:");
		String cid = sc.next();
		//sc.close(); t would we dangerous to close the scanner as it would close to input stream too
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
	/**
	 * Let X is the Student id, Y is the Course id
	 * SELECT * FROM Enrolled;
	 * WHERE sid = X AND cid = Y;
	 */
	public StudentEnrolment getOne() {
		System.out.println("Get one record in Enrolled table");
		Scanner sc = new Scanner(System.in); 
		System.out.print("Enter student id:");
		String sid = sc.next();
		System.out.print("Enter course id:");
		String cid = sc.next();
		ArrayList<StudentEnrolment> arst = StudentEnrolment.getList("s3836278");
		for(StudentEnrolment se :arst) {
			if(se.getStd().getId().equals(sid) && se.getCrs().getId().equals(cid)) {
			System.out.println(se.toString());
			return se;
			}
		}
		throw new NoSuchElementException("No student or course is found in the list");
	}

	@Override
	/**
	 * SELECT * FROM Enrolled;
	 */
	public ArrayList<StudentEnrolment> getAll() {
		System.out.println("Get all record in Enrolled table");
		ArrayList<StudentEnrolment> arst = StudentEnrolment.getList("s3836278");
		ArrayList<StudentEnrolment> result = new ArrayList<StudentEnrolment>();
		for(StudentEnrolment se :arst) {
			System.out.println(se.toString());
			result.add(se);
		}
		return result; // Empty array list
	} 
	// For printing all.
	/**
	 * <p> Let X is the Student id, Z is the semester. </p>
	 * <code> SELECT cid FROM Enrolled;
	 *  WHERE sid = X AND sem = Z;</code>
	 */
	public ArrayList<Course> printCourse() {
		ArrayList<Course> result = new ArrayList<Course>();
		StringBuilder sb = new StringBuilder();
		int counter = 0;
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace(); // We don't want update() mtethod to write to csv file but main method is fine.
		ArrayList<StudentEnrolment> arst = StudentEnrolment.getList("s3836278");
		System.out.println("Print all courses for 1 student in 1 semester");
		Scanner sc = new Scanner(System.in); 
		System.out.print("Enter student id:");
		String sid = sc.next();
		System.out.print("Enter semester:");
		String sem = sc.next();
		placeholder = sid;
		placeholder2 = sem;
	for (StudentEnrolment se : arst) {
		if ((se.getStd().getId()+se.getSem()).equals(sid+sem)) {
			counter++;
			if(stackTraceElements[2].getMethodName().equals("update")){ // If getOne() is called from update()
				// Only print for update()
				if (counter == 1) {
					System.out.println("Print all course for "+se.getStd().getName()+" "+ "("+se.getStd().getId()+")"+ " " +
							"in semester"+"("+sem+")");
					System.out.println("id"+","+"name"+","+"credit");
				}
				System.out.print(se.getCrs().getId()+",");
				System.out.print(se.getCrs().getName()+",");
				System.out.println(se.getCrs().getNumOfCredits());
				result.add(se.getCrs());
			}
			if(stackTraceElements[2].getMethodName().equals("main")){ // If getOne() is called from main()
				// Print to the console and write to csv file
				if (counter == 1) {
					sb.append("Print all course for "+se.getStd().getName()+" "+ "("+se.getStd().getId()+")"+ " " +
							"in semester"+"("+sem+")");
					sb.append('\n');
					sb.append("id"+","+"name"+","+"credit");
					sb.append('\n');
				}
				sb.append(se.getCrs().toString());
				result.add(se.getCrs());
			}
		}
		if(arst.indexOf(se)==arst.size()-1 && stackTraceElements[2].getMethodName().equals("main")) {
			System.out.println(sb.toString());
			System.out.println("writing to csv file");
			try {
				Printer.saveData(sb.toString());
			} catch (IOException e) {
				System.err.print("Make sure you have closed the csv file before you run the program");
				System.exit(1);}
				
		}
	}
	if(counter == 0) {
	throw new NoSuchElementException("No student or semester is found in the list");}
	return result;
	}
		/**
		 * <p> Let Y is the Course id, Z is the semester. </p>
		 * <code> SELECT sid FROM Enrolled;
		 *  WHERE cid = Y AND sem = Z;</code>
		 * @return 
		 */
	public ArrayList<Student> printStudent() {
		ArrayList<Student> result = new ArrayList<Student>();
		StringBuilder sb = new StringBuilder();
		int counter = 0;
		ArrayList<StudentEnrolment> arst = StudentEnrolment.getList("s3836278");
		System.out.println("Print all students of 1 course in 1 semester");
		Scanner sc = new Scanner(System.in); 
		System.out.print("Enter course id:");
		String cid = sc.next();
		System.out.print("Enter semester:");
		String sem = sc.next();
		for (StudentEnrolment se : arst) {
			if ((se.getCrs().getId()+se.getSem()).equals(cid+sem)) {
				counter++;
				// Print to the console and write to csv file
				if (counter == 1) {
					sb.append("Print all student for "+se.getCrs().getName()+" "+ "("+se.getCrs().getId()+")"+ " " +
							"in semester"+"("+sem+")");
					sb.append('\n');
					sb.append("id"+","+"name"+","+"birthday");
					sb.append('\n');
				}
				sb.append(se.getStd().toString());
				result.add(se.getStd());
			}
			if(arst.indexOf(se)==arst.size()-1) {
				System.out.println(sb.toString());
				System.out.println("writing to csv file");
				try {
					Printer.saveData(sb.toString());
				} catch (IOException e) {
					System.err.print("Make sure you have closed the csv file before you run the program");
					System.exit(1);}
			}
		}
		if(counter == 0) {
		throw new NoSuchElementException("No course or semester is found in the list");}
		return result;
		}
			/**
			 * <p> Let Z is the semester. </p>
			 * <code> SELECT cid FROM Enrolled;
			 *  WHERE sem = Z;</code>
			 */
	public ArrayList<Course> printOffered() {
		ArrayList<Course> result = new ArrayList<Course>();
		StringBuilder sb = new StringBuilder();
		int counter = 0;
		ArrayList<StudentEnrolment> arst = StudentEnrolment.getList("s3836278");
		System.out.println("Prints all courses offered in 1 semester");
		Scanner sc = new Scanner(System.in); 
		System.out.print("Enter semester:");
		String sem = sc.next();
		for (StudentEnrolment se : arst) {
			if (se.getSem().equals(sem)) {
				counter++;
				// Print to the console and write to csv file
				if (counter == 1) {
					sb.append("Print all course in semester"+"("+sem+")");
					sb.append('\n');
					sb.append("id"+","+"name"+","+"credit");
					sb.append('\n');
				}
				if(result.contains(se.getCrs())) {
					continue;
				}
				sb.append(se.getCrs().toString());
				result.add(se.getCrs());
				//handling duplication
			}
			if(arst.indexOf(se)==arst.size()-1) {
				System.out.println(sb.toString());
				System.out.println("writing to csv file");
				try {
					Printer.saveData(sb.toString());
				} catch (IOException e) {
					System.err.print("Make sure you have closed the csv file before you run the program");
					System.exit(1);}
			}
		}
		if(counter == 0) {
		throw new NoSuchElementException("No semester is found in the list");}
		return result;
	}
}
