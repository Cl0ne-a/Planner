import lombok.extern.java.Log;
import planner.MainMenu;
import utils.MessageUtils;

import java.util.Scanner;

import static utils.MessageUtils.WELCOME_MESSAGE;

@Log
public class Main {
    public static void main(String[] args) {
        final MainMenu menu = new MainMenu();
        Scanner sc = new Scanner(System.in);
        System.out.println(WELCOME_MESSAGE);

        while (sc.hasNext()) {
            int action = sc.nextInt();
            if (action!=0) {
                menu.chooseYourAction(action);
            }
            else
            {
                log.info("\nBye!");
                break;
            }
        }
    }
}
