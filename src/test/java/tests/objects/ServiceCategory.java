package tests.objects;

/**
 * Created by swa on 10/04/2018.
 */
public class ServiceCategory {
    public String id;
    public String name;
    public String translateKey;

    @Override
    public String toString() {
        return "Service{" +
                "Id='" + id + '\'' +
                ", Name='" + name + '\'' +
                ", translateKey='" + translateKey + '\'' +
                '}';
    }
}
