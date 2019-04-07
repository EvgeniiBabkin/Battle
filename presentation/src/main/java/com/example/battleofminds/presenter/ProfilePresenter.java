package com.example.battleofminds.presenter;

import com.example.battleofminds.interactor.DefaultObserver;
import com.example.battleofminds.interactor.GetUser;
import com.example.battleofminds.internal.di.PerActivity;
import com.example.battleofminds.object.User;
import com.example.battleofminds.view.ProfileView;

import javax.inject.Inject;

@PerActivity
public class ProfilePresenter implements Presenter {

    private ProfileView view;
    private final GetUser getUserUseCase;

    @Inject
    public ProfilePresenter(GetUser getUserUseCase) {
        this.getUserUseCase = getUserUseCase;
    }

    public void setView(final ProfileView view) {
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

        view = null;
    }

    public void trackData() {
        getUserUseCase.execut(new GetUserObserver(), null);

    }

    private final class GetUserObserver extends DefaultObserver<User> {

        @Override
        public void onNext(User user) {

            view.setLogin(user.getLogin());
            view.setAvatar(user.getAvatarUrl());
            view.setScore(user.getScore());
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onComplete() {
        }

        @Override
        protected void onStart() {
        }
    }
}
