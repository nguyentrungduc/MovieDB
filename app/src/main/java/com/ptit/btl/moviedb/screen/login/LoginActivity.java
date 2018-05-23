package com.ptit.btl.moviedb.screen.login;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.ptit.btl.moviedb.R;
import com.ptit.btl.moviedb.data.model.User;
import com.ptit.btl.moviedb.data.repository.UserRepository;
import com.ptit.btl.moviedb.data.source.UserDataSource;
import com.ptit.btl.moviedb.data.source.local.UserLocalDataSource;
import com.ptit.btl.moviedb.data.source.remote.UserRemoteDataSource;
import com.ptit.btl.moviedb.screen.BaseActivity;
import com.ptit.btl.moviedb.screen.home.HomeActivity;
import com.ptit.btl.moviedb.util.StringUtils;

import org.json.JSONObject;

import java.util.Arrays;

public class LoginActivity extends BaseActivity implements LoginContract.View {

    CallbackManager mCallbackManager;
    LoginButton mLoginButton;
    private Button mBtnLogin;
    private static final String TAG = LoginActivity.class.toString();
    private LoginContract.Presenter mPresenter;
    private EditText mName, mPassword;

    public static Intent getInstance(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this);
        setContentView(R.layout.activity_login);
        mPresenter = new LoginPresenter(new UserRepository(UserLocalDataSource.getInstance(this), UserRemoteDataSource.getInstance()));
        mPresenter.setView(this);
        mPresenter.checkUser();
        mName = findViewById(R.id.name);
        mPassword = findViewById(R.id.password);
        mLoginButton = findViewById(R.id.login_button);
        mBtnLogin = findViewById(R.id.btn_login);
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.login(mName.getText().toString(), mPassword.getText().toString());
            }
        });
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallbackManager = CallbackManager.Factory.create();

                //LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
                mLoginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {

                        Log.d(TAG, "sc");
                        Log.d(TAG, loginResult.getAccessToken().getToken() +" "+  loginResult.getAccessToken().getUserId());


                        mLoginButton.setReadPermissions(Arrays.asList(
                                "public_profile", "email", "user_birthday","user_about_me", "user_friends","user_photos","user_education_history","user_work_history",
                                "user_posts","read_custom_friendlists","user_friends","user_likes"));

                        AccessToken.getCurrentAccessToken().getPermissions();

                        final GraphRequest request = GraphRequest.newMeRequest(
                                AccessToken.getCurrentAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject object,
                                                            GraphResponse response) {
                                        Log.d(TAG,"aaa"+ object.toString());
                                        String mName = object.optString(getString(R.string.title_name));
                                        String mId = object.optString(getString(R.string.title_id));
                                        String mLink = object.optString(getString(R.string.title_link));
                                        User user = new User(mId, mName, StringUtils.getURLAvatar(mId));
                                        mPresenter.saveUser(user);
                                        Log.d(TAG, user.getImageLink());
                                    }
                                });
                        Bundle parameters = new Bundle();
                        parameters.putString(getString(R.string.fields), getString(R.string.fields_name));
                        request.setParameters(parameters);
                        request.executeAsync();


                    }

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onError(FacebookException error) {
                    }
                });
            }
        });
    }

    @Override
    public void onLoginSucess() {

    }

    @Override
    public void onLoginFailed() {
        Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onSaveUserSucess(User user) {
        startActivity(HomeActivity.getInstance(getApplicationContext()));
    }

    @Override
    public void goTo() {
        startActivity(HomeActivity.getInstance(getApplicationContext()));

    }

    @Override
    public void onLoginSucess(User user) {
        mPresenter.saveUser(user);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
