# Wer wird INFORMATIKär

(Wer wird Informatik-Millionär)

Kleines Spiel im Stil von „Wer wird Millionär“ für den
Informatik-Unterricht.

Projekt des legendären „Team Nürnbergs“

```
mvn install:install-file \
   -Dfile=/usr/local/share/java/jars/JGameGrid-3.00.jar \
   -DgroupId=ch.aplu \
   -DartifactId=jgamegrid \
   -Dversion=3.00 \
   -Dpackaging=jar \
   -DgeneratePom=true
```

# Testen

```
mvn test
```

# Dokumentation erstellen

```
mvn javadoc:javadoc
```
