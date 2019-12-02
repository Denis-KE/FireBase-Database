package com.deno.myfirebasedatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private EditText mEdtName,mEdtMail,mEdtIdNumber;
    private Button mBtnSave,mBtnView;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEdtName =findViewById(R.id.edtName);
        mEdtMail =findViewById(R.id.edtMail);
        mEdtIdNumber =findViewById(R.id.edtIdNumber);
        mBtnSave =findViewById(R.id.btnSave);
        mBtnView =findViewById(R.id.btnView);

        dialog =new ProgressDialog(this);
        dialog.setTitle("Saving");
        dialog.setMessage("please wait...");

        //Start by connecting your app to the FirebaseDatabase, and then
        //Set an OnClick listener to the save button to start saving
        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Start by getting data from the user
                String name = mEdtName.getText().toString().trim();
                String email = mEdtMail.getText().toString().trim();
                String id_number = mEdtIdNumber.getText().toString().trim();

                if (name.isEmpty()) {
                    mEdtName.setError("Enter Name");
                } else if (email.isEmpty()) {
                    mEdtMail.setError("Enter Email");
                } else if (id_number.isEmpty()) {
                    mEdtIdNumber.setError("Enter ID Number");
                } else {


                    long time = System.currentTimeMillis();
                    String timeConv = String.valueOf(time);
                    //Write code to create a child/Get the firebase database instance to save data
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users/" + timeConv);

                    //Use the ItemConstructor to save data from the user
                    ItemConstructor x = new ItemConstructor(timeConv, name, email, id_number);
                    dialog.show();
                    ref.setValue(x).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            dialog.dismiss();
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Saved Successfully", Toast.LENGTH_SHORT).show();
                                clear();
                            } else {
                                Toast.makeText(MainActivity.this, "Saving Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        mBtnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent view =new Intent(getApplicationContext(),ViewUsersActivity.class);
                startActivity(view);

            }
        });


    }
    public void clear(){
        mEdtName.setText("");
        mEdtMail.setText("");
        mEdtIdNumber.setText("");
    }
}
