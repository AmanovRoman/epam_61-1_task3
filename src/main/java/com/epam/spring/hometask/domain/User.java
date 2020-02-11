package com.epam.spring.hometask.domain;

import com.epam.spring.hometask.domain.enums.UserType;

import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Yuriy_Tkach
 */
public class User extends DomainId {

    private String firstName;

    private String lastName;

    private String email;

    private int userType;

    private LocalDate birthDate;

    public User() {
    }

    public User(String firstName, String lastName, String email, int userType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userType = userType;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

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

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getUserType() == user.getUserType() &&
                Objects.equals(getFirstName(), user.getFirstName()) &&
                Objects.equals(getLastName(), user.getLastName()) &&
                Objects.equals(getEmail(), user.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getEmail(), getUserType());
    }

    @Override
    public String toString() {
        return "User{" +
                "Id='" + getId() +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", userType=" + UserType.values()[userType-1] +
                ", birthday=" + birthDate +
                '}';
    }
}
