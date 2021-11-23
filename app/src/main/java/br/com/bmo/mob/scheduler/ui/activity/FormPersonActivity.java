package br.com.bmo.mob.scheduler.ui.activity;

import static br.com.bmo.mob.scheduler.ui.activity.ActiviiesConstants.PERSON_KEY;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import br.com.bmo.mob.scheduler.R;
import br.com.bmo.mob.scheduler.dao.PersonDAO;
import br.com.bmo.mob.scheduler.model.Person;

public class FormPersonActivity extends AppCompatActivity {

    public static final String TITLE_APP_BAR_NEW_PERSON = "New Person";
    public static final String TITLE_APP_BAR_EDIT_PERSON = "Edit Person";
    final PersonDAO personDao = new PersonDAO();
    private EditText nameEditText;
    private EditText emailEditText;
    private EditText phoneEditText;
    private int personId = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_form_person);
        setTitleBar(getIntent());
        bindInputValues(getIntent());
        setupSaveButton();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_form_person_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.activity_form_person_menu_item_save)
            savePersonAndFinish();
        return super.onOptionsItemSelected(item);
    }

    private void setupSaveButton() {
        Button btnSave = findViewById(R.id.activity_form_person_btn_save);
        btnSave.setOnClickListener(v -> {
            savePersonAndFinish();
        });
    }

    private void savePersonAndFinish() {
        String nameStr = nameEditText.getText().toString();
        String emailStr = emailEditText.getText().toString();
        String phoneStr = phoneEditText.getText().toString();
        Person person = new Person(nameStr, emailStr, phoneStr);

        if (personDao.isValidId(personId)) person.setId(personId);

        personDao.save(person);
        finish();
    }

    private void bindInputValues(Intent data) {
        nameEditText = findViewById(R.id.activity_form_person_editText_name);
        emailEditText = findViewById(R.id.activity_form_person_editText_email);
        phoneEditText = findViewById(R.id.activity_form_person_editText_phone);

        if (data.getSerializableExtra(PERSON_KEY) != null) {
            Person person = (Person) data.getSerializableExtra(PERSON_KEY);
            nameEditText.setText(person.getNameStr());
            emailEditText.setText(person.getEmailStr());
            phoneEditText.setText(person.getPhoneStr());
            personId = person.getId();
        }
    }

    private void setTitleBar(Intent intent) {
        if (intent.getSerializableExtra(PERSON_KEY) == null)
            setTitle(TITLE_APP_BAR_NEW_PERSON);
        else
            setTitle(TITLE_APP_BAR_EDIT_PERSON);
    }
}