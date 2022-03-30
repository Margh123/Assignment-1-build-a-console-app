package enrolment;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class VersatileKit{};
	class CDate {
	private CDate() {};
	//Check date (if the date is valid)
	private static final int MAXYR = 9999; 
	private static final int MINYR = 1800; 
	
	private static boolean isLeap(int y) {
		if(y%4==0) {
			if (y%100==0) {
				if (y%400==0) {
					return true;
				}
				return false;
			}
			return true;
		} 
		return false;
	}
	static boolean checkDate(int m, int d, int y) {//[1]
		// Range validity
		if (m<1||m>12) {
			return false;
		}
		if (d<1||d>31) {
			return false;
		}
		if (y<MINYR||y>MAXYR) {
			return false;
		}
		// With months perspective
		if (m==2) {
			if (isLeap(y)) {
				if (d <=29) {
					return true;
				}
			}
			else {if (d <=28) 
				return true;
			}
			return false;
		}
		if (m==4||m==6||m==9||m==11) {
			if (d<=30) {
				return true;
			}
			else {return false;}
		}
		return true;
		
	}
}
	//Exceptions
	class PrimaryKeyException extends RuntimeException{
		public PrimaryKeyException(String str) {
			super(str);
		}
	}
	class RelationException extends RuntimeException{
		public RelationException(String str) {
			super(str);
		}
	}
	class AccessDeniedException extends RuntimeException{
		public AccessDeniedException(String str) {
			super(str);
		}
	}
class Printer{
	private Printer() {};
	public static void saveData(String s) throws IOException{ // Warning, make sure that you have closed the report.cvs file on taskbar
		PrintWriter writer = null;
		writer = new PrintWriter(new FileWriter("report.csv",true));
		writer.write(s+"\n");
		writer.flush();
		writer.close();
	}

}
//[1]Bansal, S., 2019. C Program to check if a date is valid or not. [online] Tutorialspoint.com. Available at: 
//<https://www.tutorialspoint.com/c-program-to-check-if-a-date-is-valid-or-not> [Accessed 21 March 2022].
