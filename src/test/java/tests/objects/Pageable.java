package tests.objects;


public class Pageable {

    public Sort sort;
    public Integer pageSize;
    public Integer pageNumber;
    public Integer offset;
    public Boolean paged;
    public Boolean unpaged;

    public Pageable() {
    }

    public Pageable(Sort sort, Integer pageSize, Integer pageNumber, Integer offset, Boolean paged, Boolean unpaged) {
        super();
        this.sort = sort;
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.offset = offset;
        this.paged = paged;
        this.unpaged = unpaged;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Boolean getPaged() {
        return paged;
    }

    public void setPaged(Boolean paged) {
        this.paged = paged;
    }

    public Boolean getUnpaged() {
        return unpaged;
    }

    public void setUnpaged(Boolean unpaged) {
        this.unpaged = unpaged;
    }
}
