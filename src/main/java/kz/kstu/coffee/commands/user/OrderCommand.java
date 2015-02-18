package kz.kstu.coffee.commands.user;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import kz.kstu.coffee.commands.Command;
import kz.kstu.coffee.dao.InvoiceDAO;
import kz.kstu.coffee.entities.Drink;
import kz.kstu.coffee.entities.Invoice;
import kz.kstu.coffee.entities.User;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 *
 * @author Kirill
 */
public class OrderCommand implements Command {

    private Logger log = null;
    private static final String ORDERED_DRINKS = "orderedDrinks";
    private static final String USER = "user";
    private static final String ERROR_ORDER_MESSAGE = "errorOrderMessage";
    private static final String INVOICE_ERROR = "Заказ не может быть оформлен. Ошибка при создании счета";
    private static final String CONFIRM_ORDER_PATH = "/jsp/user/confirm_order.jsp";
    private static final String INVOICE = "invoice";
    private static final String INVOICE_PATH = "/jsp/user/invoice.jsp";

    public String execute(HttpServletRequest request) {
        String page = null;
        log = Logger.getRootLogger();
        BasicConfigurator.configure( );


        //Получаем заказанные напитки из аттрибута сессии
        HttpSession session = request.getSession(true);
        List<Drink> orderedDrinks = (List<Drink>) session.getAttribute(ORDERED_DRINKS);

        //Получаем пользователя из сесии
        User user = (User) session.getAttribute(USER);

        //Получаем текущую дату
        java.util.Date today = new java.util.Date();

        //Создаем новый счет
        Invoice invoice = new Invoice();
        invoice.setUser(user);
        invoice.setInvoiceDate(today);
        invoice.setOrderedDrinks(orderedDrinks);


        InvoiceDAO invoiceDAO = new InvoiceDAO();
        //Проверка на ошибку при создании счета
        if (!invoiceDAO.create(invoice, user)) {
            request.setAttribute(ERROR_ORDER_MESSAGE, INVOICE_ERROR);
            return page = CONFIRM_ORDER_PATH;
        }
        log.info("OrderCommand logger. New invoice has been created. User id: " + user.getId());
        session.setAttribute(INVOICE, invoice);

        page = INVOICE_PATH;

        return page;
    }
}
