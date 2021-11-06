package seedu.utility;

import seedu.budget.BillsBudget;
import seedu.budget.Budget;
import seedu.budget.EntertainmentBudget;
import seedu.budget.FoodBudget;
import seedu.budget.MedicalBudget;
import seedu.budget.MiscBudget;
import seedu.budget.OverallBudget;
import seedu.budget.TransportBudget;
import seedu.entry.Expense;
import seedu.entry.ExpenseCategory;

import java.time.LocalDate;
import java.util.ArrayList;

public class BudgetManager {
    private double threshold;
    private OverallBudget overallBudget = new OverallBudget(0);
    private FoodBudget foodBudget = new FoodBudget(0);
    private TransportBudget transportBudget = new TransportBudget(0);
    private MedicalBudget medicalBudget = new MedicalBudget(0);
    private BillsBudget billsBudget = new BillsBudget(0);
    private EntertainmentBudget entertainmentBudget = new EntertainmentBudget(0);
    private MiscBudget miscBudget = new MiscBudget(0);

    public BudgetManager() {
        this.threshold = 0.9;
    }

    public void handleBudget(Expense expense, ArrayList<Expense> expenses, Ui ui) {
        boolean isOverallExceeded = checkOverallBudget(expense, expenses, ui);
        Budget budget = expenseCategoryToBudget(expense.getCategory());
        if (budget != overallBudget) {
            checkBudget(expense, expenses, budget, isOverallExceeded, ui);
        }
    }

    private boolean checkOverallBudget(Expense expense, ArrayList<Expense> expenses, Ui ui) {
        boolean isOverallExceeded = false;
        boolean isOverallBudgetActive = overallBudget.getLimit() != 0;
        if (isOverallBudgetActive) {
            String month = LocalDate.now().getMonth().toString();
            double currAmount = overallBudget.calAmount(expenses);
            assert currAmount >= 0;
            double limit = overallBudget.getLimit();
            assert limit >= 0;
            double diff = limit - currAmount;
            double thresholdLimit = (1 - threshold) * limit;
            boolean isNearingLimit = (diff > 0) & (diff <= thresholdLimit);
            boolean isExceededLimit = diff <= 0;
            if (isNearingLimit) {
                ui.printOverallBudgetWarning(month, currAmount, limit);
            } else if (isExceededLimit) {
                ui.printOverallBudgetExceeded(month, currAmount, limit);
                isOverallExceeded = true;
            }
        }
        return isOverallExceeded;
    }

    private void checkBudget(Expense expense, ArrayList<Expense> expenses, Budget budget, boolean isOverallExceeded,
                             Ui ui) {
        assert budget != overallBudget;
        boolean isBudgetActive = budget.getLimit() != 0;
        if (isBudgetActive) {
            String month = LocalDate.now().getMonth().toString();
            double currOverallAmount = overallBudget.calAmount(expenses);
            double overallLimit = overallBudget.getLimit();
            double currAmount = budget.calAmount(expenses);
            assert currAmount >= 0;
            double limit = budget.getLimit();
            assert limit >= 0;
            double diff = limit - currAmount;
            double thresholdLimit = (1 - threshold) * limit;
            boolean isNearingLimit = (diff > 0) & (diff <= thresholdLimit);
            boolean isExceededLimit = diff <= 0;
            if (isOverallExceeded) {
                if (isNearingLimit) {
                    ui.printOverallExceededBudgetWarning(month, budget.getName(), currAmount, limit,
                            currOverallAmount, overallLimit);
                } else if (isExceededLimit) {
                    ui.printOverallExceededBudgetExceeded(month, budget.getName(), currAmount, limit,
                            currOverallAmount, overallLimit);
                }
            } else {
                if (isNearingLimit) {
                    ui.printOverallNotExceededBudgetWarning(month, budget.getName(), currAmount, limit,
                            currOverallAmount, overallLimit);
                } else if (isExceededLimit) {
                    ui.printOverallNotExceededBudgetExceeded(month, budget.getName(), currAmount, limit,
                            currOverallAmount, overallLimit);
                }
            }
        }
    }

    public void setThreshold(double threshold) {
        assert (threshold >= 0) & (threshold <= 1);
        this.threshold = threshold;
    }

    public double getThreshold() {
        return this.threshold;
    }

    public void setBudget(double amount, ExpenseCategory category) {
        assert amount >= 0;
        assert category != ExpenseCategory.NULL;
        Budget budget = expenseCategoryToBudget(category);
        budget.setLimit(amount);
    }

    public double getBudget(ExpenseCategory category) {
        assert category != ExpenseCategory.NULL;
        Budget budget = expenseCategoryToBudget(category);
        return budget.getLimit();
    }

    public ArrayList<Budget> getBudgets() {
        ArrayList<Budget> budgets = new ArrayList<>();
        budgets.add(overallBudget);
        budgets.add(foodBudget);
        budgets.add(transportBudget);
        budgets.add(medicalBudget);
        budgets.add(billsBudget);
        budgets.add(entertainmentBudget);
        budgets.add(miscBudget);
        return budgets;
    }

    private Budget expenseCategoryToBudget(ExpenseCategory category) {
        assert category != ExpenseCategory.NULL;
        Budget budget;
        switch (category) {
        case FOOD:
            budget = foodBudget;
            break;
        case TRANSPORT:
            budget = transportBudget;
            break;
        case MEDICAL:
            budget = medicalBudget;
            break;
        case BILLS:
            budget = billsBudget;
            break;
        case ENTERTAINMENT:
            budget = entertainmentBudget;
            break;
        case MISC:
            budget = miscBudget;
            break;
        default:
            budget = overallBudget;
            break;
        }
        return budget;
    }
}
