package com.routeoptimizer.ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Dashboard {

    public void show(Stage stage, int userId) {

        Label title = new Label("AI Route & Travel Cost Optimizer");
        title.getStyleClass().add("title");

        Label welcome = new Label("Welcome to Dashboard");

        Button journeyButton = new Button("Journey Input");

        journeyButton.setOnAction(e -> {
            JourneyInput journeyInput = new JourneyInput();
            journeyInput.show(stage, userId);
        });

        Button routeButton = new Button("Route Comparison");

        routeButton.setOnAction(e -> {
            RouteComparison routeComparison = new RouteComparison();
            routeComparison.show(stage, userId);
        });

        Button historyButton = new Button("Travel History");

        historyButton.setOnAction(e -> {
            TravelHistoryScreen historyScreen = new TravelHistoryScreen();
            historyScreen.show(stage, userId);
        });

        Button logoutButton = new Button("Logout");

        logoutButton.setOnAction(e -> {
            LoginScreen loginScreen = new LoginScreen();
            loginScreen.show(stage);
        });

        VBox layout = new VBox(15);
        layout.setPadding(new Insets(30));

        layout.getChildren().addAll(
                title,
                welcome,
                journeyButton,
                routeButton,
                historyButton,
                logoutButton
        );

        Scene scene = new Scene(layout, 500, 400);

        // Connect CSS
        scene.getStylesheets().add(
                getClass().getResource("/style.css").toExternalForm()
        );

        stage.setTitle("AI Route Optimizer - Dashboard");
        stage.setScene(scene);
        stage.show();
    }
}