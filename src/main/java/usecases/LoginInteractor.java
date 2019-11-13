package usecases;

import account.Result;
import authorizer.Authorizer;

public interface LoginInteractor {
    void setAuthorizer(Authorizer authorizer);

    void setUserGateway(UserGateway userGateway);

    void setPresenter(LoginPresenter presenter);

    Result login(LoginRequest request);
}
