package usecases;

import account.Result;
import account.ResultOf;
import account.User;
import authorizer.Authorizer;
import authorizer.UserId;

public class LoginInteractorImpl implements LoginInteractor {
    private Authorizer authorizer;
    private UserGateway userGateway;
    private LoginPresenter presenter;

    @Override
    public void setAuthorizer(Authorizer authorizer) {
        this.authorizer = authorizer;
    }

    @Override
    public void setUserGateway(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public void setPresenter(LoginPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public Result login(LoginRequest request) {
        ResultOf<UserId> userId = authorizer.authorize(request.username, request.password);
        if (userId.IsFailure())
            return Result.Fail(userId.Error);

        User user = userGateway.getUser(userId.Value().get());
        LoginResponse response = new LoginResponse();
        response.name = user.getName();
        response.lastLoginTime = user.getLastLoginTime();
        response.loginCount = user.getLoginCount();
        presenter.presentResponse(response);

        return Result.Ok();
    }
}
