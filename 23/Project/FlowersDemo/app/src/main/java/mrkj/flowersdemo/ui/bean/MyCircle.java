package mrkj.flowersdemo.ui.bean;

/**
 * Created by Administrator on 2016/7/31.
 */
public class MyCircle {

    private float centerX;//坐标X
    private float centerY;//坐标Y
    private float radius;//半径
    private float speed;//下落距离

    public MyCircle setSpeed(float speed) {
        this.speed = speed;
        return this;
    }

    public MyCircle setCenterX(float centerX) {
        this.centerX = centerX;
        return this;
    }

    public MyCircle setCenterY(float centerY) {
        this.centerY = centerY;
        return this;
    }

    public MyCircle setRadius(float radius) {
        this.radius = radius;
        return this;
    }


    public float getCenterX() {
        return centerX;
    }

    public float getCenterY() {
        return centerY;
    }

    public float getRadius() {
        return radius;
    }

    public float getSpeed() {
        return speed;
    }
}
