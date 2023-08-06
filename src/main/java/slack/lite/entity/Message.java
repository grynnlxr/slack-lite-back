package slack.lite.entity;

import java.util.Date;
import java.util.UUID;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@JsonInclude(Include.NON_NULL)
@Table(name = "message", schema = "public")
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(nullable = false)
	private String content;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "author_id", nullable = false, updatable = false)
	private User author;

	@Column(nullable = false, insertable = false, columnDefinition = "timestamp DEFAULT now()")
	private Date date;

	@JsonIgnore
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "thread_id", nullable = false, updatable = false)
	private Thread thread;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}
}