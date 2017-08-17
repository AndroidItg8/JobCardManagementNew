package com.itg.jobcardmanagement;

import com.itg.jobcardmanagement.registration.activity.LoginActivity;
import com.itg.jobcardmanagement.registration.mvp.LoginModuleImp;
import com.itg.jobcardmanagement.registration.mvp.LoginPresenterImp;
import com.itg.jobcardmanagement.registration.mvp.LoginRegMVP;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by itg_Android on 8/5/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterImpTest {
    private static final String USERNAME = "";
    private static final java.lang.String WRONG_MOBILE_NUMBER = "989041066";
    private static final java.lang.String WRONG_EMAIL_ADDRESS = "swap";
    private static final String CORRECT_MOBILE_NUMBER = "9890410668";

    LoginRegMVP.LoginPresenter presenter;

    @Mock
    LoginRegMVP.LoginView view;

    @Mock
    LoginRegMVP.LoginListener listener;

    @Before
    public void setUp() throws Exception {
        presenter=new LoginPresenterImp(view);

    }

    @Test
    public void shouldShowErrorMessageWhenUsernameIsEmpty() throws Exception {
//        when(view.getUsername()).thenReturn("");
        presenter.onUsernameSubmit("");
        verify(view).onUsernameFieldEmpty();
    }

    @Test
    public void shouldShowErrorMessageWhenUsernameIsMobileNumberNotValid() throws Exception {
//        when(view.getUsername()).thenReturn(WRONG_MOBILE_NUMBER);
        presenter.onUsernameSubmit(WRONG_MOBILE_NUMBER);
        verify(view).onMobileInvalid();
    }

    @Test
    public void shouldShowErrorMessageWhenUsernameIsEmailIdNotValid() throws Exception {
//        when(view.getUsername()).thenReturn(USERNAME);
        presenter.onUsernameSubmit(WRONG_EMAIL_ADDRESS);
        verify(view).onEmailInvalid();
    }

    @Test
    public void shouldShowYayOnSucessfulLogin() throws Exception {
//        when(view.getUsername()).thenReturn(USERNAME);
//        presenter.onUsernameSubmit(CORRECT_MOBILE_NUMBER);
        presenter.onUsernameSubmit(CORRECT_MOBILE_NUMBER);
        verify(view).onUserFound("1","www");
    }




}
