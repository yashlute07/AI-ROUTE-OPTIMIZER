package com.routeoptimizer.ui;

import com.routeoptimizer.database.DBConnection;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginScreen {

    public void show(Stage stage) {

        // Title
        Label title = new Label("AI Route Optimizer - Login");
        title.getStyleClass().add("title");

        // Username
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        // Password
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        // Login button
        Button loginButton = new Button("Login");

        // Message
        Label message = new Label();

        // Login button action
        loginButton.setOnAction(e -> {

            String username = usernameField.getText();
            String password = passwordField.getText();

            String sql =
                    "SELECT * FROM users WHERE username = ? AND password = ?";

            try (var connection = DBConnection.getConnection();
                 var statement = connection.prepareStatement(sql)) {

                statement.setString(1, username);
                statement.setString(2, password);

                var result = statement.executeQuery();

                if (result.next()) {

                    // Get logged-in user's ID
                    int userId = result.getInt("id");

                    // Open Dashboard
                    Dashboard dashboard = new Dashboard();
                    dashboard.show(stage, userId);

                } else {

                    message.setText("Invalid Username or Password");
                }

            } catch (Exception ex) {

                message.setText("Database Error!");
                ex.printStackTrace();
            }
        });

        // Layout
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(30));

        layout.getChildren().addAll(
                title,
                usernameField,
                passwordField,
                loginButton,
                message
        );

        // Scene
        Scene scene = new Scene(layout, 400, 300);

        // Connect CSS
        scene.getStylesheets().add(
                getClass().getResource("/style.css").toExternalForm()
        );

        // Stage
        stage.setTitle("AI Route Optimizer");
        stage.setScene(scene);
        stage.show();
    }
}