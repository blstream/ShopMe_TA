package tests.objects;

public class Category {
    public String id;
    public String name;
    public String translateKey;

    public Category(String id, String name, String translateKey) {
        this.id = id;
        this.name = name;
        this.translateKey = translateKey;
    }

    public boolean equalsOnList(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;

        if (id != null ? !id.equals(category.id) : category.id != null) return false;
        if (name != null ? !name.equals(category.name) : category.name != null) return false;
        if (translateKey != null ? !translateKey.equals(category.translateKey) : category.translateKey != null) return false;

        return true;
    }

    @Override
    public String toString() {
        return "Services{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTranslateKey(){
        return translateKey;
    }

    public void setTranslateKey(String translateKey){
        this.translateKey = translateKey;
    }
}