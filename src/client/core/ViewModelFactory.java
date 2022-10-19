package client.core;

import client.view.logInView.LogInViewModel;
import client.view.signUpView.SignUpViewModel;

public class ViewModelFactory {
    private ModelFactory mf;
    private LogInViewModel loginVM;
    private SignUpViewModel signupVM;

    public ViewModelFactory(ModelFactory mf) {
        this.mf = mf;
    }

    public LogInViewModel getLoginVM() {
        if (loginVM == null) {
            loginVM = new LogInViewModel(mf.getLoginModel());
        }
        return loginVM;
    }

    public SignUpViewModel getSignupVM() {
        if (signupVM == null) {
            signupVM = new SignUpViewModel(mf.getLoginModel());
        }
        return signupVM;
    }
}