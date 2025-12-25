package Gmail;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


	public class User implements Serializable {
	    private static final long serialVersionUID = 1L;
	    String name;
	    String email;
	    String password;
	    int attempts = 3;

	    List<Mail> inbox = new ArrayList<>();
	    List<Mail> sent = new ArrayList<>();
	    List<Mail> draft = new ArrayList<>();
	    List<Mail> trash = new ArrayList<>();

	    User(String name, String email, String password) {
	        this.name = name;
	        this.email = email;
	        this.password = password;
	    }

	    int unreadCount() {
	        int c = 0;
	        for (Mail m : inbox)
	            if (!m.read)
	                c++;
	        return c;
	    }
	}

