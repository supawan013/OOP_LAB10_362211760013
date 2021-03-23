import java.util.List;
import java.util.Scanner;

public class CompanyManagement {
    public static void main(String[] args) {
        //Create instance
        EmployeeDAOImpl dao = EmployeeDAOImpl.getInstance();

        //display all employee data
        //displayAllEmp(dao);

        //add new employee
        //addNewEmp(dao);

        findEmpByID(dao);

        //update
        updateEmp(dao);


    }//main

    private static void updateEmp(EmployeeDAOImpl dao) {
        //search employee data
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter an employee id?:");
        String id = sc.nextLine();

        Employee emp = dao.findById(id);
        //update
        System.out.println("Employee info: ");
        System.out.println(emp.toString());
        //edit
        System.out.print("Enter new position");
        String p =sc.nextLine();
        emp.setPosition(p);
        //update new data
        dao.UpdateEmp(emp);
    }

    private static Employee findEmpByID(EmployeeDAOImpl dao) {
        Employee emp = dao.findById("EMP005");
        if (emp != null){
            System.out.println(emp.toString());
        }
        return emp;
    }

    private static void addNewEmp(EmployeeDAOImpl dao) {
        Employee myEmp = new Employee("EMP005","JD BC","Student","JD@gmail.com",50000);
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
