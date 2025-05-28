import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collection;

/**
 * This class designed to program a friendly menu to the costumers of our Car Agency,
 * in this class the costumer gets a menu which he can choose an employee to help him,
 * and a car to buy, also he can upload his car to our service, and we can sell it for him.
 * This class has handles the exception problem related to the costumer wrong inputs,
 * and taking care of it in a friendly way.
 * This class saves all the important data in their records
 */

public class CarDealerShip {
    public static void checkChoice(int choice) throws EmployeeExceptions{
        if(choice<=0 || choice>5){
            throw new EmployeeExceptions("Number should be between 0-5");
        }
    }
    enum MenuOption {
        DISPLAY_EMPLOYEES(1),
        DISPLAY_UNSOLD_CARS(2),
        SELL_CAR(3),
        ADD_CAR(4),
        EXIT(5);
        final int j;
        MenuOption(int j){
            this.j =j;
        }
        public int getJ() {
            return j;
        }

        /**This method gets an integer from the user and convert it to a ENUM option from options
         * this as a helper method for the user input in the scanner
         * @param value A given number from the user
         * @return the ENUM that been asked
         */
        public static MenuOption fromValue(int value) {
            for (MenuOption option : MenuOption.values()) {
                if (option.getJ() == value) {
                    return option;
                }
            }
            return null;
        }
    }
    /**This method is used to sort the array by the top seller to the least successful.
     *
     * @param list list given
     * @param <T> reference type
     */

    public static <T extends Comparable<T>> void sortList(ArrayList<T> list) {
        Collections.sort(list);
        Collections.reverse(list);
    }

