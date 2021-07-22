package planner.impl;

import lombok.Data;
import planner.HomeMenu;
import java.util.*;
import java.util.function.BiFunction;
import static utils.MessageUtils.*;

@Data
public class HomeMenuImpl implements HomeMenu {
    public static double currentBalance = 0;

    private final Scanner sc = new Scanner(System.in);
    public final static Map<String, List<String>> purchaseList = new HashMap<>();
    public final static Map<String, Double> expensesList = new HashMap<>();

    private BiFunction<Double, Double, Double> updateExpense = Double::sum;

    @Override
    public void addIncome() {
        deliverMessage.accept(REQUEST_INCOME);
        double addedIncome = sc.nextDouble();
        currentBalance += addedIncome;
        deliverMessage.accept(INCOME_ADDED);
    }

    @Override
    public void addPurchase(String chosenType) {
        var unitPrice = Double.parseDouble(composePurchaseItem(chosenType));
        if (unitPrice > currentBalance) {
            error.accept(NO_FUNDING);
        } else {
            var existing = expensesList.getOrDefault(chosenType, 0d);
            expensesList.put(chosenType, updateExpense.apply(existing, unitPrice));
            currentBalance -= unitPrice;
            deliverMessage.accept(PURCHASE_ADDED);
        }
    }

    @Override
    public void showList(String type) {
        if (purchaseList.containsKey(type)) {
            deliverMessage.accept("\n" + type + ":");
            purchaseList.get(type).forEach(System.out::println);
            deliverMessage.accept("Total sum: $" + String.format("%.2f", expensesList.get(type)));

        } else if (type.equals("All")) {
            deliverMessage.accept("\n" + type + ":");
            purchaseList.forEach((s, strings) -> strings.forEach(System.out::println));
            var a = expensesList.values().stream().reduce(0d, Double::sum);

            deliverMessage.accept("Total sum: $" + String.format("%.2f", a));
        } else {
            error.accept("\n" + type + ":" + "\n" + LIST_EMPTY);
        }
    }

    @Override
    public void balance() {
        deliverMessage.accept("\nBalance: $" + String.format("%.2f", currentBalance));
    }

    private String composePurchaseItem(String chosenType) {
        Scanner local = new Scanner(System.in);
        List<String> itemsList = purchaseList.getOrDefault(chosenType, new ArrayList<>());

        deliverMessage.accept("\nEnter purchase name: ");
        String nameOfPurchase = local.nextLine();
        deliverMessage.accept("Enter its price: ");
        String priceOfPurchase = local.nextLine();

        itemsList.add(nameOfPurchase + " " + "$" + String.format("%.2f", Double.parseDouble(priceOfPurchase)));
        purchaseList.put(chosenType, itemsList);
        return priceOfPurchase;
    }
}
