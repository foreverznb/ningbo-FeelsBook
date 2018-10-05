package com.example.ningbo_feelsbook;


import java.util.Date;

/**
 * The type Emotion.
 */
public class Emotion {

    private Date date;
    private String message;
    private String emotion;
    private static final Integer MAX_CHARS = 140;

    //Empty argument constructor with default values


    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Set date.
     *
     * @param date the date
     */
    public void setDate(Date date){
        this.date = date;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        if (message.length() <= this.MAX_CHARS ) {
            this.message = message;
        } else {

        }
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public Date getDate() { return this.date; }

    /**
     * Get emotion string.
     *
     * @return the string
     */
    public String getEmotion(){
        return this.emotion;
    }

    /**
     * Set emotion.
     *
     * @param emotion the emotion
     */
    public void setEmotion(String emotion){
        this.emotion = emotion;
    }

    //No method body implemented! We leave that up to the subclasses (they MUST implement it)
    //public Boolean isImportant();

    @Override
    public String toString() {
        return    this.emotion + "     " + this.message+ "    "  + this.date.toString();
    }
}
