package rxjavaS.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class DummyDatabase {
    private static List<Student> userList = new ArrayList<>();

    private static DummyDatabase ourInstance = new DummyDatabase();

    public static DummyDatabase getInstance() {
        populateDatabase();
        return ourInstance;
    }

    private DummyDatabase() {
    }

    private static void populateDatabase(){
        userList.add(new Student("StudentA", 19));
        userList.add(new Student("StudentB", 21));
        userList.add(new Student("StudentC", 22));
        userList.add(new Student("StudentD", 23));
    }

    public Student getStudent(int userIndex){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userList.get(userIndex);
    }

    public List<Student> getAllStudents(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userList;
    }

}
