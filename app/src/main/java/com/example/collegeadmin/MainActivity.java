package com.example.collegeadmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
//import android.widget.Toolbar;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.collegeadmin.faculty.UpdateFaculty;
import com.example.collegeadmin.notice.deleteNoticeActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    CardView muploadNotice, addGalleryImage, addEbook, faculty, deleteNotice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        muploadNotice= findViewById(R.id.addNotice);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Admin Panel");

        muploadNotice.setOnClickListener(this);
        addGalleryImage = findViewById(R.id.addGalleryImage);
        addGalleryImage.setOnClickListener(this);
        addEbook = findViewById(R.id.addEbook);
        addEbook.setOnClickListener(this);
        faculty = findViewById(R.id.faculty);
        deleteNotice = findViewById(R.id.deleteNotice);
        faculty.setOnClickListener(this);
        deleteNotice.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.addNotice){
                    Intent intent = new Intent(getApplicationContext(), upload_Notice.class);
                    startActivity(intent);
        }
        else if (view.getId() == R.id.addGalleryImage){
            Intent intent = new Intent(getApplicationContext(), upload_image.class);
            startActivity(intent);
        }
        else if(view.getId() == R.id.addEbook){
            Intent intent = new Intent(getApplicationContext(), UploadPdfActivity.class);
            startActivity(intent);
        }
        else if(view.getId() == R.id.faculty){
            Intent intent = new Intent(getApplicationContext(), UpdateFaculty.class);
            startActivity(intent);
        }
        else if(view.getId() == R.id.deleteNotice) {
            Intent intent = new Intent(getApplicationContext(), deleteNoticeActivity.class);
            startActivity(intent);
        }
    }
}