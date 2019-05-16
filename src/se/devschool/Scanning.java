package se.devschool;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Scanning  implements java.io.Serializable{
    private Student student;
    private LocalDate date;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;

    public Scanning(Student student, LocalDateTime checkInTime, LocalDateTime checkOutTime) {
        this.student = student;
        if (checkInTime != null)
            setCheckInTime(checkInTime);
        if (checkOutTime != null)
            setCheckOutTime(checkOutTime);
    }

    public String getKey () {
        return student.getKey() + date.toString();
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(LocalDateTime checkInTime) {
        if (date == null)
            date = checkInTime.toLocalDate();
        this.checkInTime = checkInTime;
    }

    public LocalDateTime getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(LocalDateTime checkOutTime) {
        if (date == null)
            date = checkInTime.toLocalDate();
        this.checkOutTime = checkOutTime;
    }

    public String toString() {
        String returnString = Str.padRight(student.getFullName(),30);
//        returnString += student.getClassroom().getClassName()+ " ";
        returnString += date.toString() + " ";
        returnString += getCheckInTime().toLocalTime().toString().substring(0,8) + " ";
        if (getCheckOutTime() != null)
            returnString += getCheckOutTime().toLocalTime().toString().substring(0,8);
        return returnString;
    }
}
