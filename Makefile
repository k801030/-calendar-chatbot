all: build 

build:
	mvn clean package -s pom.xml
deploy:
	mvn appengine:deploy -s pom.xml
