#! /bin/sh 

gnome-terminal -e "ls" &

rmiregistry &

rmic -keep implementation.BanqueImpl
rmic -keep implementation.AgenceImpl

java -Djava.security.policy=java.policy serveur.AgenceServeur






