package com.example.notes199;//package com.example.notes199;
//
//import android.net.Uri;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.List;
//
//public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
//
//    private List<String> noteTitles;
//    private List<String> noteDescription;
//    private List<String> noteImages;
//
//
//
//    public NoteAdapter(List<String> noteTitles, List<String> noteDescription, List<String> noteImages) {
//        this.noteTitles = noteTitles;
//        this.noteDescription = noteDescription;
//        this.noteImages = noteImages;
//    }
//
//    @NonNull
//    @Override
//    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.activity_item_user_row, parent, false);
//        return new NoteViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
//        String title = noteTitles.get(position);
//        String des = noteDescription.get(position);
//        String imag = noteImages.get(position);
//        holder.titleTextView.setText(title);
//        holder.des.setText(des);
//        Uri imageUri = Uri.parse(imag);
//        holder.noteimage.setImageURI(imageUri);
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return noteTitles.size();
//
//    }
//
//    public static class NoteViewHolder extends RecyclerView.ViewHolder {
//        public TextView des;
//        public TextView titleTextView;
//        public ImageView noteimage;
//
//        public NoteViewHolder(@NonNull View itemView) {
//            super(itemView);
//            titleTextView = itemView.findViewById(R.id.titleTextView);
//            des=  itemView.findViewById(R.id.des);
//            noteimage = itemView.findViewById(R.id.noteimage);
//        }
//    }
//}
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes199.Note;
import com.example.notes199.NoteDetails;
import com.example.notes199.R;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private List<Note> notes;
    private Context context;

    public NoteAdapter(List<Note> notes, Context context) {
        this.notes = notes;
        this.context = context;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_item_user_row, parent, false);
        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.bind(note);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pass the clicked note details to the NoteDetails activity
                Intent intent = new Intent(context, NoteDetails.class);
                intent.putExtra("title", note.getTitle());
                intent.putExtra("description", note.getDescription());
                intent.putExtra("imagePath", note.getImagePath());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {
        public TextView des;
        public TextView titleTextView;
        public ImageView noteimage;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            des = itemView.findViewById(R.id.des);
            noteimage = itemView.findViewById(R.id.noteimage);
        }

        public void bind(Note note) {
            titleTextView.setText(note.getTitle());
            des.setText(note.getDescription());
            // Load image using Glide or Picasso for better performance
            // For now, let's assume you're using setImageURI for simplicity
            noteimage.setImageURI(Uri.parse(note.getImagePath()));
        }
    }
}
