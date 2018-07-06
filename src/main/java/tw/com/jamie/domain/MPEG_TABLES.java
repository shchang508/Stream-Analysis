package tw.com.jamie.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
//XML??�件中�?�根??��??
@XmlRootElement(name = "MPEG-TABLES")
//?��?��JAXB 绑�?�类中�?��?��?��?�段??��?��??
@XmlType(propOrder = { "pmts", "nit", "eit" })
public class MPEG_TABLES implements Serializable{
	private static final long serialVersionUID = 1L;

	@XmlElement(name = "PMTs")
	private PMTs pmts;
	
	@XmlElement(name = "NIT")
	private NIT nit;

	@XmlElement(name = "EIT")
	private EIT eit;

	public PMTs getPmts() {
		return pmts;
	}

	public void setPmts(PMTs pmts) {
		this.pmts = pmts;
	}
	
	public NIT getNit() {
		return nit;
	}

	public void setNit(NIT nit) {
		this.nit = nit;
	}

	public EIT getEit() {
		return eit;
	}

	public void setEit(EIT eit) {
		this.eit = eit;
	}

}
