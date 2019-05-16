package se.devschool;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.time.LocalDateTime.now;

public class SchoolAdmin {

    private Map<String, User> userList = new HashMap<>();
    private Map<String, Classroom> classroomList = new HashMap<>();
    private Map<String, Student> studentList = new HashMap<>();
    private Map<String, Scanning> scanningList = new HashMap<>();

    String dirName = "C:\\Dev\\SchoolAdmin\\";

    public SchoolAdmin() {
        /*
        User user = new User("Marcus","Gyllencreutz","Marcus","marcus.gyllencreutz@lexicon.se","hemligt","007");
        userList.put(user.getKey(),user);
        FileIO.writeObject(userList,"userlist.ser");

        Classroom classroom = new Classroom(1,"Java","Java class");
        classroomList.put(classroom.getKey(), classroom);

        FileIO.writeObject(classroomList,"classroomlist.ser");
        Student student = new Student("Nisse",
                "Ghandi",
                "nisse",
                "nisse@ghandi.se","123456789",classroomList.get("1"));

        studentList.put(student.getKey(),student);
        FileIO.writeObject(studentList,"studentlist.ser");
*/
        LoadData();

    }

    private void LoadData () {
        userList      = FileIO.readObject(dirName + "userlist.ser");
        classroomList = FileIO.readObject(dirName + "classroomlist.ser");
        studentList   = FileIO.readObject(dirName + "studentlist.ser");
        scanningList  = FileIO.readObject(dirName + "scanninglist.ser");
    }

