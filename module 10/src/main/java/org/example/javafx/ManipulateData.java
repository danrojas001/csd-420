// Dan Rojas
// Mod 10.2
// 17-May-26

package org.example.javafx;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.*;

public class ManipulateData {

    public static void main(String[] args) {
    }

    Connection con;

    /*
    makes connection to DB on application start
     */
    public void openConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/databasedb";
            String user = "student1";
            String password = "pass";

            con = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println("Error connection to database.");
            System.exit(0);
        }
    }

    /*
    closes connection when the window close button is clicked
     */
    public void closeConnection() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                System.out.println("Connection closed.");
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Connection close failed");
        }
    }

    /*
    displays the record based on the provided ID
     */
    public void displayRecord(String id, TextField firstName, TextField lastName, TextField favTeam) {
        String sql = "SELECT FIRSTNAME, LASTNAME, FAVORITETEAM FROM fans WHERE id = ?";
        int validId;

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            // validates that ID is provided
            if (id == null || id.isBlank()) {
                showAlert("Invalid ID", "ID is required.");
                return;
            }

            try {
                // validates that ID can be parsed to an int.
                validId = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                showAlert("Invalid ID", "The provided ID is not a number.");
                return;
            }

            stmt.setInt(1, validId);
            ResultSet rs = stmt.executeQuery();

            // setting Textfields to values from ResultSet and validates ID is found in DB.
            if (rs.next()) {
                firstName.setText(rs.getString("FIRSTNAME"));
                lastName.setText(rs.getString("LASTNAME"));
                favTeam.setText(rs.getString("FAVORITETEAM"));
            } else {
                firstName.setText("");
                lastName.setText("");
                favTeam.setText("");

                showAlert("Invalid ID", "The provided ID does not exist in the \"fans\" table.");
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    updates the record pertaining to the provided ID with the passed fName, lName, and favTeam
     */
    public void updateRecord(String id, String fName, String lName, String favTeam) {
        String sql = "UPDATE fans Set FIRSTNAME = ?, LASTNAME = ?, FAVORITETEAM = ? WHERE id = ?";
        int validId;

        try (PreparedStatement stmnt = con.prepareStatement(sql)) {
            // validates that ID is provided
            if (id == null || id.isBlank()) {
                showAlert("Invalid ID", "ID is required");
                return;
            }

            // validates that all other arguments have values
            if (fName == null || fName.isBlank() || lName == null || lName.isBlank() || favTeam == null || favTeam.isBlank()) {
                showAlert(" Input Error", "All fields must be filled.");
                return;
            }

            try {
                // validates that ID can be parsed to an int.
                validId = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                showAlert("Invalid ID", "The provided ID is not a number.");
                return;
            }

            stmnt.setString(1, fName);
            stmnt.setString(2, lName);
            stmnt.setString(3, favTeam);
            stmnt.setInt(4, validId);
            int rowsUpdated = stmnt.executeUpdate();

            // notification of successful or failed update
            if (rowsUpdated > 0) {
                showAlert("Record Updated", "The record has been updated successfully.");
            } else {
                showAlert("Invalid ID", "The provided ID does not exist in the \"fans\" table.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    Utility to display desired messages to the user
     */
    public void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
