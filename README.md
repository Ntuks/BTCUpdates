# Bitcoin Updates Application

This is an application allows users to keep tabs on how your assets are doing in real time.

## Installation
Clone this repository and import into **Android Studio**
```bash
git clone git@github.com:Ntuks/BTCUpdates.git
```

## Configuration
### Keystores:
Create a `local.properties` file with the following info:
```
storePassword=...
keyAlias=...
keyPassword=...
storeFile=../keystore/app-keystore.jks
fixerApiKey=...
baseApiUrl="https://api.apilayer.com/fixer/"
```
And create a keystore file and place it under the `keystore/` directory:
- `playstore.keystore`
- `stage.keystore`


## Build variants
Use the Android Studio *Build Variants* button to choose between **develop**, **staging** and **production** flavors combined with debug and release build types


## Generating signed APK
From Android Studio:
1. ***Build*** menu
2. ***Generate Signed APK...***
3. Fill in the keystore information *(you only need to do this once manually and then let Android Studio remember it)*
