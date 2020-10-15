# Porticus
Recode by TabooLib-BungeeSuite

## Example 
```java
public class Test {

    public void run(Player player) {
        PorticusAPI.getAPI().createMission()
                .timeout(10, TimeUnit.SECONDS)
                .command("command 1", "command 2", "command 3")
                .onResponse(r -> {
                    player.sendMessage("Response: " + r[0]);
                })
                .onTimeout(() -> {
                    player.sendMessage("Timeout");
                }).run(player);
    }
}

```