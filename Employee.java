import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * This class designed to record Employees names,id, and sales count
 * this class have feature of recording data
 */
public class Employee implements Comparable<Employee>{
    private String name;
    private String id;
    private int salesCount;

    /**
     *
     * @param name Given name of employee
     * @param id given id of employee
     * @param numSales given number of car the employee have sold
     */
    public Employee(String name,String id,int numSales){
        this.name = name;
        this.id = id;
        this.salesCount = numSales;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getNumSales() {
        return salesCount;
    }

    /**
     * this methods check if the input that entered is valid
     * @throws EmployeeExceptions throws a problem with the context of it
     */
    public void CheckName() throws EmployeeExceptions{
        if(!name.matches("[a-zA-Z]+")){
            throw new EmployeeExceptions("Name must contains letters only.");
        }
    }

    /**
     * this methods check if the input that entered is valid
     * @throws EmployeeExceptions
     */
    public void CheckId() throws EmployeeExceptions{
        if(getId().length()!=9){
            throw new EmployeeExceptions("ID suppose to be Exactly 9 digits");
        }
    }

    /**
     * this methods check if the input that entered is valid
     * @throws EmployeeExceptions
     */
    public void ChecknumSells() throws EmployeeExceptions{
        if(getNumSales()<0){
            throw new EmployeeExceptions("Number of Sales cannot be negative");
        }
    }

    public void setSalesCount(int salesCount) {
        this.salesCount = salesCount;
    }

    /**
     * This method record the car that have been sold
     * @param car given sold car
     * @throws CarExceptions throws a problem with context
     */

    public void carSale(Car car) throws CarExceptions {
        try {
            // Use the car's sellCar method to write the car details to the file
            car.sellCar();

            // Increment the sales count for the employee
            salesCount++;

            // Write the employee's sale details to the same file
            Path filePath = Paths.get("CarAgency/car_sales.txt");
            String saleDetails = "Employee: " + name + " (ID: " + id + "), Total Sales: " + salesCount  + "\n";
            Files.write(filePath, saleDetails.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);

        } catch (IOException e) {
            throw new CarExceptions.FileExceptions("Failed to record the sale.");
        }
    }
    //Salary of the employee always start's with 6000, and every sold car by the employee giving to the employee 100$ bonus
    public int calculateSalary() {
        return 6000 + (salesCount * 100);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public int compareTo(Employee other) {
        return Integer.compare(this.salesCount, other.salesCount);
    }
}
