package Main;

import javax.security.auth.login.LoginException;

import Listener.Listener;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

public class Main {

	public static void main(String[] args) {
		JDABuilder builder = new JDABuilder(AccountType.BOT);
		String token = "<<TOKEN>>"; 
		builder.setAutoReconnect(true);
		builder.setToken(token);
		builder.addEventListeners(new Listener());
		Activity at = Activity.listening("$help");
		System.out.println("BOT is Ready!");
		builder.setActivity(at);
		try {
			builder.build();
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
