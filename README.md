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


## Sample Request

```bash
curl https://calendar-286506.an.r.appspot.com/events/2Eu9I8bGQGks5rXJ4jZZ | jq
curl https://calendar-286506.an.r.appspot.com/events?userId=vison | jq
```