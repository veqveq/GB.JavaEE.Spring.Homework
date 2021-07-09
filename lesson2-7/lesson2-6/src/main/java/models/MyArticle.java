package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyArticle implements Serializable {

    private final List<String> tags;
    private String content;

    public MyArticle(String post) {
        this.content = post;
        this.tags = new ArrayList<>();
        scanPost(post);
    }

    private void scanPost(String post) {
        String[] words = post.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (word.contains("#")) {
                tags.add(word);
            }else{
                sb.append(word).append(" ");
            }
        }
        if (tags.size() == 0) throw new IllegalArgumentException("Tags not found");
        this.content = sb.toString();
    }

    public boolean isFinish(){
        return tags.contains("#end");
    }

    public List<String> getTags() {
        return Collections.unmodifiableList(tags);
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String tag:tags){
            sb.append(tag).append(" ");
        }
        sb.append(content);
        return sb.toString();
    }
}
