package enrolment.testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import enrolment.App;
import enrolment.Course;
import enrolment.Student;
import enrolment.StudentEnrolment;

public class AppTest {
	public static App obj = App.getApp();
	//simulator functions
	int counter = 0;
	/** PLEASE REMEMBER THESE VALUES FOR TESTING EXPECTED VALUES*/
	ArrayList<StudentEnrolment> arst = StudentEnrolment.getList("s3836278");
	StudentEnrolment se = StudentEnrolment.getList("s3836278").get(0); // sid = "S101312" cid = "COSC4030"
	ArrayList<Course> crs = new ArrayList<Course>(); // sid = "S101312" sem = "2020C"
	ArrayList<Student> std = new ArrayList<Student>(); // cid = "COSC4030" sem = "2020C"
	{//IIB
		crs.add(Course.getList("s3836278").get(0));
		crs.add(Course.getList("s3836278").get(1));
		
		std.add(Student.getList("s3836278").get(0));
		std.add(Student.getList("s3836278").get(1));
		std.add(Student.getList("s3836278").get(6));
	}
	private static void main() {
		obj.add();
	}
	private static void main(int i) {
		obj.delete();
	}
	private static ArrayList<Course> main(double i) {
		return obj.printCourse();
	}

	private static void update() {
		
		obj.add();
	}
	private static void update(int i) {
		
		obj.delete();
	}
	private static ArrayList<Course> update(double i) {
		return obj.printCourse();
	}

	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("Begin testing for App class");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("End testing for App class");
	}

	@BeforeEach
	void setUp() throws Exception {
		System.out.println("Begin method");
	}

	@AfterEach
	void tearDown() throws Exception {
		System.out.println("End method");
		System.gc();
	}

	@Test
	void testGetApp() {
		assertEquals(obj,App.getApp());
	}
	//add
	@Test
	void testAddMain() { // From main
		ArrayList<StudentEnrolment> arst = StudentEnrolment.getList("s3836278");
		int size= arst.size(); // Initial size
		try {
			main();
		} catch (Exception e) {
			fail(""); 
		}
		String placeholder = arst.get(arst.size()-1).getStd().getId();
		String placeholder2 = arst.get(arst.size()-1).getSem();
		App.setPlaceholder(placeholder, placeholder2, "s3836278");
		assertTrue(StudentEnrolment.getList("s3836278").size()==size+1); // We want to check if array list size has increased.
		//This is the ultimate result of the add() function. If the result doesn't occur, then the test is fail.
	}
	@Test
	void testAddUpdate() { // From update
		StudentEnrolment.getList("s3836278").remove(StudentEnrolment.getList("s3836278").size()-1);
		StudentEnrolment.removeVisited();
		ArrayList<StudentEnrolment> arst = StudentEnrolment.getList("s3836278");
		int size= arst.size(); // Initial size
		try {
			update();
		} catch (Exception e) {
			fail(""); 
		} 
		assertTrue(StudentEnrolment.getList("s3836278").size()==size+1);
	
	}
	@Test
	void testAddExceptionMain() {
		assertThrows(NoSuchElementException.class, // No exception is throwed is a failure
	            ()->{main();} ); 
	}
	@Test
	void testAddExceptionUpdate() {
		assertThrows(NoSuchElementException.class, 
	            ()->{update();} ); 
	}
	//
	@Test
	void testUpdate() { //Should excecuted first
		counter++;
		int size= arst.size();
		try {
			obj.update();
		} catch (Exception e) {
			fail(""); 
		}
		assertTrue(StudentEnrolment.getList("s3836278").size()!=size);
	}
	@Test
	void testUpdateException() {
		assertThrows(IllegalArgumentException.class,
	            ()->{obj.update();} ); // Insert unacceptable input in the switch statement only
	}
	//delete
	@Test
	void testDeleteMain() {
		int size= arst.size(); // Initial size
		try {
			main(0);
		} catch (Exception e) {
			fail(""); 
		}
		assertTrue(StudentEnrolment.getList("s3836278").size()!=size); 
	}
	@Test
	void testDeleteUpdate() {
		int size= arst.size(); // Initial size
		try {
			update(0);
		} catch (Exception e) {
			fail(""); 
		}
		assertTrue(StudentEnrolment.getList("s3836278").size()!=size); 
	}
	@Test
	void testDeleteExceptionMain() {
		assertThrows(NoSuchElementException.class,
	            ()->{main(0);} ); 
	}
	@Test
	void testDeleteExceptionUpdate() {	//Should throws exception in testing
		assertThrows(NoSuchElementException.class, 
	            ()->{update(0);} ); 
	}
	@Test
	void testGetOne() { 
		try {
			Assertions.assertTrue(obj.getOne().equals(se));// same as assertEqual // expected value "se"
		}
		catch (Exception e) {
			fail(""); 
		}
	}
	@Test
	void testGetOneException() {
		assertThrows(NoSuchElementException.class,
	            ()->{obj.getOne();} ); 
	}
	@Test
	void testGetAll() {
		try {
			Assertions.assertTrue(obj.getAll().equals(StudentEnrolment.getList("s3836278"))); // same as assertEqual
		}
		catch (Exception e) {
			fail(""); 
		}
	}
	@Test
	void testPrintCourseMain() {
		try {
			Assertions.assertTrue(main(0.0).equals(crs));
		}
		catch (Exception e) {
			fail(""); 
		}
	}
	@Test
	void testPrintCourseUpdate() {
		try {
			Assertions.assertTrue(update(0.0).equals(crs));
		}
		catch (Exception e) {
			fail(""); 
		}
	}
	@Test
	void testPrintCourseExceptionMain() {
		assertThrows(NoSuchElementException.class,
	            ()->{main(0.0);} ); 
	}
	@Test
	void testPrintCourseExceptionUpdate() {
		assertThrows(NoSuchElementException.class,
	            ()->{update(0.0);} ); 
	}
	@Test
	void testPrintStudent() {
		try {
			Assertions.assertTrue(obj.printStudent().equals(std));
		}
		catch (Exception e) {
			fail(""); 
		}
	}
	
	@Test
	void testPrintStudentException() {
		assertThrows(NoSuchElementException.class,
	            ()->{obj.printStudent();} ); 
	}
	
	
}
