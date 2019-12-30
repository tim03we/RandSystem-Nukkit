package tim03we.randsystem;

import cn.nukkit.Player;
import cn.nukkit.event.Listener;
import cn.nukkit.plugin.PluginBase;
import com.intellectualcrafters.plot.PS;
import com.intellectualcrafters.plot.config.Configuration;
import com.intellectualcrafters.plot.object.*;
import tim03we.randsystem.commands.RandCommand;
import tim03we.randsystem.events.PlayerFormResponded;

import java.util.ArrayList;

public class RandSystem extends PluginBase implements Listener {

    private static RandSystem instance;
    public static String prefix;
    public static ArrayList<String> rands = new ArrayList<>();

    public static RandSystem getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new PlayerFormResponded(), this);
        new RandCommand();
        prefix = getConfig().getString("prefix");
        rands.addAll(getConfig().getStringList("rands"));
    }

    public static void setRand(Player p, String id, Plot plot) {
        PlotBlock[] pb = Configuration.BLOCKLIST.parseString(id);
        if (plot.getConnectedPlots().size() > 1) {
            for (Plot plots : plot.getConnectedPlots()) {
                PS.get().getPlotManager(new Location(p.getLevel().getName(), p.getFloorX(), p.getFloorY(), p.getFloorZ())).setComponent(plots.getArea(), plots.getId(), "border", pb);
            }
        } else {
            PS.get().getPlotManager(new Location(p.getLevel().getName(), p.getFloorX(), p.getFloorY(), p.getFloorZ())).setComponent(plot.getArea(), plot.getId(), "border", pb);
        }
        plot.setSign();
        p.sendMessage(prefix + getInstance().getConfig().getString("messages.success"));
    }

}
