package com.example.tugasbesar_pbp_f.UnitTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class SignUpPresenterTest {
    @Mock
    private SignUpView view;
    @Mock
    private SignUpService service;
    @Mock
    private SignUpPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new SignUpPresenter(view, service);
    }

    @Test
    public void shouldShowErrorMessageWhenNameIsEmpty()
            throws Exception {
        when(view.getName()).thenReturn("");
        System.out.println("name : " + view.getName());
        presenter.onLoginClicked();
        verify(view).showNameError("Please Enter Name");
    }

    @Test
    public void shouldShowErrorMessageWhenEmailIsEmpty()
            throws Exception {

        when(view.getName()).thenReturn("Clara");
        System.out.println("name : " + view.getName());

        when(view.getEmail()).thenReturn("");
        System.out.println("Email : " + view.getEmail());
        presenter.onLoginClicked();
        verify(view).showEmailError("Please Enter Email!");
    }

    @Test
    public void shouldShowErrorMessageWhenPasswordIsEmpty()
            throws Exception {
        when(view.getName()).thenReturn("Clara");
        System.out.println("name : " + view.getName());

        when(view.getEmail()).thenReturn("dillaarista33@gmail.com");
        System.out.println("Email : " + view.getEmail());

        when(view.getPassword()).thenReturn("");
        System.out.println("Password : " + view.getPassword());
        presenter.onLoginClicked();
        verify(view).showPasswordError("Please Enter Password!");
    }

    @Test
    public void shouldShowErrorMessageWhenPasswordLess()
            throws Exception {
        when(view.getName()).thenReturn("Clara");
        System.out.println("name : " + view.getName());

        when(view.getEmail()).thenReturn("dillaarista33@gmail.com");
        System.out.println("Email : " + view.getEmail());

        when(view.getPassword()).thenReturn("123");
        System.out.println("Password : " + view.getPassword());
        presenter.onLoginClicked();
        verify(view).showPasswordError("Password too short!");
    }

    @Test
    public void shouldShowErrorMessageWhenEmailDoesntMatch()
            throws Exception {
        when(view.getName()).thenReturn("Clara");
        System.out.println("name : " + view.getName());

        when(view.getPassword()).thenReturn("clara123");
        System.out.println("Password : " + view.getPassword());

        when(view.getEmail()).thenReturn("dillaarista");
        System.out.println("Email : " + view.getEmail());
        presenter.onLoginClicked();
        verify(view).showEmailInvalid("Email Invalid!");
    }

    @Test
    public void shouldShowErrorMessageWhenPhoneIsEmpty()
            throws Exception {

        when(view.getName()).thenReturn("Clara");
        System.out.println("name : " + view.getName());

        when(view.getPassword()).thenReturn("clara123");
        System.out.println("Password : " + view.getPassword());

        when(view.getEmail()).thenReturn("dillaarista33@gmail.com");
        System.out.println("Email : " + view.getEmail());

        when(view.getPhone()).thenReturn("");
        System.out.println("Phone : " + view.getPhone());
        presenter.onLoginClicked();
        verify(view).showPhoneError("Please Enter Telp");
    }

    @Test
    public void shouldShowErrorMessageWhenPhoneIsLess()
            throws Exception {
        when(view.getName()).thenReturn("Clara");
        System.out.println("name : " + view.getName());

        when(view.getPassword()).thenReturn("123");
        System.out.println("Password : " + view.getPassword());

        when(view.getEmail()).thenReturn("dillaarista33@gmail.com");
        System.out.println("Email : " + view.getEmail());

        when(view.getPhone()).thenReturn("0812465");
        System.out.println("Phone : " + view.getPhone());
        presenter.onLoginClicked();
        verify(view).showPhoneLess("Phone too short!");
    }

    @Test
    public void shouldShowErrorMessageWhenCountryIsEmpty()
            throws Exception {
        when(view.getName()).thenReturn("Clara");
        System.out.println("Name : " + view.getName());

        when(view.getPassword()).thenReturn("clara1234");
        System.out.println("Password : " + view.getPassword());

        when(view.getEmail()).thenReturn("dillaarista33@gmail.com");
        System.out.println("Email : " + view.getEmail());

        when(view.getPhone()).thenReturn("081278367411");
        System.out.println("Phone : " + view.getPhone());

        when(view.getCountry()).thenReturn("");
        System.out.println("Country : " + view.getCountry());
        presenter.onLoginClicked();
        verify(view).showCountryError("Please Enter Country!");
    }
}