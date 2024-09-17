package com.example.notes199;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.io.File;

public class NoteDetails extends AppCompatActivity {
    TextView titleTextView;
    TextView descriptionTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);

        // Get the intent that started this activity
        Intent intent = getIntent();
        titleTextView = findViewById(R.id.titleTextVieww);
        descriptionTextView = findViewById(R.id.descriptionTextVieww);
        ImageView imageView = findViewById(R.id.imageVieww);

        // Retrieve note details from the intent
        String title = intent.getStringExtra("title");
        String description = intent. getStringExtra("description");
        String imagePath = intent.getStringExtra("imagePath");

        titleTextView.setText(title);
        descriptionTextView.setText(description);

        if (imagePath != null) {
            Glide.with(this)
                    .load(Uri.parse(imagePath))
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            Log.e("Glide", "Failed to load image: " + e);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            return false;
                        }
                    })
                    .into(imageView);
        } else {
            Log.e("NoteDetails", "Image path is null");
        }
    }
}
