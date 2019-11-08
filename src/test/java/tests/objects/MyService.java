package tests.objects;


public class MyService {

    public String id;
    public Long date;
    public String category;
    public String title;
    public String baseDescription;
    public Float basePrice;
    public String extendedDescription;
    public Float extendedPrice;
    public String extraDescription;
    public Float extraPrice;
    public String userName;
    public String userSurname;
    public String userEmail;
    public String userPhone;
    public String voivodeship;
    public String city;

    public MyService() {
    }

    public MyService(String title, String category, String baseDescription, Float basePrice, String extendedDescription, Float extendedPrice, String extraDescription, Float extraPrice, String userName, String userSurname, String userEmail, String userPhone, String voivodeship, String city) {
        this.title = title;
        this.category = category;
        this.baseDescription = baseDescription;
        this.basePrice = basePrice;
        this.extendedDescription = extendedDescription;
        this.extendedPrice = extendedPrice;
        this.extraDescription = extraDescription;
        this.extraPrice = extraPrice;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.voivodeship = voivodeship;
        this.city = city;
    }

    public boolean equalsOnList(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyService)) return false;
        MyService service = (MyService) o;

        if (date != null ? !date.equals(service.date) : service.date != null) return false;
        if (title != null ? !title.equals(service.title) : service.title != null) return false;
        if (category != null ? !category.equals(service.category) : service.category != null) return false;
        if (baseDescription != null ? !baseDescription.equals(service.baseDescription) : service.baseDescription != null)
            return false;
        if (basePrice != null ? !basePrice.equals(service.basePrice) : service.basePrice != null) return false;
        if (extendedDescription != null ? !extendedDescription.equals(service.extendedDescription) : service.extendedDescription != null)
            return false;
        if (extendedPrice != null ? !extendedPrice.equals(service.extendedPrice) : service.extendedPrice != null)
            return false;
        if (extraPrice != null ? !extraPrice.equals(service.extraPrice) : service.extraPrice != null) return false;
        if (extraDescription != null ? !extraDescription.equals(service.extraDescription) : service.extraDescription != null)
            return false;
        if (userName != null ? !userName.equals(service.userName) : service.userName != null)
            return false;
        if (userSurname != null ? !userSurname.equals(service.userSurname) : service.userSurname != null)
            return false;
        if (userEmail != null ? !userEmail.equals(service.userEmail) : service.userEmail != null)
            return false;
        if (userPhone != null ? !userPhone.equals(service.userPhone) : service.userPhone != null)
            return false;
        if (voivodeship != null ? !voivodeship.equals(service.voivodeship) : service.voivodeship != null)
            return false;
        if (city != null ? !city.equals(service.city) : service.city != null)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Services{" +
                "id='" + id + '\'' +
                ", category='" + category +
                ", date='" + date +
                ", title='" + title + '\'' +
                ", baseDescription='" + baseDescription + '\'' +
                ", basePrice='" + basePrice + '\'' +
                ", extendedDescription='" + extendedDescription + '\'' +
                ", extendedPrice='" + extendedDescription + '\'' +
                ", extraDescription='" + extraDescription + '\'' +
                ", extraPrice='" + extraPrice + '\'' +
                ", userName='" + userName + '\'' +
                ", userSurname='" + userSurname + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", voivodeship='" + voivodeship + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public Long getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public String getBaseDesription() {
        return baseDescription;
    }

    public Float getBasePrice() {
        return basePrice;
    }

    public String getExtendedDesription() {
        return extendedDescription;
    }

    public Float getExtendedPrice() {
        return extendedPrice;
    }

    public String getExtraDesription() {
        return extraDescription;
    }

    public Float getExtraPrice() {
        return extraPrice;
    }

    public String getVoivodeship() {
        return voivodeship;
    }

    public String getCity() {
        return city;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBaseDescription(String baseDescription) {
        this.baseDescription = baseDescription;
    }

    public void setBasePrice(Float basePrice) {
        this.basePrice = basePrice;
    }

    public void setExtendedDescription(String extendedDescription) {
        this.extendedDescription = extendedDescription;
    }

    public void setExtendedPrice(Float extendedPrice) {
        this.extendedPrice = extendedPrice;
    }

    public void setExtraDescription(String extraDescription) {
        this.extraDescription = extraDescription;
    }

    public void setExtraPrice(Float extraPrice) {
        this.extraPrice = extraPrice;
    }

    public void setId(String id) {
        this.id = id;
    }
}
