package com.crecre.admin.uis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.crecre.admin.R;
import com.crecre.admin.retrofit.APIClient;
import com.crecre.admin.retrofit.messages.requests.SignInRequest;
import com.crecre.admin.retrofit.messages.responses.SignInResponse;
import com.crecre.admin.utils.Alert;
import com.crecre.admin.utils.GsonDateFormatAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();

    @BindView(R.id.edit_login_id)
    EditText idEdit;
    @BindView(R.id.edit_login_passwd)
    EditText passwdEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_login_sign_in)
    void signIn() {
        String id = idEdit.getText().toString();
        String passwd = passwdEdit.getText().toString();

        SignInRequest signInRequest = new SignInRequest(id, passwd);

        APIClient.getInstance().getUserService()
                .signIn(signInRequest)
                .enqueue(new Callback<SignInResponse>() {
                    @Override
                    public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                        if (response.body() == null) {
                            Log.e(TAG, "로그인 중 에러 발생 : " + response.message());
                        } else {
                            if (response.body().getStatus() == 200) {
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                Alert.makeText("로그인 성공");

                                APIClient.getInstance().init(response.body().getData().getToken().getToken());

                                finish();
                            } else {
                                Alert.makeText("로그인 실패");
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<SignInResponse> call, Throwable t) {
                        Log.e(TAG, "로그인 중 네트워크 에러 발생", t);
                    }
                });
    }
}
