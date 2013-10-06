package ru.wmtool.test.httpclient;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ru.wmtool.httpclient.HttpClientImpl;

/**
 * Тестирование https клиента
 * 
 * @author nazgul
 */
public class MyHttpsClientImplTest {
	private final Logger log = LogManager.getLogger(this.getClass().getName());
	
    private String filename = "WMUsedRootCAs.cer";
    private String https_url = "https://w3s.webmoney.ru/asp/XMLOperations.asp";

    @Before
    public void setUp() throws MalformedURLException, IOException {
    	BasicConfigurator.configure();		
    }
    
    /**
     * Инициализация сертификата X.509
     * @param filename имя файла.
     * @return сертификат X.509
     */
    private X509Certificate instantiateX509(String filename) throws CertificateException{
        FileInputStream inStream = null;
        X509Certificate cert = null;
        try {
            inStream = new FileInputStream(filename);
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            cert = (X509Certificate) cf.generateCertificate(inStream);
            inStream.close();
        } catch (IOException ex) {
            System.out.println("Файл не найден или еще какая-то херня.");
        } catch (CertificateException ex) {
            System.out.println("Что-то не так с сертификатом.");
        }
        return cert;
    }

    @Test
    @Ignore
    public void testInstantiateX509() throws CertificateException{
        X509Certificate cert = instantiateX509(filename);
        System.out.println(cert.getSerialNumber());
        System.out.println(cert.getVersion());
        System.out.println(cert.getPublicKey().toString());
        System.out.println(cert.getNotBefore());
        System.out.println(cert.getNotAfter());
    }



	
	@Test
	public void sslposttest() throws MalformedURLException, IOException{
		HttpClientImpl https = new HttpClientImpl();
		String result = https.postHttps(https_url, "qwe").getContent();
		log.info(String.format("Результат http запроса: %s", result));
	}
}