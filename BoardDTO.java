package problem.board;

import java.util.Date;


public class BoardDTO {

	// private : 외부에서 사용할 수 없도록 숨겨두는 기능, 해당 클래스 내에서만 사용 가능
	 private int bno;
	 private String  title;
	 private String content;
	 private String writer;
	 private int viewcnt;
	 private Date regdate;
	
	 //priave 요소 접근 방법
	 // 시스템변수나 사용자가 함부로 접근하면 안되는 요소들을 private 선언한다.
	 // 하지만 private 요소들을 접근해서 사용 되어야 하는 경우에 제한적으로 접근하여 사용할 수 있게 만들어야한다
	 // 1. 생성자로 접근  2. getter setter로 접근
	 
	 //기본생성자
	 public BoardDTO() {}
	 
	 // 1. 생성자를 통해 접근
	 public BoardDTO(int bno, String title, String content, String writer, int viewcnt, Date regdate) {
		super();
		// this.bno는 private변수인 bno를 가리키고 , bno는 생성자로 받아오는 매개변수(parameter)를 받아와서 접근  
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.viewcnt = viewcnt;
		this.regdate = regdate;
		
	}

	//게시물등록 
	public BoardDTO(int bno, String title, String content, String writer) {
		super();
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.writer = writer;
	}

	
	public BoardDTO(int bno, String title, String content, String writer, Date regdate) {
		super();
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.regdate = regdate;
	}
	
	
	//2. getter / setter 접근
	 // 메서드를 통해 매개변수를 받아와서 private에 접근 시키는 방법
	 // 1) 매개변수(paramenter):전달된 인자를 받아들이는 변수(variable)
	 // 2) 인자 (argument) : 함수를 호출 할 대 전달되는 실제 값(value)
	 

	// getter :외부에서 private 값을 볼 수 있게 만드는 메서드 
	public int getBno() {
		return bno;
	}

	// setter : 외부에서 private 변수에 값을 넣어 영향을 미칠 수 있게 만든 메서드
	// 직접적으로 private 변수에 접근하는게 아니라 개발자가 접근 방법을 지정
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

	public String toString() {
		return + bno + "\t" 
				+ title +"\t"
				+ content + "\t"
				+ writer + "\t"
		    	+ viewcnt + "\t"
				+regdate ;
	}
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}
