package cn.movinginfo.tztf.releasechannel;

import java.util.Set;

import cn.movinginfo.tztf.sev.domain.MiddleObject;
import cn.movinginfo.tztf.sev.domain.ReleaseChannelInstance;

/**
 * 发布渠道适配器
 * @author zq
 *
 */
public interface ChannelReactor {
    
    void doRelease(ReleaseChannelInstance instance);
    public void doRelease(Set<ReleaseChannelInstance> instances, MiddleObject midObject);
    void setEntity(MiddleObject midObj);
    
    public void doRemove(Set<ReleaseChannelInstance> instances, MiddleObject midObject);
    public void doDefaultRemove(Set<ReleaseChannelInstance> instances, MiddleObject midObject);
    
}
