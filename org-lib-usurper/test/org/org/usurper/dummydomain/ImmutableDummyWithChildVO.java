package org.org.usurper.dummydomain;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author David Dossot (david@dossot.net)
 */
public class ImmutableDummyWithChildVO {

    private final DummyWithChildVO dummyWithChildVO;

    public ImmutableDummyWithChildVO(DummyWithChildVO dummyWithChildVO,/* DummyWithChildVO[] dummyWithChildVOs, */List<DummyWithChildVO> dummyWithChildVOList) {
        this.dummyWithChildVO = dummyWithChildVO;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("dummyWithChildVO", this.dummyWithChildVO).toString();
    }

    public DummyWithChildVO getDummyWithChildVO() {
        return dummyWithChildVO;
    }
}
