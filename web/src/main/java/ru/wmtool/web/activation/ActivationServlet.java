package ru.wmtool.web.activation;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import ru.wmtool.generator.UserService;
import ru.wmtool.security.User;

/**
 * Сервлет, обрабатывающий запросы на активацию. Если успешно, перенаправляет на
 * портал
 */
public class ActivationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ActivationServlet.class);
	private static Map<String, String> errorPathMap = new HashMap<String, String>(2);
	static{
		errorPathMap.put("a", "/view/activation_failed.zul");
		errorPathMap.put("r", "/view/registration_failed.zul");
	}
	private UserService userService;

	@Override
	public void init() throws ServletException {
		super.init();
		ServletContext context = getServletContext();
		WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(context);

		userService = (UserService) applicationContext.getBean("userService");

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		String code = request.getParameter("code");
		String type = request.getParameter("type");
		log.info("Обработка запроса на подтверждение кода " + code + " тип: " + type);
			User user;
			String redirectURL = null;
			try {
				try {
					user = userService.activateUser(code);
					log.debug("Код подтвержден для юзера " + user);
					log.debug("Редирект на login.zul (передача атрибутов user и setpassword)");
					request.setAttribute("setpassword", true);
					request.setAttribute("user", user);
					redirectURL = "/view/login.zul";
				} catch (Exception e) {
					log.error(e);
					if(!errorPathMap.containsKey(type)){
						redirectURL = "/view/defaultError.zul";
					}
					else{
						request.setAttribute("error", e);
						redirectURL = errorPathMap.get(type);
					}
				}
				RequestDispatcher disp = request.getRequestDispatcher(redirectURL);
				disp.forward(request, response);
			} catch (Exception e) {
				log.error("Ошибка при редиректе", e);
			} 
		}
	
}
