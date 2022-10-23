package client.core;

import client.view.logInView.LogInViewModel;
import client.view.mainView.MainViewModel;
import client.view.signUpView.SignUpViewModel;

public class ViewModelFactory {
    private ModelFactory mf;
    private LogInViewModel loginVM;
    private SignUpViewModel signupVM;
    private MainViewModel  mainVM;

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

    public MainViewModel getMainVM () {
        if (mainVM == null) {
            mainVM = new MainViewModel(mf.getChatModel(),mf.getLoginModel());
        }
        return mainVM;
    }
}
