package com.routeoptimizer;

import com.routeoptimizer.ui.LoginScreen;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {

        LoginScreen loginScreen = new LoginScreen();
        loginScreen.show(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}