package com.example.vanillatodo;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
import java.util.Objects;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.ViewHolder> {
    private ArrayList<String> localDataSet = new ArrayList<String>();

    public void addItem(String text) {
        localDataSet.add(text);
        this.notifyDataSetChanged();
    }

    public void removeItemAt(int index) {
        localDataSet.remove(index);
        this.notifyDataSetChanged();
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final Button todoDelete;
        private final TextView todoText;
        private final FlexboxLayout todoFlexContainer;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            todoDelete = (Button) view.findViewById(R.id.todoDelete);
            todoText = (TextView) view.findViewById(R.id.todoText);
            todoFlexContainer = (FlexboxLayout) view.findViewById(R.id.todoFlexContainer);
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * by RecyclerView.
     */
    public TodoListAdapter() {
        localDataSet.add("1");
        localDataSet.add("2");
        localDataSet.add("3");
        localDataSet.add("4");
        localDataSet.add("5");
        localDataSet.add("6");
        localDataSet.add("7");
        localDataSet.add("8");
        localDataSet.add("9");
        localDataSet.add("10");
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.todo_cell, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.todoText.setText(localDataSet.get(position));
        viewHolder.todoDelete.setOnClickListener(v -> {
           removeItemAt(position);
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
