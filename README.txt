## Está aplicação necessita do Java 8 ou superior para rodar.

## Está aplicação deve ser rodada via shell.

## Para compilar a aplicação rode: 
$ javac Main.java

## Para rodar a aplicação no modo servidor, rode: 
$ java Main server <porta_desejada>

Exemplo: Para rodar a aplicação no modo servidor na porta 8181:
$ java Main server 8181

## Para rodar a aplicação no modo cliente em localhost, rode: 
$ java Main client <porta_desejada> 

Exemplo: Para rodar a aplicação no modo cliente na porta 8181:
$ java Main client 8181

## Para rodar a aplicação no modo cliente especificando um endereço de IP específico, rode:
$ java Main client <porta_desejada> <ip_desejado>

Exemplo: Para rodar a aplicação no modo cliente no endereço 189.6.237.202/8181: 
$ java Main client 8181 189.6.237.202