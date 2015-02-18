package kz.kstu.coffee.commands.user;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import kz.kstu.coffee.commands.Command;
import kz.kstu.coffee.dao.InvoiceDAO;
import kz.kstu.coffee.entities.Invoice;
import kz.kstu.coffee.entities.User;

/**
 *
 * @author Kirill
 */
public class ShowUserOrders implements Command {

    private static final String USER = "user";
    private static final String INVOICE_LIST = "invoiceList";
    private static final String INVOICE_LIST_PATH = "/jsp/user/invoice_list.jsp";

    public String execute(HttpServletRequest request) {

        String page = null;

        //Получаем данные текущего пользователя
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute(USER);

        //Получаем все счета пользователя из базы
        InvoiceDAO invoiceDAO = new InvoiceDAO();
        List<Invoice> invoiceList = invoiceDAO.findInvoicesByUser(user);

        //Устанавливаем аттрибут для найденных счетов
        request.setAttribute(INVOICE_LIST, invoiceList);
        page = INVOICE_LIST_PATH;

        return page;

    }
}
