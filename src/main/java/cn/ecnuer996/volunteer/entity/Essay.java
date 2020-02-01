package cn.ecnuer996.volunteer.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * @author 11135
 */
@Document(collection = "essay")
public class Essay {
    @Id
    private String _id;
    private String title;
    private LocalDateTime postTime;
    private LocalDateTime editTime;
    private String content;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getPostTime() {
        return postTime;
    }

    public void setPostTime(LocalDateTime postTime) {
        this.postTime = postTime;
    }

    public LocalDateTime getEditTime() {
        return editTime;
    }

    public void setEditTime(LocalDateTime editTime) {
        this.editTime = editTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
