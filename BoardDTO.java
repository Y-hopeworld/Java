package board;

import java.sql.Date;

public class BoardDTO {

	 private   int bno;   
	 private   String title;
	 private   String content;
	 private   String writer;
	 private   int viewcnt;
	 private   Date regdate;
	
	 private BoardDTO() {}

	 
	 
	 
	 
	 public BoardDTO(String title, String content, String writer) {
		super();
		this.title = title;
		this.content = content;
		this.writer = writer;
	}





	public BoardDTO(int bno) {
		super();
		this.bno = bno;
	}





	public BoardDTO(int bno, String title, String content, String writer) {
		 super();
		 this.bno = bno;
		 this.title = title;
		 this.content = content;
		 this.writer = writer;
		 this.regdate = regdate;
	 }

	 public BoardDTO(int bno, String title, String content, String writer, Date regdate) {
			super();
			this.bno = bno;
			this.title = title;
			this.content = content;
			this.writer = writer;
			this.regdate = regdate;
		}
	 
	 public BoardDTO(int bno, String title, String content, String writer, int viewcnt, Date regdate) {
		super();
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.viewcnt = viewcnt;
		this.regdate = regdate;
	}
	
	 public BoardDTO(int viewcnt, int bno, String title, String writer, Date regdate, String content) {
		 super();
		 this.viewcnt = viewcnt;
		 this.bno = bno;
		 this.title = title;
 		 this.writer = writer;
		 this.regdate = regdate;
		 this.content = content;
	 }
	


	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getViewcnt() {
		return viewcnt;
	}

	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}

	

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return bno + "\t"  +  
				title + "\t" + 
				content + "\t" + 
				writer+ "\t" +
				viewcnt + "\t"+ 
				regdate ;
	}
	 
	 
	 
}
