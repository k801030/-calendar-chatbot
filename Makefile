all: build 

export KEY_PATH=/Users/zli02/.gcloud/calendar-286506-278ab127e089.json
export GOOGLE_APPLICATION_CREDENTIALS=${KEY_PATH}

build:
	mvn clean package -s pom.xml

# run locally
run:
	mvn clean package spring-boot:run -s pom.xml

# deploy to GCP
deploy:
	mvn clean package appengine:deploy -s pom.xml

