package christmas.service;

import christmas.utils.Validations;
import christmas.utils.enums.ErrorMessages;
import christmas.utils.enums.Menu;
import christmas.view.InputView;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class OrderService {
    public Map<String, Integer> processOrderInput() {
        Map<String, Integer> menu = new HashMap<>();
        InputView inputView = new InputView();
        Validations validations = new Validations();
        while(true) {
            try {
                String input = inputView.readMenu();
                validations.hasMenuValidCharacters(input);
                String[] inputs = input.split(",");
                int totalCount = 0;
                boolean isAllbeverage = false;
                String[] beverages = Menu.getBeverageItems();
                for(String str : inputs) {
                    isAllbeverage = false;
                    String[] dishes = str.split("-");
                    String dish = dishes[0];
                    isAllbeverage = Arrays.asList(beverages).contains(dish);
                    if(dish.isEmpty()) {
                        throw new IllegalArgumentException(ErrorMessages.INVALID_ORDER.getMessage()+"1");
                    }
                    if(!Menu.hasName(dish)) {
                        throw new IllegalArgumentException(ErrorMessages.INVALID_ORDER.getMessage()+"2");
                    }
                    if(dishes[1].isEmpty()) {
                        throw new IllegalArgumentException(ErrorMessages.INVALID_ORDER.getMessage()+"3");
                    }
                    int count = Integer.parseInt(dishes[1]);
                    if(count < 1 || 20 < count) {
                        throw new IllegalArgumentException(ErrorMessages.INVALID_ORDER.getMessage()+"4");
                    }
                    totalCount += count;
                    if(totalCount > 20) {
                        throw new IllegalArgumentException(ErrorMessages.INVALID_ORDER.getMessage()+"5");
                    }
                    if(menu.containsKey(dish)) {
                        throw new IllegalArgumentException(ErrorMessages.INVALID_ORDER.getMessage()+"6");
                    }
                    menu.put(dish, menu.getOrDefault(dish, 0) + count);
                }

                if(isAllbeverage) {
                    throw new IllegalArgumentException(ErrorMessages.ONLY_BEVERAGE.getMessage()+"7");
                }
                return menu;
            } catch(IllegalArgumentException e) {
                System.out.println(e);
            }
        }
    }
}
