package com.drama.bot;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Bot extends ListenerAdapter {
    private static final String PREFIX = "!";
    private static final String ROLE_NAME = "Dramáticos";

    private static final List<String> ALLOWED_CHANNELS = Arrays.asList(
            "1316627741784801372", // Canal 1
            "1316627741784801373" // Canal 2
    );

    public static void main(String[] args) throws LoginException {
        String token = "YOUR_TOKEN_HERE"; // Reemplaza con tu token real

        JDABuilder.createDefault(
                token,
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.MESSAGE_CONTENT,
                GatewayIntent.GUILD_MEMBERS)
                .addEventListeners(new Bot())
                .build();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot())
            return;

        if (ALLOWED_CHANNELS.contains(event.getChannel().getId())) {
            return;
        }

        String message = event.getMessage().getContentRaw();

        if (message.equalsIgnoreCase("!rules")) {
            EmbedBuilder rulesEmbed = new EmbedBuilder();
            rulesEmbed.setColor(Color.ORANGE);
            rulesEmbed.setTitle("📜 Reglas del Servidor");
            rulesEmbed.setDescription(
                    "1️⃣ **Respeto:** Trata a todos con respeto.\n" +
                            "2️⃣ **No spam:** Evita enviar mensajes repetitivos.\n" +
                            "3️⃣ **Contenido apropiado:** Nada de NSFW.\n" +
                            "4️⃣ **No publicidad:** No hagas publicidad ni si quiera de terceros sin permiso.\n" +
                            "5️⃣ **Canales correctos:** Usa los canales para su propósito.\n\n" +
                            "Haz clic en el botón para **aceptar las reglas** y obtener el rol **Dramáticos**.");

            event.getChannel().sendMessageEmbeds(rulesEmbed.build())
                    .setActionRow(Button.success("accept_rules", "✅ Acepto las reglas"))
                    .queue();
        }

        if (message.equalsIgnoreCase("!hola")) {
            event.getChannel().sendMessage("Pa tí mi cola Bebé, Bienvenido al drama").queue();
        }

        if (message.equalsIgnoreCase("!info")) {
            Guild guild = event.getGuild();
            if (guild != null) {
                event.getChannel().sendMessage("Este servidor tiene **" + guild.getMemberCount() + "** miembros.")
                        .queue();
            }
        }

        if (message.equalsIgnoreCase("!menu")) {
            event.getChannel().sendMessage("**Menú de Comandos:**\n" +
                    "`!hola` - El bot te saluda\n" +
                    "`!info` - Información del servidor\n" +
                    "`!rules` - Muestra las reglas\n" +
                    "`!menu` - Muestra este menú").queue();
        }
    }

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        if (event.getComponentId().equals("accept_rules")) {
            Guild guild = event.getGuild();
            if (guild == null)
                return;

            List<Role> roles = guild.getRolesByName(ROLE_NAME, true);
            Role dramaticosRole;
            if (roles.isEmpty()) {
                dramaticosRole = guild.createRole()
                        .setName(ROLE_NAME)
                        .setColor(Color.MAGENTA)
                        .setMentionable(true)
                        .complete();
            } else {
                dramaticosRole = roles.get(0);
            }

            guild.addRoleToMember(event.getMember(), dramaticosRole).queue();
            event.reply("🎭 ¡Has aceptado las reglas y ahora eres parte de **Dramáticos**!").setEphemeral(true).queue();
        }
    }
}