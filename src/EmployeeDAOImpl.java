import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class EmpDAOImpl implements EmployeeDAO {
    public static String driverName = "org.sqlite.JDBC";
    public static String url = "jdbc:sqlite:D:/company.sqlite";
    public static Connection conn = null;

    //Constant operators
    public static final String GET_ALL_EMP = "select * from employee";
    public static final String ADD_NEW_EMP = "insert into employee " +
            "(id,name,position,salary) values(?,?,?,?)";
    public static final String UPDATE_EMP = "update employee set " +
            "name = ?, position = ?, salary = ? where id = ?";
    public static final String FIND_BY_ID = "select * " +
            "from employee where id = ?";
    public static final String DELETE_EMP = "delete from " +
            "employee where id = ?";

    private static EmpDAOImpl instant = new EmpDAOImpl();
    public static EmpDAOImpl getInstant(){
        return instant;
    }
    //constructor



    private EmpDAOImpl() {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Load Driver Successfully.");
    }

    @Override
    public List<Employee> getAllEmp() {
        List<Employee> emp = new ArrayList<Employee>();

        try {
            conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(GET_ALL_EMP);

            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String position = rs.getString(3);
                double salary = rs.getDouble(4);

                emp.add(new Employee(id, name, position, salary));
            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return emp;
    }

    @Override
    public void addEmp(Employee newEmp) {

        try {
            conn = DriverManager.getConnection(url);
            PreparedStatement ps = conn.prepareStatement(ADD_NEW_EMP);

            ps.setInt(1,newEmp.getId());
            ps.setString(2,newEmp.getName());
            ps.setString(3,newEmp.getName());
            ps.setDouble(4,newEmp.getSalary());

            boolean rs = ps.execute();
            if (rs == true) {
                System.out.println("Could not add data.");
                System.exit(1);
            }
            System.out.println("Already add new employee.");
            ps.close();
            conn.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateEmp(Employee emp) {

        try {
            conn = DriverManager.getConnection(url);
            PreparedStatement ps = conn.prepareStatement(UPDATE_EMP);

            ps.setString(1,emp.getName());
            ps.setString(2,emp.getPosition());
            ps.setDouble(3,emp.getSalary());
            ps.setInt(4,emp.getId());

            int rs = ps.executeUpdate();
            if (rs != 0) {
                System.out.println("Information of employee with id "
                        + emp.getId() + " was updated.");
            } else {
                System.out.println("Could not update information " +
                        "with employee id:"+emp.getId());
            }
            ps.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteEmp(int id) {

        try {
            conn = DriverManager.getConnection(url);
            PreparedStatement ps = conn.prepareStatement(DELETE_EMP);
            ps.setInt(1,id);
            int rs = ps.executeUpdate();
            if (rs != 0) {
                System.out.println("Employee with id:"
                        + id + " was deleted.");
            } else {
                System.out.println("Could not delete " +
                        "employee with id: "+id);
            }
            ps.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public Employee findEmp(int id) {
        Employee emp = null;

        try {
            conn = DriverManager.getConnection(url);
            PreparedStatement ps = conn.prepareStatement(FIND_BY_ID);

            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int idd = rs.getInt(1);
                String name = rs.getString(2);
                String position = rs.getString(3);
                double salary = rs.getDouble(4);
                emp = new Employee(idd, name, position, salary);
            }else {
                System.out.println("Could not find " +
                        "employee with id:" + id);
            }
            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emp;

    }
}//class