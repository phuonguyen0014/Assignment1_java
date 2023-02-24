public class WordFrequency implements Comparable<WordFrequency> {
    
    Integer count;
    String word;    

    /**
     * Constructor for WordFrequency
     * @param word
     * @param count
     */
    public WordFrequency(String word, Integer count) {
        this.word = word;
        this.count = count;
    }

    /**
     * setWord method
     * @param word
     */
    public void setWord(String word)
    {
        this.word = word;
    }

    /**
     * getWord method
     * @return
     */
    public String getWord()
    {
        return word;
    }

    /**
     * setCount method
     * @param count
     */
    public void setCount(Integer count)
    {
        this.count = count;
    }

    /**
     * getCount method
     * @return
     */
    public Integer getCount()
    {
        return count;
    }

    
    @Override
    public String toString() {
        return "WordFrequency [word = " + word + ", count = " + count + "]";
    }

    @Override
    public int compareTo(WordFrequency otherWord) {
        int compareValue = 2;
        if(this.count > otherWord.count) 
        { 
            compareValue = 1; 
        }
        if(this.count < otherWord.count)
        { 
            compareValue = -1; 
        } 
        if(this.count == otherWord.count)
        {
            compareValue = 0;
        } 
        return compareValue;
    }
}
