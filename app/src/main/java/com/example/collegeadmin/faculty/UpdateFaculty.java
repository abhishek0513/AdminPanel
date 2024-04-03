package com.example.collegeadmin.faculty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.collegeadmin.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UpdateFaculty extends AppCompatActivity {

    FloatingActionButton fab;
    private RecyclerView counsellorDepartment, csDepartment, mechanicalDepartment, physicsDepartment, EEDepartment, managementDepartment,
            diplomaDepartment, bmltDepartment, trainingDepartment, transportDepartment;

    private LinearLayout counsellorNoData, csNoData, mechanicalNoData, physicsNoData, eeNoData, managementNoData, diplomaNoData, bmltNoData,
            trainingNoData, transportNoData;

    private List<TeacherData>list1, list2, list3, list4, list5, list6, list7, list8, list9, list10;
    private DatabaseReference reference, dbref;

    private TeacherAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_faculty);
        fab = findViewById(R.id.fab);

        counsellorDepartment = findViewById(R.id.counsellorDepartment);
        csDepartment = findViewById(R.id.csDepartment);
        mechanicalDepartment = findViewById(R.id.mechanicalDepartment);
        physicsDepartment = findViewById(R.id.physicsDepartment);
        EEDepartment = findViewById(R.id.EEDepartment);
        managementDepartment = findViewById(R.id.managementDepartment);
        diplomaDepartment = findViewById(R.id.diplomaDepartment);
        bmltDepartment = findViewById(R.id.bmltDepartment);
        trainingDepartment = findViewById(R.id.trainingDepartment);
        transportDepartment = findViewById(R.id.transportDepartment);



        counsellorNoData = findViewById(R.id.counsellorNoData);
        csNoData = findViewById(R.id.csNoData);
        mechanicalNoData = findViewById(R.id.mechanicalNoData);
        physicsNoData = findViewById(R.id.physicsNoData);
        eeNoData = findViewById(R.id.eeNoData);
        managementNoData = findViewById(R.id.managementNoData);
        diplomaNoData = findViewById(R.id.diplomaNoData);
        bmltNoData = findViewById(R.id.bmltNoData);
        trainingNoData = findViewById(R.id.trainingNoData);
        transportNoData = findViewById(R.id.transportNoData);

        reference = FirebaseDatabase.getInstance().getReference().child("Teacher");

        csDepartment();
        mechanicalDepartment();
        counsellorDepartment();
        physicsDepartment();
        EEDepartment();
        managementDepartment();
        diplomaDepartment();
        bmltDepartment();
        trainingDepartment();
        transportDepartment();





        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UpdateFaculty.this, AddTeachers.class));
            }
        });

    }

    private void csDepartment() {

        dbref = reference.child("Computer Department");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    list1 = new ArrayList<>();
                    if (!dataSnapshot.exists()){
                        csNoData.setVisibility(View.VISIBLE);
                        csDepartment.setVisibility(View.GONE);
                    }
                    else{

                        csNoData.setVisibility(View.GONE);
                        csDepartment.setVisibility(View.VISIBLE);
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                            TeacherData data = snapshot.getValue(TeacherData.class);
                            list1.add(data);
                        }
                        csDepartment.setHasFixedSize(true);
                        csDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                        adapter = new TeacherAdapter(list1, UpdateFaculty.this, "Computer Department");
                        csDepartment.setAdapter(adapter);
                    }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void mechanicalDepartment() {

        dbref = reference.child("Mechanical Department");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list2 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    mechanicalNoData.setVisibility(View.VISIBLE);
                    mechanicalDepartment.setVisibility(View.GONE);
                }
                else{

                    mechanicalNoData.setVisibility(View.GONE);
                    mechanicalDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list2.add(data);
                    }
                    mechanicalDepartment.setHasFixedSize(true);
                    mechanicalDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list2, UpdateFaculty.this, "Mechanical Department");
                    mechanicalDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void counsellorDepartment() {

        dbref = reference.child("Others");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list3 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    counsellorNoData.setVisibility(View.VISIBLE);
                    counsellorDepartment.setVisibility(View.GONE);
                }
                else{

                    counsellorNoData.setVisibility(View.GONE);
                    counsellorDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list3.add(data);
                    }
                    counsellorDepartment.setHasFixedSize(true);
                    counsellorDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list3, UpdateFaculty.this, "Others");
                    counsellorDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void physicsDepartment() {

        dbref = reference.child("Physics/Chemistry/Maths");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list4 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    physicsNoData.setVisibility(View.VISIBLE);
                    physicsDepartment.setVisibility(View.GONE);
                }
                else{

                    physicsNoData.setVisibility(View.GONE);
                    physicsDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list4.add(data);
                    }
                    physicsDepartment.setHasFixedSize(true);
                    physicsDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list4, UpdateFaculty.this, "Physics/Chemistry/Maths");
                    physicsDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void EEDepartment() {

        dbref = reference.child("Electronics/Civil Department");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list5 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    eeNoData.setVisibility(View.VISIBLE);
                    EEDepartment.setVisibility(View.GONE);
                }
                else{

                    eeNoData.setVisibility(View.GONE);
                    EEDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list5.add(data);
                    }
                    EEDepartment.setHasFixedSize(true);
                    EEDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list5, UpdateFaculty.this, "Electronics/Civil Department");
                    EEDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void managementDepartment() {

        dbref = reference.child("Management Department");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list6 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    managementNoData.setVisibility(View.VISIBLE);
                    managementDepartment.setVisibility(View.GONE);
                }
                else{

                    managementNoData.setVisibility(View.GONE);
                    managementDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list6.add(data);
                    }
                    managementDepartment.setHasFixedSize(true);
                    managementDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list6, UpdateFaculty.this, "Management Department");
                    managementDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void diplomaDepartment() {

        dbref = reference.child("Diploma Department");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list7 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    diplomaNoData.setVisibility(View.VISIBLE);
                    diplomaDepartment.setVisibility(View.GONE);
                }
                else{

                    diplomaNoData.setVisibility(View.GONE);
                    diplomaDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list7.add(data);
                    }
                    diplomaDepartment.setHasFixedSize(true);
                    diplomaDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list7, UpdateFaculty.this, "Diploma Department");
                    diplomaDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void bmltDepartment() {
        dbref = reference.child("BMLT/DMLT Department");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list8 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    bmltNoData.setVisibility(View.VISIBLE);
                    bmltDepartment.setVisibility(View.GONE);
                }
                else{

                    bmltNoData.setVisibility(View.GONE);
                    bmltDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list8.add(data);
                    }
                    bmltDepartment.setHasFixedSize(true);
                    bmltDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list8, UpdateFaculty.this, "BMLT/DMLT Department");
                    bmltDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void trainingDepartment() {
        dbref = reference.child("Training and Placement Offices");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list9 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    trainingNoData.setVisibility(View.VISIBLE);
                    trainingDepartment.setVisibility(View.GONE);
                }
                else{

                    trainingNoData.setVisibility(View.GONE);
                    trainingDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list9.add(data);
                    }
                    trainingDepartment.setHasFixedSize(true);
                    trainingDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list9, UpdateFaculty.this, "Training and Placement Offices");
                    trainingDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void transportDepartment() {
        dbref = reference.child("Transports");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list10 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    transportNoData.setVisibility(View.VISIBLE);
                    transportDepartment.setVisibility(View.GONE);
                }
                else{

                    transportNoData.setVisibility(View.GONE);
                    transportDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list10.add(data);
                    }
                    transportDepartment.setHasFixedSize(true);
                    transportDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list10, UpdateFaculty.this, "Transports");
                    transportDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateFaculty.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }




}