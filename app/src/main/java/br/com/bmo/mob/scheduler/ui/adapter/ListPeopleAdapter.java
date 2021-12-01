package br.com.bmo.mob.scheduler.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.bmo.mob.scheduler.R;
import br.com.bmo.mob.scheduler.model.Person;

public class ListPeopleAdapter extends BaseAdapter {

    private final List<Person> people = new ArrayList<>();
    private final Context context;

    public ListPeopleAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return people.size();
    }

    @Override
    public Person getItem(int position) {
        return people.get(position);
    }

    @Override
    public long getItemId(int position) {
        return people.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = createView(parent);
        Person person = people.get(position);

        updateView(view, person);
        return view;
    }

    private void updateView(View view, Person person) {
        TextView nameTextView = view.findViewById(R.id.item_list_person_name);
        nameTextView.setText(person.getNameStr());

        TextView phoneTextView = view.findViewById(R.id.item_list_person_phone_number);
        phoneTextView.setText(person.getPhoneStr());
    }

    private View createView(ViewGroup parent) {
        return LayoutInflater
                .from(context)
                .inflate(R.layout.item_list_person, parent, false);
    }

    public void clear() {
        people.clear();
    }

    public void addAll(List<Person> people) {
        this.people.addAll(people);
    }

    public void remove(Person person) {
        people.remove(person);
        notifyDataSetChanged();
    }

    public void updateList(List<Person> people) {
        clear();
        addAll(people);
        notifyDataSetChanged();
    }
}
