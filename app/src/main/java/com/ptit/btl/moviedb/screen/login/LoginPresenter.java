package com.ptit.btl.moviedb.screen.login;

import com.ptit.btl.moviedb.data.model.User;
import com.ptit.btl.moviedb.data.repository.UserRepository;
import com.ptit.btl.moviedb.data.source.UserDataSource;


public class LoginPresenter implements LoginContract.Presenter {
    private UserRepository mUserRepository;
    private LoginContract.View mView;

    public LoginPresenter(UserRepository userRepository) {
        mUserRepository = userRepository;
    }

    @Override
    public void saveUser(User user) {
        mUserRepository.saveUser(user);
        mView.onSaveUserSucess(user);


    }

    @Override
    public void login(String name,String pw) {
        mUserRepository.login(name, pw, new UserDataSource.GetUserCallback() {
            @Override
            public void onSucess(User user) {
                mView.onLoginSucess(user);
            }

            @Override
            public void onFailed() {
                mView.onLoginFailed();

            }
        });

    }

    @Override
    public void checkUser() {
        if (mUserRepository.getUser() != null) {
            mView.goTo();
            return;
        }

    }

    @Override
    public void setView(LoginContract.View view) {
        mView = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
