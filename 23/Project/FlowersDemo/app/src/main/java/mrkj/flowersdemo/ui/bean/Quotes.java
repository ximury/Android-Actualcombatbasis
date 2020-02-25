package mrkj.flowersdemo.ui.bean;

/**
 * 名言警句
 * Created by Administrator on 2016/8/1.
 */
public class Quotes {

    private String quotes;//名言
    private String author;//作者

    public Quotes setQuotes(String quotes) {
        this.quotes = quotes;
        return this;
    }

    public Quotes setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getQuotes() {
        return quotes;
    }

    public String getAuthor() {
        return author;
    }
}
