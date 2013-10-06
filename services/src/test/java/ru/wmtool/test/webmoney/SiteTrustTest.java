package ru.wmtool.test.webmoney;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ru.wmtool.httpclient.HttpClientImpl;
import ru.wmtool.webmoney.SiteTrust;
import ru.wmtool.webmoney.signer.WmSignerImpl;
import ru.wmtool.webmoney.xmlinterfaces.trust.TrustList;
import ru.wmtool.webmoney.xmlinterfaces.trust.TrustListRequest;
import ru.wmtool.webmoney.xmlinterfaces.trust.TrustedWmidRequest;

/**
 * Тестирование получения реального списка доверия с сайта вебмани
 * 
 * @author null
 * 
 */
public class SiteTrustTest {

	@Test
	public void testGetTrustListFromsite() throws Throwable {
		SiteTrust siteTrusts = new SiteTrust(new HttpClientImpl());

		TrustedWmidRequest trustedWmid = createTrustedWmidRequest();
		TrustListRequest request = createTrustListRequest(trustedWmid);

		TrustList trustListFromSite = siteTrusts.getTrustsFromSite(request);

		System.out.println("Ответ сервера: " + trustListFromSite.toString());
		
		if (trustListFromSite.getRetval() != 0) {
			System.out.println("Ошибка в запросе: " + trustListFromSite.getRetvalErrorDescription());
		}
		
		assertEquals(trustListFromSite.getRetval(), new Integer(0));
		assertEquals(trustListFromSite.getTrusts().getCnt(), new Integer(1));
		assertEquals(trustListFromSite.getTrusts().getTrustList().size(), 1);
	}

	/**
	 * Получить класс запроса для доверия
	 * 
	 * @param trustedWmid
	 * @return
	 */
	private TrustListRequest createTrustListRequest(TrustedWmidRequest trustedWmid) {
		TrustListRequest request = new TrustListRequest(trustedWmid);

		Integer reqN = new Integer(10);
		request.setReqN(reqN);

		String wmidNumber = "294124932358";
		request.setWmid(wmidNumber);

		WmSignerImpl signer = new WmSignerImpl();
		String signStr = signer.sign(wmidNumber + reqN);

		request.setSign(signStr);
		return request;
	}

	private TrustedWmidRequest createTrustedWmidRequest() {
		TrustedWmidRequest trustedWmid = new TrustedWmidRequest();
		String wmidNumber = "294124932358";
		trustedWmid.setWmid(wmidNumber);
		return trustedWmid;
	}
}
