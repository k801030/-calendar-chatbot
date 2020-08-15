# Run an app

```bash
mvn clean package spring-boot:run -s pom.xml
```

# Deploy to GCP

```bash
mvn clean package appengine:deploy -s pom.xml
```


## GCP configuration

```bash
gcloud app create
gcloud config set project \
    calendar-286506
```