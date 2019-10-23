package moneycommands;

import controlpanel.MoneyStorage;
import money.Account;
import controlpanel.DukeException;
import controlpanel.Ui;
import money.Expenditure;

import java.text.ParseException;

/**
 * This command allows users to check the expenditure
 * for a previous or future month specified by the user input.
 */
public class ViewPastMonthExpenditure extends MoneyCommand {
    private int month;
    private int year;

    /**
     * Constructor of the command which initialises the check expenditure command
     * with the data for the month and year to check as given in the user input.
     * @param command Check command inputted from user
     */
    //@@author chengweixuan
    public ViewPastMonthExpenditure(String command) {
        String inputString = command.replaceFirst("check expenditure ", "");
        String[] splitStr = inputString.split(" ");
        month = Integer.parseInt(splitStr[0]);
        year = Integer.parseInt(splitStr[1]);
    }

    /**
     * This method returns the name of a month given the index of the month from 1-12.
     * @param month Index of the month
     * @return String of the month name
     */
    private String getMonthName(int month) {
        switch (month) {
            case 1: {
                return "January";
            }
            case 2: {
                return "February";
            }
            case 3: {
                return "March";
            }
            case 4: {
                return "April";
            }
            case 5: {
                return "May";
            }
            case 6: {
                return "June";
            }
            case 7: {
                return "July";
            }
            case 8: {
                return "August";
            }
            case 9: {
                return "September";
            }
            case 10: {
                return "October";
            }
            case 11: {
                return "November";
            }
            case 12: {
                return "December";
            }
            default:
                return null;
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * This method executes the check expenditure command. Takes the input from the user
     * and checks the Total Expenditure List for expenditures which occur in the month specified
     * in the user input. Prints the expenditures found and computes the total expenditure for that
     * month and prints to the user.
     * @param account Account object containing all financial info of user saved on the programme
     * @param ui Handles interaction with the user
     * @param storage Saves and loads data into/from the local disk
     * @throws ParseException If invalid date is parsed
     * @throws DukeException When the command is invalid
     */
    @Override
    public void execute(Account account, Ui ui, MoneyStorage storage) throws DukeException, ParseException {
        if (month < 1 || month > 12) {
            throw new DukeException("Month is invalid! Please pick a month from 1-12");
        }

        float totalMonthExpenditure = 0;
        int counter = 1;
        for (Expenditure i : account.getExpListTotal()) {
            if (i.getDateBoughtDate().getMonthValue() == month && i.getDateBoughtDate().getYear() == year) {
                ui.appendToOutput(" " + counter + "." + i.toString() + "\n");
                counter++;
                totalMonthExpenditure += i.getPrice();
            }
        }

        ui.appendToOutput("Total expenditure for " + getMonthName(month) + " of " + year + " : $");
        ui.appendToOutput(totalMonthExpenditure + "\n");

    }

    @Override
    //@@author Chianhaoplanks
    public void undo(Account account, Ui ui, MoneyStorage storage) throws DukeException {
        throw new DukeException("Command can't be undone!\n");
    }
}

