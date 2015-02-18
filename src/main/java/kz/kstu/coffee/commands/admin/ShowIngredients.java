package kz.kstu.coffee.commands.admin;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import kz.kstu.coffee.commands.Command;
import kz.kstu.coffee.dao.IngredientDAO;
import kz.kstu.coffee.entities.Ingredient;

/**
 *
 * @author Kirill
 */
public class ShowIngredients implements Command {

    private static final String INGREDIENTS = "ingredients";
    private static final String INGREDIENTS_PATH = "/jsp/admin/ingredients.jsp";

    public String execute(HttpServletRequest request) {
        String page = null;

        //Создаем коллекцию для хранения ингредиентов
        List<Ingredient> ingredients = new ArrayList<Ingredient>();

        //Получаем из базы доступные ингредиенты
        ingredients = new IngredientDAO().findAll();

        //Устанавливаем аттрибут для отображения напитков
        HttpSession session = request.getSession(true);
        session.setAttribute(INGREDIENTS, ingredients);

        page = INGREDIENTS_PATH;

        return page;
    }
}
