package org.org.usurper.dummydomain;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author Pierre-Antoine Grégoire
 */
public class ImmutableDummyWithChildVOCollections {

    private final List<DummyWithChildVO> dummyWithChildVOList;

    private final Set<DummyWithChildVO> dummyWithChildVOSet;

    private final Map<DummyWithChildVO, DummyWithChildVO> dummyWithChildVOMap;

    public ImmutableDummyWithChildVOCollections(final List<DummyWithChildVO> dummyWithChildVOList, final Set<DummyWithChildVO> dummyWithChildVOSet, final Map<DummyWithChildVO, DummyWithChildVO> dummyWithChildVOMap) {
        super();
        this.dummyWithChildVOList = dummyWithChildVOList;
        this.dummyWithChildVOSet = dummyWithChildVOSet;
        this.dummyWithChildVOMap = dummyWithChildVOMap;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("dummyWithChildVOList", this.dummyWithChildVOList).append("dummyWithChildVOSet", this.dummyWithChildVOSet).append("dummyWithChildVOMap", this.dummyWithChildVOMap).toString();
    }

    public List<DummyWithChildVO> getDummyWithChildVOList() {
        return dummyWithChildVOList;
    }

    public Map<DummyWithChildVO, DummyWithChildVO> getDummyWithChildVOMap() {
        return dummyWithChildVOMap;
    }

    public Set<DummyWithChildVO> getDummyWithChildVOSet() {
        return dummyWithChildVOSet;
    }

}
