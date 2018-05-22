package com.ptit.btl.moviedb.screen.login;

public class LoginContract {
    public interface View {
        void onLoginSucess();

        void onLoginFailed();

    }

    public interface Presenter {
        void loginFacebook();

        void login();

    }
}
