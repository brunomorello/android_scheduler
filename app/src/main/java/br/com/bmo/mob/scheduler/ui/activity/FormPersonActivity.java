package br.com.bmo.mob.scheduler.ui.activity;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_form_person);
        setTitle(TITLE_APP_BAR);

        bindInputValues();

        Button btnSave = findViewById(R.id.activity_form_person_btn_save);
        btnSave.setOnClickListener(v -> {
            savePersonAndFinish();
        });
    }

    private void savePersonAndFinish() {
        String nameStr = nameEditText.getText().toString();
        String emailStr = emailEditText.getText().toString();
        String phoneStr = phoneEditText.getText().toString();

        personDao.save(new Person(nameStr, emailStr, phoneStr));
        finish();
    }

    private void bindInputValues() {
        nameEditText = findViewById(R.id.activity_form_person_editText_name);
        emailEditText = findViewById(R.id.activity_form_person_editText_email);
        phoneEditText = findViewById(R.id.activity_form_person_editText_phone);
    }
}