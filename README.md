# Bitcoin Updates Application

This is an application allows users to keep tabs on how your assets are doing in real time.

## Installation
Clone this repository and import into **Android Studio**
```bash
git clone git@github.com:Ntuks/BTCUpdates.git
```

## Configuration
### Environment:
Create a `local.properties` file in root `dir` of the project with the following info:
```
sdk.dir=
storePassword=...
keyAlias=...
keyPassword=...
storeFile=../keystore/app-keystore.jks
fixerApiKey=...
baseApiUrl="https://api.apilayer.com/fixer/"
```

### Keystore:
And create a keystore file and place it under the `keystore/` directory and name it`app-keystore.jks`


## Build variants
Use the Android Studio *Build Variants* button to choose between **develop**, **staging** and **production** flavors combined with debug and release build types


## Generating signed APK
From Android Studio:
1. ***Build*** menu
2. ***Generate Signed APK...***
3. Fill in the keystore information *(you only need to do this once manually and then let Android Studio remember it)*
