[![Build Status](https://travis-ci.org/navikt/modig-frontend.svg?branch=modig-frontend_4)](https://travis-ci.org/navikt/modig-frontend)
[![Published on Maven](https://img.shields.io/maven-metadata/v/http/central.maven.org/maven2/no/nav/modig/modig-frontend/maven-metadata.xml.svg)](http://central.maven.org/maven2/no/nav/modig/modig-frontend/)

# modig-frontend

Dette er NAV sitt tidligere frontend-kjernebibliotek, som siden
har blitt erstattet med [nav-frontend-moduler](https://github.com/navikt/nav-frontend-moduler).

Koden er ikke i aktiv utvikling.

#### Bygge koden

Gjør en `git clone` av repoet, og kjør `mvn clean install`.

#### Release ny versjon

For å release en ny versjon:

```bash
# pass på at vi har siste versjon av koden
git checkout modig-frontend_4 && git pull

# Denne kommandoen oppdaterer git-repoet med versjoner osv.
mvn release:prepare

# Denne rydder opp i prosjektet etter release-kommandoene.
mvn release:clean
```

Kommandoen `mvn release:prepare` vil be om et versjonsnummer å release,
og om et versjonsnummer å øke til. Denne kommandoen gjør også en `git push`
på dine vegne, som oppdaterer kodebasen upstream. Så vil Travis CI trigge
et bygg, og laste opp artifaktet.

Først finner man det [Sonatype OSS releases](https://oss.sonatype.org/content/repositories/releases/no/nav/modig/modig-frontend/),
før det senere (noen minutter etterpå) blir synkronisert til [Maven Central](http://central.maven.org/maven2/no/nav/modig/modig-frontend/).


### Kontakt

Opprette en Github-issue på dette repoet.
