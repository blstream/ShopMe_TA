package tests.objects;


public class MyService {

    public String id;
    public Long date;
    public Category category;
    public String title;
    public String baseDescription;
    public Float basePrice;
    public String extendedDescription;
    public Float extendedPrice;
    public String extraDescription;
    public Float extraPrice;
    public User user;

    public MyService() {
    }

    public MyService(String title, Category category, String baseDescription, Float basePrice, String extendedDescription, Float extendedPrice, String extraDescription, Float extraPrice, User user) {
        this.title = title;
        this.category = category;
        this.baseDescription = baseDescription;
        this.basePrice = basePrice;
        this.extendedDescription = extendedDescription;
        this.extendedPrice = extendedPrice;
        this.extraDescription = extraDescription;
        this.extraPrice = extraPrice;
        this.user = user;
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
        if (user != null ? !user.equals(service.user) : service.user != null) return false;

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
                ", user='" + user +
                '}';
    }

    public String getId() {
        return id;
    }

    public Long getDate() {
        return date;
    }

    public Category getCategory() {
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

    public User getUser() {
        return user;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public void setCategory(Category category) {
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

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(String id) {
        this.id = id;
    }
}
