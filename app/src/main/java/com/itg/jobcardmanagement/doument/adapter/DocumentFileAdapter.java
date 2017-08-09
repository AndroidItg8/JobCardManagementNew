package com.itg.jobcardmanagement.doument.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.itg.jobcardmanagement.R;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Android itg 8 on 8/8/2017.
 */

public class DocumentFileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {



    private Context mContext;
    private ArrayList<String> documentPath = new ArrayList<>();
    private DocumentListener listener;

    private static final int IMAGE = 1;
    private static final int BUTTON = 2;
    File file ;
    Uri imageUri;


    public DocumentFileAdapter(Context mContext, ArrayList<String> documentPath, DocumentListener listener) {

        this.mContext = mContext;
        this.documentPath = documentPath;
        this.listener = listener;
        file = new File(documentPath.get(documentPath.size()-1));
        imageUri =  Uri.fromFile(file);
    }

    @Override
    public int getItemViewType(int position) {
        if (documentPath.size() - 1 == position)
            return BUTTON;
        else
            return IMAGE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder view;

        if (viewType == IMAGE) {
            view = new DocumentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_document, parent, false));
        } else {
            view = new AddButtonViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_add_button, parent, false));
        }

        return view;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof  DocumentViewHolder)
        {
            Glide.with(mContext)
                    .load(imageUri)
                    .into(((DocumentViewHolder) holder).imgDocument);
        }
    }

    @Override
    public int getItemCount() {
        return documentPath.size();
    }

    public class DocumentViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_document)
        ImageView imgDocument;
        @BindView(R.id.img_cross)
        ImageView imgCross;

        public DocumentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            imgCross.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onRemoveDocument(getAdapterPosition());
                }
            });

        }

    }

    public interface DocumentListener {
        void onAddDocumentClicked();

        void onRemoveDocument(int adapterPosition);
    }

    public class AddButtonViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_add_document)
        ImageView imgAddDocument;


        public AddButtonViewHolder(final View inflate) {
            super(inflate);
            ButterKnife.bind(this, inflate);
            imgAddDocument.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onAddDocumentClicked();
                }
            });


        }


    }


}
