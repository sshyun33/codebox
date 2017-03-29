package modelmapper;

public class OrderDTO {
  String customerFirstName;
  String customerLastName;
  String billingStreet;
  String billingCity;

  public void setCustomerFirstName(String customerFirstName) {
    this.customerFirstName = customerFirstName;
  }

  public void setCustomerLastName(String customerLastName) {
    this.customerLastName = customerLastName;
  }

  public void setBillingStreet(String billingStreet) {
    this.billingStreet = billingStreet;
  }

  public void setBillingCity(String billingCity) {
    this.billingCity = billingCity;
  }

  @Override
  public String toString() {
    return "OrderDTO{" +
        "customerFirstName='" + customerFirstName + '\'' +
        ", customerLastName='" + customerLastName + '\'' +
        ", billingStreet='" + billingStreet + '\'' +
        ", billingCity='" + billingCity + '\'' +
        '}';
  }
}
