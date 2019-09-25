# Desafio 1 - _String Formatter_

## Executando o projeto

Para facilitar a execução do projeto ele foi todo dockerizado, basta seguir os comandos:

```
$ ./gradlew clean build
$ docker-compose build
$ docker-compose up
```
_Para execução desses comandos tenha docker instalado na sua máquina._

Após subir a App é possível importar para o seu **Postman** a [collection com as requests](IdWall.postman_collection.json), possíbilitando a interação com a API.

## Processo de Resolução
Para resolução deste desafio optei em pode disponibilizar uma Api que receber um Json com o texto :
```json
{
  "text":"$TEXT"
}
```
Este é enviado para rota `/stings/formatter` que recebe como obrigatório o parâmetro `?width=X` para dizer qual o tamanho desejado das linhas do texto. Caso queira receber o texto também Justificado é possível passar jundo o parâmetro `justify=true` na rota(por default ele está como `false`).

Foi utilizada uma interface para implementação de classes abstratas que pudessem realizar a formatação e com Funcion Interface, assim facilitando a criação de novos formatores e a utilização deles.

**OBS**: O uso de API foi apenas para facilitar o uso da app para avaliação!