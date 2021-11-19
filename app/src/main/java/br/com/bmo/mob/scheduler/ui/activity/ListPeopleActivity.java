package br.com.bmo.mob.scheduler.ui.activity;

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

    private PersonDAO dao = new PersonDAO();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, "Hello, Bruno!", Toast.LENGTH_LONG).show();

        setContentView(R.layout.activity_list_people);
        setTitle("Family");

        dao.save(new Person("Bruno", "test@test.com", "123"));
        dao.save(new Person("Jade", "jade@gmail.com", "321"));

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
        List<Person> people = dao.getPeople();
        namesListView.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                people
        ));

        namesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Person personSelected = people.get(position);
                Log.i("Edit Personq", "onItemClick: Editing Person " + personSelected + " id= " + personSelected.getId());
                Intent goToFormPersonActivity = new Intent(ListPeopleActivity.this, FormPersonActivity.class);
                goToFormPersonActivity.putExtra("person", personSelected);
                startActivity(goToFormPersonActivity);
            }
        });
    }
}
