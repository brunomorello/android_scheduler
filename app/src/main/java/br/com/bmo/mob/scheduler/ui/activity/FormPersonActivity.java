package br.com.bmo.mob.scheduler.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import br.com.bmo.mob.scheduler.R;
import br.com.bmo.mob.scheduler.dao.PersonDAO;
import br.com.bmo.mob.scheduler.model.Person;

public class FormPersonActivity extends AppCompatActivity {

    public static final String TITLE_APP_BAR = "New Person";
    final PersonDAO personDao = new PersonDAO();
    private EditText nameEditText;
    private EditText emailEditText;
    private EditText phoneEditText;
    private int personId = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_form_person);
        setTitle(TITLE_APP_BAR);

        bindInputValues(getIntent());
        setupSaveButton();

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

        if (personId > 0) person.setId(personId);

        personDao.save(person);
        finish();
    }

    private void bindInputValues(Intent data) {
        nameEditText = findViewById(R.id.activity_form_person_editText_name);
        emailEditText = findViewById(R.id.activity_form_person_editText_email);
        phoneEditText = findViewById(R.id.activity_form_person_editText_phone);

        if (data.getSerializableExtra("person") != null) {
            Person person = (Person) data.getSerializableExtra("person");
            nameEditText.setText(person.getNameStr());
            emailEditText.setText(person.getEmailStr());
            phoneEditText.setText(person.getPhoneStr());
            personId = person.getId();
        }
    }
}