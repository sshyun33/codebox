package modelmapper;

public class Order {
  Customer customer;
  Address billingAddress;

  public Order(Customer customer, Address billingAddress) {
    this.customer = customer;
    this.billingAddress = billingAddress;
  }

  public Customer getCustomer() {
    return customer;
  }

  public Address getBillingAddress() {
    return billingAddress;
  }
}

class Customer {
  Name name;

  public Customer(Name name) {
    this.name = name;
  }

  public Name getName() {
    return name;
  }
}

class Name {
  String firstName;
  String lastName;

  public Name(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }
}

class Address {
  String street;
  String city;

  public Address(String street, String city) {
    this.street = street;
    this.city = city;
  }

  public String getStreet() {
    return street;
  }

  public String getCity() {
    return city;
  }
}
