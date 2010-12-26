#! /bin/sh 

pid=$(pkill rmiregistry)

sleep 1

rmiregistry &

rmic -keep implementation.BanqueImpl
rmic -keep implementation.AgenceImpl
rmic -keep implementation.ClientImpl
rmic -keep implementation.CompteImpl


gnome-terminal -e "java -Djava.security.policy=java.policy serveur.BanqueServeur"

sleep 1

gnome-terminal -e "java -Djava.security.policy=java.policy serveur.CompteServeur"

sleep 1

gnome-terminal -e "java -Djava.security.policy=java.policy serveur.ClientServeur"

sleep 1

gnome-terminal -e "java -Djava.security.policy=java.policy serveur.AgenceServeur"





