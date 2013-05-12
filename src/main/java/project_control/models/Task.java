package project_control.models;

import java.util.Date;
import java.util.Set;

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
	@Persistent(valueStrategy = IdGeneratorStrategy.SEQUENCE)
	private Long key;

	@Persistent
	@Size(min = 3, max = 80)
	private String title;

	@Persistent
	@NotNull
	private Date createdAt;

	@Persistent
	private Date startAt;

	@Persistent
	private Date deadLineAt;

	@Persistent
	private Long parentTask;

	@Persistent
	private String created;

	@Persistent
	private String assigned;

	@Persistent
	private String fixed;

	@Persistent
	private String calendarEventId;
	
	@Persistent
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

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

	public Long getKey() {
		return key;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getAssigned() {
		return assigned;
	}

	public void setAssigned(String assigned) {
		this.assigned = assigned;
	}

	public String getFixed() {
		return fixed;
	}

	public void setFixed(String fixed) {
		this.fixed = fixed;
	}

	public Date getStartAt() {
		return startAt;
	}

	public void setStartAt(Date startAt) {
		this.startAt = startAt;
	}

	public Date getDeadLineAt() {
		return deadLineAt;
	}

	public void setDeadLineAt(Date deadLineAt) {
		this.deadLineAt = deadLineAt;
	}

	public Long getParentTask() {
		return parentTask;
	}

	public void setParentTask(Long parentTask) {
		this.parentTask = parentTask;
	}

	public String getCalendarEventId() {
		return calendarEventId;
	}

	public void setCalendarEventId(String calendarEventId) {
		this.calendarEventId = calendarEventId;
	}

	
}
