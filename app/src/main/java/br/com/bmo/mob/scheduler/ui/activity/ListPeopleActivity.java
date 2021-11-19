package br.com.bmo.mob.scheduler.ui.activity;

import static br.com.bmo.mob.scheduler.ui.activity.ActiviiesConstants.PERSON_KEY;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import br.com.bmo.mob.scheduler.R;
import br.com.bmo.mob.scheduler.dao.PersonDAO;
import br.com.bmo.mob.scheduler.model.Person;

public class ListPeopleActivity extends AppCompatActivity {

    private PersonDAO personDAO = new PersonDAO();
    private ArrayAdapter<Person> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, "Hello, Bruno!", Toast.LENGTH_LONG).show();

        setContentView(R.layout.activity_list_people);
        setTitle("Family");

        personDAO.save(new Person("Bruno", "test@test.com", "123"));
        personDAO.save(new Person("Jade", "jade@gmail.com", "321"));

        FloatingActionButton fabNewPerson = findViewById(R.id.activity_list_people_fab_new_person);
        fabNewPerson.setOnClickListener(v -> startActivity(new Intent(ListPeopleActivity.this, FormPersonActivity.class)));

    }

    @Override
    protected void onResume() {
        super.onResume();
        setupListView();
    }

    private void setupListView() {
        ListView namesListView = findViewById(R.id.activity_main_names_list);
        List<Person> people = personDAO.getPeople();

        setAdapterToListView(namesListView, people);
        setupListenerOnItemClick(namesListView);
        setupListenerOnItemLongClick(namesListView);
    }

    private void setupListenerOnItemLongClick(ListView namesListView) {
        namesListView.setOnItemLongClickListener((parent, view, position, id) -> {
            Person personSelected = (Person) parent.getItemAtPosition(position);
            Log.i("Delete Person", "setupListenerOnItemLongClick: Delete Person " + personSelected + " id= " + personSelected.getId());
            personDAO.delete(personSelected);
            adapter.remove(personSelected);
            return true;
        });
    }

    private void setupListenerOnItemClick(ListView namesListView) {
        namesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Person personSelected = (Person) parent.getItemAtPosition(position);
                Log.i("Edit Person", "onItemClick: Editing Person " + personSelected + " id= " + personSelected.getId());
                Intent goToFormPersonActivity = new Intent(ListPeopleActivity.this, FormPersonActivity.class);
                goToFormPersonActivity.putExtra(PERSON_KEY, personSelected);
                startActivity(goToFormPersonActivity);
            }
        });
    }

    private void setAdapterToListView(ListView namesListView, List<Person> people) {
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                people
        );
        namesListView.setAdapter(adapter);
    }
}
