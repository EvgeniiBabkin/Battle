package com.example.battleofminds.presenter;

import android.support.annotation.NonNull;

import com.example.battleofminds.interactor.DefaultObserver;
import com.example.battleofminds.interactor.ToSignIn;
import com.example.battleofminds.model.pojo.CredentialsObject;
import com.example.battleofminds.util.CredentialValidator;
import com.example.battleofminds.view.SignInView;

public class SignInPresenter implements Presenter {


    private final ToSignIn toSingInUseCase;
    private final CredentialValidator credentialValidator;

    private SignInView view;

    SignInPresenter(ToSignIn toSingInUseCase, CredentialValidator credentialValidator){
        this.toSingInUseCase = toSingInUseCase;
        this.credentialValidator = credentialValidator;
    }


    public void setView(@NonNull final SignInView view) {
        this.view = view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    public void signIn(final String login, final String password){
        if (!credentialValidator.isLoginCorrect(login)){
            view.showLoginIsIncorrect();
            return;
        }
        if (!credentialValidator.isPasswordCorrect(password)){
            view.showPasswordIsIncorrect();
            return;
        }
        CredentialsObject credentialsObject = new CredentialsObject();
        credentialsObject.setLogin(login);
        credentialsObject.setPassword(password);
    }

    private final class SignInObserver extends DefaultObserver<String>{

        @Override
        protected void onStart() {
            view.showLoading();
        }

        @Override
        public void onComplete() {

        }

        @Override
        public void onError(Throwable exception) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            view.hideLoading();
        }
    }
}
