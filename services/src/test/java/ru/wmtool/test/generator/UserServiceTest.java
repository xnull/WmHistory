package ru.wmtool.test.generator;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.wmtool.commons.utils.CodeUtils;
import ru.wmtool.dao.AuthorityDao;
import ru.wmtool.dao.ContactDao;
import ru.wmtool.dao.EmptyResultException;
import ru.wmtool.dao.NotSingleResultException;
import ru.wmtool.dao.RegistrationConfirmationCodeDao;
import ru.wmtool.dao.UserDao;
import ru.wmtool.dao.WmidDao;
import ru.wmtool.generator.ConfirmCodeAlreadyUsedException;
import ru.wmtool.generator.NoRegistrationConfirmCodeIn24HoursFoundException;
import ru.wmtool.generator.RegistrationConfirmCodeGenerator;
import ru.wmtool.generator.UnableSavePasswordException;
import ru.wmtool.generator.UserDataNotDefinedException;
import ru.wmtool.generator.UserService;
import ru.wmtool.generator.impl.RegistrationConfirmCodeGeneratorImpl;
import ru.wmtool.generator.impl.UserServiceImpl;
import ru.wmtool.interfaces.MesssageSendingFailedException;
import ru.wmtool.security.Authority;
import ru.wmtool.security.Contact;
import ru.wmtool.security.User;
import ru.wmtool.security.registration.RegistrationConfirmCode;
import ru.wmtool.services.RegistrationConfirmCodeSenderService;
import ru.wmtool.test.interfaces.JpaManager;
import ru.wmtool.webmoney.Wmid;
import static org.junit.Assert.*;

@SuppressWarnings("unused")
public class UserServiceTest {
	private static UserService userService;
	private static String testEmail = "testEmail@gmail.com";
	private static String testName = null;
	private static String testUserName;
	private static String testPassword;
	private static Boolean testEnabled = false;
	private static EntityManager em;
	private static UserDao userDao;
	private static AuthorityDao authorityDao;
	private static WmidDao wmidDao;
	private static RegistrationConfirmationCodeDao confirmationCodeDao;
	private User user;
	static {
		try {
			testUserName = testEmail;
			testPassword = CodeUtils.generateSHA256(testEmail);
		} catch (NoSuchAlgorithmException e) {
		}
	}

	@Before
	public void setUpUser() {
		user = new User();
		// user.setName("testUser");
		user.setUsername("username");
		user.setPassword("pass");
		user.setEnabled(true);
		Set<Authority> authorities = new HashSet<Authority>();
		authorities.add(new Authority(user, "ROLE_USER"));
		user.setAuthorities(authorities);
//		RegistrationConfirmCode code = new RegistrationConfirmCode();
//		code.setConfirmCode("blabla");
//		Timestamp now = new Timestamp(0);
//		code.setRegistrationDate(now);
//		user.setRegistrationConfirmCode(code);
		List<Wmid> wmids = new ArrayList<Wmid>();
		Wmid wmid = new Wmid();
		// wmid.setNumber(123);
		wmids.add(wmid);
		user.setWmids(wmids);
		Contact contact = new Contact();
		contact.setEmail("email");
		contact.setPhone("phonenumber");
		user.setContact(contact);
	}

	@BeforeClass
	public static void setUp() {
		ApplicationContext context = new ClassPathXmlApplicationContext("testcontext.xml");
		em = JpaManager.getEmf().createEntityManager();
		userDao = (UserDao) context.getBean("userDao");
		confirmationCodeDao =  (RegistrationConfirmationCodeDao) context.getBean("registrationCodeDao");
		ContactDao contactDao = (ContactDao) context.getBean("contactDao");
		authorityDao = new AuthorityDao();
		contactDao.setEntityManager(em);
		authorityDao.setEntityManager(em);
		userDao.setEntityManager(em);
		confirmationCodeDao.setEntityManager(em);
		wmidDao = new WmidDao();
		wmidDao.setEntityManager(em);
		userDao.setEntityManager(em);
		userService = new UserServiceImpl(userDao, confirmationCodeDao,
				contactDao, authorityDao);
	}

//	@Test
	public void createAndSaveUserTest() {
		User user = userService.createAndSaveUser(testEmail);
		assertEquals(user.getPassword(), testPassword);
		assertNull(user.getName());
		assertNull(user.getRegistrationConfirmCode());
		assertEquals(user.getUsername(), testUserName);
		assertFalse(user.getEnabled());

		assertNull(user.getContact().getPhone());
		assertEquals(user.getContact().getEmail(), testEmail);
		assertNotNull(userDao.find(user.getUsername()));

		Authority authority = new Authority();
		authority.setAuthority("ROLE_USER");
		authority.setUser(user);
		assertNotNull(authorityDao.find(authority));

		authorityDao.remove(authority);
		userDao.remove(user);
	}

//	@Test
	public void saveUserTest() {
		User user2 = userService.saveUser(user);
		assertEquals(user2.getPassword(), user.getPassword());
		// assertEquals(user2.getName(), user.getName());
		assertEquals(user2.getUsername(), user.getUsername());
		assertEquals(user2.getEnabled(), user.getEnabled());
		// assertEquals(user2.getContact().getPhone(),user.getContact().getPhone());
		assertEquals(user2.getContact().getEmail(), user.getContact()
				.getEmail());

		assertNotNull(userDao.find(user.getUsername()));

		assertNotNull(wmidDao.findByUser(user2));

		assertNotNull(authorityDao.findByUser(user2));

		userDao.remove(user2);
	}

	@Test
	public void activateUserTest() throws NotSingleResultException,
			EmptyResultException,
			NoRegistrationConfirmCodeIn24HoursFoundException,
			ConfirmCodeAlreadyUsedException, NoSuchAlgorithmException,
			UserDataNotDefinedException, MesssageSendingFailedException {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		userDao.save(user);
		tx.commit();
		RegistrationConfirmCodeGenerator codeGenerator = new RegistrationConfirmCodeGeneratorImpl();
		RegistrationConfirmCode confirmCode = codeGenerator.generateCode(user);
		String code2 = confirmCode.getConfirmCode();
		RegistrationConfirmCodeSenderService regService = new RegistrationConfirmCodeSenderService();
		regService.send(user);
		assertEquals(user.getRegistrationConfirmCode().getConfirmCode(), code2);
		userService.activateUser(code2);
		assertTrue(confirmationCodeDao.findByRegistrationConfirmCodeAnd24hours(
				code2).getUsed());
		User user2 = userDao.find(user.getUsername());
		userDao.remove(user2);
	}
	
	@Test
	public void savePasswordTest() throws UnableSavePasswordException{
		userDao.save(user);
		userService.savePassword(user, testPassword);
		User user2 = userDao.find(user.getUsername());
		assertEquals(user2.getPassword(), testPassword);
	}

}
