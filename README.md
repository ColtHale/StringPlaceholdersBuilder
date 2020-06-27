# String Placeholder Builder
The *old methods* of parsing strings and replacing placeholders via `string.replace()` was **inefficient**, and **ugly**. I've created a ***new*** method to perform such actions, that looks **a lot cleaner**, and gives a great **professional feel**.
#### This API is especially useful when dealing with Language files

## How it works

The **basic** functionality of the API, is pretty *easy* to understand. When a placeholder is added, the string specificed to be replaced ***(First @param)***, as well as the `Object` to replace it with ***(Second @param)***, are added to a `placeholders map`. At this point, a method is run to `apply()` **all placeholders** cached within the **Map**. **It is *usually best* to have a method such as `getMessage()` where the results of `apply()` are returned.**

## Usage

   ### Basic Example Workflow
   * Instanciate StringPlaceholders Class ***(Optional - not recomended)***
     * **IF** string to parse contains ***only 1*** placeholder to replace
       * Use `single(String, Object)` to add **1** placeholder to be replaced
     * **IF** string to parse contains ***more than 1*** placeholder to replace
       * Use `builder(String, Object)` to get instance of *builder class*, & **continue** adding more placeholders
       * `addPlaceholder(String, Object)` can be called on the **Builder instance** to add more placeholders to be replaced
       * Call `build()` method on the **Builder instance** to finish adding placeholders
   * Create method to get/send strings
   * Make sure to call `apply()` method from `StringPlacerholders class` to apply **all** added placeholders
   * Use the *returned string from `apply()`*, this string will be the **parsed string with all placeholders replaced**
   
   ### Basic Code Usage Example
   #### `single(String, Object)` Method
       ```
       public void sendMSG(Player player)
       {
          player.sendMessage(getMsg(StringPlaceholders.single("playerName", player.getDisplayName())));
       }
       
       public String getMsg(StringPlaceholders stringPlaceholders)
       {
          return StringPlacerholders.apply("The player's name is %playerName%");
       }
       ```
   #### `builder()` Method
       ```
       public void sendMSG(Player player)
       {
          player.sendMessage(getMsg(StringPlaceholders.builder("playerName", player.getDisplayName()).addPlaceholder("playerHealth", player.getHealth()).addPlaceholder("playerXP", player.getExp()).build()));
       }
       
       public String getMsg(StringPlaceholders stringPlaceholders)
       {
          return StringPlacerholders.apply("The player's name is %playerName%, Health: %playerHealth%, Player EXP: %playerXP%");
       }
       ```
#### If you make any changes you feel will improve the file feel free to create a pull request
