package project_control.models;

import java.util.Date;
import java.util.Set;

import com.google.appengine.api.datastore.Key;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Element;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@PersistenceCapable
public class Task {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
	
	@Persistent
	@Size(min = 3, max = 80)
	private String title;
	
	@Persistent
	@NotNull
	private Date createdAt;
	
	@Persistent(mappedBy = "parentTask")
	@Element(dependent = "true")
	private Set<Task> subtasks;
	
	@Persistent
	private Task parentTask;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Set<Task> getSubtasks() {
		return subtasks;
	}

	public void setSubtasks(Set<Task> subtasks) {
		this.subtasks = subtasks;
	}
	
	public void addSubtask(Task task){
		subtasks.add(task);
	}
	
	public void removeSubtask(Task task){
		subtasks.remove(task);
	}

	public Task getParentTask() {
		return parentTask;
	}

	public void setParentTask(Task parentTask) {
		this.parentTask = parentTask;
	}

	public Key getKey() {
		return key;
	}
	
	
}
