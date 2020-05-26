# Socket Chat

Socket chat is a  simple chat command line application that uses Java socket library.

## Prerequisites

You need Java 8 or later to build and run this program.

## Usage

**Building the Project:** In the terminal, navigate to the project root directory and enter the following command waiting until the project successfully has been builded.

```
javac Main.java
```

**Usage:** Once you successfully build the project you are gonna be able to run the program in `client` or `server` mode.

To run it as a `server` type the following command on the terminal:

```
java Main server <desired_port>
```

To run it in locally in `client` mode, type the following command on the termianl:

```
java Main client <desired_port>
```


To run it in `client` mode, specifing the server ip address you want to look for, type the following command on the termianl:

```
java Main client <desired_port> <server_ip_address>
```
