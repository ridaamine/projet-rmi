# Commande #
```
cd ~/Documents/workspace/Projet-RMI/bin/
```

## Lancement "rmiregistry" ##
```
rmiregistry &
```

## Création des stubs ##
```
rmic -keep implementation.BanqueImpl
rmic -keep implementation.AgenceImpl
rmic -keep implementation.ClientImpl
```

## Lancement des serveurs ##
```
java -Djava.security.policy=/Users/votre_nom_utilisateur/Documents/workspace/Projet-RMI/java.policy serveur.BanqueServeur
java -Djava.security.policy=/Users/votre_nom_utilisateur/Documents/workspace/Projet-RMI/java.policy client.BanqueClient
```