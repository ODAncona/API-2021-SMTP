# README.md

## Description
Le but de ce projet est de programmer un robot configurable qui envoie des plaisanteries par mail. L'utilisateur va compiler une liste de victime et une liste de paramètres pour sa campagne de spam. Ce programme est destiné à la formation uniquement dans le but de démontrer la simplicité de la forge de mail. Nous déclinons toutes responsabilités pour un autre usage de ce programme. Avec de grands pouvoirs, viennent de grandes responsabilités.

## Getting Started
Afin de tester une campagne de pourriel dans environnement de test il faudra :
1. Paramétrer et lancer un mailtrap qui fait aussi serveur smtp
2. Compiler une liste de victime et de paramètres respectant le format requis
3. Compiler et lancer l'application

### Setting up Mock SMTP server
Dans cette section, nous expliquons comment implémenter le mailtrap MockMock à l'aide de docker. Nous allons utiliser le [fork](https://github.com/HEIGVD-Course-API/MockMock) MockMock de HEIGVD-Course-API. Voici les étapes à effectuer :
* Commencer par télécharger le code de MockMock
* Compilere le code ou exécuter le code déjà compilé dans release. (Généralement, il faudrait être sûr que le fichier compilé est bien celui que l'on souhaite pour des raisons de sécurité)
* Normalement, le serveur devrait écouter sur le port 25 et être disponible sur localhost:8282 avec la commande `java -jar MockMock.jar -p 25 -h 8282
  `
* Nous allons maintenant rendre ce service disponible dans un container docker. Premièrement il faut se placer dans le même dossier que l'exécutable du mailtrap.
* Rédiger le dockerfile de la manière suivante:
```
FROM openjdk:11
COPY . .
WORKDIR .
CMD ["java", "-jar", "MockMock.jar"]
  ```
* Compiler l'image docker à l'aide de `docker build -t mailtrap .`
* Instancier un container et liez les ports à l'aide de `docker run -p 8282:8282 -p 25:25 mailtrap`
### Setting up configuration file
Afin de pouvoir utiliser le programme, il faut configuration une campagne avec trois fichiers :
- Configuration.txt
- Victims.txt
- jokes.txt

#### Configuration
Le fichier de configuration doit contenir:
```
smtp_server <adresse du serveur>
smtp_port <25 par défaut>
nb_groups <nombre de groupe de la campagne de spam
```
#### Victims
Le fichier de victim doit être structuré:
```
<email de la victime 1> <nom de la victime 1>
etc...
```
#### Jokes
Il peut exister 2 fichiers de configuration pour le mode joke pour chaque mode :

**mode Factory :**
```
<personne> <action> <nombre> <object>
etc...
```
**mode Custom :**
```
<joke1>
etc...
```

#### Launch application
//TODO
Afin de lancer l'application il faut exécuter:
`java -jar app <victims> <configuration> <jokes> <mode>`
### Configuration
* **Clear and simple instructions for configuring your tool and running a prank campaign**. If you do a good job, an external user should be able to clone your repo, edit a couple of files and send a batch of e-mails in less than 10 minutes.
### Compilation

## Project Structure
![diagramme de classe de l'application](figures/smtp.png)

### pranker.ConfigurationService
Cette classe s'occupe de parser le fichier de configuration et de lier chaque paramètre de configuration avec sa valeur dans un ensemble.
### pranker.Group
Cette classe gère les informations d'un groupe que l'on veut piéger. Elle contient donc une liste de victimes, dont l'une va envoyer le mail forgé tandis que les autres seront les destinataires de ce mail. 
### pranker.GroupManager
Cette classe s'occupe de la gestion des différents groupes de notre campagne de mails forgés. Elle va donc séparer une liste de victimes en plusieurs groupes, chacun de ces groupes se voyant attribué une joke différente par la suite.
### pranker.MailSender
MailSender est en quelque sorte la classe principale de notre programme. Elle s'occupe de lire les arguments passés en ligne de commande par l'utilisateur, puis en fonction de ceux-ci elle crée un GroupManager et une PrankFactory avant d'utiliser MailService pour envoyer les différents emails à leur groupe respectif.
### pranker.MailService
Cette classe va faire le gros du travail de cette campagne de mails forgés. Elle ouvre une connection avec le serveur SMTP, puis pour chaque groupe de notre GroupManager elle va envoyer un mail avec une joke différente tout en lisant les réponses du serveur afin de gérer les éventuelles erreurs avec le protocole SMTP
### pranker.PrankFactory
Cette classe s'occupe de générer les blagues que l'on va emvoyer par mail. Elle a deux modes de fonctionnement selon le paramètre `<mode>` que l'on passe en ligne de commande. Si on choisit le mode Custom, elle choisit aléatoirement une blague dans un fichier de configuration (par exemple jokes_custom.txt). Si on choisit le mode Factory, elle va créer de toutes pièces une blague en concaténant un nom, un verbe, un nombre et un objet en se basant sur un fichier contenant chacun de ces éléments sur chacune de ses lignes (par exemple jokes_factory.txt).
### pranker.Victim
Cette classe gère les informations concernant une victime, en l'occurence son email et son nom
## References

* [Ici se trouve notre fork du serveur MockMock](https://github.com/HEIGVD-Course-API/MockMock), dans lequel nous avons résolu un problème de dépendance (voir cette [pull request](https://github.com/tweakers/MockMock/pull/8) pour avoir plus d'informations).
* Le service de [mailtrap](<https://mailtrap.io/>) en ligne pour tester SMTP
* La [SMTP RFC](<https://tools.ietf.org/html/rfc5321#appendix-D>), et en particulier ce [scénario d'exemple](<https://tools.ietf.org/html/rfc5321#appendix-D>)
* Tester SMTP avec TLS: `openssl s_client -connect smtp.mailtrap.io:2525 -starttls smtp -crlf`
