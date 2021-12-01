package br.com.bmo.mob.scheduler.ui.activity.helper;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;

import br.com.bmo.mob.scheduler.dao.PersonDAO;
import br.com.bmo.mob.scheduler.model.Person;
import br.com.bmo.mob.scheduler.ui.adapter.ListPeopleAdapter;

public class ListPeopleActivityHelper {

    private final ListPeopleAdapter adapter;
    private PersonDAO personDAO;
    private Context context;

    public ListPeopleActivityHelper(Context context) {
        this.context = context;
        this.adapter = new ListPeopleAdapter(context);
        this.personDAO = new PersonDAO();
    }

    public void updateViewList() {
        adapter.updateList(personDAO.getPeople());
    }

    public void setAdapterToListView(ListView namesListView) {
        namesListView.setAdapter(adapter);
    }

    private void remove(Person personSelected) {
        personDAO.delete(personSelected);
        adapter.remove(personSelected);
    }

    public void confirmDeleteDialog(@NonNull MenuItem item) {
        new AlertDialog.Builder(context)
                .setTitle("Delete Person")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    Person personSelected = adapter.getItem(menuInfo.position);
                    Log.i("Delete Person", "Deleting Person " + personSelected + " id= " + personSelected.getId());
                    remove(personSelected);
                })
                .setNegativeButton("No", null)
                .show();
    }

}
