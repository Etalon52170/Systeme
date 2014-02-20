#!/bin/bash
VERSION=V4
cd ~/svn-colnet/EnsCC/Caisses
new_version=0
for b in *
do
    cd ~/svn-colnet/EnsCC/Caisses
    if [ ! -d $b ] 
    then
	continue
    fi
    cd $b
    if ! cd $VERSION >& /dev/null
    then
	mkdir $VERSION
	cp ../*.java $VERSION
	new_version=1
    fi
done
if [ $new_version == 1 ]
then
    echo New version $VERSION is empty.
    exit 0
else
    echo Running all tests for version ${VERSION}.
fi
cd ~/svn-colnet/EnsCC/Caisses
echo -1-------------------------------------------------------------------
for b in *
do
    cd ~/svn-colnet/EnsCC/Caisses
    if [ ! -d $b ] 
    then
	continue
    fi
    cd $b
    cd $VERSION
    rm -f EssaiLoiDePoisson.java EssaiPressRandomNG.java *.class
    echo -n $(grep assert *.java | wc -l) "asserts "
    echo -n $(ls *.java | wc -l) "fichiers "
    echo -n $(cat *.java | wc -l) "lignes de java et "
    echo " (" $b ")"
done
cd ~/svn-colnet/EnsCC/Caisses
echo -2- recompilation ----------------------------------------------------
for b in *
do
    cd ~/svn-colnet/EnsCC/Caisses
    if [ ! -d $b ] 
    then
	continue
    fi
    cd $b
    cd $VERSION
    if javac -nowarn *.java >& /dev/null
    then
	echo $b '(good)'
    else
	echo $b '(????)'
    fi
done
cd ~/svn-colnet/EnsCC/Caisses
echo -3- Main/Constantes ---------------------------------------------------
for b in *
do
    cd ~/svn-colnet/EnsCC/Caisses
    if [ ! -d $b ] 
    then
	continue
    fi
    cd $b
    cd $VERSION
    if [ $(ls *.java | wc -l) == 2 ]
    then
	echo $b '(????)'
	continue
    fi
    good=1
    if ! diff -q Main.java ../../Main.java > /dev/null
    then
	echo $b Main.java modifié.
	good=0
    fi
    if ! diff -q Constantes.java ../../Constantes.java > /dev/null
    then
	echo $b Constantes.java modifié.
	good=0
    fi
    if [ $good == 1 ]
    then
	echo $b '(good)'
    fi
done
cd ~/svn-colnet/EnsCC/Caisses
echo -4- input.1 -------------------------------------------------------------
for b in *
do
    cd ~/svn-colnet/EnsCC/Caisses
    if [ ! -d $b ] 
    then
	continue
    fi
    cd $b
    cd $VERSION
    java -ea Main < ../../input.1 >& output.1
    if ! diff -q output.1 ../../output.1 > /dev/null
    then
	echo diff output.1 $b/${VERSION}/output.1 \| more
    else
	echo $b '(good)'
    fi
done
cd ~/svn-colnet/EnsCC/Caisses
echo -5- input.2 -------------------------------------------------------------
for b in *
do
    cd ~/svn-colnet/EnsCC/Caisses
    if [ ! -d $b ] 
    then
	continue
    fi
    cd $b
    cd $VERSION
    java -ea Main < ../../input.2 >& output.2
    if ! diff -q output.2 ../../output.2 > /dev/null
    then
	echo diff output.2 $b/${VERSION}/output.2 \| more
    else
	echo $b '(good)'
    fi
done
cd ~/svn-colnet/EnsCC/Caisses
echo -6- input.3 -------------------------------------------------------------
for b in *
do
    cd ~/svn-colnet/EnsCC/Caisses
    if [ ! -d $b ] 
    then
	continue
    fi
    cd $b
    cd $VERSION
    java -ea Main < ../../input.3 >& output.3
    if ! diff -q output.3 ../../output.3 > /dev/null
    then
	echo diff output.3 $b/${VERSION}/output.3 \| more
    else
	echo $b '(good)'
    fi
done
