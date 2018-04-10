package tests.objects;

/**
 * Created by swa on 10/04/2018.
 */
public class MyServices {

    public String id;
    public Long date;
    public ServiceCategory category;
    public String title;
    public String baseDesription;
    public Float basePrice;

    public MyServices(Long date, String title, Float basePrice) {
        this.date = date;
        this.title = title;
        this.basePrice = basePrice;
    }


    public boolean equalsOnList(Object o) {
        if (this == o) return true;
        if (!(o instanceof Staff)) return false;
        MyServices service = (MyServices) o;

        if (date != null ? !date.equals(service.date) : service.date != null) return false;
        if (title != null ? !title.equals(service.title) : service.title != null) return false;
        if (basePrice != null ? !basePrice.equals(service.basePrice) : service.basePrice != null) return false;

        return true;

    }
        @Override
    public String toString() {
        return "Service{" +
                "Id='" + id + '\'' +
                ", category " + category +
                ", date " + date +
                ", Title='" + title + '\'' +
                ", BaseDescription='" + baseDesription + '\'' +
                ", BaseProice='" + basePrice + '\'' +
                '}';
    }

}
