//unit5.hw
//Group members: Alvin Zhao, Dodge Holmbeck, Jilben Brito

public class DriverClassHW5 {
	// DO NOT CHANGE ANYTHING IN THIS CLASS (DriverClass)
	public static void main(String[] args) {

    Student s;
	//***********
	s = new PhdStudent ("Zaydoun BenSellam",
	"zb5954" ,
	"Gary Richarson",
	"Fuzzy Topology" ,
	2599 );
	s.printInvoice();
	//***********
	int [] gradCrnsTaken = {7587,8997} ;
	s = new MsStudent ( "Emily Jones",
	"em1254",
	gradCrnsTaken,
	1997);
	s.printInvoice();
	//***********
	int [] undergradCrnsTaken = {4587,2599};
	s = new UndergraduateStudent ("Jamila Jones" ,
	"ja5225" ,
	undergradCrnsTaken ,
	3.0,
	false );
	s.printInvoice();
	}//end of main
	}


//---------------------------
abstract class Student {

    private static String[][] courses = {
        {"4587", "MAT 236", "4"},
        {"4599", "COP 220", "3"},
        {"8997", "GOL 124", "1"},
        {"9696", "COP 100", "3"},
        {"4580", "MAT 136", "1"},
        {"2599", "COP 260", "3"},
        {"1997", "CAP 424", "1"},
        {"5696", "KOL 110", "2"},
        {"7587", "MAT 936", "5"},
        {"1599", "COP 111", "3"},
        {"6997", "GOL 109", "1"},
        {"2696", "COP 101", "3"},
        {"5580", "MAT 636", "2"},
        {"2099", "COP 268", "3"},
        {"4997", "CAP 427", "1"},
        {"3696", "KOL 910", "2"}
    };

    public static String[][] getCourses(){
        return courses;
    }

    String name;
    String id;

    public Student(String name, String id) {
        this.name = name;
        this.id = id;
    }

    abstract public void printInvoice();
}
//---------------------------

class UndergraduateStudent extends Student {

    int[] undergradCrns;
    double gpa;
    boolean resident;
    public UndergraduateStudent(String name, String id, int[] undergradCrnsTaken, double gpa, boolean resident) {
        super(name, id);
        this.undergradCrns = undergradCrnsTaken;
        this.gpa = gpa;
        this.resident = resident;
    }

    @Override
    public void printInvoice() {

    for (Integer crn : undergradCrns) {
        for (String[] course : Student.getCourses()) {
            if(Integer.parseInt(course[0]) == crn && Integer.parseInt(course[0]) > 5000){
                System.out.print("ERROR: INVALID COURSE(S)\n\n");
                return;
            }
        } 
    }
    
    double total = 0;
    double costOfCredit;
    if(this.resident){
        costOfCredit = 120.25;
    }
    else{
        costOfCredit = 240.50;
    }

    System.out.print("VALENCE COLLEGE \nORLANDO FL 10101\n---------------\n\nFee Invoice Prepared for Student:\n" + this.id.toUpperCase() + "-"+ this.name.toUpperCase() + "\n");
    System.out.printf("\n1 Credit Hour = $%.2f\n\nCRN   CR_PREFIX  CR_HOURS\n", costOfCredit);
    for (Integer crn : undergradCrns) {
        for (String[] course : Student.getCourses()) {
            if (Integer.parseInt(course[0]) == crn) {
                System.out.printf("%-5s %-10s %-7s $ %.2f\n", course[0], course[1], course[2], (Integer.parseInt(course[2])*costOfCredit));
                total += Integer.parseInt(course[2]) * costOfCredit;
                break;
            }
        }
    }

    System.out.printf("\n\tHealth & id fees $ 35.00");
    System.out.printf("\n\n----------------------------------");
    
    total += 35.00; //Health & ID fee

    if(this.gpa >= 3.5 && total > 500){
        System.out.printf("\n\t\t\t $ %.2f", total);
        System.out.printf("\n\t\t\t-$ %.2f", total * 0.25);
        total = total - (total * 0.25);
        System.out.print("\n                      ------------");
    }
    System.out.printf("\n\t TOTAL PAYMENTS  $ %.2f", total);

    }
}
//---------------------------

abstract class GraduateStudent extends Student {

    int TAclass;
    public GraduateStudent(String name, String id, int crn) {

        super(name, id);
        this.TAclass = crn;

    }
}
//---------------------------

class PhdStudent extends GraduateStudent {
    String advisor;
    String reasearchSubject;

    public PhdStudent(String name, String id, String advisor, String researchSubject, int crn) {

        super(name, id, crn);
        this.advisor = advisor;
        this.reasearchSubject = researchSubject;

    }

    @Override
    public void printInvoice() {

    System.out.print("VALENCE COLLEGE \nORLANDO FL 10101\n---------------\n\nFee Invoice Prepared for Student:\n" + this.id.toUpperCase() +"-"+ this.name.toUpperCase() + "\n\n");

    System.out.printf("RESEARCH\n" + this.reasearchSubject +"\t\t $ 700.00\n\n");
    System.out.printf("\tHealth & id fees $ 35.00\n\n---------------------------------\n");

    System.out.printf("\tTotal Payments : $ 735.00");

    System.out.printf("\n\n");

    }
}
//---------------------------

class MsStudent extends GraduateStudent {

    int[] gradClasses;
    public MsStudent(String name, String id, int[] gradCrnsTaken, int crn) {

        super(name, id, crn);
        this.gradClasses = gradCrnsTaken;

    }

    @Override
    public void printInvoice() {

    for (Integer crn : gradClasses) {
        for (String[] course : Student.getCourses()) {
            if(Integer.parseInt(course[0]) == crn && Integer.parseInt(course[0])< 5000){
                System.out.print("ERROR: INVALID COURSE(S)\n\n");
                return;
            }
        } 
    }

    double total = 0;
    System.out.print("VALENCE COLLEGE \nORLANDO FL 10101\n---------------\n\nFee Invoice Prepared for Student:\n" + this.id.toUpperCase() +"-"+ this.name.toUpperCase() + "\n");
    System.out.print("\n");

    System.out.print("1 Credit Hour  = $300.00\n\n");

    System.out.print("CRN   CR_PREFIX  CR_HOURS\n");
    for (Integer crn : gradClasses) {
        for (String[] course : Student.getCourses()) {
            if (Integer.parseInt(course[0]) == crn) {
                System.out.printf("%-5s %-10s %-7s $ %.2f\n", course[0], course[1], course[2], (Integer.parseInt(course[2])*300.00));
                total += Integer.parseInt(course[2]) * 300.00;
                break;
            }
        }
    }

    System.out.printf("\n\tHealth & id fees $ 35.00\n");
    System.out.printf("\n----------------------------------\n");
    System.out.printf("\tTotal Payments   $ %.2f", total + 35.00);

    System.out.printf("\n\n");
    }
}