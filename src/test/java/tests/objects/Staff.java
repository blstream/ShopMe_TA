package tests.objects;

public class Staff {

    public String City;
    public String Temperature;
    public String Humidity;
    public String WeatherDescription;
    public String WindSpeed;
    public String WindDirectionDegree;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Staff)) return false;

        Staff staff = (Staff) o;

        if (City != null ? !City.equals(staff.City) : staff.City != null) return false;
        if (Temperature != null ? !Temperature.equals(staff.Temperature) : staff.Temperature != null) return false;
        if (Humidity != null ? !Humidity.equals(staff.Humidity) : staff.Humidity != null) return false;
        if (WeatherDescription != null ? !WeatherDescription.equals(staff.WeatherDescription) : staff.WeatherDescription != null)
            return false;
  //      if (WindSpeed != null ? !WindSpeed.equals(staff.WindSpeed) : staff.WindSpeed != null) return false;
        return WindDirectionDegree != null ? WindDirectionDegree.equals(staff.WindDirectionDegree) : staff.WindDirectionDegree == null;
    }

    @Override
    public int hashCode() {
        int result = City != null ? City.hashCode() : 0;
        result = 31 * result + (Temperature != null ? Temperature.hashCode() : 0);
        result = 31 * result + (Humidity != null ? Humidity.hashCode() : 0);
        result = 31 * result + (WeatherDescription != null ? WeatherDescription.hashCode() : 0);
        result = 31 * result + (WindSpeed != null ? WindSpeed.hashCode() : 0);
        result = 31 * result + (WindDirectionDegree != null ? WindDirectionDegree.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "City='" + City + '\'' +
                ", Temperature='" + Temperature + '\'' +
                ", Humidity='" + Humidity + '\'' +
                ", WeatherDescription='" + WeatherDescription + '\'' +
                ", WindSpeed='" + WindSpeed + '\'' +
                ", WindDirectionDegree='" + WindDirectionDegree + '\'' +
                '}';
    }
}