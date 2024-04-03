package com.example.collegeadmin.faculty;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.collegeadmin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class UpdateTeacherActivity extends AppCompatActivity {

    private ImageView updateTeacherImage;
    private EditText updateTeacherName, updateTeacherNumber, updateTeacherPost;
    private Button updateTeacherBtn, deleteTeacherBtn;


    private  String name, number, post, image;
    private  final int REQ = 1 ;
    private Bitmap bitmap = null;

    private StorageReference storageReference;
    private String downloadUrl, category, uniqueKey;
    private DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_teacher);

        name = getIntent().getStringExtra("name");
        number = getIntent().getStringExtra("number");
        post = getIntent().getStringExtra("post");
        image = getIntent().getStringExtra("image");


        updateTeacherImage  = findViewById(R.id.updateTeacherImage);
        updateTeacherName  = findViewById(R.id.updateTeacherName);
        updateTeacherNumber  = findViewById(R.id.updateTeacherNumber);
        updateTeacherPost  = findViewById(R.id.updateTeacherPost);
        deleteTeacherBtn  = findViewById(R.id.deleteTeacherBtn);
        updateTeacherBtn  = findViewById(R.id.updateTeacherBtn);

         uniqueKey = getIntent().getStringExtra("key");
         category  =getIntent().getStringExtra("category");

        reference = FirebaseDatabase.getInstance().getReference().child("Teacher");
        storageReference = FirebaseStorage.getInstance().getReference();

        try {
            Picasso.get().load(image).into(updateTeacherImage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        updateTeacherNumber.setText(number);
        updateTeacherName.setText(name);
        updateTeacherPost.setText(post);

        updateTeacherImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        updateTeacherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkValidation();
            }
        });

        deleteTeacherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData();
            }
        });

    }

    private void deleteData() {
        reference.child(category).child(uniqueKey).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(UpdateTeacherActivity.this, "Staff Deleted Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateTeacherActivity.this, UpdateFaculty.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UpdateTeacherActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void checkValidation() {
        name = updateTeacherName.getText().toString();
        number = updateTeacherNumber.getText().toString();
        post = updateTeacherPost.getText().toString();
        if (name.isEmpty()){
            updateTeacherName.setError("Empty");
            updateTeacherName.requestFocus();
        }
        else if (number.isEmpty()){
            updateTeacherNumber.setError("Empty");
            updateTeacherNumber.requestFocus();
        }
        else if (post.isEmpty()){
            updateTeacherPost.setError("Empty");
            updateTeacherPost.requestFocus();
        }
        else if(bitmap == null){
            updateData(image);
        }else{
            uploadImage(); 
        }
    }

    private void uploadImage() {


        ByteArrayOutputStream baos  = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] finalimg = baos.toByteArray();
        final StorageReference filePath;
        filePath = storageReference.child("Teachers").child(finalimg+".jpg");
        final UploadTask uploadTask = filePath.putBytes(finalimg);
        uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()){
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    downloadUrl = String.valueOf(uri);
                                    updateData(downloadUrl);
                                }
                            });
                        }
                    });
                }else{
//                    pd.dismiss();
                    Toast.makeText(UpdateTeacherActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void updateData(String s) {

        HashMap hp = new HashMap();
        hp.put("name", name);
        hp.put("post", post);
        hp.put("number", number);
        hp.put("image", s);

        Log.d("UpdateTeacherActivity", "Category: " + category);
        Log.d("UpdateTeacherActivity", "UniqueKey: " + uniqueKey);
        Log.d("UpdateTeacherActivity", "HashMap: " + hp);

        if (category != null && uniqueKey != null && hp != null) {
            reference.child(category).child(uniqueKey).updateChildren(hp).addOnSuccessListener(new OnSuccessListener() {
                @Override
                public void onSuccess(Object o) {
                    Toast.makeText(UpdateTeacherActivity.this, "Staff Updated Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UpdateTeacherActivity.this, UpdateFaculty.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(UpdateTeacherActivity.this, "Something went wrong ", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            // Handle the case when category, unique, or hp is null
            Toast.makeText(UpdateTeacherActivity.this, "Invalid data for update operation", Toast.LENGTH_SHORT).show();
        }

    }

    private void openGallery() {
        Intent pickImage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickImage, REQ);
    }


    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ && resultCode == RESULT_OK){
            Uri uri = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            updateTeacherImage.setImageBitmap(bitmap);
        }
    }
}