package Users;

public class Person {

  private String firstName;
  private String lastName;
  private int age;
  private long phoneNumber;
  private String emailAddress;

  public Person() {
    firstName = "";
    lastName = "";
    age = -1;
    phoneNumber = -1;
    emailAddress = "";
  }

  public Person(String[] strings) {
    firstName = strings[0];
    lastName = strings[1];
    age = Integer.parseInt(strings[2]);
    phoneNumber = Long.parseLong(strings[3]);
    emailAddress = strings[4];
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public int getAge() {
    return age;
  }

  public long getPhoneNumber() {
    return phoneNumber;
  }

  public String getEmailAddress() {
    return emailAddress;
  }
}
