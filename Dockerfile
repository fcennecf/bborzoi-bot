FROM java:8
MAINTAINER Mike <@tdMuninn>

ENV home /bborzoi
WORKDIR ${home}

COPY ./target/scala-2.12/bborzoi_bot-assembly-0.1.jar ${home}
COPY ./etc ${home}/etc

ENTRYPOINT ["java", "-Dbot.conf=./etc/bot.conf", "-jar", "bborzoi_bot-assembly-0.1.jar"]

CMD ["telegram-bot"]
