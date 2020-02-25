package mrkj.flowersdemo.ui.bean;

/**
 * 数据模型
 * @author Administrator
 *
 */
public class DandelionModel {

	private int pointX;//绘制图片的横坐标
	private int pointY;//绘制图片的纵坐标

	private int portOffset;//降落的偏移量
	private int landOffset;//水平的偏移量


	public DandelionModel(int pointX, int pointY, int portOffset, int landOffset) {
		super();
		this.pointX = pointX;
		this.pointY = pointY;
		this.portOffset = portOffset;
		this.landOffset = landOffset;
	}
	public int getPointX() {
		return pointX;
	}
	public void setPointX(int pointX) {
		this.pointX = pointX;
	}
	public int getPointY() {
		return pointY;
	}
	public void setPointY(int pointY) {
		this.pointY = pointY;
	}
	public int getPortOffset() {
		return portOffset;
	}
	public void setPortOffset(int portOffset) {
		this.portOffset = portOffset;
	}
	public int getLandOffset() {
		return landOffset;
	}
	public void setLandOffset(int landOffset) {
		this.landOffset = landOffset;
	}

}
