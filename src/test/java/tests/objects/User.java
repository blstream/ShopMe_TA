package tests.objects;

public class User {

    public String id;
    public String name;
    public String surname;
    public String email;
    public String password;
    public String phoneNumber;
    public String bankAccount;
    public String additionalInfo;
    public String city;
    public Address address;
    public Voivodeship voivodeship;
    public boolean invoiceRequest;
    public Invoice invoice;

    public User(String name, String email, String phoneNumber, String additionalInfo, Voivodeship voivodeship, String city) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.additionalInfo = additionalInfo;
        this.city = city;
        this.voivodeship = voivodeship;
    }

    public User(String id, String name, String surname, String email, String password, String phoneNumber, String bankAccount, Address address, Voivodeship voivodeship, boolean invoiceRequest, Invoice invoice) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.bankAccount = bankAccount;
        this.address = address;
        this.voivodeship = voivodeship;
        this.invoiceRequest = invoiceRequest;
        this.invoice = invoice;
    }

    public User() {

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

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public boolean isInvoiceRequest() {
        return invoiceRequest;
    }

    public void setInvoiceRequest(boolean invoiceRequest) {
        this.invoiceRequest = invoiceRequest;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Voivodeship getVoivodeship() {
        return voivodeship;
    }

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
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                ", address='" + address + '\'' +
                ", voivodeship='" + voivodeship + '\'' +
                ", invoiceRequest='" + invoiceRequest + '\'' +
                ", invoice='" + invoice + '\'' +
                '}';
    }

    public String getCity() {
        return city;
    }
}
