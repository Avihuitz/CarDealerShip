import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Car {

    private int carID;
    private int manufactureDate;
    private String manufactureName;
    private int km;
    private double price;

    /**
     *
     * @param carId car ID that holds 6 digits(integers)
     * @param manufactureDate date birth of the car
     * @param manufactureName name of the manufacturer
     * @param km Kilometers that the car had driven
     * @param price the price of the car
     * @throws Exception exceptions needed to control and lead the user
     */

    public Car(int carId, int manufactureDate, String manufactureName, int km, int price) throws Exception {
        this.carID = carId;
        this.manufactureDate = manufactureDate;
        this.manufactureName = manufactureName;
        this.km = km;
        this.price = price;
        checkID();
        checkManufactureDate();
        checkManufactureName();
        checkKM();
        checkPrice(); // Ensure the price is above the minimum threshold of 10,000
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCarID() {
        return carID;
    }

    /**
     * this method is to check if ID is correct (0-6 numbers)
     * @throws CarExceptions Throws the correct exception to the situation
     */

    public void checkID() throws CarExceptions {
        if (carID < 100000 || carID >= 1000000) {
            throw new CarExceptions("Car ID doesn't qualify the requirements: " + getCarID());
        }
    }

    public int getManufactureDate() {
        return manufactureDate;
    }

    public int getKm() {
        return km;
    }

    public double getPrice() {
        return price;
    }

    public String getManufactureName() {
        return manufactureName;
    }

    /**
     * this method checks if the manufacture date is correct
     * @throws CarExceptions Throws the correct exception to the situation
     */

    public void checkManufactureDate() throws CarExceptions {
        if (manufactureDate > 2023 || manufactureDate < 2017) {
            throw new CarExceptions("This car manufacture date is not suitable for out Agency: " + getManufactureDate());
        }
    }

    /**
     * this method checks if the manufacture name is correct.
     * @throws CarExceptions Throws the correct exception to the situation
     */
    public void checkManufactureName() throws CarExceptions {
        if (manufactureName.length() <= 2) {
            throw new CarExceptions("Manufacture name must be longer than two letters.");
        }
    }

    /**
     * this method correct if the km is not a negative number.
     * @throws CarExceptions Throws the correct exception to the situation
     */
    public void checkKM() throws CarExceptions {
        if(km < 0){
            throw new CarExceptions("Invalid km typed, km can't be negative: "+ getKm());
        }
    }

    /**
     * this method checks if the price given is not negative and above 10000.
     * @throws CarExceptions
     */
    public void checkPrice() throws CarExceptions{
        if(price<=10000){
            throw new CarExceptions("Our car agency does not accept prices lower than: " + getPrice());
        }
    }

    /**
     * String given as asked in the assignment.
     * @return
     */
    @Override
    public String toString(){
        return "Car details:\n" + "CarID:"+ carID + " ,Car nanufacture name:"+getManufactureName()+" ,Car manufacture date:" + getManufactureDate()+
                " ,Car km:"+getKm()+" ,Car price:"+getPrice();
    }

    /**
     * A helper method build for printing as asked to text
     * @return
     */
    public String unSold(){
        return getCarID()+" "+getManufactureDate() + " "+ getManufactureName()+ " "+getKm()+" " +getPrice()+"\n";
    }

    /**
     * this method check if the given down price is correct according the asked assignment
     * @param percentage given rate of discount
     * @throws Exception Throws the correct exception to the situation
     */
    public void discount(double percentage) throws Exception {
        double perc1 = 0;
        perc1 = percentage/100;
        if(perc1>=100 || perc1<=0){
            throw new CarExceptions("percentage might be negative or over 100: "+ percentage+"%");
        }
        if(price*perc1>5000){
            throw new CarExceptions("down price cannot be over 5000, please choose lower percentage rate than: "+percentage+"%");
        }
        price =price - price*perc1;
    }

    public void sellCar() throws CarExceptions {
        Path filePath = Paths.get("CarAgency/car_sales.txt");
        String carDetails = carID + " " + manufactureDate + " " + manufactureName + " " + km + " " + price +"\n" + "---------------------------";

        try {
            Files.write(filePath, carDetails.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);

            // Append a new line after writing the car details
            Files.writeString(filePath, "\n", StandardOpenOption.APPEND);
        } catch (IOException e) {

            throw new CarExceptions.FileExceptions("An error occurred while writing the car details to the file.");
        }
    }
}
