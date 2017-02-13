
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Comment extends DomainEntity {

	// Attributes
	private String	title;
	private Date	momentPosted;
	private String	text;
	private int		stars;


	@NotBlank
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	public Date getMomentPosted() {
		return momentPosted;
	}

	public void setMomentPosted(Date momentPosted) {
		this.momentPosted = momentPosted;
	}

	@NotBlank
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Range(min = 0, max = 5)
	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}


	// Relationships
	private ConsumerActor	commented;
	private ConsumerActor	commentator;


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public ConsumerActor getCommented() {
		return commented;
	}

	public void setCommented(ConsumerActor commented) {
		this.commented = commented;
	}

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public ConsumerActor getCommentator() {
		return commentator;
	}

	public void setCommentator(ConsumerActor commentator) {
		this.commentator = commentator;
	}

}
