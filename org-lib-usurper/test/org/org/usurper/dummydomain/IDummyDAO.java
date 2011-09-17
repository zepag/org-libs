package org.org.usurper.dummydomain;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IDummyDAO {
    public DummyVO getVO(String param);

    public List<DummyVO> getVOList(String param);

    public Set<DummyVO> getVOSet(String param);

    public Map<DummyVO, OtherDummyVO> getVOMap(String param);
}
