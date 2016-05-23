# Game Development Demo
A demo of Game Development from the presentation at the CAPTURE meeting

## Install
Below you will find instructions on how to install the game on your system. This game is built in java so all you need to work on it is the [Java Development Kit(JDK)](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) to run the game, an IDE to edit files, and Git to install it. Below you will find instructions on running this game in [Jetbrain's IntelliJ IDEA](https://www.jetbrains.com/idea/).

### Download the Source
First you will need to open up the command prompt on your machine and change directories to an empty one where you would like to download the game

#### Latest Update
For the most recent version of the game run the following in a command prompt
```
git clone https://github.com/jakex10sen/game-dev-demo.git
```
This version is recommended if you wish to contribute to the game and add features

#### Stable Update
For a more stable version of the game run the following in a command prompt
```
git clone --branch v1.0 https://github.com/jakex10sen/game-dev-demo.git
```
This version is recommended if you want a stable bland playable version of the game

#### Game Skeleton
If you are looking for a good place to start to make your own games you'll want to enter the following in a command prompt

```
git clone --branch skeleton https://github.com/jakex10sen/game-dev-demo.git
```

### Import Into IntelliJ
1. After you have downloaded the source files open up IntelliJ and select Import Project  
![After you have downloaded the source files open up IntelliJ and select Import](http://i.imgur.com/60anw8E.png)  
2. Then navigate to where you downloaded the source files and and select the build.gradle file  
![Then navigate to where you downloaded the source files and and select the build.gradle file](http://i.imgur.com/QRB3Ey1.png)  
3. Then make sure all three option on the top are unchecked and hit OK  
![Then make sure all three option on the top are unchecked and hit OK](http://i.imgur.com/UWATKy8.png)  
4. If all three items are checked hit OK on the next dialog box and you're ready to start coding  
![If all three items are checked hit OK on the next dialog box and you're ready to start coding](http://i.imgur.com/iiOUBI6.png)  

## Running the game
If you are using IntelliJ you can run the game by following these steps:
1. Open the DesktopLauncher file in the folder labeled desktop/src/com/capture/game/desktop  
![Open the DesktopLauncher file in the folder labeled desktop/src/com/capture/game/desktop](http://i.imgur.com/N37g1fp.png)  
2. Right click on the file and select Create 'DesktopLauncher.main()'...  
![Right click on the file and select Create 'DesktopLauncher.main()'...](http://i.imgur.com/MDvyCLF.png)  
3. Change the Working Directory to the assets folder in the folder labeled core/assets and press OK  
![Change the Working Directory to the assets folder in the folder labeled core/assets and press OK](http://i.imgur.com/VXGHjVM.png)  
![Change the Working Directory to the assets folder in the folder labeled core/assets and press OK](http://i.imgur.com/yD9YEOL.png)  
4. Then press OK an the play button should light up green which means you're good to go.  
