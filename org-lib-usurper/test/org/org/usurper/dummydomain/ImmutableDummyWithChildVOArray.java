package org.org.usurper.dummydomain;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author Pierre-Antoine Grégoire
 */
public class ImmutableDummyWithChildVOArray {

    private final DummyWithChildVO[] dummyWithChildVOs;

    public ImmutableDummyWithChildVOArray(DummyWithChildVO[] dummyWithChildVOs) {
        this.dummyWithChildVOs = dummyWithChildVOs;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("dummyWithChildVO", this.dummyWithChildVOs).toString();
    }

    public DummyWithChildVO[] getDummyWithChildVOs() {
        return dummyWithChildVOs;
    }
}
