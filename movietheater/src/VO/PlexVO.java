package VO;

public class PlexVO {
	private int PlexNo;
	private String plexName;	
	private int row;
	private int column;

	public int getPlexNo() {
		return PlexNo;
	}
	public void setPlexNo(int PlexNo) {
		this.PlexNo = PlexNo;
	}
	public String getName() {
		return plexName;
	}
	public void setName(String plexName) {
		this.plexName = plexName;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
}