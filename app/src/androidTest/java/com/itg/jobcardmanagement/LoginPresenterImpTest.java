package com.itg.jobcardmanagement;

import com.itg.jobcardmanagement.registration.mvp.LoginModuleImp;
import com.itg.jobcardmanagement.registration.mvp.LoginRegMVP;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

/**
 * Created by itg_Android on 8/5/2017.
 */

public class LoginPresenterImpTest {
    private static final String USERNAME = "9890410668";
    @Mock
    LoginRegMVP.LoginPresenter presenter;

    @Mock
    LoginRegMVP.LoginView view;

    @Mock
    LoginRegMVP.LoginListener listener;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
//        when(presenter.onUsernameSubmit(USERNAME)).thenReturn()
    }
}
