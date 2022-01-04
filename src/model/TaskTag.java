package model;

/**
 * @author wangxiaohu
 * @version Id: TaskTag.java, v0.1 2020年06月22日 11:19:24 wangxiaohu Exp $
 */
public class TaskTag {
    private String tag;
    private Task task;

    public TaskTag(String tag, Task task){
        this.tag = tag;
        this.task = task;
    }
    public String toString(){
        return "tag:"+tag+" ;task:"+task;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
