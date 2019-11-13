package usecases;

import account.Result;
import account.ResultOf;
import authorizer.Authorizer;
import authorizer.UserId;

import static account.Result.Fail;

public class RejectingAuthorizerSpy extends AuthorizerSpy {
    @Override
    protected ResultOf<UserId> makeUser() {
        return Result.Fail(null, "Invalid user id");
    }
}
