package org.org.usurper.dummydomain;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author pagregoire
 */
public class DummyVOWithVOArrays {
    private SonOfDummyWithChildVO[] sonOfDummyVOList;

    /**
     * @return Returns the sonOfDummyVOList.
     */
    public SonOfDummyWithChildVO[] getSonOfDummyVOList() {
        return this.sonOfDummyVOList;
    }

    /**
     * @param sonOfDummyVOList
     *            The sonOfDummyVOList to set.
     */
    public void setSonOfDummyVOList(SonOfDummyWithChildVO[] sonOfDummyVOList) {
        this.sonOfDummyVOList = sonOfDummyVOList;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("sonOfDummyVOList", this.sonOfDummyVOList).toString();
    }

}