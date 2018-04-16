#!/bin/bash -e
if [ "$TRAVIS_BRANCH" = 'modig-frontend_4' ] && [ "$TRAVIS_PULL_REQUEST" == 'false' ]; then
    openssl aes-256-cbc -K $encrypted_75b18248efca_key -iv $encrypted_75b18248efca_iv \
  -in travis/codesigning.asc.enc -out travis/codesigning.asc -d
    gpg --fast-import travis/codesigning.asc
    mvn --settings travis/settings.xml deploy -Prelease -DskipTests=true
fi