    public static void main(String[] args) throws Exception,EmployeeExceptions,CarExceptions {

        ArrayList<Car> car = new ArrayList<>();

        ArrayList<Employee> employees = new ArrayList<>();
        /**
         * Try and catch to initialize the instances
         */
        String[] parts;
        Path filePath = Paths.get("CarAgency/src/Employee.txt");
        try {
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                parts = line.split("");
                String name = parts[0];
                String id = parts[1];
                int salesCount = Integer.parseInt(parts[2]);
                Employee employee = new Employee(name, id, salesCount);
                employees.add(employee);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        /**
         * Extracting the text into the Arraylist, also blocked it in try-catch to make sure it's valid
         */

        String[] parts1;
        Path filePath1 = Paths.get("CarAgency/src/CarDealerShip.txt");
        try {
            List<String> lines1 = Files.readAllLines(filePath1);
            for (String line : lines1) {
                parts1 = line.split(" ");
                int carId = Integer.parseInt(parts1[0]);
                int year = Integer.parseInt(parts1[1]);
                String manufactureName = parts1[2];
                int km = Integer.parseInt(parts1[3]);
                int price = Integer.parseInt(parts1[4]);
                Car car1 = new Car(carId, year, manufactureName, km, price);
                car.add(car1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        MenuOption choice;
        System.out.println("Hello and welcome to the CarDealerShip menu, \nyou may select a number" +
                "between 1-5 each number represent an option in the menu.\n");

        boolean exit = true;
        //Try-catch for exceptions.
        while(exit) {
            try {
                //Getting the user input
                System.out.println(""+
                        "Option 1: Shows the list employees who works in our CarAgency\n" +
                        "Option 2: Represent the cars the still unsold\n" +
                        "Option 3: Choose a car based of on the employee you've chosen\n" +
                        "Option 4: Add car to sell\n" +
                        "Option 5: Leave menu");
                System.out.print("Enter your Choice: ");

                Scanner scanner = new Scanner(System.in);
                int userChoice = scanner.nextInt();
                checkChoice(userChoice);
                //Convert it to ENUM choices
                choice = MenuOption.fromValue(userChoice);
                //Options in switch case
                switch (choice) {
                    case DISPLAY_EMPLOYEES:
                        //Displaying the Employees by using enhanced for-loop
                        sortList(employees);
                        System.out.println("\nEmployee List:");
                        for (Employee employee : employees) {
                            System.out.println(employee.getName() + " " + employee.getId() + " " + employee.getNumSales());
                            System.out.println("-----------------");
                        }
                        break;
                    case DISPLAY_UNSOLD_CARS:
                        //Displaying the unsold cars by using enhanced for-loop
                        System.out.println("List of unsold cars: ");
                        for(Car car1: car){
                            System.out.println("Car number: "+car1.getCarID()+", Maunfacturing year: "+car1.getManufactureDate()+"\n" +
                                    "Manufacturer: "+ car1.getManufactureName()+", Mileage: "+car1.getKm()+", Price: "+car1.getPrice());
                            System.out.println("--------------");
                        }
                        break;
                    case SELL_CAR:
                        Scanner scanner1 = new Scanner(System.in);
                        System.out.println("Here our employees:\n");
                        //Printing the list employees to show the employees options to the user.
                        for(Employee employee:employees){
                            System.out.println("Employee name - "+employee.getName()+" Employee id - "+employee.getId()+"\n-------------");
                        }
                        System.out.println("Please choose employee to help you choose cards.\n To choose employee you should " +
                                "enter their id in the requested area\n");
                        System.out.print("Enter employee id you want to work with: ");
                        String id = null;
                        //Flag for the while loop
                        boolean flag = false;
                        int i = 0;
                        //Unlimited tries to get a valid ID.
                        while(!(flag)) {
                            id = scanner1.nextLine();
                            for(Employee employee:employees){
                                if(employee.getId().equals(id)){
                                    flag = true;
                                    break;
                                }
                                i++;
                            }
                            if(flag == false){System.out.print("Incorrect id.\n" +
                                    "Please insert id: ");}
                        }
                        System.out.println("Here is a list of unsold cars for you to choose to buy:\n");
                        System.out.println("List of unsold cars: ");
                        //Displaying the car for the costumer to choose.
                        for(Car car1: car){
                            System.out.println("Car number: "+car1.getCarID()+", Maunfacturing year: "+car1.getManufactureDate()+"\n" +
                                    "Manufacturer: "+ car1.getManufactureName()+", Mileage: "+car1.getKm()+", Price: "+car1.getPrice());
                            System.out.println("--------------");
                        }

                        System.out.print("If you want to buy one of the unsold cars type it ID: ");
                        int j = 0;
                        int chooseId;
                        Scanner scanner2 = new Scanner(System.in);
                        //Flag for the while loop
                        boolean flag1 = false;
                        while (!(flag1)){
                            chooseId = scanner2.nextInt();
                            //Unlimited tries for the user
                            for(Car car1 :car){
                                if(car1.getCarID() == chooseId){
                                    flag1 = true;
                                    break;
                                }
                                j++;
                            }
                            //in case the user input was wrong
                            if(flag1==false){
                                System.out.print("Incorrect car id, Please enter a correct car id: ");
                            }
                        }
                        employees.get(i).setSalesCount((employees.get(i).getNumSales()+1));
                        Path filePath2 = Paths.get("CarAgency/src/Sold.txt");
                        String Sold = "Salesman:" +employees.get(i).getName()+ ", carID:"+ car.get(j).getCarID() +", Number of car sold:"+
                                employees.get(i).getNumSales();
                        Files.write(filePath2,Sold.getBytes(),StandardOpenOption.CREATE,StandardOpenOption.APPEND);
                        Files.writeString(filePath2,"\n",StandardOpenOption.APPEND);
                        car.remove(j);
                        break;
                    case ADD_CAR:
                        //Creating a scanner which the user will use to enter he details
                        Scanner scanner3 = new Scanner(System.in);
                        String manufactureName;

                        System.out.print("Enter CarID: ");
                        int id3 = scanner3.nextInt();

                        System.out.print("Enter car's manufacture date: ");
                        int manufactureDate = scanner3.nextInt();

                        scanner3.nextLine();
                        System.out.print("Enter car's manufacturer: ");

                        manufactureName = scanner3.nextLine();
                        System.out.print("Enter car's mileage: ");

                        int km = scanner3.nextInt();
                        System.out.print("Enter car's price: ");

                        int price = scanner3.nextInt();
                        //Creating an instance to enter the user demands.
                        Car car1 = new Car(id3,manufactureDate,manufactureName,km,price);
                        car.add(car1);
                        break;
                    case EXIT:
                        //NewCarDealerShip.txt collect data of unsold cars.
                        Path fileP = Paths.get("CarAgency/src/NewCarDealerShip.txt");
                        for(Car car4:car){
                            String unSold = car4.unSold();
                            Files.write(fileP,unSold.getBytes(),StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                        }
                        String divide = "--------\n";
                        Files.write(fileP,divide.getBytes(),StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                        exit = false;
                        break;
                    default:
                }

            } catch (EmployeeExceptions | IOException e) {
                System.out.println(e.getMessage()+"\n");
            }
            catch (CarExceptions e){
                System.out.println(e.getMessage()+"\n--------");
            }
        }

    }
}
