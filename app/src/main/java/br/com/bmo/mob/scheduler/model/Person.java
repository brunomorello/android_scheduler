package br.com.bmo.mob.scheduler.model;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Objects;

public class Person implements Serializable {

    private int id;
    private final String nameStr;
    private final String emailStr;
    private final String phoneStr;

    public Person(String nameStr, String emailStr, String phoneStr) {
        this.nameStr = nameStr;
        this.emailStr = emailStr;
        this.phoneStr = phoneStr;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNameStr() {
        return nameStr;
    }

    public String getEmailStr() {
        return emailStr;
    }

    public String getPhoneStr() {
        return phoneStr;
    }

    @NonNull
    @Override
    public String toString() {
        return nameStr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && Objects.equals(nameStr, person.nameStr) && Objects.equals(emailStr, person.emailStr) && Objects.equals(phoneStr, person.phoneStr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameStr, emailStr, phoneStr);
    }

}
