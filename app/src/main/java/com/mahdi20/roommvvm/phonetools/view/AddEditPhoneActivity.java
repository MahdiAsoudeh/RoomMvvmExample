package com.mahdi20.roommvvm.phonetools.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.mahdi20.roommvvm.R;


public class AddEditPhoneActivity extends AppCompatActivity {
    public static final String EXTRA_ID =
            "com.codinginflow.architectureexample.EXTRA_ID";
    public static final String EXTRA_NAME =
            "com.codinginflow.architectureexample.EXTRA_NAME";
    public static final String EXTRA_PHONE =
            "com.codinginflow.architectureexample.EXTRA_PHONE";

    private EditText edit_text_name;
    private EditText edit_text_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_phone);

        edit_text_name = findViewById(R.id.edit_text_name);
        edit_text_phone = findViewById(R.id.edit_text_phone);


        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_launcher_foreground);

        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Phone");
            edit_text_name.setText(intent.getStringExtra(EXTRA_NAME));
            edit_text_phone.setText(intent.getStringExtra(EXTRA_PHONE));
        } else {
            setTitle("Add Phone");
        }


        Button btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                savePhone();

            }
        });


    }

    private void savePhone() {

        String name = edit_text_name.getText().toString();
        String phone = edit_text_phone.getText().toString();

        if (name.trim().isEmpty() || phone.trim().isEmpty()) {
            Toast.makeText(this, "insert a name and phone", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_NAME, name);
        data.putExtra(EXTRA_PHONE, phone);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }

}