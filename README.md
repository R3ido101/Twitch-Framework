# Twitch-Framework 
A Twitch Bot Framework made in Java using the Twitch IRC/PubSub. Made for making the creation of Bot's for Twitch quicker

### How to use/Example of usage
Coming soon!

#### Current Version: 1.1.7
### With Maven
In your `pom.xml` add:
```xml
<repositories>
  <repository>
    <id>maven.mjrlegends.com</id>
    <url>https://maven.mjrlegends.com/</url>
  </repository>
</repositories>

<dependencies>
  <dependency>
    <groupId>com.mjr.twitchframework</groupId>
    <artifactId>Twitch-Framework</artifactId>
    <version>@VERSION@</version>
  </dependency>
</dependencies>
```
### With Gradle
In your `build.gradle` add: 
```groovy
repositories {
  	maven {
	    name 'MJRLegends'
	    url = "https://maven.mjrlegends.com/"
    }
}

dependencies {
  compile "com.mjr.twitchframework:Twitch-Framework:@VERSION@"
}
```

<a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/"><img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by-nc-nd/4.0/88x31.png" /></a><br />This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a>. **For more information on the license see** https://tldrlegal.com/license/creative-commons-attribution-noncommercial-noderivs-(cc-nc-nd)#summary
