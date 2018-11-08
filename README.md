#AMUtil

## Installing

#### Add jitpack repository to top (project) build.gradle file:

    maven { url 'https://jitpack.io' }

    Example:
    allprojects {
        repositories {
            jcenter()
            maven { url 'https://jitpack.io' }
        }
    }

#### Add dependency to app (module) build.gradle file:

    compile 'com.github.adaptmobile-organization:amutil:version'

  Example:
    
    dependencies {
        compile 'com.github.adaptmobile-organization:amutil:1.2.1'
    }

## Development

  After you have committed your changes push a new/the next tag for a new version of amutil on jitpack
  ```
    git tag -a 0.0.0 
    git push origin 0.0.0
  ```

## Jitpack

  https://jitpack.io/#adaptmobile-organization/amutil
