// syntax to practice java swing code
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class EmployeeManagementSystem extends JFrame {

    // MySQL connection information
    private static final String URL =
            "jdbc:mysql://localhost:3306/java_company";

    private static final String USER = "root";

    private static final String PASSWORD = "YOUR_PASSWORD";

    // GUI components
    private JTextField nameField;
    private JTextField emailField;
    private JTextField departmentField;
    private JTextField salaryField;

    private JTable employeeTable;
    private DefaultTableModel tableModel;

    public EmployeeManagementSystem() {

        // Window configuration
        setTitle("Java Swing + SQL Employee Management System");
        setSize(950, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main layout
        setLayout(new BorderLayout(10, 10));

        // Create GUI
        createForm();
        createTable();

        // Load database records
        loadEmployees();
    }

    // Create employee input form
    private void createForm() {

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));

        formPanel.setBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        // Name
        formPanel.add(new JLabel("Employee Name:"));

        nameField = new JTextField();
        formPanel.add(nameField);

        // Email
        formPanel.add(new JLabel("Email:"));

        emailField = new JTextField();
        formPanel.add(emailField);

        // Department
        formPanel.add(new JLabel("Department:"));

        departmentField = new JTextField();
        formPanel.add(departmentField);

        // Salary
        formPanel.add(new JLabel("Salary:"));

        salaryField = new JTextField();
        formPanel.add(salaryField);

        // Buttons
        JPanel buttonPanel = new JPanel();

        JButton addButton = new JButton("Add Employee");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");
        JButton refreshButton = new JButton("Refresh");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);

        // Add button
        addButton.addActionListener(e -> addEmployee());

        // Update button
        updateButton.addActionListener(e -> updateEmployee());

        // Delete button
        deleteButton.addActionListener(e -> deleteEmployee());

        // Refresh button
        refreshButton.addActionListener(e -> loadEmployees());

        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }

    // Create database table
    private void createTable() {

        String[] columns = {
                "ID",
                "Name",
                "Email",
                "Department",
                "Salary"
        };

        tableModel = new DefaultTableModel(columns, 0);

        employeeTable = new JTable(tableModel);

        employeeTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scrollPane =
                new JScrollPane(employeeTable);

        add(scrollPane, BorderLayout.SOUTH);

        // When user selects a row
        employeeTable.getSelectionModel()
                .addListSelectionListener(e -> {

                    if (!e.getValueIsAdjusting()) {

                        int row =
                                employeeTable.getSelectedRow();

                        if (row >= 0) {

                            nameField.setText(
                                    tableModel.getValueAt(row, 1).toString()
                            );

                            emailField.setText(
                                    tableModel.getValueAt(row, 2).toString()
                            );

                            departmentField.setText(
                                    tableModel.getValueAt(row, 3).toString()
                            );

                            salaryField.setText(
                                    tableModel.getValueAt(row, 4).toString()
                            );
                        }
                    }
                });
    }

    // Connect to MySQL
    private Connection connect() throws SQLException {

        return DriverManager.getConnection(
                URL,
                USER,
                PASSWORD
        );
    }

    // Add employee
    private void addEmployee() {

        String sql =
                "INSERT INTO employees " +
                "(name, email, department, salary) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection connection = connect();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(
                    1,
                    nameField.getText()
            );

            statement.setString(
                    2,
                    emailField.getText()
            );

            statement.setString(
                    3,
                    departmentField.getText()
            );

            statement.setDouble(
                    4,
                    Double.parseDouble(
                            salaryField.getText()
                    )
            );

            statement.executeUpdate();

            JOptionPane.showMessageDialog(
                    this,
                    "Employee added successfully."
            );

            clearFields();
            loadEmployees();

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Salary must be a number."
            );

        } catch (SQLException ex) {

            showDatabaseError(ex);
        }
    }

    // Update employee
    private void updateEmployee() {

        int row =
                employeeTable.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Select an employee first."
            );

            return;
        }

        int employeeId =
                Integer.parseInt(
                        tableModel.getValueAt(row, 0).toString()
                );

        String sql =
                "UPDATE employees SET " +
                "name = ?, " +
                "email = ?, " +
                "department = ?, " +
                "salary = ? " +
                "WHERE id = ?";

        try (Connection connection = connect();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(
                    1,
                    nameField.getText()
            );

            statement.setString(
                    2,
                    emailField.getText()
            );

            statement.setString(
                    3,
                    departmentField.getText()
            );

            statement.setDouble(
                    4,
                    Double.parseDouble(
                            salaryField.getText()
                    )
            );

            statement.setInt(
                    5,
                    employeeId
            );

            statement.executeUpdate();

            JOptionPane.showMessageDialog(
                    this,
                    "Employee updated successfully."
            );

            clearFields();
            loadEmployees();

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Salary must be a number."
            );

        } catch (SQLException ex) {

            showDatabaseError(ex);
        }
    }

    // Delete employee
    private void deleteEmployee() {

        int row =
                employeeTable.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Select an employee first."
            );

            return;
        }

        int employeeId =
                Integer.parseInt(
                        tableModel.getValueAt(row, 0).toString()
                );

        int confirmation =
                JOptionPane.showConfirmDialog(
                        this,
                        "Delete this employee?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION
                );

        if (confirmation != JOptionPane.YES_OPTION) {
            return;
        }

        String sql =
                "DELETE FROM employees WHERE id = ?";

        try (Connection connection = connect();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(
                    1,
                    employeeId
            );

            statement.executeUpdate();

            JOptionPane.showMessageDialog(
                    this,
                    "Employee deleted successfully."
            );

            clearFields();
            loadEmployees();

        } catch (SQLException ex) {

            showDatabaseError(ex);
        }
    }

    // Load employees from SQL
    private void loadEmployees() {

        tableModel.setRowCount(0);

        String sql =
                "SELECT id, name, email, department, salary " +
                "FROM employees " +
                "ORDER BY id";

        try (Connection connection = connect();
             Statement statement =
                     connection.createStatement();
             ResultSet result =
                     statement.executeQuery(sql)) {

            while (result.next()) {

                Object[] row = {

                        result.getInt("id"),

                        result.getString("name"),

                        result.getString("email"),

                        result.getString("department"),

                        result.getDouble("salary")
                };

                tableModel.addRow(row);
            }

        } catch (SQLException ex) {

            showDatabaseError(ex);
        }
    }

    // Clear input fields
    private void clearFields() {

        nameField.setText("");
        emailField.setText("");
        departmentField.setText("");
        salaryField.setText("");

        employeeTable.clearSelection();
    }

    // Display SQL errors
    private void showDatabaseError(SQLException ex) {

        JOptionPane.showMessageDialog(
                this,
                "Database Error:\n" + ex.getMessage(),
                "SQL Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

    // Start application
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            EmployeeManagementSystem app =
                    new EmployeeManagementSystem();

            app.setVisible(true);
        });
    }
}
