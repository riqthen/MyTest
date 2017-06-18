package com.riq.testa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.flowerfat.volleyutil.callback.Callback;
import com.flowerfat.volleyutil.main.VolleyUtils;
import com.riq.mylibrary.JSON;
import com.riq.mylibrary.utils.Lcat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main8Activity extends AppCompatActivity {

    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv)
    public void onViewClicked() {
        VolleyUtils.getInstance()
                .get()
                .url("http://data.yunduapp.org/admin_app/test.php?c=list&id=19212&page=0&pageSize=999&memberID=1450")
                .tag("MainActivity")
                .Go(new Callback<String>() {
                    @Override
                    public Decide dataBeautifulPlus(String response) throws IOException {
                        return null;
                    }

                    @Override
                    public void onSuccess(String  response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            JSONArray normal = JSON.getArray(object, "normal");
                            for (int i = 0; i < normal.length(); i++) {
                                JSONObject objInArray = JSON.getObjInArray(normal, i);
                                Lcat.print(JSON.getString(objInArray, "topic"));
                                Lcat.print(JSON.getString(objInArray, "addTime"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(String e) {

                    }
                });
    }
}
