package tw.com.jamie.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "DESCRIPTOR")
@XmlType(propOrder = { "tag", "length", "data" })
public class PMTs_CHANNEL_ELEMENTARY_STREAM_DESCRIPTOR {
	
	@XmlElement(name = "TAG")
	private String tag;
	
	@XmlElement(name = "LENGTH")
	private String length;
	
	@XmlElement(name = "DATA")
	private String data;

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	

}
