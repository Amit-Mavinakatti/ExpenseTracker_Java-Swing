import javax.swing.*;
import java.awt.*;
public class ExpenseTrackerGUI extends JFrame {
ExpenseTracker tracker = new ExpenseTracker(); 
JTextArea displayArea;

    ExpenseTrackerGUI() {

        setTitle("Expense Tracker Application");
        setSize(850, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

       
        JPanel headerPanel = new JPanel(new GridLayout(3, 1));
        headerPanel.setBackground(new Color(230, 230, 250));

        JLabel title = new JLabel("EXPENSE TRACKER APPLICATION", JLabel.CENTER);
       title.setForeground(new Color(0, 51, 102)); 

        JLabel team = new JLabel(
                "Developed By: Amit Mavinakatti | Ankush Tambe",
                JLabel.CENTER);
        team.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel desc = new JLabel(
                "Java Swing based application to manage daily expenses",
                JLabel.CENTER);
        desc.setFont(new Font("Arial", Font.ITALIC, 13));

        headerPanel.add(title);
        headerPanel.add(team);
        headerPanel.add(desc);

     
        JPanel buttonPanel = new JPanel(new GridLayout(4, 2, 15, 15));
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Menu Options"));

        JButton createBtn = new JButton("Create Account");
        JButton addBtn = new JButton("Add Expense");
        JButton viewBtn = new JButton("View Expenses");
        JButton balanceBtn = new JButton("Remaining Balance");
        JButton categoryBtn = new JButton("View by Category");
        JButton updateBtn = new JButton("Update Expense");
        JButton deleteBtn = new JButton("Delete Expense");
        JButton resetBtn = new JButton("Reset Expenses");

        buttonPanel.add(createBtn);
        buttonPanel.add(addBtn);
        buttonPanel.add(viewBtn);
        buttonPanel.add(balanceBtn);
        buttonPanel.add(categoryBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(resetBtn);

      
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Consolas", Font.PLAIN, 13));
        displayArea.setBorder(BorderFactory.createTitledBorder("Output"));

        JScrollPane scroll = new JScrollPane(displayArea);

        add(headerPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scroll, BorderLayout.SOUTH);

    
        createBtn.addActionListener(e -> createAccountGUI());
        addBtn.addActionListener(e -> addExpenseGUI());
        viewBtn.addActionListener(e -> viewExpensesGUI());
        balanceBtn.addActionListener(e -> balanceGUI());
        categoryBtn.addActionListener(e -> categoryGUI());
        updateBtn.addActionListener(e -> updateGUI());
        deleteBtn.addActionListener(e -> deleteGUI());
        resetBtn.addActionListener(e -> resetGUI());

        setVisible(true);
    }


    void createAccountGUI() {
        tracker.name = JOptionPane.showInputDialog(this, "Enter Name:");
        tracker.in = Double.parseDouble(
                JOptionPane.showInputDialog(this, "Enter Monthly Income:")
        );
        JOptionPane.showMessageDialog(this, "Account Created Successfully!");
    }

    void addExpenseGUI() {
        String cat = JOptionPane.showInputDialog(this, "Enter Category:");
        String name = JOptionPane.showInputDialog(this, "Enter Expense Name:");
        double amt = Double.parseDouble(
                JOptionPane.showInputDialog(this, "Enter Amount:")
        );
        String desc = JOptionPane.showInputDialog(this, "Enter Description:");

        tracker.expenses.add(new Expense(cat, name, amt, desc));
        JOptionPane.showMessageDialog(this, "Expense Added!");
    }

    void viewExpensesGUI() {
        displayArea.setText("Name\tCategory\tAmount\tDescription\n");
        for (Expense e : tracker.expenses) {
            displayArea.append(
                    e.ename + "\t" + e.cat + "\t" + e.amt + "\t" + e.dsrp + "\n"
            );
        }
    }
void balanceGUI() {
        double total = 0;
        for (Expense e : tracker.expenses)
            total += e.amt;

        JOptionPane.showMessageDialog(this,
                "Remaining Balance: " + (tracker.in - total));
}
void categoryGUI() {
        String search = JOptionPane.showInputDialog(this, "Enter Category:");
        displayArea.setText("");

        for (Expense e : tracker.expenses) {
            if (e.cat.equalsIgnoreCase(search)) {
                displayArea.append(
                        e.ename + "  " + e.amt + "  " + e.dsrp + "\n");
            }
        }
    }

    void updateGUI() {
        String search = JOptionPane.showInputDialog(this, "Enter Expense Name:");
        for (Expense e : tracker.expenses) {
            if (e.ename.equalsIgnoreCase(search)) {
                e.amt = Double.parseDouble(
                        JOptionPane.showInputDialog(this, "New Amount:")
                );
                e.dsrp = JOptionPane.showInputDialog(this, "New Description:");
                JOptionPane.showMessageDialog(this, "Updated!");
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Expense Not Found!");
    }

    void deleteGUI() {
        String search = JOptionPane.showInputDialog(this, "Enter Expense Name:");
        tracker.expenses.removeIf(e -> e.ename.equalsIgnoreCase(search));
        JOptionPane.showMessageDialog(this, "Deleted (if found)");
    }

    void resetGUI() {
        tracker.expenses.clear();
        displayArea.setText("");
        JOptionPane.showMessageDialog(this, "All Expenses Reset!");
    }

    public static void main(String[] args) {
        new ExpenseTrackerGUI();
    }
}
