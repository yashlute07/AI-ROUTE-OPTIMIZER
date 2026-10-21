package com.routeoptimizer.ui;

import com.routeoptimizer.database.DBConnection;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JourneyInput {

    public void show(Stage stage, int userId) {

        Label title = new Label("Journey Details");
        title.getStyleClass().add("title");

        TextField sourceField = new TextField();
        sourceField.setPromptText("Source");

        TextField destinationField = new TextField();
        destinationField.setPromptText("Destination");

        TextField distanceField = new TextField();
        distanceField.setPromptText("Distance (km)");

        TextField mileageField = new TextField();
        mileageField.setPromptText("Vehicle Mileage (km/l)");

        TextField fuelPriceField = new TextField();
        fuelPriceField.setPromptText("Fuel Price (₹/l)");

        Button calculateButton = new Button("Calculate Cost");

        Label result = new Label();

        calculateButton.setOnAction(e -> {

            try {

                String source = sourceField.getText();
                String destination = destinationField.getText();

                double distance =
                        Double.parseDouble(distanceField.getText());

                double mileage =
                        Double.parseDouble(mileageField.getText());

                double fuelPrice =
                        Double.parseDouble(fuelPriceField.getText());

                double fuelUsed = distance / mileage;
                double fuelCost = fuelUsed * fuelPrice;

                String sql =
                        "INSERT INTO travel_history " +
                        "(user_id, source, destination, route_name, total_cost) " +
                        "VALUES (?, ?, ?, ?, ?)";

                try (var connection = DBConnection.getConnection();
                     var statement = connection.prepareStatement(sql)) {

                    statement.setInt(1, userId);
                    statement.setString(2, source);
                    statement.setString(3, destination);
                    statement.setString(4, "Calculated Journey");
                    statement.setDouble(5, fuelCost);

                    statement.executeUpdate();
                }

                result.setText(
                        "Journey: " + source +
                        " → " + destination +
                        "\nFuel Cost: ₹" +
                        String.format("%.2f", fuelCost) +
                        "\nJourney Saved!"
                );

            } catch (Exception ex) {

                result.setText("Please enter valid details.");
                ex.printStackTrace();
            }
        });

        Button backButton = new Button("Back to Dashboard");

        backButton.setOnAction(e -> {

            Dashboard dashboard = new Dashboard();
            dashboard.show(stage, userId);
        });

        VBox layout = new VBox(12);
        layout.setPadding(new Insets(25));

        layout.getChildren().addAll(
                title,
                sourceField,
                destinationField,
                distanceField,
                mileageField,
                fuelPriceField,
                calculateButton,
                result,
                backButton
        );

        Scene scene = new Scene(layout, 450, 500);

        // Connect CSS
        scene.getStylesheets().add(
                getClass().getResource("/style.css").toExternalForm()
        );

        stage.setTitle("Journey Input");
        stage.setScene(scene);
        stage.show();
    }
}