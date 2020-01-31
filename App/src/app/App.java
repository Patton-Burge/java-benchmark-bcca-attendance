package app;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        ArrayList<Student> students = loadStudents();

        Student newStudent = startDay();

        students.add(newStudent);
        saveStudents(students);
    }

    public static Student startDay() {
        System.out.print("Good Morning could I get a name please:  ");
        String name = in.nextLine();
        System.out.print("What time did you get here? Enter in this format (X.XX) or 0 if you did not show up: ");
        String time = in.nextLine();
        return new Student(name, time);
    }

    private static void saveStudents(ArrayList<Student> students) {
        try {
            FileOutputStream fileStream = new FileOutputStream("students.ser");
            ObjectOutputStream os = new ObjectOutputStream(fileStream);
            os.writeObject(students);
            os.close();

        } catch (IOException ex) {
            System.out.println("Failed to save orders.");
        }
    }

    public static ArrayList<Student> loadStudents() {
        try {
            FileInputStream fileStream = new FileInputStream("students.ser");
            ObjectInputStream os = new ObjectInputStream(fileStream);
            ArrayList<Student> students = (ArrayList<Student>) os.readObject();
            os.close();
            return students;
        } catch (IOException | ClassNotFoundException ex) {
            return new ArrayList<Student>();
        }
    }
}