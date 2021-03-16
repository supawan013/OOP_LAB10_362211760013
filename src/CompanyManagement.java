import java.util.List;

public class CompanyManagement {
    public static void main(String[] args) {
        //Create instance
        EmployeeDAOImpl dao = EmployeeDAOImpl.getInstance();

        //display all employee data
        //displayAllEmp(dao);

        //add new employee
        //addNewEmp(dao);

        findEmpByID(dao);

    }//main

    private static void findEmpByID(EmployeeDAOImpl dao) {
        Employee emp = dao.findById("EMP005");
        if (emp != null){
            System.out.println(emp.toString());
        }
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
