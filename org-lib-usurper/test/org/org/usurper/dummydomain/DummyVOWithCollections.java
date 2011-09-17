package org.org.usurper.dummydomain;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author pagregoire
 */
public class DummyVOWithCollections {
    private List<Integer> integerList;

    private List<DummyVOWithEnum.EnumProperty> enumList;

    private Set<Integer> integerSet;

    private Set<DummyVOWithEnum.EnumProperty> enumSet;

    private Map<String, Integer> integerMap;

    private Map<DummyVOWithEnum.EnumProperty, DummyVOWithEnum.EnumProperty2> enumMap;

    /**
     * @return Returns the integerList.
     */
    public List<Integer> getIntegerList() {
        return this.integerList;
    }

    /**
     * @param integerList
     *            The integerList to set.
     */
    public void setIntegerList(List<Integer> integerList) {
        this.integerList = integerList;
    }

    /**
     * @return Returns the integerMap.
     */
    public Map<String, Integer> getIntegerMap() {
        return this.integerMap;
    }

    /**
     * @param integerMap
     *            The integerMap to set.
     */
    public void setIntegerMap(Map<String, Integer> integerMap) {
        this.integerMap = integerMap;
    }

    /**
     * @return Returns the integerSet.
     */
    public Set<Integer> getIntegerSet() {
        return this.integerSet;
    }

    /**
     * @param integerSet
     *            The integerSet to set.
     */
    public void setIntegerSet(Set<Integer> integerSet) {
        this.integerSet = integerSet;
    }

    public List<DummyVOWithEnum.EnumProperty> getEnumList() {
        return this.enumList;
    }

    public void setEnumList(List<DummyVOWithEnum.EnumProperty> enumList) {
        this.enumList = enumList;
    }

    public Map<DummyVOWithEnum.EnumProperty, DummyVOWithEnum.EnumProperty2> getEnumMap() {
        return this.enumMap;
    }

    public void setEnumMap(Map<DummyVOWithEnum.EnumProperty, DummyVOWithEnum.EnumProperty2> enumMap) {
        this.enumMap = enumMap;
    }

    public Set<DummyVOWithEnum.EnumProperty> getEnumSet() {
        return this.enumSet;
    }

    public void setEnumSet(Set<DummyVOWithEnum.EnumProperty> enumSet) {
        this.enumSet = enumSet;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("enumSet", this.enumSet).append("enumMap", this.enumMap).append("enumList", this.enumList).append("integerSet", this.integerSet).append("integerList", this.integerList).append("integerMap", this.integerMap).toString();
    }

}