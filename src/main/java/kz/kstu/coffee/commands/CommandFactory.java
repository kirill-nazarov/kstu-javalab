package kz.kstu.coffee.commands;

/**
 *
 * @author Kirill
 */
import javax.servlet.http.HttpServletRequest;
import kz.kstu.coffee.commands.admin.RefillDrinks;
import kz.kstu.coffee.commands.admin.RefillIngredients;
import kz.kstu.coffee.commands.admin.ShowDrinks;
import kz.kstu.coffee.commands.admin.ShowIngredients;
import kz.kstu.coffee.commands.user.*;
import kz.kstu.coffee.constants.Constants;

public class CommandFactory {

    enum CommandEnum {

        LOGIN, LOGOUT,
        DRINKS_CATALOGUE, INGREDIENTS_CATALOGUE,
        CONFIRM_ORDER, ORDER, SHOW_USER_ORDERS,
        SHOW_DRINKS, SHOW_INGREDIENTS,
        REFILL_DRINKS, REFILL_INGREDIENTS;
    }

    public static Command getCommand(HttpServletRequest request) {
        // извлечение команды из запроса
        String action = request.getParameter(Constants.PARAMETER_COMMAND);
        if (action == null || action.isEmpty()) {
            // если команда не задана в текущем запросе
            return new NoCommand();
        }
        // получение объекта, соответствующего команде
        CommandEnum current = CommandEnum.valueOf(action.toUpperCase());
        switch (current) {
            case LOGIN:
                return new LoginCommand();
            case LOGOUT:
                return new LogoutCommand();
            case DRINKS_CATALOGUE:
                return new DrinksCatalogue();
            case INGREDIENTS_CATALOGUE:
                return new IngredientsCatalogue();
            case CONFIRM_ORDER:
                return new ConfirmOrder();
            case ORDER:
                return new OrderCommand();
            case SHOW_USER_ORDERS:
                return new ShowUserOrders();
            case SHOW_DRINKS:
                return new ShowDrinks();
            case SHOW_INGREDIENTS:
                return new ShowIngredients();
            case REFILL_DRINKS:
                return new RefillDrinks();
            case REFILL_INGREDIENTS:
                return new RefillIngredients();
            default:
                throw new EnumConstantNotPresentException(
                        current.getDeclaringClass(), current.name());
        }
    }
}
