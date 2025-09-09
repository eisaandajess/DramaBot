# Discord Bot Java

Este proyecto es un bot de Discord desarrollado en Java utilizando la biblioteca JDA (Java Discord API). El bot está diseñado para interactuar con los usuarios en un servidor de Discord, proporcionando comandos y gestionando roles.

## Estructura del Proyecto

```
discord-bot-java
├── src
│   └── main
│       └── java
│           └── com
│               └── drama
│                   └── bot
│                       └── Bot.java
├── pom.xml
└── README.md
```

### Archivos Principales

- **`src/main/java/com/drama/bot/Bot.java`**: Contiene la clase `Bot`, que extiende `ListenerAdapter` de la biblioteca JDA. Maneja eventos de mensajes y botones en Discord, incluyendo métodos para responder a mensajes y manejar interacciones con botones. Define constantes como `PREFIX` y `ROLE_NAME`, así como una lista de canales permitidos.

- **`pom.xml`**: Archivo de configuración para Maven. Define las dependencias del proyecto, la versión de Java y otros parámetros necesarios para compilar y ejecutar el bot de Discord.

## Requisitos

- Java 11 o superior
- Maven

## Instalación

1. Clona el repositorio en tu máquina local:
   ```
   git clone <URL_DEL_REPOSITORIO>
   ```

2. Navega al directorio del proyecto:
   ```
   cd discord-bot-java
   ```

3. Compila el proyecto utilizando Maven:
   ```
   mvn clean install
   ```

## Ejecución

Para ejecutar el bot, asegúrate de tener un token de bot de Discord y reemplaza el token en el archivo `Bot.java`. Luego, ejecuta el siguiente comando:

```
mvn exec:java -Dexec.mainClass="com.drama.bot.Bot"
```

## Contribuciones

Las contribuciones son bienvenidas. Si deseas contribuir, por favor abre un issue o envía un pull request.

## Licencia

Este proyecto está bajo la Licencia MIT.