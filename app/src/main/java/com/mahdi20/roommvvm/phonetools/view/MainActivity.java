package com.mahdi20.roommvvm.phonetools.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mahdi20.roommvvm.PhoneAdapter;
import com.mahdi20.roommvvm.R;
import com.mahdi20.roommvvm.phonetools.model.Phone;
import com.mahdi20.roommvvm.phonetools.viewModel.PhoneViewModel;

import java.util.List;


public class MainActivity extends AppCompatActivity {


    public static final int ADD_Phone_REQUEST = 1;
    public static final int EDIT_Phone_REQUEST = 2;
    private PhoneViewModel phoneViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnInsert = findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, AddEditPhoneActivity.class);
                startActivityForResult(intent, ADD_Phone_REQUEST);

            }
        });


        Button btnDeleteAll = (Button) findViewById(R.id.btnDeleteAll);
        btnDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                phoneViewModel.deleteAllphones();
                Toast.makeText(MainActivity.this, "All Phones deleted", Toast.LENGTH_SHORT).show();

            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final PhoneAdapter adapter = new PhoneAdapter();
        recyclerView.setAdapter(adapter);

        phoneViewModel = ViewModelProviders.of(this).get(PhoneViewModel.class);
        phoneViewModel.getAllphones().observe(this, new Observer<List<Phone>>() {
            @Override
            public void onChanged(@Nullable List<Phone> Phones) {
                adapter.submitList(Phones);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                phoneViewModel.delete(adapter.getPhoneAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Phone deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new PhoneAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Phone Phone) {
                Intent intent = new Intent(MainActivity.this, AddEditPhoneActivity.class);
                intent.putExtra(AddEditPhoneActivity.EXTRA_ID, Phone.getId());
                intent.putExtra(AddEditPhoneActivity.EXTRA_NAME, Phone.getName());
                intent.putExtra(AddEditPhoneActivity.EXTRA_PHONE, Phone.getPhone());
                startActivityForResult(intent, EDIT_Phone_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_Phone_REQUEST && resultCode == RESULT_OK) {
            String title = data.getStringExtra(AddEditPhoneActivity.EXTRA_NAME);
            String description = data.getStringExtra(AddEditPhoneActivity.EXTRA_PHONE);

            Phone Phone = new Phone(title, description);
            phoneViewModel.insert(Phone);

            Toast.makeText(this, "Phone saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_Phone_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(AddEditPhoneActivity.EXTRA_ID, -1);

            if (id == -1) {
                Toast.makeText(this, "Phone can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String title = data.getStringExtra(AddEditPhoneActivity.EXTRA_NAME);
            String description = data.getStringExtra(AddEditPhoneActivity.EXTRA_PHONE);

            Phone Phone = new Phone(title, description);
            Phone.setId(id);
            phoneViewModel.update(Phone);

            Toast.makeText(this, "Phone updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Phone not saved", Toast.LENGTH_SHORT).show();
        }
    }


}