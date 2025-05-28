import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CarTest {
    public static void main(String[] args) throws Exception{
        Car c0 = null;
        Car c1 = null;
        Car c2 = null;
        Car[] car = new Car[3];
        int j = 0;
        /**This try-catch block is used in a way to minimizes uses of multiple try-catch blokes, in a way that
         * we are encapsulating each instance in a Car type array and checking through with a for loop which one is
         * a correct instance, when its find an instance with an error its pointing which instance was the problematic, and
         * we fix the error.
         *
         * @param j is used to count the iteration through the indexes of the array and when there is a problematic instance,
         *          then it's getting linked to the "c" instance that had the problem.
         *
         *          An important note, each instance name is starts with "c" for reaching problems easier if we find.
         *
         */

        try{
            c0 = new Car(123233,2022,"Adiv",30000,100000);
            car[j++] = c0;
            c1 = new Car(113311,2023,"Nissan",90000,17000);
            car[j++] = c1;
            c2 = new Car(143333,2017,"Daihatsu",100,500001);
            car[j++] = c2;
            for(int i = 0; i<car.length;i++){
                System.out.println(car[i]);
            }
        }
        catch (CarExceptions e) {
            System.out.println(e.getMessage()+ " (Problem Occurred in - Array named: c"+j+")");
        }

        try {
            c1.discount(2);
            System.out.println(c1);
        }
        catch (CarExceptions e){
            System.out.println();
            System.out.println(e.getMessage());
        }
        //-----------------------
        //Try and catch for collecting data in to txt
        try {
            System.out.println();
            for(Car cars : car){
                cars.sellCar();
            }
        } catch (CarExceptions e) {
            System.out.println(e.getMessage());
        }
    }
}
