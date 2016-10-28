package com.chenyuan.sentence.mvp.model.entity;

import io.realm.RealmObject;

/**
 * 美图美文
 */
public class SentenceImageText extends RealmObject{


    /**
     * text : 一个内心自卑的人，外在表现一般体现在两个方面，一是对别人的语言行为过分敏感，总觉得别人话中有话矛头指向自己。二是外在行为常常表现为过激反应，为一件小事或一句话大发雷霆，因为内心的虚弱需要用外表的强悍来保护。克服自卑最好的办法是某件事做到极好，赢得别人的赞美，这样胸怀自然就开朗了。 ——@俞敏洪
     * desc : 一个内心自卑的人，外在表现一般体现在两个方面，一是对别人的语言行为过分敏感，总觉得别人话中有话矛头指向自己。二是外在行为常常表现为过激反应，为一件小事或一句话大发雷霆，因为内心的虚弱需要用外表的强悍来保护。克服自卑最好的办法是某件事做到极好，赢得别人的赞美，这样胸怀自然就开朗了。 ——俞敏洪
     * url : http://www.juzimi.com/ju/1045245
     * pic : http://file.juzimi.com/weibopic/jrzpml7.jpg
     */
    private String text;
    private String desc;
    private String url;
    private String pic;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

}
