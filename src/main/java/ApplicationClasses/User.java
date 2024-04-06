package applicationclasses;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;



public class User {
    private static final Logger logger = Logger.getLogger(User.class.getName());

    boolean logState;
    private  String username;
    private  String address;

    private  String phone;
    private  String email;
    private  String gender;

    private  String password;
    private String birthDate;

    public static final List<User> allUsers = new ArrayList<>();

    public User(String username, String password, String address, String phone, String email, String gender) {
        this.username = username;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.gender = gender;

    }

    public static List<User> getUserList() {
        return allUsers;
    }

    public static void initializeUsers() {
        User r1 = new User("sara", "111", "QAM", "02872228", "sara@gmail.com", "Female");
        allUsers.add(r1);
        Logging.getQ().put(r1.getEmail(), r1.getPassword());

        User r2 = new User("john", "222", "ABC", "01234567", "john@gmail.com", "Male");
        allUsers.add(r2);
        Logging.getQ().put(r2.getEmail(), r2.getPassword());

        User r3 = new User("emma", "333", "XYZ", "98765432", "emma@gmail.com", "Female");
        allUsers.add(r3);
        Logging.getQ().put(r3.getEmail(), r3.getPassword());

        User r4 = new User("mike", "444", "LMN", "55555555", "mike@gmail.com", "Male");
        allUsers.add(r4);
        Logging.getQ().put(r4.getEmail(), r4.getPassword());

        User r5 = new User("lisa", "555", "PQR", "66666666", "lisa@gmail.com", "Female");
        allUsers.add(r5);
        Logging.getQ().put(r5.getEmail(), r5.getPassword());
    }
   public User(String un, String pa,String bd) {
        this.username =un;
        this.password=pa;
        this.birthDate =bd;
    }


    // Getters and setters for all properties

    public  String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public  String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public  String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public  String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static User getUserByEmail(String email) {
        for (User user : allUsers) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null; // User not found
    }
    public void logging(boolean t) {
        logState=t;
    }


    public String getB() {
        return this.birthDate;
    }






}
