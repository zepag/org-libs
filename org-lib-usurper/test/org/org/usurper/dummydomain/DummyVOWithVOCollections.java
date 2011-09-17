package org.org.usurper.dummydomain;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author pagregoire
 */
public class DummyVOWithVOCollections {
    private List<SonOfDummyWithChildVO> sonOfDummyVOList;

    private Set<SonOfDummyWithChildVO> sonOfDummyVOSet;

    private Map<String, SonOfDummyWithChildVO> sonOfDummyVOMap;

    /**
     * @return Returns the sonOfDummyVOList.
     */
    public List<SonOfDummyWithChildVO> getSonOfDummyVOList() {
        return this.sonOfDummyVOList;
    }

    /**
     * @param sonOfDummyVOList
     *            The sonOfDummyVOList to set.
     */
    public void setSonOfDummyVOList(List<SonOfDummyWithChildVO> sonOfDummyVOList) {
        this.sonOfDummyVOList = sonOfDummyVOList;
    }

    /**
     * @return Returns the sonOfDummyVOMap.
     */
    public Map<String, SonOfDummyWithChildVO> getSonOfDummyVOMap() {
        return this.sonOfDummyVOMap;
    }

    /**
     * @param sonOfDummyVOMap
     *            The sonOfDummyVOMap to set.
     */
    public void setSonOfDummyVOMap(Map<String, SonOfDummyWithChildVO> sonOfDummyVOMap) {
        this.sonOfDummyVOMap = sonOfDummyVOMap;
    }

    /**
     * @return Returns the sonOfDummyVOSet.
     */
    public Set<SonOfDummyWithChildVO> getSonOfDummyVOSet() {
        return this.sonOfDummyVOSet;
    }

    /**
     * @param sonOfDummyVOSet
     *            The sonOfDummyVOSet to set.
     */
    public void setSonOfDummyVOSet(Set<SonOfDummyWithChildVO> sonOfDummyVOSet) {
        this.sonOfDummyVOSet = sonOfDummyVOSet;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("sonOfDummyVOMap", this.sonOfDummyVOMap).append("sonOfDummyVOList", this.sonOfDummyVOList).append("sonOfDummyVOSet", this.sonOfDummyVOSet).toString();
    }

}