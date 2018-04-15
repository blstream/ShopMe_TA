package tests.objects;

public class Sort {

    public Boolean sorted;
    public Boolean unsorted;

    public Sort() {
    }

    public Sort(Boolean sorted, Boolean unsorted) {
        super();
        this.sorted = sorted;
        this.unsorted = unsorted;
    }

    public Boolean getSorted() {
        return sorted;
    }

    public void setSorted(Boolean sorted) {
        this.sorted = sorted;
    }

    public Boolean getUnsorted() {
        return unsorted;
    }

    public void setUnsorted(Boolean unsorted) {
        this.unsorted = unsorted;
    }
}
