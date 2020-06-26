import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StringPlaceholders
{
    private final Map<String, String> placeholders;

    public StringPlaceholders()
    {
        this.placeholders = new HashMap<>();
    }

    /**
     * Adds placeholder, & it's replacement to map
     *
     * @param placeholder What should be replaced
     * @param value What to replace the placeholder with
     */
    public void addPlaceholder(String placeholder, Object value)
    {
        this.placeholders.put(placeholder, objectToString(value));
    }

    /**
     * Applies all placeholders in map to string
     *
     * @param string string to apply placeholder replacement to
     * @return String with applied placeholders
     */
    public String apply(String string)
    {
        for (String key : this.placeholders.keySet())
        {
            string = string.replaceAll(Pattern.quote('%' + key + '%'), Matcher.quoteReplacement(this.placeholders.get(key)));
        }
        return string;
    }

    /**
     * Gets all placeholders from map
     *
     * @return placeholders map
     */
    public Map<String, String> getPlaceholders()
    {
        return Collections.unmodifiableMap(this.placeholders);
    }

    /**
     * Gets new instance of Builder class
     *
     * @return builder class instance
     */
    public static Builder builder()
    {
        return new Builder();
    }

    /**
     * Gets new instance of Builder class while passing params
     *
     * @param placeholder What to replace
     * @param value What the placeholder should be replaced with
     * @return Builder instance
     */
    public static Builder builder(String placeholder, Object value)
    {
        return new Builder(placeholder, objectToString(value));
    }

    /**
     * Returns instance of StringPlaceholders class using Builder
     *
     * @return StringPlaceholders instance
     */
    public static StringPlaceholders empty()
    {
        return builder().build();
    }

    /**
     * Replaces a single placeholder in a string
     *
     * @param placeholder What to replace
     * @param value What the placeholder should be replaced with
     * @return StringPlaceholders instance
     */
    public static StringPlaceholders single(String placeholder, Object value)
    {
        return builder(placeholder, value).build();
    }

    /**
     * Converts Object to string
     *
     * @param object Object to transform
     * @return object to string, if null returns string "null"
     */
    private static String objectToString(Object object)
    {
        return object != null ? object.toString() : "null";
    }

    public static class Builder
    {

        /**
         * Map of placeholders that will to be replaced
         */
        private StringPlaceholders stringPlaceholders;

        private Builder()
        {
            this.stringPlaceholders = new StringPlaceholders();
        }

        /**
         * Adds placeholder to placeholders map in StringPlaceholders class
         *
         * @param placeholder What to replace
         * @param value What to replace the placeholder with
         */
        private Builder(String placeholder, Object value)
        {
            this();
            this.stringPlaceholders.addPlaceholder(placeholder, objectToString(value));
        }

        /**
         * Adds placeholder to placeholders map in StringPlaceholders class
         *
         * @param placeholder What to replace
         * @param value What to replace the placeholder with
         * @return Builder instance to continue adding placeholders to replace
         */
        public Builder addPlaceholder(String placeholder, Object value)
        {
            this.stringPlaceholders.addPlaceholder(placeholder, objectToString(value));
            return this;
        }

        /**
         * Invokes StringPlaceholders apply method
         *
         * @param string string to apply placeholder replacement to
         * @return string with replaced placeholders
         */
        public String apply(String string)
        {
            return this.stringPlaceholders.apply(string);
        }

        /**
         * Finishes placeholder building
         *
         * @return StringPlaceholders instance
         */
        public StringPlaceholders build()
        {
            return this.stringPlaceholders;
        }

    }

}
