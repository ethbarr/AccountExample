package usecases;

import account.User;
import authorizer.UserId;

public interface UserGateway {
    User getUser(UserId userId);
    UserId getRequestedId();
}
