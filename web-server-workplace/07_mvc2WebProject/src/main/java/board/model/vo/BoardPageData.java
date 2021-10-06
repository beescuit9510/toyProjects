package board.model.vo;

import java.util.ArrayList;

public class BoardPageData {
	private ArrayList<Board> boards;
	private String pageNavi;
	private int start;

	public ArrayList<Board> getBoards() {
		return boards;
	}

	public void setBoards(ArrayList<Board> boards) {
		this.boards = boards;
	}

	public String getPageNavi() {
		return pageNavi;
	}

	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}


	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public BoardPageData(ArrayList<Board> boards, String pageNavi, int start) {
		super();
		this.boards = boards;
		this.pageNavi = pageNavi;
		this.start = start;
	}

}
