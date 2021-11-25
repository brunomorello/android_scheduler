package br.com.bmo.mob.scheduler;

import android.app.Application;

import br.com.bmo.mob.scheduler.dao.PersonDAO;
import br.com.bmo.mob.scheduler.model.Person;

public class SchedulerApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        fillOutList();

    }

    private void fillOutList() {
        PersonDAO personDAO = new PersonDAO();
        personDAO.save(new Person("Bruno", "test@test.com", "123"));
        personDAO.save(new Person("Jade", "jade@gmail.com", "321"));
    }
}
