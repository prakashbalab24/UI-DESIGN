package singledevapps.schoolcomtask.Utils;

import android.app.Activity;
import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import singledevapps.schoolcomtask.model.PostData;

/**
 * Created by prakash on 18/7/17.
 */

public class JsonEncoder {
    public static JSONObject createPost(ArrayList<String> uri,String post) throws JSONException {

        Log.i("PostTostore",uri.toString());
        JSONObject jResult = new JSONObject();
        JSONArray jsonArray = new JSONArray(uri);
        jResult.putOpt("post", post);
        jResult.put("media_uri",jsonArray);
        Log.i("PostTostore",jResult.toString());


        return jResult;
    }
}
