package singledevapps.schoolcomtask;

import android.content.Intent;
import android.database.Cursor;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import singledevapps.schoolcomtask.adapter.DataAdapter;
import singledevapps.schoolcomtask.data.DataList;
import singledevapps.schoolcomtask.model.PostData;


public class WritePostActivity extends AppCompatActivity {
    private static final int IMAGE_PICKER_SELECT = 111;
    private ImageView attach;
    private LinearLayout preview;
    private Button post;
    private EditText postEt;
    private Uri selectedMediaUri;
    private ArrayList<Uri> uriList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_post);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        uriList = new ArrayList<>();
        attach = (ImageView) findViewById(R.id.ivAttach);
        post = (Button) findViewById(R.id.btn_post);
        preview = (LinearLayout) findViewById(R.id.llPrev);
        postEt = (EditText) findViewById(R.id.etPost);
        attach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_MIME_TYPES, new String[] {"image/*", "video/*"});
                startActivityForResult(intent, IMAGE_PICKER_SELECT);
            }
        });
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String post = postEt.getText().toString();
                if (!post.isEmpty()) {
                    PostData pd = new PostData(post,uriList);
                    DataList.DataList().add(pd);
                    DataAdapter.instance(WritePostActivity.this).notifyDataSetChanged();
                    finish();
                }
            }
        });
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            selectedMediaUri = data.getData();
            Toast.makeText(WritePostActivity.this,selectedMediaUri.toString(),Toast.LENGTH_SHORT).show();
            addViews(selectedMediaUri);
        }

    }

    public void addViews(Uri uri) {
        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(300,
                300);
        ImageView iv = new ImageView(this);
        iv.setLayoutParams(lparams);
        if (uri.toString().contains("image")) {
            uriList.add(uri);
            iv.setImageURI(uri);
        } else  if (uri.toString().contains("video")) {
            //TODO Video thumb debug
            String mUri = getRealPathFromURI(uri);
           iv.setImageBitmap(ThumbnailUtils.createVideoThumbnail(
                   mUri, MediaStore.Video.Thumbnails.FULL_SCREEN_KIND));
        }
        preview.addView(iv, 0);
    }

    public String getRealPathFromURI(Uri contentURI) {

        String result  = null;
        try {
            Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
            if (cursor == null) {
                result = contentURI.getPath();
            } else {
                cursor.moveToFirst();
                int idx = 0;
                    idx = cursor.getColumnIndex(MediaStore.Video.VideoColumns.DATA);
                result = cursor.getString(idx);
                cursor.close();
            }
        } catch (Exception e){
        }
        return result;
    }

}
