package tests.objects;

public class Category {
    public String name;

    public Category(String name) {
        this.name = name;
    }

    public boolean equalsOnList(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;

        if (name != null ? !name.equals(category.name) : category.name != null) return false;

        return true;
    }

    @Override
    public String toString() {
        return "Services{" +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
