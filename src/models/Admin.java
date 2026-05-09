package models;

public class Admin extends Employee {
    private int accessLevel;

    public Admin(String login, String password,
                 double salary, String department,
                 int accessLevel) {
        super(login, password, salary, department);
        this.accessLevel = accessLevel;
    }

    public void manageUsers() {
        System.out.println("Managing users...");
    }

    public void viewLogs() {
        System.out.println("Viewing logs...");
    }

    public int getAccessLevel() { return accessLevel; }

    @Override
    public String getInfo() {
        return "Admin: " + getLogin() + ", AccessLevel: " + accessLevel;
    }
}
