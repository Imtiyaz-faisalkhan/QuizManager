
package fr.epita.quiz.web.services.messages;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Faisal
 *
 *  
 */
@XmlRootElement
public class QuestionMessage {

	private String title;

	
	public String getTitle() {
		return title;
	}

	
	public void setTitle(String title) {
		this.title = title;
	}

}
