public class CarExceptions extends Exception{
    /**
     * This class was built to make the Exceptions for the Car class.
     * @param exception
     */
    public CarExceptions(String exception){
        super("Error in: " +exception);
    }
    public static class FileExceptions extends CarExceptions {
        public FileExceptions(String message) {
            super(message);
        }
    }
}
