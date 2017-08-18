package com.itg.jobcardmanagement.doument.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.itg.jobcardmanagement.R;
import com.itg.jobcardmanagement.common.CommonMethod;
import com.itg.jobcardmanagement.doument.adapter.DocumentFileAdapter;
import com.itg.jobcardmanagement.widget.SimpleDividerItemDecoration;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * <p>
 * to handle interaction events.
 * Use the {@link DocumentDailogueFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DocumentDailogueFragment extends DialogFragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int GALLERY = 234;
    private static final int CANCEL = 987;
    private static final String IMAGE_DIRECTORY = "/" + CommonMethod.APPNAME + "/" + "Document";

    Unbinder unbinder;
    @BindView(R.id.recyclerViewDocument)
    RecyclerView recyclerViewDocument;
    @BindView(R.id.img_add)
    ImageView imgAdd;
    @BindView(R.id.txt_no)
    TextView txtNo;
    @BindView(R.id.btn_upload)
    Button btnUpload;
    @BindView(R.id.rl_no_item)
    RelativeLayout rlNoItem;
    @BindView(R.id.rl_recyclerView)
    RelativeLayout rlRecyclerView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private Context mContext;
    private ArrayList<String> documnetFileList;
    private DocumentFileAdapter adapter;

    public DocumentDailogueFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DocumentDailogueFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DocumentDailogueFragment newInstance(String param1, String param2) {
        DocumentDailogueFragment fragment = new DocumentDailogueFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = RelativeLayout.LayoutParams.MATCH_PARENT;
        params.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setLayout(params.width, params.height);

    }

    @Override
    public void onResume() {
        super.onResume();
        WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = RelativeLayout.LayoutParams.MATCH_PARENT;
        params.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setLayout(params.width, params.height);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_document_dailogue, container, false);
        unbinder = ButterKnife.bind(this, view);

        getDialog().setTitle("Transfer Document");

        imgAdd.setOnClickListener(this);
        documnetFileList = new ArrayList<>();
        documnetFileList.add("");

        setRecyclerView();
        return view;
    }

    private void setRecyclerView() {
        if (documnetFileList != null && documnetFileList.size() > 0) {
            rlRecyclerView.setVisibility(View.VISIBLE);
            rlNoItem.setVisibility(View.GONE);
            SimpleDividerItemDecoration simpleDividerItemDecoration = new SimpleDividerItemDecoration(recyclerViewDocument.getContext());
            recyclerViewDocument.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            recyclerViewDocument.addItemDecoration(simpleDividerItemDecoration);
            adapter = new DocumentFileAdapter(mContext, documnetFileList, new DocumentFileAdapter.DocumentListener() {
                @Override
                public void onAddDocumentClicked() {
                    OpenGalleryForAttachDocument();
                }

                @Override
                public void onRemoveDocument(int adapterPosition) {
                    documnetFileList.remove(adapterPosition);
                    adapter.notifyItemRemoved(adapterPosition);
                }
            });
            recyclerViewDocument.setAdapter(adapter);
        } else {
            rlNoItem.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mContext != null) {
            mContext = null;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_add:
                OpenGalleryForAttachDocument();
                break;
            case R.id.btn_upload:

                break;
        }

    }

    private void OpenGalleryForAttachDocument() {
        Intent intentGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intentGallery, GALLERY);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.CANCEL) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), contentURI);
                    String path = saveImage(bitmap);
                    documnetFileList.remove(documnetFileList.size() - 1);
                    documnetFileList.add(path);
                    documnetFileList.add(path);
                    Log.d(getClass().getSimpleName(), "FilePath:" + path);

                    setRecyclerView();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(mContext, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        }

    }

    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        Log.d(getClass().getSimpleName(), "FilePath:" + Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);

        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }


        File f = new File(wallpaperDirectory, Calendar.getInstance()
                .getTimeInMillis() + ".png");

        try {
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(mContext,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);

        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("TAG", "File Saved::--->" + f.getAbsolutePath());
        return f.getAbsolutePath();

    }

}
