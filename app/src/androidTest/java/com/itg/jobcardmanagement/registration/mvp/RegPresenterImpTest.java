package com.itg.jobcardmanagement.registration.mvp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by me  swapnilmeshram on 09/08/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class RegPresenterImpTest {
    private static String EMPTY="";
    private static String PASS_WITH_LESS_THAN_6="1234";
    private static String PASS_VALID="123456";
    private static String C_PASSWORD_INVALID="123554";
    private static String NAME_INVALID="saf232";

    @Mock
    RegMVP.RegView view;

    @Mock
    RegMVP.RegPresenter presenter;


    @Before
    public void setUp() throws Exception {
        presenter=new RegPresenterImp(view);
    }

    @Test
    public void onFirstNameEmpty() throws Exception {
        when(view.getFName()).thenReturn(EMPTY);
        presenter.onNextButtonClicked();
        verify(view).onFirstnameEmpty();
    }

    @Test
    public void onLastNameEmpty() throws Exception{
        when(view.getLName()).thenReturn(EMPTY);
        presenter.onNextButtonClicked();
        verify(view).onLastnameEmpty();
    }
    @Test
    public void onPasswordEmpty() throws Exception{
        when(view.getPassword()).thenReturn(EMPTY);
        presenter.onNextButtonClicked();
        verify(view).onPasswordEmpty();
    }
    @Test
    public void onPasswordIsLessThanSixCharacter() throws Exception{
        when(view.getPassword()).thenReturn(PASS_WITH_LESS_THAN_6);
        presenter.onNextButtonClicked();
        verify(view).onPasswordConditionFail();
    }

    @Test
    public void onPasswordAndConformPasswordNotMatch() throws Exception{
        when(view.getPassword()).thenReturn(PASS_VALID);
        when(view.getCPassword()).thenReturn(C_PASSWORD_INVALID);
        presenter.onNextButtonClicked();
        verify(view).onPasswordNotMatch();
    }


    @Test
    public void onFirstNameContainsNumber() throws Exception{
        when(view.getFName()).thenReturn(NAME_INVALID);
        presenter.onNextButtonClicked();
        verify(view).onNameContainingInteger(1);
    }


    @Test
    public void onLastNameContainsNumber() throws Exception{
        when(view.getLName()).thenReturn(NAME_INVALID);
        presenter.onNextButtonClicked();
        verify(view).onNameContainingInteger(2);
    }





}