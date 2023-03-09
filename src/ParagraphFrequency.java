public class ParagraphFrequency implements Comparable<ParagraphFrequency> {
    Integer countPa;
    String paragraph;    

    public ParagraphFrequency(String paragraph, Integer countPa) {
        this.paragraph = paragraph;
        this.countPa = countPa;
    }

    public void setParagraph(String paragraph)
    {
        this.paragraph = paragraph;
    }

    public String getParagraph()
    {
        return paragraph;
    }

    public void setCountP(Integer countPa)
    {
        this.countPa = countPa;
    }

    /**
     * getCount method
     * @return
     */
    public Integer getCountP()
    {
        return countPa;
    }

    
    @Override
    public String toString() {
        return "ParagraphFrequency [paragraph = " + paragraph + ", count = " + countPa + "]";
    }

    @Override
    public int compareTo(ParagraphFrequency otherParag) {
        int compareValue = 2;
        if(this.countPa > otherParag.countPa) 
        { 
            compareValue = 1; 
        }
        if(this.countPa < otherParag.countPa)
        { 
            compareValue = -1; 
        } 
        if(this.countPa == otherParag.countPa)
        {
            compareValue = 0;
        } 
        return compareValue;
    }
}
