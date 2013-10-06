package ru.wmtool.webmoney.xmlinterfaces.trust;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Класс ответа по просмотру текущих настроек управления "по доверию".
 * Формат ответа:
 * <w3s.response>
 *	<reqn></reqn>
 *	<retval></retval>
 *	<retdesc></retdesc>
 *	<trustlist cnt="n"> 
 *		<trust id="" inv="0/1" trans="0/1" purse="0/1" transhist="0/1">
 *			<master></master>
 *			<purse></purse>
 *			<daylimit></daylimit>
 *			<dlimit></dlimit>
 *			<wlimit></wlimit>
 *			<mlimit></mlimit>
 *			<dsum></dsum>
 *			<wsum></wsum>
 *			<msum></msum>
 * 			<lastsumdate></lastsumdate>
 *		</trust>
 *		<trust>...
 *		</trust>
 *	</trustlist>
 * </w3s.response>
 * 
 * @author null (xrw.null@gmail.com)
 */

@XmlRootElement(name = "trustlist")
public class Trusts {
		
	@XmlAttribute	
	private Integer cnt;
	
	@XmlElements(value = { @XmlElement(name = "trust") })
	private List<Trust> trustList;

	/**
	 * @return количество кошельков в возвращенном списке
	 */
	public Integer getCnt() {
		return cnt;
	}

	/**
	 * @return Список доверий
	 */
	public List<Trust> getTrustList() {		
		return trustList;
	}
	
	

}
