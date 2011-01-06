#! /bin/sh 

pid=$(pkill rmiregistry)

sleep 1

rmiregistry &

rmic -keep implementation.BanqueImpl
rmic -keep implementation.AgenceImpl
rmic -keep implementation.ClientImpl
rmic -keep implementation.CompteImpl


gnome-terminal -e "java -Djava.security.policy=/Users/benjamintardieu/Documents/workspace/Projet-RMI/java.policy serveur.BanqueServeur"

sleep 1

gnome-terminal -e "java -Djava.security.policy=/Users/benjamintardieu/Documents/workspace/Projet-RMI/java.policy serveur.AgenceServeur"

sleep 1

gnome-terminal -e "java -Djava.security.policy=/Users/benjamintardieu/Documents/workspace/Projet-RMI/java.policy serveur.ClientServeur"

sleep 1

gnome-terminal -e "java -Djava.security.policy=/Users/benjamintardieu/Documents/workspace/Projet-RMI/java.policy serveur.CompteServeur"