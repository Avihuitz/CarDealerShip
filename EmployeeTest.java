public class EmployeeTest {
    public static void main(String[] args) throws Exception{
        Employee employee1 = new Employee("Ben", "207695149", 3);
        try {
            Employee employee = new Employee("Max", "207695156", 2);
            Car car = new Car(123223, 2022, "Toyota", 30000, 25000);
            employee.carSale(car);
        } catch (EmployeeExceptions | CarExceptions e) {
            System.out.println(e);
        }
    }
}
