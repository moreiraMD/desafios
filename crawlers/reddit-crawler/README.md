# Desafio 2: Crawlers

Como parte do meu futuro trabalho na IDwall inclui desenvolver *crawlers/scrapers* para coletar dados de websites.

## Executando o projeto

```
$ ./gradlew clean build
$ docker-compose build
$ docker run -it reddit_crawler /bin/bash
```

Agora é só começar procurar pelos subreddits:
```
$ java -jar /app.jar ‘cats;dogs’
```

## Processo de Resolução
Para resolução deste desafio, como recomendado, foi utilizado o Jsoup para realizar os parser das páginas do Reddit, capturando as informações necessárias para compor os dados para retorno.

Durante o desenvolvimento encontrei a lib Picocli que tentei durante o desafio implementar pela primeira vez para conhecer e testar. Poderiam ter melhores formar de utilizar a mesma, mas acredito que foi aprendizado e não tempo perdido durante a realização do desafio. 