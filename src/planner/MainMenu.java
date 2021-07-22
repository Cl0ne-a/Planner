package planner;

import lombok.extern.java.Log;
import planner.impl.HomeMenuImpl;

import java.util.Scanner;

import static utils.MessageUtils.*;


public class MainMenu {
    private final HomeMenu menu = new HomeMenuImpl();

    public void chooseYourAction(int action) {
        switch (action) {
            case 1:
                this.menu.addIncome();
                break;
            case 2:
                if(HomeMenuImpl.currentBalance!=0) {
                    deliverMessage.accept("\n" + TYPE_OF_PURCHASE);
                    String chosenType = chooseTypeOfPurchase();
                    while (!chosenType.equals("All")) {
                        chosenType = addingPurchaseByType(chosenType);
                    }
                } else {
                    error.accept(NO_FUNDING);
                }
                break;
            case 3:
                if (HomeMenuImpl.purchaseList.isEmpty()) {
                    deliverMessage.accept("\n" + LIST_EMPTY);
                } else {
                    deliverMessage.accept("\n" + PURCHASES);
                    gettingPurchase(chooseTypeOfPurchase());
                }
                break;
            case 4:
                this.menu.balance();
                break;
        }
        deliverMessage.accept("\n" + WELCOME_MESSAGE);
    }

    // private
    private String addingPurchaseByType(String chosenType) {
        this.menu.addPurchase(chosenType);

        deliverMessage.accept("\n" + TYPE_OF_PURCHASE);
        chosenType = chooseTypeOfPurchase();
        return chosenType;
    }

    private void gettingPurchase(String type) {
        while (!type.equals("Back")) {
            this.menu.showList(type);
            deliverMessage.accept("\n" + PURCHASES);
            type = chooseTypeOfPurchase();
        }
    }

    // protected
    protected static String chooseTypeOfPurchase() {
        Scanner local = new Scanner(System.in);
        String type = local.next();
        type = switch (type) {
            case "1" -> "Food";
            case "2" -> "Clothes";
            case "3" -> "Entertainment";
            case "4" -> "Other";
            case "5" -> "All";
            default -> "Back";
        };
        return type;
    }
}
