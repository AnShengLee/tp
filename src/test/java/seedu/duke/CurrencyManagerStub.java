package seedu.duke;

import seedu.commands.currency.CurrencyType;
import seedu.utility.CurrencyManager;

public class CurrencyManagerStub extends CurrencyManager {
    @Override
    public CurrencyType getCurrency() {
        return CurrencyType.SGD;
    }
}
