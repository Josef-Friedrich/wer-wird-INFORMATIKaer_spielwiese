install_cli:
	mvn jar:jar
	sudo cp -f target/wwim-0.1.0.jar /usr/local/share/java/jars
	sudo cp -f src/main/java/nuernberg/team/cli/wer-wird-INFORMATIKaer.sh /usr/local/bin

.phony: install_cli
