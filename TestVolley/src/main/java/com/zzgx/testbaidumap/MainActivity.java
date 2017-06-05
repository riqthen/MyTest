package com.zzgx.testbaidumap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.riq.mylibrary.utils.Lcat;
import com.riq.mylibrary.utils.SPUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.tv_success)
    TextView tv1;
    @BindView(R.id.tv_error)
    TextView tv2;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_pwd)
    EditText etPwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        SPUtils.getInstance().init(this, "session");

    }

    @OnClick({R.id.btn, R.id.btn2})
    public void onViewClicked(View view) {
        tv1.setText("");
        tv2.setText("");
        switch (view.getId()) {
            // 登录
            case R.id.btn:
                login();
                break;

            //消息列表
            case R.id.btn2:
                getMessageList();
                break;

        }
    }

    private void login() {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST,
                "http://112.74.62.15:8088/alarm/web/Applogin",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        tv1.setText(response);
                        Lcat.print(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Lcat.print(error);
                        tv2.setText(error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("userName", etName.getText().toString());    //用户名
                params.put("passWord", etPwd.getText().toString());     //密码
                return params;
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                Response<String> superResponse = super.parseNetworkResponse(response);
                Map<String, String> responseHeaders = response.headers;
                String rawCookies = responseHeaders.get("Set-Cookie");
                String localCookie = rawCookies.substring(0, rawCookies.indexOf(";"));
                Lcat.print("session_id", localCookie);
                SPUtils.getInstance().saveString("JSESSIONID", localCookie, "session");
                return superResponse;
            }
        };
        queue.add(request);
    }

    //----
    private void getMessageList() {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST,
                "http://112.74.62.15:8088/alarm/web/AppMonitorManage",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        tv1.setText(response);
                        Lcat.print(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        tv2.setText(error.toString());
                        Lcat.print(error);
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                if (SPUtils.getInstance().getString("JSESSIONID", "session") != null
                        && SPUtils.getInstance().getString("JSESSIONID", "session").length() > 0) {
                    HashMap<String, String> headers = new HashMap<>();
                    headers.put("cookie", SPUtils.getInstance().getString("JSESSIONID", "session"));
                    Lcat.print("headers", headers);
                    return headers;
                } else {
                    return super.getHeaders();
                }
            }
        };
        queue.add(request);
    }


}
