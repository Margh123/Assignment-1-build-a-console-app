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
import enrolment.StudentEnrolment;

class AppTest {
	public static App obj = App.getApp();
	//simulator functions
	String temp1 = null;
	String temp2 = null;
	private static void main() {
		obj.add();
	}
	private static void main(int i) {
		obj.delete();
	}

	private static void update() {
		
		obj.add();
	}
	private static void update(int i) {
		
		obj.delete();
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
	void testAddException() {
		assertThrows(NoSuchElementException.class,
	            ()->{obj.add();} ); 
	}
	//
	@Test
	void testUpdate() {
		ArrayList<StudentEnrolment> arst = StudentEnrolment.getList("s3836278");
		int size= arst.size();
		try {
			obj.update();
		} catch (Exception e) {
			fail(""); 
		}
		String placeholder = arst.get(arst.size()-1).getStd().getId();
		App.setPlaceholder(placeholder, null, "s3836278");
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
		ArrayList<StudentEnrolment> arst = StudentEnrolment.getList("s3836278");
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
		ArrayList<StudentEnrolment> arst = StudentEnrolment.getList("s3836278");
		int size= arst.size(); // Initial size
		try {
			update(0);
		} catch (Exception e) {
			fail(""); 
		}
		assertTrue(StudentEnrolment.getList("s3836278").size()!=size); 
	}
	@Test
	void testDeleteException() {
		assertThrows(NoSuchElementException.class,
	            ()->{obj.delete();} ); 
	}
	@Test
	void testGetOne() {
		try {
			obj.getOne();
		}
		catch (Exception e) {
			fail(""); 
		}
	Assertions.assertTrue(true);
	}
	@Test
	void testGetAll() {
		try {
			obj.getAll();
		}
		catch (Exception e) {
			fail(""); 
		}
	Assertions.assertTrue(true);
	}


}
