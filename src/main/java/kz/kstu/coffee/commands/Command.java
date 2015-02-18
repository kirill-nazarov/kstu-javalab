package kz.kstu.coffee.commands;

/**
 *
 * @author Kirill
 */
import javax.servlet.http.HttpServletRequest;

public interface Command {

    String execute(HttpServletRequest request);
}
