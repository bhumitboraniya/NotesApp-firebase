package com.example.notes199;//package com.example.notes199;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    FirebaseAuth auth;
    private List<Note> noteList;
    private NoteAdapter noteAdapter;
    private static final int ADD_NOTE_REQUEST = 1;
    ImageView logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize the list of notes
        noteList = new ArrayList<>();

        // Initialize the RecyclerView and set its layout manager
        RecyclerView noteRecyclerView = findViewById(R.id.noteRecyclerView);
        logout = findViewById(R.id.image_logout);
        noteRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the NoteAdapter with the list of notes and set it to the RecyclerView
        noteAdapter = new NoteAdapter(noteList, this);
        noteRecyclerView.setAdapter(noteAdapter);

        auth = FirebaseAuth.getInstance();
        // Button to add new note
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(Home.this,R.style.Dialoge);
                dialog.setContentView(R.layout.dialog_layout);
                TextView yesbtn,nobtn;
                yesbtn = dialog.findViewById(R.id.yesbtn);
                nobtn= dialog.findViewById(R.id.nobtn);
                yesbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(getApplicationContext(),Register.class));
                    }
                });
                nobtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });



        findViewById(R.id.addnotew).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the AddNotes activity to add a new note
                Intent intent = new Intent(Home.this, AddNotes.class);
                startActivityForResult(intent, ADD_NOTE_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK && data != null) {
            // Retrieve note details from the AddNotes activity and add the note to the list
            String title = data.getStringExtra("title");
            String description = data.getStringExtra("description");
            String imagePath = data.getStringExtra("imagePath");
//            Toast.makeText(getApplicationContext(), imagePath, Toast.LENGTH_SHORT).show();

            Note note = new Note(title, description, imagePath);
            noteList.add(note);
            noteAdapter.notifyDataSetChanged();
        }
    }
}

//
//import android.content.Intent;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.Toast;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Home extends AppCompatActivity {
//    private List<Note> noteList;
//    private NoteAdapter noteAdapter;
//    private static final int ADD_NOTE_REQUEST = 1;
//
//    private NoteDatabaseHelper databaseHelper;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home);
//
//        databaseHelper = new NoteDatabaseHelper(this);
//
//        noteList = new ArrayList<>();
//        retrieveNotesFromDatabase();
//
//        RecyclerView noteRecyclerView = findViewById(R.id.noteRecyclerView);
//        noteRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        noteAdapter = new NoteAdapter(noteList, this);
//        noteRecyclerView.setAdapter(noteAdapter);
//
//        findViewById(R.id.addnotew).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Home.this, AddNotes.class);
//                startActivityForResult(intent, ADD_NOTE_REQUEST);
//            }
//        });
//    }
//
//    private void retrieveNotesFromDatabase() {
//        SQLiteDatabase db = databaseHelper.getReadableDatabase();
//        Cursor cursor = db.query(
//                NoteDatabaseHelper.TABLE_NOTES,
//                null,
//                null,
//                null,
//                null,
//                null,
//                null
//        );
//
//        if (cursor != null && cursor.moveToFirst()) {
//            do {
//                long id = cursor.getLong(cursor.getColumnIndex(NoteDatabaseHelper.COLUMN_ID));
//                String title = cursor.getString(cursor.getColumnIndex(NoteDatabaseHelper.COLUMN_TITLE));
//                String description = cursor.getString(cursor.getColumnIndex(NoteDatabaseHelper.COLUMN_DESCRIPTION));
//                String imagePath = cursor.getString(cursor.getColumnIndex(NoteDatabaseHelper.COLUMN_IMAGE_PATH));
//
//                Note note = new Note();
//                note.setId(id);
//                note.setTitle(title);
//                note.setDescription(description);
//                note.setImagePath(imagePath);
//
//                noteList.add(note);
//            } while (cursor.moveToNext());
//            cursor.close();
//        }
//        db.close();
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK && data != null) {
//            String title = data.getStringExtra("title");
//            String description = data.getStringExtra("description");
//            String imagePath = data.getStringExtra("imagePath");
//
//            insertNoteIntoDatabase(title, description, imagePath);
//
//            Note note = new Note();
//            note.setTitle(title);
//            note.setDescription(description);
//            note.setImagePath(imagePath);
//
//            noteList.add(note);
//            noteAdapter.notifyDataSetChanged();
//        }
//    }
//
//    private void insertNoteIntoDatabase(String title, String description, String imagePath) {
//        SQLiteDatabase db = databaseHelper.getWritableDatabase();
//
//        // Create a ContentValues object to store the values to be inserted
//        ContentValues values = new ContentValues();
//        values.put(NoteDatabaseHelper.COLUMN_TITLE, title);
//        values.put(NoteDatabaseHelper.COLUMN_DESCRIPTION, description);
//        values.put(NoteDatabaseHelper.COLUMN_IMAGE_PATH, imagePath);
//
//        // Insert the values into the database
//        db.insert(NoteDatabaseHelper.TABLE_NOTES, null, values);
//        db.close();
//    }
//}
