package com.routeoptimizer.ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RouteComparison {

    public void show(Stage stage, int userId) {

        Label title = new Label("AI Route Comparison");
        title.getStyleClass().add("title");

        Label route1 = new Label(
                "Route 1\n" +
                "Distance: 65 km\n" +
                "Travel Time: 90 minutes\n" +
                "Toll: ₹50"
        );

        Label route2 = new Label(
                "Route 2\n" +
                "Distance: 72 km\n" +
                "Travel Time: 80 minutes\n" +
                "Toll: ₹100"
        );

        Label preferenceLabel = new Label(
                "What is your preference?"
        );

        ComboBox<String> preferenceBox = new ComboBox<>();

        preferenceBox.getItems().addAll(
                "Cheapest",
                "Fastest",
                "Balanced"
        );

        preferenceBox.setValue("Cheapest");

        Button recommendButton =
                new Button("Get AI Recommendation");

        Label result = new Label();

        recommendButton.setOnAction(e -> {

            double cost1 = (65.0 / 15) * 105 + 50;
            double cost2 = (72.0 / 15) * 105 + 100;

            String preference = preferenceBox.getValue();

            if (preference.equals("Cheapest")) {

                result.setText(
                        "AI Recommendation: Route 1\n" +
                        "Reason: Route 1 has lower total travel cost.\n" +
                        "Total Cost: ₹" +
                        String.format("%.2f", cost1)
                );

            } else if (preference.equals("Fastest")) {

                result.setText(
                        "AI Recommendation: Route 2\n" +
                        "Reason: Route 2 has shorter travel time.\n" +
                        "Travel Time: 80 minutes"
                );

            } else {

                result.setText(
                        "AI Recommendation: Route 2\n" +
                        "Reason: Route 2 provides a better balance " +
                        "between cost and travel time."
                );
            }
        });

        Button backButton =
                new Button("Back to Dashboard");

        backButton.setOnAction(e -> {

            Dashboard dashboard = new Dashboard();
            dashboard.show(stage, userId);
        });

        VBox layout = new VBox(15);
        layout.setPadding(new Insets(25));

        layout.getChildren().addAll(
                title,
                route1,
                route2,
                preferenceLabel,
                preferenceBox,
                recommendButton,
                result,
                backButton
        );

        Scene scene = new Scene(layout, 550, 500);

        // Connect CSS
        scene.getStylesheets().add(
                getClass().getResource("/style.css").toExternalForm()
        );

        stage.setTitle("AI Route Recommendation");
        stage.setScene(scene);
        stage.show();
    }
}