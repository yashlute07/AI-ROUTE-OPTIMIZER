package com.routeoptimizer.ui;

import com.routeoptimizer.database.DBConnection;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.ResultSet;

public class TravelHistoryScreen {

    public void show(Stage stage, int userId) {

        Label title = new Label("Travel History");
        title.getStyleClass().add("title");

        VBox historyBox = new VBox(10);
        historyBox.setPadding(new Insets(10));

        try {

            String sql =
                    "SELECT source, destination, route_name, total_cost " +
                    "FROM travel_history " +
                    "WHERE user_id = ? " +
                    "ORDER BY id DESC";

            try (var connection = DBConnection.getConnection();
                 var statement = connection.prepareStatement(sql)) {

                statement.setInt(1, userId);

                try (ResultSet resultSet = statement.executeQuery()) {

                    boolean found = false;

                    while (resultSet.next()) {

                        found = true;

                        String source =
                                resultSet.getString("source");

                        String destination =
                                resultSet.getString("destination");

                        String route =
                                resultSet.getString("route_name");

                        double cost =
                                resultSet.getDouble("total_cost");

                        Label journey = new Label(
                                source + " → " + destination +
                                "\nRoute: " + route +
                                "\nTotal Cost: ₹" +
                                String.format("%.2f", cost)
                        );

                        historyBox.getChildren().add(journey);
                    }

                    if (!found) {
                        historyBox.getChildren().add(
                                new Label(
                                        "No travel history available yet."
                                )
                        );
                    }
                }
            }

        } catch (Exception e) {

            historyBox.getChildren().add(
                    new Label("Database Error!")
            );

            e.printStackTrace();
        }

        Button backButton =
                new Button("Back to Dashboard");

        backButton.setOnAction(e -> {

            Dashboard dashboard = new Dashboard();
            dashboard.show(stage, userId);
        });

        VBox layout = new VBox(20);
        layout.setPadding(new Insets(30));

        layout.getChildren().addAll(
                title,
                historyBox,
                backButton
        );

        Scene scene = new Scene(layout, 500, 400);

        // Connect CSS
        scene.getStylesheets().add(
                getClass().getResource("/style.css").toExternalForm()
        );

        stage.setTitle("Travel History");
        stage.setScene(scene);
        stage.show();
    }
}