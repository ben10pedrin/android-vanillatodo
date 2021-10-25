package com.example.vanillatodo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.example.vanillatodo.databinding.TodoCellBinding;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    public void showKeyboard(){
        InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    public void closeKeyboard(){
        InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.todoCellsContainer);
        TodoListAdapter todoListAdapter = new TodoListAdapter();
        recyclerView.setAdapter(todoListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MaterialToolbar topAppBar = (MaterialToolbar) findViewById(R.id.topAppBar);
        topAppBar.setOnMenuItemClickListener(item -> {
            if(item.getItemId() == R.id.add) {
                Log.d("hi", "hi");
                MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(this);
                View textInputLayout = this.getLayoutInflater().inflate(R.layout.text_field, null);
                TextInputEditText textInputEditText = textInputLayout.findViewById(R.id.textInputEditText);
                materialAlertDialogBuilder
                        .setTitle("Add a task")
                        .setNeutralButton("Cancel", (dialog, which) -> {
                        })
                        .setPositiveButton("Done", (dialog, which) -> {
                            todoListAdapter.addItem(textInputEditText.getText().toString());
                        })
                        .setView(textInputLayout)
                        .setOnDismissListener(dialog -> {
                            closeKeyboard();
                        })
                        .show();
                textInputEditText.requestFocus();
                showKeyboard();
                return true;
            }
            return false;
        });
    }

}