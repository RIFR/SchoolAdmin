package se.devschool;

public class Student  implements java.io.Serializable{
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String barCode;
    private Classroom classroom;

    public Student(String firstName, String lastName, String userName, String email, String barCode, Classroom classroom) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.barCode = barCode;
        this.classroom = classroom;
    }

    public String getKey() {
        return barCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public static String toStringHeader() {
        String returnString;
        returnString  = Str.padRight("Firstname",16);
        returnString += Str.padRight("Lastname",16);
        returnString += Str.padRight("Username",11);
        returnString += Str.padRight("email",21);
        returnString += Str.padRight("Barcode",14);
        returnString += "Class";
        return returnString;
    }

    public String toStringLine() {
        String returnString;
        returnString  = Str.padRight(getFirstName(),16);
        returnString += Str.padRight(getLastName(),16);
        returnString += Str.padRight(getUserName(),11);
        returnString += Str.padRight(getEmail(),21);
        returnString += Str.padRight(getBarCode(),14);
        returnString += getClassroom().getClassName();
        return returnString;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", barCode='" + barCode + '\'' +
                ", classroom=" + classroom +
                '}';
    }
}
