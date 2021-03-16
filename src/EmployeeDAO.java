import java.util.List;

public interface EmployeeDAO {
    public List<Employee> getAllEmp();
    public void addEmp(Employee newEmp);
    public void updateEmp(Employee emp);
    public void deleteEmp(int id);
    public Employee findEmp(int id);
}