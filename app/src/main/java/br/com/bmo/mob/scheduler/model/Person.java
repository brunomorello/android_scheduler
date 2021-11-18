package br.com.bmo.mob.scheduler.model;

import androidx.annotation.NonNull;

public class Person {
    private final String nameStr;
    private final String emailStr;
    private final String phoneStr;

    public Person(String nameStr, String emailStr, String phoneStr) {
        this.nameStr = nameStr;
        this.emailStr = emailStr;
        this.phoneStr = phoneStr;
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
}
