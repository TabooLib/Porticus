package ink.ptms.porticus.bungeeside.api;

import ink.ptms.porticus.common.Response;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.plugin.Cancellable;
import net.md_5.bungee.api.plugin.Event;

import java.util.UUID;

/**
 * @author 坏黑
 * @since 2018-04-16
 */
public class PorticusBungeeEvent extends Event implements Cancellable, Response {
	
	private final Server sender;
	private final String[] args;
	private final UUID uid;
	private boolean cancel;

	public static void call(Server sender, UUID uid, String[] args) {
		BungeeCord.getInstance().getPluginManager().callEvent(new PorticusBungeeEvent(sender, uid, args));
	}
	
	PorticusBungeeEvent(Server sender, UUID uid, String[] args) {
		this.sender = sender;
		this.args = args;
		this.uid = uid;
	}

	@Override
	public void response(String... args) {
	}

	@Override
	public boolean isCancelled() {
		return this.cancel;
	}
	
	@Override
	public void setCancelled(boolean cancel) {
		this.cancel = cancel;
	}

	public Server getSender() {
		return sender;
	}

	public String[] getArgs() {
		return this.args;
	}

	public UUID getUID() {
		return this.uid;
	}
}
