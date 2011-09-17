package org.org.usurper.dummydomain;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IOtherDummyDAO {
    public ImmutableDummyVO getVO(String param);

    public List<ImmutableDummyVO> getVOList(String param);

    public Set<ImmutableDummyVO> getVOSet(String param);

    public Map<ImmutableDummyVO, ImmutableDummyVOWithEnum> getVOMap(String param);
}
