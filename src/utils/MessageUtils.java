package utils;

import java.util.function.Consumer;

public class MessageUtils {
    public static final String WELCOME_MESSAGE =
            "Choose your action:\n" +
                    "1) Add income\n" +
                    "2) Add purchase\n" +
                    "3) Show list of purchases\n" +
                    "4) Balance\n" +
                    "0) Exit";
    public static final String TYPE_OF_PURCHASE =
            "Choose the type of purchase\n" +
                    "1) Food\n" +
                    "2) Clothes\n" +
                    "3) Entertainment\n" +
                    "4) Other\n" +
                    "5) Back";

    public static final String PURCHASES =
            "Choose the type of purchases\n" +
                    "1) Food\n" +
                    "2) Clothes\n" +
                    "3) Entertainment\n" +
                    "4) Other\n" +
                    "5) All\n" +
                    "6) Back";

    public static final String PURCHASE_ADDED = "Purchase was added!";
    public static final String LIST_EMPTY = "The purchase list is empty!";
    public static final String INCOME_ADDED = "Income was added!";
    public static final String REQUEST_INCOME = "\nEnter income:";
    public static final String NO_FUNDING = "Insufficient balance";

    public static Consumer<String> deliverMessage = System.out::println;
    public static Consumer<String> error = System.err::println;
}
