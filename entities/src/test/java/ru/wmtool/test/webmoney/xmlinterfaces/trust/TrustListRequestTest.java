package ru.wmtool.test.webmoney.xmlinterfaces.trust;

import junit.framework.Assert;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import ru.wmtool.commons.xml.JAXBUtils;
import ru.wmtool.commons.xml.XpathUtils;
import ru.wmtool.webmoney.xmlinterfaces.trust.TrustListRequest;
import ru.wmtool.webmoney.xmlinterfaces.trust.TrustedWmidRequest;


public class TrustListRequestTest {
	
	@Test
	public void checkMapping() throws Throwable{		
		TrustedWmidRequest trustedWmid = createTrustedWmidRequest();
		TrustListRequest request = createTrustListRequest(trustedWmid);
		
		JAXBUtils jaxb = new JAXBUtils();		
		System.out.println(jaxb.classToXmlString(request));
		
		Document trustListRequestXmlDoc = jaxb.classToXmlDocument(request);
		
		XpathUtils xpathUtils = new XpathUtils();
		Node wmidXmlNode = xpathUtils.getNode(trustListRequestXmlDoc, "//wmid");
		
		Assert.assertEquals(wmidXmlNode.getTextContent(), "123");
	}

	/**
	 * Получить класс запроса для доверия
	 * @param trustedWmid
	 * @return
	 */
	private TrustListRequest createTrustListRequest(
			TrustedWmidRequest trustedWmid) {
		TrustListRequest request = new TrustListRequest(trustedWmid);
		request.setReqN(1);
		request.setWmid("123");
		request.setSign("signed string");
		return request;
	}

	private TrustedWmidRequest createTrustedWmidRequest() {
		TrustedWmidRequest trustedWmid = new TrustedWmidRequest();
		trustedWmid.setWmid("12345");
		return trustedWmid;
	}

}
