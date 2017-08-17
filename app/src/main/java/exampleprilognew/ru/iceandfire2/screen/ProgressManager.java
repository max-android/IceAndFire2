package exampleprilognew.ru.iceandfire2.screen;

import android.app.ProgressDialog;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;


/**
 * Created by Максим on 28.07.2017.
 */

public class ProgressManager {

    private ProgressDialog progressDialog;


    public ProgressManager(Context context) {

        this.progressDialog = new ProgressDialog(context);
    }


    public void showDialog(String title,int max){

        progressDialog.setTitle(title);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(max);
        //progressDialog.setCancelable(true);
        progressDialog.show();
    }

public void dismissDialog() {

    if (progressDialog.isShowing()) {

        progressDialog.dismiss();
    }
}

    public void showProgress(String... str){
        JSONArray progress=null;
        try {
            progress = new JSONArray(str[0]);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int i = 0; i <progress.length(); i++) {
            progressDialog.setProgress(i+1);

        }
    }
}
