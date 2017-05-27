package com.riq.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import com.riq.mylibrary.volleyutils.JSON;

import org.json.JSONException;
import org.json.JSONObject;

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
    @BindView(R.id.ly_main)
    LinearLayout lyMain;

    private String userId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        SPUtils.getInstance().init(this, "session");
    }

    @OnClick({R.id.btn, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5})
    public void onViewClicked(View view) {
        tv1.setText("");
        tv2.setText("");
        switch (view.getId()) {
            // 登录
            case R.id.btn:
                login();
                login2();
                getVolleyMa();
                break;

            //消息列表
            case R.id.btn2:
                getMessageList();
                getMessageList2();
                break;

            //未处理变处理中
            case R.id.btn3:
                toHandling();
                break;

            //提交表单
            case R.id.btn4:
                submitTable();
                break;

            //事件类型
            case R.id.btn5:
                getEventType();
                break;
        }
    }


    // TODO: 2017/5/27 登录 --->
    private void login() {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST,
                "http://112.74.62.15:8088/alarm/web/Applogin",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Lcat.print(response);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        userId = JSON.getString(jsonObject, "userId");
                        Lcat.print(userId);
                        tv1.setText(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Lcat.print(error);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("userName", etName.getText().toString());    //用户名
                params.put("passWord", "123456");
                return params;
            }
        };
        queue.add(request);
    }

    // TODO: 2017/5/27 消息列表 --->
    private void getMessageList() {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST
                , "http://112.74.62.15:8088/alarm/web/AppMonitorManage",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Lcat.print(response);
                        tv1.setText(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Lcat.print(error);
                        tv1.setText(error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("userId", userId);    //
                Lcat.print(userId);
                return params;
            }
        };
        queue.add(request);
    }

    // TODO: 2017/5/27 未处理变处理中 --->
    private void toHandling() {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST
                , "http://112.74.62.15:8088/alarm/web/AppUpdateStatus",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Lcat.print(response);
                        tv1.setText(response);
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
                HashMap<String, String> params = new HashMap<>();
                params.put("id", "1");    //
                params.put("status", "");    //
                return params;
            }
        };
        queue.add(request);
    }

    // TODO: 2017/5/27 提交表单 --->
    private void submitTable() {

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST
                , "http://112.74.62.15:8088/alarm/web/APPUpdateResults",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Lcat.print(response);
                        tv1.setText(response);
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
                HashMap<String, String> params = new HashMap<>();
                params.put("id", "1");    //
                params.put("alarmType", "");    //
                params.put("handleOpinion", "");    //
                params.put("handleResult", "");    //
                params.put("userName", "");    //
                return params;
            }
        };
        queue.add(request);
    }

    // TODO: 2017/5/27 事件类型 --->
    private void getEventType() {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST
                , "http://112.74.62.15:8088/alarm/web/selectAlarmType",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Lcat.print(response);
                        tv1.setText(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Lcat.print(error);
                        tv2.setText(error.toString());
                    }
                });
        queue.add(request);
    }


    private void login2() {
        Map<String, String> params = new HashMap<>();
        params.put("userName", etName.getText().toString());    //用户名
        params.put("passWord", "123456");
        MyStringRequest request = new MyStringRequest(Request.Method.POST,
                "http://112.74.62.15:8088/alarm/web/Applogin", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                tv2.setText(response);
                Lcat.print(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Lcat.print(error);
            }
        }, params);
        MyApp.getInstance().getRequestQueue().add(request);
    }


    private void getMessageList2() {
        MyStringRequest request = new MyStringRequest(Request.Method.POST,
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
                }, null);
        MyApp.getInstance().getRequestQueue().add(request);

    }


    private void getVolleyMa() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://112.74.62.15:8088/alarm/web/Applogin";
        //POST方式更加安全
        StringRequest stringrequest = new StringRequest(Request.Method.POST, url,
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
                    }
                }) {
            //重写parseNetworkResponse方法，返回的数据中 Set-Cookie:***************;
            //其中**************为session id
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                Response<String> superResponse = super.parseNetworkResponse(response);
                Map<String, String> responseHeaders = response.headers;
                String rawCookies = responseHeaders.get("Set-Cookie");
                //Constant是一个自建的类，存储常用的全局变量
                MyApp.localCookie = rawCookies.substring(0, rawCookies.indexOf(";"));
                Lcat.print("session_id", MyApp.localCookie); // JSESSIONID=FA5BD406B934194DCEF85B015BEF52E6
                return superResponse;
            }
        };
        requestQueue.add(stringrequest);
    }


}
