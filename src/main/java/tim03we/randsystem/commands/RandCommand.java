package tim03we.randsystem.commands;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.window.FormWindowSimple;
import com.intellectualcrafters.plot.commands.CommandCategory;
import com.intellectualcrafters.plot.commands.MainCommand;
import com.intellectualcrafters.plot.commands.RequiredType;
import com.intellectualcrafters.plot.commands.SubCommand;
import com.intellectualcrafters.plot.object.PlotPlayer;
import com.plotsquared.general.commands.CommandDeclaration;
import tim03we.randsystem.RandSystem;

@CommandDeclaration(
        command = "rand",
        permission = "plots.rand",
        description = "Change your plot rand",
        usage = "/plot rand",
        category = CommandCategory.SETTINGS,
        requiredType = RequiredType.PLAYER)

public class RandCommand extends SubCommand {

    public RandCommand() {
        MainCommand.getInstance().addCommand(this);
    }

    @Override
    public boolean onCommand(PlotPlayer plotPlayer, String[] strings) {
        String name = plotPlayer.getName();
        Player player = Server.getInstance().getPlayer(name);
        if(player != null) {
            FormWindowSimple gui = new FormWindowSimple(RandSystem.getInstance().getConfig().getString("form.title"), " ");
            for (String rands : RandSystem.rands) {
                String[] ex = rands.split(":");
                gui.addButton(new ElementButton(ex[0]));
            }
            player.showFormWindow(gui);
        }
        return false;
    }
}
