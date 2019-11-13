package usecases;

import account.User;
import authorizer.UserId;

public class UserGatewaySpy implements UserGateway {
    private UserId requestedId;

    public User getUser(UserId id) {
        requestedId = id;
        return new UserStub();
    }

    public UserId getRequestedId() {
        return requestedId;
    }
}
