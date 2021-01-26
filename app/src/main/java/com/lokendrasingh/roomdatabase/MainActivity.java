package com.lokendrasingh.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Application;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements UsersAdapter.ClickListner {
    RecyclerView recyclerView;
    UsersAdapter usersAdapter;
    ViewModel  userViewModel;
    FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);
        userViewModel = ViewModelProviders.of(this).get(ViewModel.class);
        floatingActionButton = findViewById(R.id.btnadduser);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        usersAdapter = new UsersAdapter(this);
        userViewModel.getAllUsers().observe(this, new Observer<List<Users>>() {
            @Override
            public void onChanged(List<Users> users) {
                if (users.size() > 0) {
                    usersAdapter.setData(users);
                    recyclerView.setAdapter(usersAdapter);
                }

            }
        });

        floatingActionButton.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUser();
            }
        });
    }
        public void addUser()
        {
            final AlertDialog.Builder builder= new AlertDialog.Builder(this);
            final View view= getLayoutInflater().inflate(R.layout.row_add, null);
            builder.setView(view);
            AlertDialog alertDialog= builder.create();
            Button btnadduser= view.findViewById(R.id.button);
             EditText edUsers= view.findViewById(R.id.editname);
            TextView tvdetails= view.findViewById(R.id.txtname);
            btnadduser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(edUsers.getText()!=null)
                    {
                        String username= edUsers.getText().toString().trim();
                        Users users= new Users();
                        users.setUsername(username);
                        userViewModel.insertuser(users);
                        alertDialog.dismiss();
                    }

                }
            });
            alertDialog.show();
        }

    @Override
    public void UpdateClicked(Users users) {
       updateUser(users);

    }

    @Override
    public void deleteClicked(Users users) {
        userViewModel.deleteuser(users);

    }

    public void updateUser(Users users)
    {
        final AlertDialog.Builder builder= new AlertDialog.Builder(this);
        final View view= getLayoutInflater().inflate(R.layout.row_add, null);
        builder.setView(view);
        AlertDialog alertDialog= builder.create();
        Button btnadduser= view.findViewById(R.id.button);
        EditText edUsers= view.findViewById(R.id.editname);
        TextView tvdetails= view.findViewById(R.id.txtname);
        tvdetails.setText("Update Details");
        btnadduser.setText("Update");
        btnadduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edUsers.getText()!=null)
                {
                    String username= edUsers.getText().toString().trim();
                    users.setUsername(username);
                    userViewModel.updateuser(users);
                    alertDialog.dismiss();
                }

            }
        });
        alertDialog.show();
    }
}
