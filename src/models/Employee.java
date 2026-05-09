package models;

import communication.Message;
import java.util.Date;

public abstract class Employee extends User {
    private double salary;
    private String department;
    private Date hireDate;

    public Employee(String login, String password,
                    double salary, String department) {
        super(login, password);
        this.salary = salary;
        this.department = department;
        this.hireDate = new Date();
    }

    public void sendMessage(Employee to, String body) {
        Message msg = new Message(this, to, body);
        msg.send();
    }

    public double getSalary() { return salary; }
    public String getDepartment() { return department; }
    public Date getHireDate() { return hireDate; }

    @Override
    public String toString() {
        return "Employee{login='" + getLogin() + "', dept=" + department + "}";
    }
}
