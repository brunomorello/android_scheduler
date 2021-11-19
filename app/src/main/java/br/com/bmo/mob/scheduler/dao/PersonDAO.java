package br.com.bmo.mob.scheduler.dao;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.bmo.mob.scheduler.model.Person;

public class PersonDAO {

    private final static List<Person> people = new ArrayList<>();
    private static int idCounter = 1;

    public void save(Person person) {
        Person personFound = findById(person.getId());

        if (personFound != null) {
            int i = people.indexOf(personFound);
            people.set(i, person);
        } else {
            person.setId(idCounter);
            people.add(person);
            idCounter++;
        }
    }

    public List<Person> getPeople() {
        return new ArrayList<>(people);
    }

    public Person findById(int id) {
        for (Person p : people) {
            if (p.getId() == id)
                return p;
        }
        return null;
    }
}
