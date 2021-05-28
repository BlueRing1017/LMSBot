package Listener;

import java.util.ArrayList;
import java.util.HashMap;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Listener extends ListenerAdapter {
	
	HashMap<String, String> learnedMap = new HashMap<>();
	ArrayList<String> learnedList = new ArrayList<>();

	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		String[] args = e.getMessage().getContentRaw().split(" ");
		String sender = e.getMember().getUser().getName();

		if (args[0].contains("$")) {
			String cmd = args[0].replace("$", "");
			if (args.length == 1) {
				if (cmd.equalsIgnoreCase("ping")) {
					say(e.getChannel(), "Pong!");
				}else if (cmd.equalsIgnoreCase("help")) {
					String[][] field =
						{
								{"$help", "Zyo 의 명령어 도움말을 열람합니다."},
								{"$ping", "Zyo 가 Pong! 하고 답장해줍니다."},
								{"$learn", "Zyo 를 특정 단어에 답장하도록 학습시킵니다."}
								{"$forget", "Zyo 학습한 대화를 잊게합니다."}
								
						};
					String url = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fenglishcomposition.org%2Fessential-writing%2Fthe-question-mark%2F&psig=AOvVaw26WoG9YLb8Nz56weS5MTrG&ust=1622264165267000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCNiFkI_L6_ACFQAAAAAdAAAAABAE";
					String[] author = {"",url};
					
					MessageEmbed embed = setEmbed(author, 0x6799FF, "Zyo's Command Book :blue_book:", url, "Zyo 의 명령어를 확인 할 수 있습니다.", field, "");
					sayE(e.getChannel(), embed);
				}
			}else {
				if (cmd.equalsIgnoreCase("learn")) {
					if (args.length >= 3) {
						String key = args[1];
						String value = "";
						for (int i = 2; i < args.length; i++) {
							value += args[i];
							value += " ";
						}
						
						learnedList.add(key);
						learnedMap.put(key, value);
						say(e.getChannel(), "```단어 \"" + key + "\" 에 대한 대화를 학습했습니다.```");
					}
				}
				else if (cmd.equalsIgnoreCase("forget")) {
				}
			}
		}else {
			if (!sender.equals("Henry Zyo")) {
				System.out.println("Recieved!");
				boolean pass = false;
				String key = "";
				for(int i = 0; i < args.length; i++) {
					for(int j = 0; j < learnedList.size(); j++) {
						if(args[i].equals(learnedList.get(j))) {					
							key = args[i];
							pass = true;
						}
					}
				}
				if(pass) {
					say(e.getChannel(), learnedMap.get(key));
				}
			}
		}
	}

	void sayE(MessageChannel channel, MessageEmbed messageEmbed) {
		channel.sendMessage(messageEmbed).queue();
	}

	void say(MessageChannel channel, String msg) {
		channel.sendMessage(msg).queue();
	}
	
	private MessageEmbed setEmbed(String[] author, int color, String title, String thumnail, String desc, String[][] field, String foot) {
		EmbedBuilder embed = new EmbedBuilder();
		
		embed.setAuthor(author[0], author[1]);
		embed.setColor(color);
		embed.setTitle(title);
		embed.setThumbnail(thumnail);
		embed.setDescription(desc);
		embed.setFooter(foot);
		
		for(int i = 0; i < field.length; i++) {
			embed.addField(field[i][0], field[i][1], false);
		}
		
		return embed.build();
	}
}

