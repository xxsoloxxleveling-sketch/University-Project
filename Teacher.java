public class Teacher {
    private String name;
    private String subject;
    private int Id;
    private int age;
 public void setName(String name) {
        this.name = name;
    }
 public String getName() {
        return this.name;
    }
 public void setSubject(String subject) {
        this.subject = subject;
    }
 public String getSubject() {
        return this.subject;
    }
 public void setId(int Id) {
        this.Id = Id;
    }
 public int getId() {
        return this.Id;
    }
 public void setAge(int age) {
        this.age = age;
    }
 public int getAge() {
        return this.age;
    }
 public void display() {
        System.out.println("Name: " + this.name);
        System.out.println("Subject: " + this.subject);
        System.out.println("ID: " + this.Id);
        System.out.println("Age: " + this.age);
    }
}