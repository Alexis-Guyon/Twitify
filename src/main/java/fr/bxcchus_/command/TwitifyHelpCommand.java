package fr.bxcchus_.command;

import fr.bxcchus_.util.Command;
import fr.bxcchus_.util.CommandHandler;

public class TwitifyHelpCommand extends Command {
    private final CommandHandler bot;
    public TwitifyHelpCommand(CommandHandler bot) {
        super("help", "help", "Help Command");
        this.bot = bot;
    }

    @Override
    public void execute() {
        System.out.println("Liste des commandes :");
        for (int i = 0; i < bot.commands.size(); i++) {
            Command command = bot.commands.get(i);
            System.out.println(
                    "\nNom : " + command.identifier +
                    "\nSyntax : " + command.syntax +
                    "\nDescription : " + command.description);
        }
    }
}
