package tests.objects;

import java.util.List;

public class Services {

    public List<MyService> content = null;
    public Boolean last;
    public Integer totalPages;
    public Integer totalElements;
    public Boolean first;
    public Integer numberOfElements;
    public Integer size;
    public Integer number;

    public Services() {
    }

    public Services(List<MyService> content, Boolean last, Integer totalPages, Integer totalElements, Boolean first, Integer numberOfElements, Integer size, Integer number) {
        super();
        this.content = content;
        this.last = last;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.first = first;
        this.numberOfElements = numberOfElements;
        this.size = size;
        this.number = number;
    }

    public List<MyService> getContent() {
        return content;
    }

    public void setContent(List<MyService> content) {
        this.content = content;
    }

    public Boolean getLast() {
        return last;
    }

    public void setLast(Boolean last) {
        this.last = last;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Integer totalElements) {
        this.totalElements = totalElements;
    }

    public Boolean getFirst() {
        return first;
    }

    public void setFirst(Boolean first) {
        this.first = first;
    }

    public Integer getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(Integer numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}