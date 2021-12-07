# README.md

## Description
Le but de ce projet est d'écrire un robot configurable qui envoie des plaisanteries par mail. L'utilisateur va compiler une liste de victime et une liste de paramètres pour sa campagne de spam. Ce programme est destiné à la formation uniquement dans le but de démontrer la simplicité de forge de mail. Nous déclinons toutes responsabilités pour un autre usage de ce programme. Avec de grands pouvoirs, vient de grandes responsabilités.

## Getting Started
Afin de tester une campagne de pourriel dans environnement de test il faudra :
1. Paramétrer et lancer un mailtrap qui fait aussi serveur smtp
2. Compiler une liste de victime et de paramètres respectant le format requis
3. Compiler et lancer l'application

### Setting up Mock SMTP server
Dans cette section, nous expliquons comment implémenter le mailtrap MockMock à l'aide de docker. Nous allons utiliser le [fork](https://github.com/HEIGVD-Course-API/MockMock) MockMock de HEIGVD-Course-API. Voici les étapes à effectuer :
* Commencer par télécharger le code de MockMock
* Compilez vous même le code ou exécutez le code déjà compilé dans release. (Généralement, il faudrait être sûr que le fichier compilé est bien celui que l'on souhaite pour des raisons de sécurité)
* Normalement, le serveur devrait écouter sur le port 25 et être disponible sur localhost:8282 avec la commande `java -jar MockMock.jar -p 25 -h 8282
  `
* Nous allons maintenant rendre ce service disponible dans un container docker. Premièrement placez-vous dans le même dossier que l'exécutable du mailtrap.
* Rédiger le dockerfile de la manière suivante:
```
FROM openjdk:11
COPY . .
WORKDIR .
CMD ["java", "-jar", "MockMock.jar"]
  ```
* Compilez l'image docker à l'aide de `docker build`
### Configuration
* **Clear and simple instructions for configuring your tool and running a prank campaign**. If you do a good job, an external user should be able to clone your repo, edit a couple of files and send a batch of e-mails in less than 10 minutes.
### Compilation

## Project Structure
* **A description of your implementation**: document the key aspects of your code. It is probably a good idea to start with a class diagram. Decide which classes you want to show (focus on the important ones) and describe their responsibilities in text. It is also certainly a good idea to include examples of dialogues between your client and an SMTP server (maybe you also want to include some screenshots here).

### ConfigurationService
This service will parse the configuration file and link each config param with his value into a set.
### Group

### GroupManager
### MailSender
### MailService
### PrankFactory
### Victim
## References

* [Here is our fork of MockMock server](https://github.com/HEIGVD-Course-API/MockMock), in which we resolved an issues with a dependency (see this [pull request](https://github.com/tweakers/MockMock/pull/8) if you want to have more information).
* The [mailtrap](<https://mailtrap.io/>) online service for testing SMTP
* The [SMTP RFC](<https://tools.ietf.org/html/rfc5321#appendix-D>), and in particular the [example scenario](<https://tools.ietf.org/html/rfc5321#appendix-D>)
* Testing SMTP with TLS: `openssl s_client -connect smtp.mailtrap.io:2525 -starttls smtp -crlf`
