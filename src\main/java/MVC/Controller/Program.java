package MVC.Controller;

import java.sql.*;



public class Program {
    private String url = "jdbc:sqlite:machine_database.db";

    public void delete_SQLite(String number) {
        Connection connection = null;
        Statement statement = null;
        try {
            // Подключение к базе данных
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            String insertSql = "DELETE FROM car WHERE number = '" + number + "'";
            statement.executeUpdate(insertSql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void change_SQLite(String number, String stamp, int release_year, int mileage) {
        Connection connection = null;
        Statement statement = null;
        try {
            // Подключение к базе данных
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            String insertSql = "INSERT INTO car (number , stamp , release_year , mileage)"
                    + " VALUES ('" + number + "', '" + stamp + "', " + release_year + ", " + mileage
                    + " )";
            statement.executeUpdate(insertSql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void add_SQLite(String number, String stamp, int release_year, int mileage) {
        Connection connection = null;
        Statement statement = null;
        //number =8
        try {
            // Подключение к базе данных
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            String selectSql_i = "SELECT * FROM car WHERE number = "+number;
            ResultSet resultSet = statement.executeQuery(selectSql_i);
            // Добавление данных в таблицу
            if (resultSet == null) {
                String insertSql = "INSERT INTO car (number , stamp , release_year , mileage)"
                        + " VALUES ('" + number + "', '" + stamp + "', " + release_year + ", " + mileage
                        + " )";
                statement.executeUpdate(insertSql);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public String[] information_SQLite() {
        Connection connection = null;
        Statement statement = null;
        try {
            // Подключение к базе данных
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            // Извлечение данных из таблицы
            String selectSql = "SELECT * FROM car";
            ResultSet resultSet = statement.executeQuery(selectSql);
            String mas_string = "";
            while (resultSet.next()) {
                String number = resultSet.getString("number");
                String stamp = resultSet.getString("stamp");
                int release_year = resultSet.getInt("release_year");
                int mileage = resultSet.getInt("mileage");
                mas_string += "number: " + number + ", stamp: " + stamp
                        + ", release year: " + release_year + ", mileage: " + mileage+"@##";
            }
            return mas_string.split("@##");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return new String[]{};
    }

    public String[] information_sorting_SQLite(int number){
        String[] mas = information_SQLite();
        boolean y_n =true;
        int qwe = 0;
        while (y_n){
            y_n = false;

            for (String s : mas){

                if (qwe == mas.length){qwe=0;}

                if (mas[qwe].split(", ")[number].equalsIgnoreCase(mas[qwe+1].split(", ")[number])) {
                    String mas_1 = mas[qwe];
                    mas[qwe] = mas[qwe + 1];
                    mas[qwe + 1] = mas_1;
                    y_n = true;
                }
            }
        }
        return mas;
    }

    public void create_SQLite() {
        Connection connection = null;
        Statement statement = null;
        try {
            // Подключение к базе данных
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            // Создание таблицы "car"
            String createTableSql = "CREATE TABLE IF NOT EXISTS car ( "
                    + " number TEXT NOT NULL , "
                    + " stamp TEXT NOT NULL, " + " release_year INTEGER, "
                    + " mileage INTEGER "
                    + ");";
            statement.execute(createTableSql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}