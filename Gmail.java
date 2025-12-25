package Gmail;

import java.io.Serializable;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


public class Gmail implements Serializable{
   
	 Scanner sc = new Scanner(System.in);
	    List<User> users = DataStore.load();

	    void start() {
	        while (true) {
	            System.out.println("\n1.Create New Account \n 2.Login Your Account \n 3.Exit");
	            int option = new Scanner(System.in).nextInt();
	            if (option == 1)
	                createAccount();
	            else if (option == 2)
	                loginAccount();
	            else
	                System.exit(0);
	        }
	    }

	    void createAccount() {
	        System.out.print("Name: ");
	        String name = sc.nextLine();
	        System.out.print("Email: ");
	        String email = sc.nextLine();
	        System.out.print("Password: ");
	        String pass = sc.nextLine();

	        for (User user : users)
	            if (user.email.equals(email)) {
	                System.out.println("Email already exists");
	                return;
	            }

	        users.add(new User(name, email, pass));
	        System.out.println("Account Created");
	    }

	    void loginAccount() {
	        System.out.print("Email: ");
	        String email = sc.nextLine();
	        System.out.print("Password: ");
	        String pass = sc.nextLine();

	        for (User user : users) {
	            if (user.email.equals(email)) {
	                if (user.attempts == 0) {
	                    System.out.println("Account Locked");
	                    return;
	                }
	                if (user.password.equals(pass)) {
	                    user.attempts = 3;
	                    home(user);
	                } else {
	                    user.attempts--;
	                    System.out.println("Attempts left: " + user.attempts);
	                }
	                return;
	            }
	        }
	        System.out.println("User not found");
	    }

	    void home(User user) {
	        while (true) {
	            System.out.println(
	                            "1.Compose\n" +
	                            "2.Inbox (Unread: " + user.unreadCount() + ")\n" +
	                            "3.Sent\n" +
	                            "4.Draft\n" +
	                            "5.Starred\n" +
	                            "6.Trash\n" +
	                            "7.Search\n" +
	                            "8.Logout");

	            int option = new Scanner(System.in).nextInt();
	            switch (option) {
	                case 1 -> compose(user);
	                case 2 -> Inbox(user.inbox, user, "Inbox");
	                case 3 -> Sent("Sent", user.sent);
	                case 4 -> Draft(user);
	                case 5 -> Starred(user);
	                case 6 -> Trash(user);
	                case 7 -> search(user);
	                case 8 -> {
	                   return;
	                }
	                default -> System.out.println("Invalid option");
	            }
	        }
	    }

	    void compose(User user) {
	        System.out.print("To: ");
	        String receive = sc.nextLine();
	        User to = findUser(receive);

	        System.out.print("Subject: ");
	        String sub = sc.nextLine();
	        System.out.print("Body: ");
	        String body = sc.nextLine();

	        Mail mail = new Mail(user.email, receive, sub, body);

	        System.out.print("Label \n Work/Personal/Important  ");
	        mail.label = sc.nextLine();

	        System.out.print("Send? (yes/no): ");
	        if (sc.nextLine().equalsIgnoreCase("yes") && receive != null) {
	            user.sent.add(mail);
	            to.inbox.add(mail);
	        } else {
	            user.draft.add(mail);
	            System.out.println("Saved to Draft");
	        }
	    }

	    void Inbox(List<Mail> list, User user, String name) {
	        if (list.isEmpty()) {
	            System.out.println(name + " empty");
	            return;
	        }

	        for (int i = 0; i < list.size(); i++)
	            list.get(i).displayDetails(i + 1);

	        System.out.print("Open mail: ");
	        int i = new Scanner(System.in).nextInt() - 1;

	        Mail mail = list.get(i);
	        mail.openMail();

	        System.out.println("1.Star/Unstar \n 2.Delete \n 3.Back");
	        int op = new Scanner(System.in).nextInt();

	        if (op == 1)
	            mail.starred = !mail.starred;
	        else if (op == 2) {
	            list.remove(mail);
	            user.trash.add(mail);
	        }
	    }

	    void Draft(User user) {
	        if (user.draft.isEmpty()) {
	            System.out.println("No drafts");
	            return;
	        }

	        for (int i = 0; i < user.draft.size(); i++)
	            user.draft.get(i).displayDetails(i + 1);

	        System.out.print("Select draft: ");
	        int i = new Scanner(System.in).nextInt() - 1;
	        Mail mail = user.draft.get(i);

	        System.out.print("Edit Subject: ");
	        mail.subject = sc.nextLine();
	        System.out.print("Edit Body: ");
	        mail.body = sc.nextLine();

	        System.out.print("Send now? (yes/no): ");
	        if (sc.nextLine().equalsIgnoreCase("yes")) {
	            User receive = findUser(mail.to);
	            if (receive != null) {
	                user.sent.add(mail);
	                receive.inbox.add(mail);
	                user.draft.remove(mail);
	            } else
	                System.out.println("Receiver not found");
	        }
	    }

	    void Starred(User user) {
	        List<Mail> star = new ArrayList<>();
	        for (Mail mail : user.inbox)
	            if (mail.starred)
	                star.add(mail);

	        if (star.isEmpty()) {
	            System.out.println("No starred mails");
	            return;
	        }

	        for (int i = 0; i < star.size(); i++)
	            star.get(i).displayDetails(i + 1);

	        System.out.print("Open mail: ");
	        Mail mail = star.get(new Scanner(System.in).nextInt() - 1);
	        mail.openMail();

	        System.out.println("1.Unstar 2.Delete 3.Back");
	        int ch = new Scanner(System.in).nextInt();
	        if (ch == 1)
	            mail.starred = false;
	        else if (ch == 2) {
	            user.inbox.remove(mail);
	            user.trash.add(mail);
	        }
	    }

	    void Trash(User user) {
	        if (user.trash.isEmpty()) {
	            System.out.println("Trash empty");
	            return;
	        }

	        for (int i = 0; i < user.trash.size(); i++)
	            user.trash.get(i).displayDetails(i + 1);

	        System.out.println("1.Restore  \n 2.Delete Permanently \n 3.Back");
	        int ops = new Scanner(System.in).nextInt();

	        if (ops == 1) {
	            System.out.print("Select mail: ");
	            Mail mail = user.trash.remove(new Scanner(System.in).nextInt() - 1);
	            user.inbox.add(mail);
	        } else if (ops == 2) {
	            System.out.print("Select mail: ");
	            user.trash.remove(new Scanner(System.in).nextInt() - 1);
	        }
	    }

	    void search(User user) {
	        System.out.print("Keyword: ");
	        String keyword = sc.nextLine().toLowerCase();

	        for (Mail mail : user.inbox)
	            if (mail.subject.toLowerCase().contains(keyword)
	                    || mail.from.toLowerCase().contains(keyword)) {
	                mail.displayDetails(1);
	            }
	    }

	    User findUser(String email) {
	        for (User user : users)
	            if (user.email.equals(email))
	                return user;
	        return null;
	    }

	   

	    void Sent(String title, List<Mail> list) {
	        System.out.println("\n--- " + title + " ---");
	        if (list.isEmpty()) {
	            System.out.println("Empty");
	            return;
	        }
	        int i = 1;
	        for (Mail mail : list)
	            mail.displayDetails(i++);
	    }
}
