package tests.objects;

public class User {

    public String id;
    public String name;
    public String email;
    public String phoneNumber;
    public String additionalInfo;

    public User(String name, String email, String phoneNumber, String additionalInfo) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.additionalInfo = additionalInfo;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAdditionalInfo() { return additionalInfo; }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAdditionalInfo(String additionalInfo) { this.additionalInfo = additionalInfo; }

    public boolean equalsOnList(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;

        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(user.phoneNumber) : user.phoneNumber != null) return false;

        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
