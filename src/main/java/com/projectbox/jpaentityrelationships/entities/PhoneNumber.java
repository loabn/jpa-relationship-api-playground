package com.projectbox.jpaentityrelationships.entities;

import com.projectbox.jpaentityrelationships.entities.enums.PhoneNumberType;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "PhoneNumber")
public class PhoneNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private PhoneNumberType phoneNumberType;
    private String phoneNumber;

    public PhoneNumber(Long id, PhoneNumberType phoneNumberType, String phoneNumber) {
        this.id = id;
        this.phoneNumberType = phoneNumberType;
        this.phoneNumber = phoneNumber;
    }

    public PhoneNumber(PhoneNumberType phoneNumberType, String phoneNumber) {
        this.phoneNumberType = phoneNumberType;
        this.phoneNumber = phoneNumber;
    }

    public PhoneNumber() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PhoneNumberType getPhoneNumberType() {
        return phoneNumberType;
    }

    public void setPhoneNumberType(PhoneNumberType phoneNumberType) {
        this.phoneNumberType = phoneNumberType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return Objects.equals(id, that.id) &&
                phoneNumberType == that.phoneNumberType &&
                Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phoneNumberType, phoneNumber);
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "id=" + id +
                ", phoneNumberType=" + phoneNumberType +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

}
