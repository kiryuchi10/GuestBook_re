package himedia.vo;

import java.util.Date;
import java.util.Objects;

public class BookListVo {
	private long no;
	private String name;
	private String password;
	private String content;
	private Date reg_date;
	
	
	public BookListVo(long no, String name, String password, String content, Date reg_date) {
		super();
		this.no = no;
		this.name = name;
		this.password = password;
		this.content = content;
		this.reg_date = reg_date;
	}
	public BookListVo(String name,String password,
			String content) {
		this.name=name;
		this.password=password;
		this.content=content;
	}
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		if (password == null)
			return "";
		return password;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	@Override
	public String toString() {
		return "BookListVo [no=" + no + ", name=" + name + ", password=" + password + ", content=" + content
				+ ", reg_date=" + reg_date + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash( no);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookListVo other = (BookListVo) obj;
		return Objects.equals(no, other.no);
	}

}
