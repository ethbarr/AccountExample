package usecases;

import account.ResultOf;
import authorizer.Authorizer;
import authorizer.UserId;

public abstract class AuthorizerSpy implements Authorizer {
    private String password;
    private String username;

    @Override
    public ResultOf<UserId> authorize(String username, String password) {
        this.username = username;
        this.password = password;
        return makeUser();
    }

    protected abstract ResultOf<UserId> makeUser();

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
