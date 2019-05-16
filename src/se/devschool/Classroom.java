package se.devschool;

public class Classroom  implements java.io.Serializable{
    private int classId;
    private String className;
    private String description;

    public Classroom(int classId, String className, String description) {
        this.classId = classId;
        this.className = className;
        this.description = description;
    }

    public String getKey() {
        return Integer.toString(classId).trim();
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Classroom{" +
                "classId=" + classId +
                ", className='" + className + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
