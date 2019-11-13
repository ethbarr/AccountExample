package usecases;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import de.bechte.junit.runners.context.HierarchicalContextRunner;

@RunWith(HierarchicalContextRunner.class)
public class LoginInteractorTest {

    private LoginInteractorImpl interactor;
    private AuthorizerSpy authorizerSpy;
    private UserGateway gatewaySpy;
    private LoginPresenterSpy presenter;

    @Before
    public void setUp() throws Exception {
        interactor = new LoginInteractorImpl();
        presenter = new LoginPresenterSpy();
        interactor.setPresenter(presenter);
    }

    public class ValidLoginTests {
        @Before
        public void setUp() throws Exception {
            authorizerSpy = new AcceptingAuthorizerSpy();
            gatewaySpy = new UserGatewaySpy();
            interactor.setAuthorizer(authorizerSpy);
            interactor.setUserGateway(gatewaySpy);
        }

        @Test
        public void normalLogin() {
            LoginRequest request = new LoginRequest();
            request.username = "username";
            request.password = "password";

            interactor.login(request);

            Assert.assertEquals("username", authorizerSpy.getUsername());
            Assert.assertEquals("password", authorizerSpy.getPassword());

            Assert.assertEquals(AcceptingAuthorizerSpy.STUB_ID, gatewaySpy.getRequestedId());

            LoginResponse response = presenter.getInvokedResponse();
            Assert.assertEquals(UserStub.STUB_NAME, response.name);
            Assert.assertEquals(UserStub.STUB_TIME, response.lastLoginTime);
        }

    }

    public class InvalidLoginTests {
        @Before
        public void setUp() throws Exception {
            authorizerSpy = new RejectingAuthorizerSpy();
            gatewaySpy = new UserGatewaySpy();
            interactor.setAuthorizer(authorizerSpy);
            interactor.setUserGateway(gatewaySpy);
        }

        @Test
        public void invalidLogin() {
            LoginRequest request = new LoginRequest();
            request.username = "username";
            request.password = "password";
            Assert.assertTrue(interactor.login(request).IsFailure());
        }

    }

}
