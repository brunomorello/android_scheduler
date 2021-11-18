package br.com.bmo.mob.scheduler.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;
import java.util.List;

import br.com.bmo.mob.scheduler.R;
import br.com.bmo.mob.scheduler.dao.PersonDAO;

public class ListPeopleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, "Hello, Bruno!", Toast.LENGTH_LONG).show();

        setContentView(R.layout.activity_list_people);
        setTitle("Family");

        FloatingActionButton fabNewPerson = findViewById(R.id.activity_list_people_fab_new_person);
        fabNewPerson.setOnClickListener(v -> startActivity(new Intent(ListPeopleActivity.this, FormPersonActivity.class)));

    }

    @Override
    protected void onResume() {
        super.onResume();
        PersonDAO dao = new PersonDAO();

        ListView namesListView = findViewById(R.id.activity_main_names_list);
        namesListView.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                dao.getPeople()
        ));
    }
}
