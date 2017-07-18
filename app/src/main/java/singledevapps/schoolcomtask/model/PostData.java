package singledevapps.schoolcomtask.model;

import android.net.Uri;

import java.util.ArrayList;

/**
 * Created by prakash on 18/7/17.
 */

public class PostData {
    private String postText;
    private ArrayList<Uri> postUri;

    public PostData(String postText, ArrayList<Uri> postUri){
        this.postText = postText;
        this.postUri = postUri;

    }

    public  String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public ArrayList<Uri> getPostUri() {
        return postUri;
    }

    public void setPostUri(ArrayList<Uri> postUri) {
        this.postUri = postUri;
    }
}
