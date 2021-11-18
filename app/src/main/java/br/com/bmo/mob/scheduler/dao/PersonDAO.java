package br.com.bmo.mob.scheduler.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.bmo.mob.scheduler.model.Person;

public class PersonDAO {

    private final static List<Person> people = new ArrayList<>();

    public void save(Person person) {
        people.add(person);
    }

    public List<Person> getPeople() {
        return new ArrayList<>(people);
    }
}