    private boolean login() {
        try {
            int noLogins = 0;
            while (noLogins < 3) {
                StdIO.clearScreen();
                StdIO.writeLine("");
                StdIO.writeLine("Logon");
                StdIO.writeLine("");
                StdIO.write("Username : ");
                String userName = StdIO.readLine();
                StdIO.write("Password : ");
                String password = StdIO.readLine();
                noLogins++;
                if (!checkLogin(userName,password).isEmpty()) {
                    return true;
                }
            }
            //System.exit(0);
            return false;

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
    public String checkLogin(String username, String password) {
        String returnStr = "";

        if (userList.containsKey(username) && userList.get(username).getPassword().equals(password))
            returnStr = userList.get(username).getUserName();
        return returnStr;
    }

    public void start() {

        if (userList != null && !login()) return;

        String answer = "";
        do {
            answer =menu();
            try{
                switch (answer) {
                    case "0":
                        LoadData();
                        break;
                    case "1":
                        addStudent();
                        break;
                    case "2":
                        listStudents();
                        break;
                    case "3":
                        scanArrivalDeparture();
                        break;
                    case "4":
                        listAttendance();
                        break;
                    case "5":
                        maintainUsers();
                        break;
                    case "6":
                        listUsers();
                        break;
                    case "7":
                        maintainClasses();
                        break;
                    case "8":
                        listClasses();
                        break;
                    case "88":

                        Classroom classroom = new Classroom(1,"Java","Java class");
                        classroomList.put(classroom.getKey(), classroom);

                        Student student = new Student("Kalle", "Anka", "KANKA", "kalle.anka@ankeborg.com", "189901011111", classroomList.get(classroom));
                        studentList.put(student.getKey(),student);

                        User user = new User("Super","User","SuperUser","admin@lexicon.se","Super","007");
                        userList.put(user.getKey(),user);

                    case "99":
                        listUsers(); System.out.println("");
                        listClasses();System.out.println("");
                        listStudents();System.out.println("");
                        listAttendance();System.out.println("");
                        break;
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
        } while (!answer.equalsIgnoreCase("q"));
    }

    private String menu() {
        try {
            String answer;

            StdIO.clearScreen();
            StdIO.writeLine("Menu");
            StdIO.writeLine("");
            StdIO.writeLine("0. Reload");
            StdIO.writeLine("1. Add student");
            StdIO.writeLine("2. List students");
            StdIO.writeLine("3. Scan arrival/departure");
            StdIO.writeLine("4. List attendance");
            StdIO.writeLine("5. Maintain users");
            StdIO.writeLine("6. List users");
            StdIO.writeLine("7. Maintain classes");
            StdIO.writeLine("8. List classes");
            StdIO.writeLine("");
            StdIO.writeLine("q. Exit");
            StdIO.writeLine("");
            StdIO.write("Select : ");
            answer = StdIO.readLine();
            return answer;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    private void scanArrivalDeparture() {
        try {
            String barcode = "";
            StdIO.clearScreen();
            StdIO.writeLine("Scan Arrival");
            do {
                StdIO.writeLine("");
                StdIO.write("Barcode : ");
                barcode = StdIO.readLine();
                String fullName = registerArrivalDeparture(barcode);
                if (!fullName.isEmpty()) {
                    StdIO.writeLine(fullName);
                }
            } while (!barcode.trim().equals(""));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String registerArrivalDeparture(String barcode) {
        System.out.println(barcode);
        try {
            String returnStr = "";
            if (studentList.containsKey(barcode)) {
                Student student = studentList.get(barcode);
                LocalDate date = LocalDate.now();
                if (scanningList.containsKey(student.getKey() + date.toString())) {
                    Scanning scanning = scanningList.get(student.getKey() + date.toString());
                    scanning.setCheckOutTime(LocalDateTime.now());
                    scanningList.put(scanning.getKey(), scanning);
                    returnStr = "Good bye " + student.getFullName() + "!";
                } else {
                    Scanning scanning = new Scanning(student, LocalDateTime.now(), null);
                    scanningList.put(scanning.getKey(), scanning);
                    returnStr = "Welcome " + student.getFullName() + "!";

                }
                FileIO.writeObject(scanningList, dirName + "scanninglist.ser");
                return returnStr;
            }
            return "Student with "+barcode+" not found";
        } catch (Exception e) {
            e.printStackTrace();
            return "EXCEPTION: " +e;
        }
    }

    public String listArrivalDeparture() {
        try {
            String returnStr = "";
            for (Scanning x : scanningList.values()) {
                returnStr += x.toString() +"\r\n";
            }

            return returnStr;
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR: " +e;
        }
    }

    private void listStudents() {
        System.out.println(Student.toStringHeader());
        studentList.forEach((k,v) -> System.out.println(v.toStringLine()));
    }
    private void addStudent() {
        try {
            StdIO.clearScreen();
            listStudents();
            StdIO.writeLine("");
            StdIO.writeLine("Add a student");
            StdIO.writeLine("");
            StdIO.write("First name : ");
            String firstName = StdIO.readLine();
            StdIO.write("Last name : ");
            String lastName = StdIO.readLine();
            StdIO.write("User name : ");
            String userName = StdIO.readLine();
            StdIO.write("email : ");
            String email = StdIO.readLine();
            StdIO.write("Barcode : ");
            String barcode = StdIO.readLine();
            StdIO.write("Class ID : ");
            String classroom = StdIO.readLine();
            Student student = new Student(firstName, lastName, userName, email, barcode, classroomList.get(classroom));
            studentList.put(student.getKey(),student);
            FileIO.writeObject(studentList,dirName + "studentlist.ser");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private void listAttendance() {
        scanningList.forEach((k,v) -> System.out.println(v));
    }

    private  void listUsers() {
        userList.forEach((k,v)-> System.out.println(v));
    }

    private void maintainUsers() {
        try {
            StdIO.clearScreen();
            listUsers();
            StdIO.writeLine("");
            StdIO.writeLine("Add a user");
            StdIO.writeLine("");
            StdIO.write("First name : ");
            String firstName = StdIO.readLine();
            StdIO.write("Last name : ");
            String lastName = StdIO.readLine();
            StdIO.write("User name : ");
            String userName = StdIO.readLine();
            StdIO.write("email : ");
            String email = StdIO.readLine();
            StdIO.write("Password : ");
            String password = StdIO.readLine();
            StdIO.write("Barcode : ");
            String barcode = StdIO.readLine();
            User user = new User(firstName,lastName,userName,email,password,barcode);
            userList.put(user.getKey(),user);
            FileIO.writeObject(userList,dirName + "userlist.ser");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void listClasses() {
        classroomList.forEach((k,v)-> System.out.println(v));
    }

    private void maintainClasses() {
        try {
            StdIO.clearScreen();
            listClasses();
            StdIO.writeLine("");
            StdIO.writeLine("Add a class");
            StdIO.writeLine("");
            StdIO.write("Class ID : ");
            String classID = StdIO.readLine();
            StdIO.write("Classname : ");
            String className = StdIO.readLine();
            StdIO.write("Decsription : ");
            String description = StdIO.readLine();

            Classroom classroom = new Classroom(Integer.parseInt(classID),className,description);
            classroomList.put(classroom.getKey(),classroom);
            FileIO.writeObject(classroomList,dirName + "classroomlist.ser");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
