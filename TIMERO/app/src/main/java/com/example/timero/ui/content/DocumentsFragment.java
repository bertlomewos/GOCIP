package com.example.timero.ui.content;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.timero.R;
import com.example.timero.data.model.Post;

public class DocumentsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_documents, container, false);

        // Since the PDF viewer is removed, we'll just display a placeholder text.
        // In a real app, you would initialize the PDF viewer here.
        TextView placeholder;

        if (getArguments() != null) {
            Post post = getArguments().getParcelable(PostContentActivity.POST_EXTRA);
            if (post != null) {
                //placeholder.setText("Displaying document: " + post.getTitle());
            }
        }

        return view;
    }
}
