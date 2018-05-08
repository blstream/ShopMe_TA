package tests.objects;

public class Invoice {
    private String id;
    private String companyName;
    private String nip;
    private Address invoiceAddress;

    public Invoice(String id, String companyName, String nip, Address invoiceAddress) {
        this.id = id;
        this.companyName = companyName;
        this.nip = nip;
        this.invoiceAddress = invoiceAddress;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id='" + id + '\'' +
                ", companyName='" + companyName + '\'' +
                ", nip='" + nip + '\'' +
                ", invoiceAddress='" + invoiceAddress + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public Address getInvoiceAddress() {
        return invoiceAddress;
    }

    public void setInvoiceAddress(Address invoiceAddress) {
        this.invoiceAddress = invoiceAddress;
    }
}
