package Gmail;

import java.time.LocalDateTime;
import java.io.Serializable;


public class Mail implements Serializable {

	
	 private static final long serialVersionUID = 1L;
	    String from, to, subject, body, label = "General";
	    boolean starred = false;
	    boolean read = false;
	    LocalDateTime time = LocalDateTime.now();

	    Mail(String from, String to, String sub, String body) {
	        this.from = from;
	        this.to = to;
	        this.subject = sub;
	        this.body = body;
	    }

	    void displayDetails(int i) {
	        System.out.println(i + "Subject:" + subject +
	                " .... From: " + from +
	                " .... To: " + to +
	                " .... Body: "+ body+
	                " .... Label: " + label);
	 
	    }

	    void openMail() {
	        read = true;
	        System.out.println("From    : " + from  );
	        System.out.println("To      : " + to  );
	        System.out.println("Subject : " + subject);
	        System.out.println("Body    : " + body );
	        System.out.println("Label   : " + label );
	        System.out.println("Starred : " + starred );
	        System.out.println("Time    : " + time );
	                
	    }
}
