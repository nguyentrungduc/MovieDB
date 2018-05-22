package com.ptit.btl.moviedb.screen.login;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

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
import com.ptit.btl.moviedb.screen.home.HomeActivity;
import com.ptit.btl.moviedb.util.StringUtils;

import org.json.JSONObject;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    CallbackManager mCallbackManager;
    LoginButton mLoginButton;
    private static final String TAG = LoginActivity.class.toString();

    public static Intent getInstance(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this);
        setContentView(R.layout.activity_login);
        mLoginButton = findViewById(R.id.login_button);
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
                                        String mGender = object.optString(getString(R.string.title_gender));
                                        String mEmail = object.optString(getString(R.string.title_email));
                                        String mLink = object.optString(getString(R.string.title_link));
                                        User user = new User(mId, mName, StringUtils.getURLAvatar(mId));
                                        startActivity(HomeActivity.getInstance(getApplicationContext()));
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

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
