package authorizer;

import account.ResultOf;

public interface Authorizer {
    public ResultOf<UserId> authorize(String username, String password);
}
