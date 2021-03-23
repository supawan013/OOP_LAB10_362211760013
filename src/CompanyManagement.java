import java.util.List;
import java.util.Scanner;


public class CompanyManagement {
    public static void main(String[] args) {
        //Create instance
        EmployeeDAOImpl dao = EmployeeDAOImpl.getInstance();

        //display options list
        int s = 0;
        do{
            System.out.println("Please, select option below: ");
            System.out.println("1.Display all employee.");
            System.out.println("2.Add new employee.");
            System.out.println("3.Find employee by ID.");
            System.out.println("4.Update employee data.");
            System.out.println("5.Delete employee data.");
            System.out.println("6.Exit.");
            System.out.print("Select?: ");

            Scanner sc = new Scanner(System.in);
            System.out.print("Please, select number(1-6) : ");
            s = Integer.parseInt(sc.nextLine());


            switch (s){
                case 1:displayAllEmp(dao);
                    break;
                case 2:addNewEmp(dao);
                    break;
                case 3:findEmpByID(dao);
                    break;
                case 4:updateEmp(dao);
                    break;
                case 5:deleteEmp(dao);
                    break;
                case 6:System.exit(1);
                default:System.out.println("Please select specufic number (1-6)! ");
            }
        }while (s != 0);





        //display all employee data
        //displayAllEmp(dao);

        //add new employee
        //addNewEmp(dao);

        //findEmpByID(dao);

        //update employee
        //updateEmp(dao);

        //delete
        //deleteEmp(dao);

    }//main

    private static void deleteEmp(EmployeeDAOImpl dao) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter employee id that you want delete? ");
        String s = sc.nextLine();
        dao.deleteEmp(s);
    }

    private static void updateEmp(EmployeeDAOImpl dao) {
        //Search data
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter an employee id : ");
        String id = sc.nextLine();

        Employee emp = dao.findById(id);

        //update
        System.out.println("Employee info : ");
        System.out.println(emp.toString());

        //edit
        System.out.println("Enter new position? : ");
        String p = sc.nextLine();
        emp.setPosition(p);

        dao.UpdateEmp(emp);

    }

    private static Employee findEmpByID(EmployeeDAOImpl dao) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter an employee id : ");
        String id = sc.nextLine();

        Employee emp = dao.findById(id);

        if (emp != null){
            System.out.println(emp.toString());
        }

        return emp;
    }

    private static void addNewEmp(EmployeeDAOImpl dao) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter data for new employee : ");
        System.out.println("Employee id : ");
        String id = sc.nextLine();

        System.out.println("Employee name : ");
        String name = sc.nextLine();

        System.out.println("Employee position : ");
        String position = sc.nextLine();

        System.out.println("Employee email : ");
        String email = sc.nextLine();

        System.out.println("Employee salary : ");
        double salary = Double.parseDouble(sc.nextLine());


        Employee myEmp = new Employee(id,name,position,email,salary);
        dao.addEmp(myEmp);
    }

    private static void displayAllEmp(EmployeeDAOImpl dao) {
        List<Employee> emp = dao.getAllEmp();
        System.out.println("Employee Data : ");
        for (Employee e: emp) {
            System.out.println(e.toString());
        }//for

    }

}//class