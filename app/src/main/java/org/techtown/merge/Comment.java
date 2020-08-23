package org.techtown.merge;

public class Comment {
    String nickname;
    String content;

    public Comment(String nickname, String content) {
        this.nickname = nickname;
        this.content = content;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
