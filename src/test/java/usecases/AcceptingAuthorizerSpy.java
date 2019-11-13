package usecases;

import account.ResultOf;
import authorizer.UserId;
import java.util.Optional;

public class AcceptingAuthorizerSpy extends AuthorizerSpy {
    public static final UserId STUB_ID = new UserId(1);

    public ResultOf<UserId> makeUser() {
        return ResultOf.Ok(Optional.of(STUB_ID));
    }
}
