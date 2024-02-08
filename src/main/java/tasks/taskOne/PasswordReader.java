package tasks.taskOne;

import java.io.*;
import java.time.LocalDate;

public class PasswordReader {
    static boolean checkInputData(String file) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {

            while (br.ready()) {
                String line = br.readLine();

                String[] array = line.split(" ");
                if (array[0].length() > 20) {
                    throw new WrongLogin("Wrong login!\nThe password cannot be more than 20 characters long");
                }
                if (!(array[1].equals(array[2]))) throw new WrongPassword("The entered passwords do not match!");
                if (array[1].length() > 20) throw new WrongPassword("Your password is less than 20 characters long!");
            }
        } catch (IOException | WrongLogin | WrongPassword e) {
            logging(e.getMessage());
            return false;
        }
        logging("All is fine");
        return true;
    }

    public static void logging(String message) {
        try (FileWriter fw = new FileWriter("Logging", true)) {
            fw.write(message + " " + LocalDate.now());
            fw.write("\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
