package com.makesrc;

import java.io.Serializable;

/** Created by Brent Burbidge on 4/23/2016. Sampled from Kent Yang **/
public class Person implements Serializable {
    public static final long serialVersionUID = 1L;

    //region Properties
    private String firstName = "";
    private String lastName = "";
    private String email = "";
    private String phone = "";
    //endregion

    //region Property Getters And Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    //endregion

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Person person = (Person) o;

        if (!firstName.equals(person.firstName)) {
            return false;
        }
        if (!lastName.equals(person.lastName)) {
            return false;
        }
        if (!email.equals(person.email)) {
            return false;
        }
        return phone.equals(person.phone);

    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + phone.hashCode();
        return result;
    }

    private void Person() {
    }

    public void Person(String firstName, String lastName, String email, String phone) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPhone(phone);
    }

    void ToXMLUsingDOM() {

    }

    void ToXMLUsingSAX() {

    }

}

