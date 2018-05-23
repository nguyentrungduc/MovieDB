package com.ptit.btl.moviedb.screen.login;

import com.ptit.btl.moviedb.data.model.User;
import com.ptit.btl.moviedb.screen.BasePresenter;

public class LoginContract {
    public interface View {
        void onLoginSucess();

        void onLoginFailed();

        void onSaveUserSucess(User user);

        void goTo();

        void onLoginSucess(User user);
    }

    public interface Presenter extends BasePresenter<View> {
        void saveUser(User user);

        void login(String name, String pw);

        void checkUser();
    }
}
