package br.com.bmo.mob.scheduler.ui.activity;

import static br.com.bmo.mob.scheduler.ui.activity.ActiviiesConstants.PERSON_KEY;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.bmo.mob.scheduler.R;
import br.com.bmo.mob.scheduler.dao.PersonDAO;
import br.com.bmo.mob.scheduler.model.Person;
import br.com.bmo.mob.scheduler.ui.adapter.ListPeopleAdater;

public class ListPeopleActivity extends AppCompatActivity {

    public static final String ACTIVITY_TITLE = "Family";
    private PersonDAO personDAO = new PersonDAO();
//    private ArrayAdapter<Person> adapter;
    private ListPeopleAdater adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_people);
        setTitle(ACTIVITY_TITLE);

        FloatingActionButton fabNewPerson = findViewById(R.id.activity_list_people_fab_new_person);
        fabNewPerson.setOnClickListener(v -> startActivity(new Intent(ListPeopleActivity.this, FormPersonActivity.class)));
        setupListView();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_list_people_menu, menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateViewList();
    }

    private void updateViewList() {
        adapter.updateList(personDAO.getPeople());
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.activity_list_people_menu_item_delete) {
            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Person personSelected = adapter.getItem(menuInfo.position);
            Log.i("Delete Person", "Deleting Person " + personSelected + " id= " + personSelected.getId());
            remove(personSelected);
        }
        return super.onContextItemSelected(item);
    }

    private void remove(Person personSelected) {
        personDAO.delete(personSelected);
        adapter.remove(personSelected);
    }

    private void setupListView() {
        ListView namesListView = findViewById(R.id.activity_main_names_list);

        setAdapterToListView(namesListView);
        setupListenerOnItemClick(namesListView);
        registerForContextMenu(namesListView);
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

    private void setAdapterToListView(ListView namesListView) {
//        adapter = new ArrayAdapter<>(
//                this,
//                android.R.layout.simple_list_item_1
//                R.layout.item_list_person
//        );
        adapter = new ListPeopleAdater(this);
        namesListView.setAdapter(adapter);
    }
}
