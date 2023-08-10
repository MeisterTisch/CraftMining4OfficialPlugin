# Official Plugin for SMP-Server Craft Mining
Craft Mining is in its fourth Season now, and it needs a custom plugin with functions requested by its players.
That's why I, MeisterTisch, one of the Admins at Craft Mining, created this plugin. I have some experience with Java and the Spigot API, but not that much, so excuse me if you find some code snippets odd.

# Firstly, what is Craft Mining?
Craft Mining, as already said, is a survival Multiplayer Server. Every Season, up to 25 players come along to play some survival Minecraft together and enjoy the time.
The players can create teams and even go to war against other teams (only if the opponent accepts). But most of the time, it's a very peaceful time here at Craft Mining.

# And now the plugin.
I made this plugin for two reasons.
1. I wanted to train my programming skills and improve them.
2. I did not want to download plugins. It was so much pain in my ass to set up the plugin. The only two downloaded plugins are DiscordSRV-Plugin and Luckperms.

If you want to understand more about the plugin, feel free to continue reading.

## Functions of the Plugin
We have a Discord server where we manage our players, let them talk, and do everything else you do on a Discord server.
There, I created a channel asking for the functions they wished for. Some people actually wrote something, and I liked it. I wrote everything I accepted and wanted to do down and programmed it. (At the time I am writing this, I'm not finished with this plugin. (I just wanted to take a little break and thought I could write a readme file.)

The functions are sorted by how long and how hard they were programmed, beginning with the longest/hardest:
### 1. Intro
I wanted a cool Intro for the start of the Season. Not a big one. Just something to have because it could attract more players.

But what was so complicated about this? Making it work with more than one player. I had stupid problems that were just there because of one word. **S T A T I C**, and I hated me because of this.
I couldn't remember why I made it static, but because of this, it broke itself when there were more than one player on the server.

The long intro is played when I write `/start` in the chat. Everyone who has accepted the rules already would get this intro played. When another player joins the server, having also accepted the rules, the intro will slowly but safely break itself.
The later-joined player gets the intro played, of course later than the other players, and when the intro comes to an end at one of the players, all other players would be stuck in the intro because it ended at some player. Just because of the word **S T A T I C**...

Now to the actual explanation:

There are two intros: the long/first-time intro and the short intro.

The short intro is always played when the player joins the server. It's simple and short. A title saying "welcome back" and that's it!

The long intro is played at the start of the season or when a new player joins the server later after the start.
The intro wants to "hype up" the players a bit, and give them a little warning, that they get an elytra and when they land, it's gone. After the intro, they get shot up, and then in the direction they're looking at. This elytra-shot is also only available once for every player.

### 2. Accepting rules at the beginning
This part was not that hard. Just make them write `/rules` just to get a link and to find out they need to write `/rules accept`, and that's basically it.
They can also write `/rules decline`, if they decline the rules, but they only get kicked with the message that they need to accept it to play at CM.

### 3. Spitting
I found it very funny that you could spit. I saw this in a Minecraft Video once, and I thought, why not add it here? It's just a simple command, done in five minutes.

### 4. Rest
I don't want to write any comments on the rest of the functions I added, so here's just a list of all the other functions I added:
- Playerlist Header and Footer
- `/ping` to check the ping of yourself or other players to the server.
