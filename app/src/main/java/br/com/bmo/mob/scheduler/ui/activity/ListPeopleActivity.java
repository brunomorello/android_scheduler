package br.com.bmo.mob.scheduler.ui.activity;

import static br.com.bmo.mob.scheduler.ui.activity.ActivitiesConstants.PERSON_KEY;

import android.app.AlertDialog;
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
import br.com.bmo.mob.scheduler.ui.activity.helper.ListPeopleActivityHelper;
import br.com.bmo.mob.scheduler.ui.adapter.ListPeopleAdapter;

public class ListPeopleActivity extends AppCompatActivity {

    public static final String ACTIVITY_TITLE = "Family";
    private final ListPeopleActivityHelper helper = new ListPeopleActivityHelper(this);

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
        helper.updateViewList();
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.activity_list_people_menu_item_delete) {
            helper.confirmDeleteDialog(item);
        }
        return super.onContextItemSelected(item);
    }

    private void setupListView() {
        ListView namesListView = findViewById(R.id.activity_main_names_list);

        helper.setAdapterToListView(namesListView);
        setupListenerOnItemClick(namesListView);
        registerForContextMenu(namesListView);
    }

    private void setupListenerOnItemClick(ListView namesListView) {
        namesListView.setOnItemClickListener((parent, view, position, id) -> {
            Person personSelected = (Person) parent.getItemAtPosition(position);
            Log.i("Edit Person", "onItemClick: Editing Person " + personSelected + " id= " + personSelected.getId());
            Intent goToFormPersonActivity = new Intent(ListPeopleActivity.this, FormPersonActivity.class);
            goToFormPersonActivity.putExtra(PERSON_KEY, personSelected);
            startActivity(goToFormPersonActivity);
        });
    }

}
