
"FlavorCraft: Culinary Adventures"
-------------------------------------------
Introducing "FlavorCraft: Culinary Adventures"! Immerse yourself in a delightful culinary experience with this Minecraft mod. Discover a world of delectable flavors as you explore new foods, craft intricate recipes, and cultivate bountiful crops. Unleash your inner chef and create mouthwatering dishes using a variety of cooking tools and specialized crafting tables. From savory meals to sweet treats, this mod expands the culinary landscape of Minecraft, offering a delightful array of food-related items and ingredients. Embark on a gastronomic adventure and let your creativity flourish in the world of FlavorCraft!

Setup Process:
==============================

Step 1: Open your command-line and browse to the folder where you extracted the zip file.

Step 2: You're left with a choice.
If you prefer to use Eclipse:
1. Run the following command: `gradlew genEclipseRuns` (`./gradlew genEclipseRuns` if you are on Mac/Linux)
2. Open Eclipse, Import > Existing Gradle Project > Select Folder 
   or run `gradlew eclipse` to generate the project.

If you prefer to use IntelliJ:
1. Open IDEA, and import project.
2. Select your build.gradle file and have it import.
3. Run the following command: `gradlew genIntellijRuns` (`./gradlew genIntellijRuns` if you are on Mac/Linux)
4. Refresh the Gradle Project in IDEA if required.

If at any point you are missing libraries in your IDE, or you've run into problems you can 
run `gradlew --refresh-dependencies` to refresh the local cache. `gradlew clean` to reset everything 
{this does not affect your code} and then start the process again.

Mapping Names:
=============================
By default, the MDK is configured to use the official mapping names from Mojang for methods and fields 
in the Minecraft codebase. These names are covered by a specific license. All modders should be aware of this
license, if you do not agree with it you can change your mapping names to other crowdsourced names in your 
build.gradle. For the latest license text, refer to the mapping file itself, or the reference copy here:
https://github.com/MinecraftForge/MCPConfig/blob/master/Mojang.md

Additional Resources: 
=========================
Community Documentation: https://mcforge.readthedocs.io/en/latest/gettingstarted/  
LexManos' Install Video: https://www.youtube.com/watch?v=8VEdtQLuLO0  
Forge Forum: https://forums.minecraftforge.net/  
Forge Discord: https://discord.gg/UvedJ9m  
