package model;

import java.util.List;

/**
 * @author wangxiaohu
 * @version Id: Task.java, v0.1 2020年06月22日 10:23:42 wangxiaohu Exp $
 */
public class Task {
    private String name;
    private List<String> tags;
    public Task(String name, List<String> tags){
        this.name = name;
        this.tags = tags;
    }
    public String toString(){
        return "name:"+name+" ;tags:"+tags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
